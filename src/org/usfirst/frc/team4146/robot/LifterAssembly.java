package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class LifterAssembly {
	public enum LifterPositionEnum{
		SCALE,
		SWITCH,
		DOWN
	}
	
	enum LifterModeEnum{
		AUTO_LIFT,
		MANUAL_LIFT,
	}
	
	LifterPositionEnum autoLifterPosition = LifterPositionEnum.DOWN;
	LifterModeEnum lifterMode = LifterModeEnum.AUTO_LIFT;
	
	private double triggerInput = 0.0; // Used for manual mode input.
	
	private boolean limitSwitchPressedFlag = false;
	
	private double tareEncoderTick;
	
	public LifterAssembly(){
		
	}
	
	public void update(double dt) {
		// Reset encoder at bottom
		if(RobotMap.bottomLimitSwitch.get() && !limitSwitchPressedFlag){
			System.out.println("Resetting lift encoder!");
			lifterMode = LifterModeEnum.MANUAL_LIFT;
			RobotMap.lifterBackLeft.set(ControlMode.PercentOutput, 0.0);
			tareEncoderTick = RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition();
			//RobotMap.lifterBackLeft.setSelectedSensorPosition(0, 0, 10); // No work....
			limitSwitchPressedFlag = true;
		}
		if(!RobotMap.bottomLimitSwitch.get()){
			limitSwitchPressedFlag = false;
		}
		
		if(lifterMode == LifterModeEnum.AUTO_LIFT && autoLifterPosition == LifterPositionEnum.DOWN){
			double loweringSpeed = -0.4;
			if(RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition() < tareEncoderTick + 30000){
				loweringSpeed = -0.05;
			}
			RobotMap.lifterBackLeft.set(ControlMode.PercentOutput, loweringSpeed);
		}
		
		// Checks for what mode to have
		if(RobotMap.driveController.getLeftTriggerBool() || RobotMap.driveController.getRightTriggerBool() || RobotMap.driveController.getDPadBool() || RobotMap.driveController.getButtonStart()){
			lifterMode = LifterModeEnum.MANUAL_LIFT;
		} else if(RobotMap.driveController.getButtonA() || RobotMap.driveController.getButtonB() || RobotMap.driveController.getButtonY()){
			lifterMode = LifterModeEnum.AUTO_LIFT;
		}
		
		// Checks mode and sets motors.
		if(lifterMode == LifterModeEnum.MANUAL_LIFT) {
//			RobotMap.driveController.getDPad() > 90 && RobotMap.driveController.getDPad() < 270
			if(RobotMap.driveController.getButtonStart()){
				triggerInput = -1.0;
			} else if(RobotMap.driveController.getLeftTriggerBool() || RobotMap.driveController.getRightTriggerBool()) {
				triggerInput = getTriggerSum();
				
				// If the lifter goes to bottom limit switch and is trying to go down set lifter to 0.0
				if(RobotMap.bottomLimitSwitch.get() && triggerInput < 0.0){
					triggerInput = 0.0;
				}
				
				// If the lifter goes to top soft stop and is trying to go up set lifter to 0.0.
				if(RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition() >= tareEncoderTick + (130920-2496) && triggerInput > 0.0){
					triggerInput = 0.0;
				}
			} else {
				triggerInput = 0.0;
			}
			
			RobotMap.lifterBackLeft.set(ControlMode.PercentOutput, triggerInput);
		} else if(lifterMode == LifterModeEnum.AUTO_LIFT){
			// Checking buttons, setting enums and updating motors.
			// The motor updating is in here so that it only updates of you press a button.
			if(RobotMap.driveController.getButtonY()){
				autoLifterPosition = LifterPositionEnum.SCALE;
				updateAutoHeight();
			} else if(RobotMap.driveController.getButtonB()){
				autoLifterPosition = LifterPositionEnum.SWITCH;
				updateAutoHeight();
			} else if(RobotMap.driveController.getButtonA()){
				autoLifterPosition = LifterPositionEnum.DOWN;
				updateAutoHeight();
			}
		}
		
		// Dashboard Sendings
		Dashboard.send("Raw Lifter Position", RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition());
		Dashboard.send("Tared Lifter Position", tareEncoderTick);
		Dashboard.send("Bottom Limit Switch", RobotMap.bottomLimitSwitch.get());
		Dashboard.send("Lifter Position Enum", autoLifterPosition.toString());
		Dashboard.send("Lifter Mode Enum", lifterMode.toString());
		Dashboard.send("Lifter Error", RobotMap.lifterBackLeft.getClosedLoopError(0));
		
		// This gives you how many (arbitrary units) "ticks" the motor has gone
//		System.out.println(RobotMap.frontLeft.getSensorCollection().getPulseWidthPosition());
		
//		if(RobotMap.driveController.getButtonA() && !button_flag){
//		targetPosition += 4096; //* 4096;
//		RobotMap.frontLeft.set(ControlMode.Position, targetPosition);
//		button_flag = true;
//	}
//	if(!RobotMap.driveController.getButtonA()){
//		button_flag = false;
//	}
	}
	
	/* ||
	 * ||              SCALE = tareEncoderTick + (130920-2496)
	 * ||     
	 * ||
	 * ||              SWITCH = tareEncoderTick + (64212-2496)
	 * ||  
	 * ||           O
	 * ||_________0    ZERO = tareEncoderTick (2496)
	 * ||     |
	 * ||     |___
	 */
	public void updateAutoHeight(){
		switch(autoLifterPosition){
		case SCALE:
			RobotMap.lifterBackLeft.set(ControlMode.Position, tareEncoderTick + (130920-2496)); // Set this to the Scale height
			break;
		case SWITCH:
			RobotMap.lifterBackLeft.set(ControlMode.Position, tareEncoderTick + (64212-2496)); // Set this to the Switch height
			break;
		case DOWN:
//			RobotMap.lifterBackLeft.set(ControlMode.Position, tareEncoderTick); // Set this to the Down height
//			RobotMap.lifterBackLeft.set(ControlMode.PercentOutput, loweringSpeed);
			break;
		default:
			System.out.println("Holy shit we're defaulting in LifterPosition!");
			break;
		}
	}
	
	/** 
	 * Returns the sum of the two triggers where the left is - and the left + so that the left
	 * returns negative and the right returns positive, added together.
	 */ 
	private double getTriggerSum(){
		return ((-RobotMap.driveController.getLeftTrigger()) + RobotMap.driveController.getRightTrigger());
	}

}

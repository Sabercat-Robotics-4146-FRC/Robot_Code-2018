package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class LifterAssembly {
	enum LifterPositionEnum{
		SCALE,
		SWITCH,
		DOWN
	}
	
	enum LifterModeEnum{
		AUTO_LIFT,
		MANUAL_LIFT
	}
	
	LifterPositionEnum autoLifterPosition = LifterPositionEnum.DOWN;
	LifterModeEnum lifterMode = LifterModeEnum.AUTO_LIFT;
	
	private double triggerInput = 0.0; // Used for manual mode input.
	
	private boolean limitSwitchPressed = false;
	
	public LifterAssembly(){
		
	}
	
	public void update(double dt) {
		// Reset encoder
		if(RobotMap.bottomLimitSwitch.get() && !limitSwitchPressed){
			System.out.println("Resetting lift encoder!");
			RobotMap.lifterBackLeft.setSelectedSensorPosition(0, 0, 10);
			limitSwitchPressed = true;
		}
		if(!RobotMap.bottomLimitSwitch.get()){
			limitSwitchPressed = false;
		}
		
		// Checks for what mode to have
		if(RobotMap.driveController.getLeftTriggerBool() || RobotMap.driveController.getRightTriggerBool()){
			lifterMode = LifterModeEnum.MANUAL_LIFT;
		} else {
			lifterMode = LifterModeEnum.AUTO_LIFT;
		}
		
		// Does motor settings depending upon if manual mode or not.
		if(lifterMode == LifterModeEnum.MANUAL_LIFT){
			triggerInput = getTriggerSum();
			
			// If the lifter goes to bottom limit switch and is trying to go down set lifter to 0.0
			if(RobotMap.bottomLimitSwitch.get() && triggerInput < 0.0){
				triggerInput = 0.0;
			}
			
			// If the lifter goes to top soft stop set lifter to 0.0.
			if(RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition() >= 138200.0){
				triggerInput = 0.0;
			}
			
			RobotMap.lifterBackLeft.set(ControlMode.PercentOutput, triggerInput);
		} else {
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
		Dashboard.send("Lifter Position", RobotMap.lifterBackLeft.getSensorCollection().getPulseWidthPosition());
		Dashboard.send("Bottom Limit Switch", RobotMap.bottomLimitSwitch.get());
		Dashboard.send("Lifter Position Enum", autoLifterPosition.toString());
		Dashboard.send("Lifter Mode Enum", lifterMode.toString());
		
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
	
	public void updateAutoHeight(){
		switch(autoLifterPosition){
		case SCALE:
			RobotMap.lifterBackLeft.set(ControlMode.Position, 130920); // Set this to the Scale height
			break;
		case SWITCH:
			RobotMap.lifterBackLeft.set(ControlMode.Position, 64212); // Set this to the Switch height
			break;
		case DOWN:
			RobotMap.lifterBackLeft.set(ControlMode.Position, 2496); // Set this to the Down height
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
		return ((-RobotMap.driveController.getLeftTrigger() / 3) + RobotMap.driveController.getRightTrigger());
	}

}

package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc.team4146.robot.IntakeAssembly.IntakeTiltEnum;

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
	
	enum LifterLockEnum{
		LOCKED,
		UNLOCKED
	}
	
	enum BarReleaseEnum{
		HELD,
		RELEASED
	}
	
	LifterPositionEnum autoLifterPosition = LifterPositionEnum.DOWN;
	LifterModeEnum lifterMode = LifterModeEnum.AUTO_LIFT;
	LifterLockEnum lockMode = LifterLockEnum.UNLOCKED;
	BarReleaseEnum barMode = BarReleaseEnum.HELD;
	
	private double triggerInput = 0.0; // Used for manual mode input.
	
	private boolean limitSwitchPressedFlag = false;
	
	private boolean lockFlag = false;
	
	private boolean barFlag = false;
	
	private double tareEncoderTick;
	
	public LifterAssembly(){
		
	}
	
	public void update(double dt) {
		//System.out.println("Limit Switch: " + RobotMap.bottomLimitSwitch.get());
		// Reset encoder at bottom
		boolean stop = RobotMap.bottomLimitSwitch.get();
		if(stop && !limitSwitchPressedFlag){
			System.out.println("Resetting lift encoder!");
			lifterMode = LifterModeEnum.MANUAL_LIFT;
			RobotMap.lifterFrontRight.set(ControlMode.PercentOutput, 0.0);
			tareEncoderTick = RobotMap.lifterFrontRight.getSensorCollection().getPulseWidthPosition();
			//RobotMap.lifterFrontRight.setSelectedSensorPosition(0, 0, 10); // No work....
			limitSwitchPressedFlag = true;
		}
		if(!RobotMap.bottomLimitSwitch.get()){
			limitSwitchPressedFlag = false;
		}
		
		if(lifterMode == LifterModeEnum.AUTO_LIFT && autoLifterPosition == LifterPositionEnum.DOWN){
			double loweringSpeed = -0.4;
			if(RobotMap.lifterFrontRight.getSensorCollection().getPulseWidthPosition() > tareEncoderTick - 21000){
				loweringSpeed = -0.05;
			}
			Dashboard.send("Lifter Down Power", loweringSpeed);
			RobotMap.lifterFrontRight.set(ControlMode.PercentOutput, loweringSpeed);
		}
		
		// Checks for what mode to have
		if(RobotMap.lifterController.getLeftTriggerBool() || RobotMap.lifterController.getRightTriggerBool() 
				/*|| RobotMap.driveController.getButtonStart() || RobotMap.driveController.getButtonBack()*/ ){ // commented this so that they don't do anything.
			lifterMode = LifterModeEnum.MANUAL_LIFT;
		} else if(/*RobotMap.driveController.getButtonY() ||*/ RobotMap.driveController.getLeftTriggerBool() || RobotMap.driveController.getRightTriggerBool()){
			lifterMode = LifterModeEnum.AUTO_LIFT;
		}
		
		// Checks mode and sets motors.
		if(lifterMode == LifterModeEnum.MANUAL_LIFT) {
//			RobotMap.driveController.getDPad() > 90 && RobotMap.driveController.getDPad() < 270
//			if(RobotMap.driveController.getButtonStart()){
//				triggerInput = -1.0;
			if(RobotMap.lifterController.getLeftTriggerBool() || RobotMap.lifterController.getRightTriggerBool()) {
				triggerInput = getTriggerSum();
				
				// If the lifter goes to bottom limit switch and is trying to go down set lifter to 0.0
				if(RobotMap.bottomLimitSwitch.get() && triggerInput < 0.0){
					triggerInput = 0.0;
				}
				
				// If the lifter goes to top soft stop and is trying to go up set lifter to 0.0.
				//CHanged the value to be negative
				if(RobotMap.lifterFrontRight.getSensorCollection().getPulseWidthPosition() <= -(tareEncoderTick + (130920-2496)) && triggerInput > 0.0){
					triggerInput = 0.0;
				}
			}
//			else if(RobotMap.driveController.getButtonStart()){
//				//triggerInput = 0.2; // Don't want to introduce errors by changing too much so just commented these...
//			} else if(RobotMap.driveController.getButtonBack()){
//				//triggerInput = -0.1;
//			} 
			else {
				triggerInput = 0.0;
			}
			
			RobotMap.lifterFrontRight.set(ControlMode.PercentOutput, triggerInput);
		} else if(lifterMode == LifterModeEnum.AUTO_LIFT){
			// Checking buttons, setting enums and updating motors.
			// The motor updating is in here so that it only updates of you press a button.
			if(RobotMap.driveController.getButtonY()){
//				autoLifterPosition = LifterPositionEnum.SCALE;
//				updateAutoHeight();
			} else if(RobotMap.driveController.getLeftTriggerBool()){
				RobotMap.intake.intakeTiltPosition = IntakeTiltEnum.TILTED_MID;
				autoLifterPosition = LifterPositionEnum.SCALE;
				updateAutoHeight();
			} else if(RobotMap.driveController.getRightTriggerBool()){
				RobotMap.intake.intakeTiltPosition = IntakeTiltEnum.TILTED_UP;
				autoLifterPosition = LifterPositionEnum.DOWN;
				updateAutoHeight();
			}
		}
		
		// Locking Servo Stuff
		
		if(RobotMap.lifterController.getButtonB() && !lockFlag){
			lockFlag = true;
			if(lockMode == LifterLockEnum.LOCKED){
				lockMode = LifterLockEnum.UNLOCKED;
			} else {
				lockMode = LifterLockEnum.LOCKED;
			}
			System.out.println(lockMode == LifterLockEnum.LOCKED ? "locked": "unlocked");
		}
		if(!RobotMap.lifterController.getButtonB()){
			lockFlag = false;
		}
		
		if(lockMode.equals(LifterLockEnum.LOCKED)){
			RobotMap.liftLocker.set(RobotMap.LIFTER_LOCKED_POSITION);
		} else if(lockMode.equals(LifterLockEnum.UNLOCKED)){
			RobotMap.liftLocker.set(RobotMap.LIFTER_UNLOCKED_POSITION);
		}
		
		// Bar servo stuff
		
		if (RobotMap.lifterController.getButtonA() && !barFlag) {
			barFlag = true;
			if (barMode == BarReleaseEnum.HELD) {
				barMode = BarReleaseEnum.RELEASED;
			} else {
				barMode = BarReleaseEnum.HELD;
			}
			System.out.println(barMode == BarReleaseEnum.HELD ? "held": "released");
		}
		if (!RobotMap.lifterController.getButtonA()) {
			barFlag = false;
		}
		
		if (barMode.equals(BarReleaseEnum.HELD)) {
			RobotMap.barRelease.set(RobotMap.BAR_HELD_POSITION);
		} else if (barMode.equals(BarReleaseEnum.RELEASED)) {
			RobotMap.barRelease.set(RobotMap.BAR_RELEASE_POSITION);
		}
		
		// Dashboard Sendings
		Dashboard.send("Raw Lifter Position", RobotMap.lifterFrontRight.getSensorCollection().getPulseWidthPosition());
		Dashboard.send("Tared Lifter Position", tareEncoderTick);
		Dashboard.send("Bottom Limit Switch", RobotMap.bottomLimitSwitch.get());
		Dashboard.send("Lifter Position Enum", autoLifterPosition.toString());
		Dashboard.send("Lifter Mode Enum", lifterMode.toString());
		Dashboard.send("Lifter Error", RobotMap.lifterFrontRight.getClosedLoopError(0));
		Dashboard.send("Locking Servo Position", RobotMap.liftLocker.get());
		Dashboard.send("Servo State", lockMode.toString());
		Dashboard.send("Soft Stop?", -(tareEncoderTick + (130920-2496)));
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
			RobotMap.lifterFrontRight.set(ControlMode.Position, tareEncoderTick + (130920-2496)); // Set this to the Scale height
			break;
		case SWITCH:
			RobotMap.lifterFrontRight.set(ControlMode.Position, tareEncoderTick + (64212-2496)); // Set this to the Switch height
			break;
		case DOWN:
//			RobotMap.lifterFrontRight.set(ControlMode.Position, tareEncoderTick); // Set this to the Down height
//			RobotMap.lifterFrontRight.set(ControlMode.PercentOutput, loweringSpeed);
			break;
		default:
			System.out.println("Holy shit we're defaulting in LifterPosition!");
			break;
		}
	}
	
	/** 
	 * Returns the sum of the two triggers where the left is + and the right is - so that the left
	 * returns positive and the right returns negative, added together.
	 */ 
	private double getTriggerSum(){
		return ((RobotMap.lifterController.getLeftTrigger()) + -RobotMap.lifterController.getRightTrigger());
	}

}

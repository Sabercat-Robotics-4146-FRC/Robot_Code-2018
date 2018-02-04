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
	
	LifterPositionEnum lifterPosition = LifterPositionEnum.DOWN;
	LifterModeEnum lifterMode = LifterModeEnum.AUTO_LIFT;
	
	private double triggerInput = 0.0; // Used for manual mode input.
	
	public LifterAssembly(){
		
	}
	
	public void update(double dt) {
		// Checks for what mode to have
		if(RobotMap.driveController.getLeftTriggerBool() || RobotMap.driveController.getLeftTriggerBool()){
			lifterMode = LifterModeEnum.MANUAL_LIFT;
		} else {
			lifterMode = LifterModeEnum.AUTO_LIFT;
		}
		
		// Does motor settings depending upon manual mode or no.
		if(lifterMode == LifterModeEnum.MANUAL_LIFT){
			// Getting trigger Sum
			triggerInput = getTriggerSum();
			
			// If the lifter goes to top limit switch and is trying to go up set lifter to 0.0
			if(RobotMap.topLimitSwitch.get() && triggerInput > 0.0){
				triggerInput = 0.0;
			}
			// If the lifter goes to bottom limit switch and is trying to go down set lifter to 0.0
			if(RobotMap.bottomLimitSwitch.get() && triggerInput < 0.0){
				triggerInput = 0.0;
			}
			
			RobotMap.lifterFrontLeft.set(ControlMode.PercentOutput, triggerInput);
		} else {
			// Checking buttons and setting enums.
			if(RobotMap.driveController.getButtonY()){
				lifterPosition = LifterPositionEnum.SCALE;
			} else if(RobotMap.driveController.getButtonB()){
				lifterPosition = LifterPositionEnum.SWITCH;
			} else if(RobotMap.driveController.getButtonA()){
				lifterPosition = LifterPositionEnum.DOWN;
			}
			
			switch(lifterPosition){
			case SCALE:
				RobotMap.lifterFrontLeft.set(ControlMode.Position, 0); // Set this to the Scale height
				break;
			case SWITCH:
				RobotMap.lifterFrontLeft.set(ControlMode.Position, 0); // Set this to the Switch height
				break;
			case DOWN:
				RobotMap.lifterFrontLeft.set(ControlMode.Position, 0); // Set this to the Down height
				break;
			default:
				System.out.println("Holy shit we're defaulting in LifterPosition!");
				break;
			}
		}
		
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
	
	
	/** 
	 * Returns the sum of the two triggers where the left is - and the left + so that the left
	 * returns negative and the right returns positive, added together.
	 */ 
	private double getTriggerSum(){
		return (-RobotMap.driveController.getLeftTrigger() + RobotMap.driveController.getRightTrigger());
	}

}

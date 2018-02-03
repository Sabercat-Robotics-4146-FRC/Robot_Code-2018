package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class LifterAssembly {
	enum LifterPositionEnum{
		SCALE,
		SWITCH,
		DOWN
	}
	
	LifterPositionEnum lifterPosition = LifterPositionEnum.DOWN;
	
	private double controllerInput = 0.0; // Used for manual mode input.
	
	public LifterAssembly(){
		
	}
	
	public void update(double dt){
		// Checking buttons and setting enums.
		if(RobotMap.driveController.getButtonY()){
			lifterPosition = LifterPositionEnum.SCALE;
		} else if(RobotMap.driveController.getButtonB()){
			lifterPosition = LifterPositionEnum.SWITCH;
		} else if(RobotMap.driveController.getButtonA()){
			lifterPosition = LifterPositionEnum.DOWN;
		}

		// Checks if back button for manual stuff or does regular stuff
		if(RobotMap.driveController.getButtonBack()){
			RobotMap.manualMode = true;
			
			controllerInput = RobotMap.driveController.getDeadbandRightYAxis();
			// If the lifter goes to top limit switch and is trying to go up set lifter to 0.0
			if(RobotMap.topLimitSwitch.get() && controllerInput > 0.0){
				controllerInput = 0.0;
			}
			// If the lifter goes to bottom limit switch and is trying to go down set lifter to 0.0
			if(RobotMap.bottomLimitSwitch.get() && controllerInput < 0.0){
				controllerInput = 0.0;
			}
			
			RobotMap.lifterFrontLeft.set(ControlMode.PercentOutput, controllerInput);
		} else {
			RobotMap.manualMode = false;
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

}

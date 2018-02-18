package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAssembly {
	enum IntakeTiltEnum{
		TILTED_UP,
		TILTED_DOWN
	}
	
	private boolean tiltFlag = false;
	
	IntakeTiltEnum intakeTiltEnum = IntakeTiltEnum.TILTED_UP;
	
	public IntakeAssembly(){
		
	}
	
	public void update(double dt){ 
		// Controlling intake tilt
		if(RobotMap.driveController.getRightBumper() && !tiltFlag){
			tiltFlag = true;
			
			if(intakeTiltEnum == IntakeTiltEnum.TILTED_UP){
				intakeTiltEnum = IntakeTiltEnum.TILTED_DOWN;
			} else {
				intakeTiltEnum = IntakeTiltEnum.TILTED_UP;
			}
		}
		if(!RobotMap.driveController.getRightBumper()){
			tiltFlag = false;
		}
		
		switch(intakeTiltEnum){
			case TILTED_UP:
				// Pot value is inverted so make up value be bigger.
				if(-RobotMap.tiltPot.get() < RobotMap.TILT_UP_LIMIT){
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.2);
				} else {
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.0);
				}
				break;
			case TILTED_DOWN:
				// Pot value is inverted so make up value be bigger.
				if(-RobotMap.tiltPot.get() >= RobotMap.TILT_DOWN_LIMIT){
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, -0.4);
				} else {
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.0);
				}
				break;
			default:
				System.out.println("Holy shit we're defalting in intakeTilt!");
				break;
		}
		
		// Controlling roller speed
		if(RobotMap.driveController.getLeftBumper()){
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 1.0);
		} else {
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.0);
		}
		
		// Dashboard Sendings
		Dashboard.send("Pot Value", -RobotMap.tiltPot.get());
	}
}

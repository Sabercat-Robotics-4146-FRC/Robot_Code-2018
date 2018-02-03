package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAssembly {
	enum IntakeintakeTilt{
		TILTED_UP,
		TILTED_DOWN
	}
	
	private boolean tiltFlag = false;
	
	IntakeintakeTilt intakeTilt = IntakeintakeTilt.TILTED_UP;
	
	public IntakeAssembly(){
		
	}
	
	public void update(double dt){ 
		// Controlling intake tilt
		if(RobotMap.driveController.getRightBumper() && !tiltFlag){
			tiltFlag = true;
			
			if(intakeTilt == IntakeintakeTilt.TILTED_UP){
				intakeTilt = IntakeintakeTilt.TILTED_DOWN;
			} else {
				intakeTilt = IntakeintakeTilt.TILTED_UP;
			}
		}
		if(!RobotMap.driveController.getRightBumper()){
			tiltFlag = false;
		}
		
		switch(intakeTilt){
			case TILTED_UP:
				RobotMap.intakeTilt.set(ControlMode.Position, 0); // We gotta set this!!
				break;
			case TILTED_DOWN:
				RobotMap.intakeTilt.set(ControlMode.Position, 0); // We gotta set this!!
				break;
			default:
				System.out.println("Holy shit we're defalting in intakeTilt!");
				break;
		}
		
		// Controlling roller speed
		if(RobotMap.driveController.getLeftTrigger()){
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, -0.6);
		} else {
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.0);
		}
	}
}

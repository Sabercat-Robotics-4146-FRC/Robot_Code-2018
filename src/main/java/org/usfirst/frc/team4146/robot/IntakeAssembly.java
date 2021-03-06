package org.usfirst.frc.team4146.robot;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.usfirst.frc.team4146.robot.PID.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAssembly {
	
	TiltPID tiltPID;
	
	enum IntakeTiltEnum{
		TILTED_UP,
		TILTED_DOWN,
		TILTED_MID,
		TILTED_LOW_MID
	}
	
	private boolean tiltFlag = false;
	
	IntakeTiltEnum intakeTiltPosition = IntakeTiltEnum.TILTED_UP;
	
	public IntakeAssembly(){
		tiltPID = new TiltPID();
	}
	
	public void update(double dt){ 
		tiltPID.update(dt);
		
		// Controlling intake tilt
		if(RobotMap.driveController.getRightBumper() && !tiltFlag){
			tiltFlag = true;
			
			if(intakeTiltPosition == IntakeTiltEnum.TILTED_UP){
				intakeTiltPosition = IntakeTiltEnum.TILTED_DOWN;
			} else {
				intakeTiltPosition = IntakeTiltEnum.TILTED_UP;
			}
		}
		if(!RobotMap.driveController.getRightBumper()){
			tiltFlag = false;
		}
		
		// enable tilt mid PID state
		if (RobotMap.driveController.getButtonX() && intakeTiltPosition != IntakeTiltEnum.TILTED_MID) {
			intakeTiltPosition = IntakeTiltEnum.TILTED_MID;
		}
		
		// enable tilt low mid PID state
		if (RobotMap.driveController.getButtonY() && intakeTiltPosition != IntakeTiltEnum.TILTED_LOW_MID) {
			intakeTiltPosition = IntakeTiltEnum.TILTED_LOW_MID;
		}
		
		switch(intakeTiltPosition){
			case TILTED_UP:
				// Pot value is inverted so make up value be bigger.
				if(RobotMap.tiltPot.get() >= RobotMap.TILT_UP_LIMIT){
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.8);
					//System.out.println("Moving intake up");
				} else {
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.0);
					//System.out.println("Intake is up stop moving");
				}
				break;
			case TILTED_DOWN:
				// Pot value is inverted so make up value be bigger.
				if(RobotMap.tiltPot.get() <= RobotMap.TILT_DOWN_LIMIT){
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, -0.8);
				} else {
					RobotMap.intakeTilt.set(ControlMode.PercentOutput, 0.0);
				}
				break;
			case TILTED_MID:
				tiltPID.setSetpoint(RobotMap.TILT_MID);
				RobotMap.intakeTilt.set(ControlMode.PercentOutput, -tiltPID.get());
				break;
			case TILTED_LOW_MID:
				tiltPID.setSetpoint(RobotMap.TILT_LOW_MID);
				RobotMap.intakeTilt.set(ControlMode.PercentOutput, -tiltPID.get());
				break;
			default:
				System.out.println("Holy shit we're defalting in intakeTilt!");
				break;
		}
		
		// Controlling roller speed
		if(RobotMap.driveController.getLeftBumper()){
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.8);
		} else if(RobotMap.driveController.getButtonA()){
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, -0.8); // was -.65
		} else if(RobotMap.driveController.getButtonB()){
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.45);
		} else {
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.0);
		}
		
		// Dashboard Sendings
		Dashboard.send("Pot Value", RobotMap.tiltPot.get());
		Dashboard.send("Tilt PID Out", -tiltPID.get());
		//System.out.println("Pot Value" + RobotMap.tiltPot.get());
		
	}
}

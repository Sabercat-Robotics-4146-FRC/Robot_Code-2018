package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public Robot() {
		RobotMap.ROBOT = this;
	}
	
	/**
	 * Runs once when the robot is powered on and called when you are basically guaranteed that
	 * all WPILIBJ stuff will work.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); // Instantiates and Declares things to be used from RobotMap.
		RobotMap.gyro.reset();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomous() {
		String autoSelected = (String)RobotMap.chooser.getSelected();
		System.out.println(autoSelected);
		
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Game Data: " + RobotMap.gameData);
		
		boolean isLeft = RobotMap.gameData.charAt(0) == 'L';
		
		switch(autoSelected){
			case "Left":
				if(isLeft) {
					RobotMap.auto.leftLeftAutonomous();
				} else {
					RobotMap.auto.leftRightAutonomous();
				}
				break;
			case "Middle":
				if(isLeft) {
					RobotMap.auto.middleLeftAutonomous();
				} else {
					RobotMap.auto.middleRightAutonomous();
				}
				break;
			case "Right":
				if(isLeft) {
					RobotMap.auto.rightLeftAutonomous();
				} else {
					RobotMap.auto.rightRightAutonomous();
				}
				break;
			case "Cross Baseline":
				RobotMap.auto.crossBaseline();
				break;
			case "Do Nothing":
				System.out.println("");
				break;
			
		}
	}

	/**
	 * This function is run once each time the robot enters operator control mode
	 */
	@Override
	public void operatorControl() {
		Timer timer = new Timer();
		double dt = 0.0;
		
		while (isOperatorControl() && isEnabled()) {
			dt = timer.getDT();
			RobotMap.drive.update(dt);
			RobotMap.intake.update(dt);
			RobotMap.lifter.update(dt);
			timer.update();
			
		}
	}

	/**
	 * This function is run once each time the robot enters test mode
	 */
	@Override
	public void test() {
		
	}
}

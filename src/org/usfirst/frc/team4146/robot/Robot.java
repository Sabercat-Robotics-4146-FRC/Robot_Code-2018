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
	 * This function is run once each time the robot enters autonomous mode.
	 * This autonomous method mostly deals with choosing the auto to execute.
	 */
	@Override
	public void autonomous() {
		String colorSelected = (String)RobotMap.colorChooser.getSelected();
		System.out.println("Color Data: " + colorSelected);
		
		String autoSelected = (String)RobotMap.chooser.getSelected();
		System.out.println("Base Chooser Data: " + autoSelected);
		
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Game Data: " + RobotMap.gameData);
		
		boolean isSwitchLeft = RobotMap.gameData.charAt(0) == 'L';
		
		// <color><Robot Position><Switch Position>Autonomous
		
		if(colorSelected.equals("Red")){
			switch(autoSelected){
				case "Left":
					if(isSwitchLeft) {
						System.out.println("Running Red Left Left Auto.");
						RobotMap.auto.redLeftLeftAutonomous();
					} else {
						System.out.println("Running Red Left Right Auto.");
						RobotMap.auto.redLeftRightAutonomous();
					}
					break;
				case "Middle":
					if(isSwitchLeft) {
						System.out.println("Running Red Middle Left Auto.");
						RobotMap.auto.redMiddleLeftAutonomous();
					} else {
						System.out.println("Running Red Middle Right Auto.");
						RobotMap.auto.redMiddleRightAutonomous();
					}
					break;
				case "Right":
					if(isSwitchLeft) {
						System.out.println("Running Red Right Left Auto.");
						RobotMap.auto.redRightLeftAutonomous();
					} else {
						System.out.println("Running Red Right Right Auto.");
						RobotMap.auto.redRightRightAutonomous();
					}
					break;
				case "Cross Baseline":
					System.out.println("Running Red Cross Baseline Auto.");
					RobotMap.auto.redCrossBaseline();
					break;
				case "Do Nothing":
					System.out.println("Doing nothing.... :'(");
					break;
			}
		} else if(colorSelected.equals("Blue")){
			switch(autoSelected){
				case "Left":
					if(isSwitchLeft) {
						System.out.println("Running Blue Left Left Auto.");
						RobotMap.auto.blueLeftLeftAutonomous();
					} else {
						System.out.println("Running Blue Left Right Auto.");
						RobotMap.auto.blueLeftRightAutonomous();
					}
					break;
				case "Middle":
					if(isSwitchLeft) {
						System.out.println("Running Blue Middle Left Auto.");
						RobotMap.auto.blueMiddleLeftAutonomous();
					} else {
						System.out.println("Running Blue Middle Right Auto.");
						RobotMap.auto.blueMiddleRightAutonomous();
					}
					break;
				case "Right":
					if(isSwitchLeft) {
						System.out.println("Running Blue Right Left Auto.");
						RobotMap.auto.blueRightLeftAutonomous();
					} else {
						System.out.println("Running Blue Right Right Auto.");
						RobotMap.auto.blueRightRightAutonomous();
					}
					break;
				case "Cross Baseline":
					System.out.println("Running Blue Cross Baseline Auto.");
					RobotMap.auto.blueCrossBaseline();
					break;
				case "Do Nothing":
					System.out.println("Doing nothing.... :'(");
					break;
			}
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

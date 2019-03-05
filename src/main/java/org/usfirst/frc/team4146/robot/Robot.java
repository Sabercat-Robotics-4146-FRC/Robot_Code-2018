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
		//RobotMap.gyro.reset();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 * This autonomous method mostly deals with choosing the auto to execute.
	 */
	@Override
	public void autonomous() {
	
	}

	/**
	 * This function is run once each time the robot enters operator control mode
	 */
	@Override
	public void operatorControl() {
		Timer timer = new Timer();
		double dt = 0.0;
		
		RobotMap.drive.setPeakOutput(1.0);
		
		while (isOperatorControl() && isEnabled()) {
			dt = timer.getDT();
			RobotMap.drive.update(dt);

			if (RobotMap.driveController.getButtonA()) {
				RobotMap.leftTop.set(ControlMode.PercentOutput, 0.5);
			} else if (RobotMap.driveController.getButtonB()) {
				RobotMap.leftBottom.set(ControlMode.PercentOutput, 0.5);
			} else if (RobotMap.driveController.getButtonY()) {
				RobotMap.rightTop.set(ControlMode.PercentOutput, 0.5);
			} else if (RobotMap.driveController.getButtonX()) {
				RobotMap.rightBottom.set(ControlMode.PercentOutput, 0.5);
			} else {
				RobotMap.leftTop.set(ControlMode.PercentOutput, 0.0);
				RobotMap.leftBottom.set(ControlMode.PercentOutput, 0.0);
				RobotMap.rightTop.set(ControlMode.PercentOutput, 0.0);
				RobotMap.rightBottom.set(ControlMode.PercentOutput, 0.0);
			}
			
			timer.update();
		}
	}

	/**
	 * This function is run once each time the robot enters test mode
	 */
	@Override
	public void test() {
		System.out.println("Teeeeest Mode");
		
	}
}

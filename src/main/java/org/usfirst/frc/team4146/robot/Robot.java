package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.LifterAssembly.LifterModeEnum;

import com.ctre.phoenix.motion.SetValueMotionProfile;

/*
 * ---Things to change for competition Robot---
 * - Change the potentiometer port to 3  - done
 * - Test pot values
 * - Uncomment pid setting stuff for pids
 * 
 */

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motion.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	//MotionProfile profile;
	
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
		//profile = new MotionProfile(GeneratedProfiles.Points, GeneratedProfiles.kNumPoints);
		//RobotMap.gyro.reset();
		/* fill our buffer object with the excel points */
        //initBuffer(GeneratedProfiles.Points, GeneratedProfiles.kNumPoints);
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
			//RobotMap.drive.update(dt);
			//RobotMap.intake.update(dt);
			//RobotMap.lifter.update(dt);
			//profile.update();
			RobotMap.testProflies.update();
			
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

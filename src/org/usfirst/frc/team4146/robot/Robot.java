package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

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
		Controller driver = new Controller(0);
		
		CANTalon frontRight = new CANTalon(0);
		CANTalon rearRight = new CANTalon(1);
		CANTalon frontLeft = new CANTalon(2);
		CANTalon rearLeft = new CANTalon(3);
		
		frontRight.setSafetyEnabled(false);
		rearRight.setSafetyEnabled(false);
		frontLeft.setSafetyEnabled(false);
		rearLeft.setSafetyEnabled(false);
		
		RobotDrive drive = new RobotDrive(frontRight, rearRight, frontLeft, rearLeft);
		
		while (isOperatorControl() && isEnabled()) {
			
			if  (driver.getButtonA()){
				frontRight.set(0.5);
				rearRight.set(0.5);
				frontLeft.set(0.5);
				rearLeft.set(0.5);
				
			}else{
				frontRight.set(0.0);
				rearRight.set(0.0);
				frontLeft.set(0.0);
				rearLeft.set(0.0);
				
			}
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
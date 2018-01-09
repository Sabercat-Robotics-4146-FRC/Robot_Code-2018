package org.usfirst.frc.team4146.robot;

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
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void operatorControl() {
		Timer timer = new Timer();
		double dt = 0.0;
		double spin;
		double move;
		
		while (isOperatorControl() && isEnabled()) {
			dt = timer.getDT();
			
			// Start of Drive Code (in testing phase)
			move = RobotMap.driveController.getDeadbandLeftYAxis();
			spin = -RobotMap.driveController.getDeadbandRightXAxis();
			
			Dashboard.send("Move", move);
			Dashboard.send("Spin", spin);
			
			RobotMap.drive.arcadeDrive(move, spin);
			
			// End of Drive Code
			timer.update();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void test() {
		
	}
}

package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.PID.*;

public class MoveDistance {
	public MoveDistancePID moveDistanceController;
	double theoreticalPosition; // this does confusing shit
	
	public MoveDistance(){
		theoreticalPosition = 0;
		moveDistanceController = new MoveDistancePID();
	}
	
	public void moveToAbsolute(double absolutePosition) {
		Timer autoMoveDistanceTimer = new Timer();
		double dt = 0.0;
		tareMoveDistanceRelative();

		// Start the move distance controller
		moveDistanceController.setSetpoint(absolutePosition);
		moveDistanceController.flush();


		while (moveDistanceController.getTimeInTolerance() < 1.0) {
			dt = autoMoveDistanceTimer.getDT();

			moveDistanceController.update(dt);
			RobotMap.differentialDrive.arcadeDrive(moveDistanceController.get(), 0.0);

			autoMoveDistanceTimer.update();
		}
	}
	
	public void moveToRelative(double relativePosition){
		theoreticalPosition += relativePosition;
	    moveToAbsolute(theoreticalPosition);
	}
	
	public void tareMoveDistanceRelative(){
		resetEcoders();
		theoreticalPosition = (RobotMap.leftDriveEncoder.getRaw() + RobotMap.leftDriveEncoder.getRaw()) / 2;	
	}
	
	public void resetEcoders(){
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder.reset();
	}
	
//	public double feetToEncoderTicks(){
//		return 
//	}
}

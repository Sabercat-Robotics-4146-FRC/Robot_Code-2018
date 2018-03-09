package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.PID.*;

public class MoveDistance {
	public MoveDistancePID moveDistanceController;
	double theoreticalPosition; // this does confusing shit
	
	public MoveDistance(){
		theoreticalPosition = 0;
		moveDistanceController = new MoveDistancePID();
		moveDistanceController.setIRange(10);
	}
	
	public void moveToAbsolute(double absolutePosition) {
		System.out.println("Starting move to abs with " + absolutePosition);
		
		Timer autoMoveDistanceTimer = new Timer();
		double dt = 0.0;
		tareMoveDistanceRelative();
//		RobotMap.leftTop.configOpenloopRamp(1, 0);
//		RobotMap.rightTop.configOpenloopRamp(1, 0);
		
		// Start the move distance controller
		moveDistanceController.reset();
		moveDistanceController.setSetpoint(absolutePosition);
		moveDistanceController.flush();
		
		if(absolutePosition <= 36){
			System.out.println("Changing to small distance pid values.");
			moveDistanceController.setPID(RobotMap.SMALL_MOVE_kP, RobotMap.SMALL_MOVE_kI, RobotMap.SMALL_MOVE_kD);
		}


		while (moveDistanceController.getTimeInTolerance() < 1.0 && RobotMap.ROBOT.isAutonomous() && RobotMap.ROBOT.isEnabled()) {
			dt = autoMoveDistanceTimer.getDT();
			
			
			RobotMap.intake.update(dt);
			

			moveDistanceController.update(dt);
			RobotMap.differentialDrive.arcadeDrive(clamp(moveDistanceController.get(), 0.8), 0.0);

			Dashboard.send("Move Error", moveDistanceController.getError());
			Dashboard.send("Move Value", moveDistanceController.getValue());
			Dashboard.send("Move Get", moveDistanceController.get());
			Dashboard.send("setpoint", moveDistanceController.setpoint);
			autoMoveDistanceTimer.update();
		}
		
		moveDistanceController.setPID(RobotMap.MOVE_kP, RobotMap.MOVE_kI, RobotMap.MOVE_kD);
		
		System.out.println("Done Moving!");
		RobotMap.differentialDrive.arcadeDrive(0.0, 0.0);
//		RobotMap.leftTop.configOpenloopRamp(0, 0);
//		RobotMap.rightTop.configOpenloopRamp(0, 0);
	}
	
	public void moveToRelative(double relativePosition){
		theoreticalPosition += relativePosition;
	    moveToAbsolute(theoreticalPosition);
	}
	
	public void tareMoveDistanceRelative(){
		resetEcoders();
		
    	//RobotMap.rightDriveEncoder.setDistancePerPulse(Math.PI/64.0);
    	//+++RobotMap.leftDriveEncoder.setDistancePerPulse(Math.PI/64.0);
		
//		theoreticalPosition = (RobotMap.leftDriveEncoder.getRaw() + RobotMap.leftDriveEncoder.getRaw()) / 2;
		theoreticalPosition = RobotMap.leftDriveEncoder.getRaw();
		moveDistanceController.reset();
	}
	
	public void resetEcoders(){
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder.reset();
	}
	
	public static double clamp(double valueToClamp, double clampValue) {
		if(valueToClamp > clampValue){
			return clampValue;
		} else if(valueToClamp < -(clampValue)) {
			return -(clampValue);
		}
		return valueToClamp;
	}
}

package org.usfirst.frc.team4146.robot;

public class DriveAssembly {
	double move, spin;
	boolean rampFlag = true;
	Heading testHeading;
	MoveDistance testMoveDistance;
	
	public DriveAssembly() {
		testHeading = new Heading();
		testHeading.headingController.setSetpoint(45);
		
		testMoveDistance = new MoveDistance();
		testMoveDistance.moveDistanceController.setSetpoint(256);
	}
	
	public void update(double dt){
		
		
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis();
		spin = RobotMap.driveController.getDeadbandRightXAxis();
		
//		testHeading.headingController.update(dt);
//		if (RobotMap.driveController.getButtonX()){
//			spin = testHeading.headingController.get();
//		}
		
		testMoveDistance.moveDistanceController.update(dt);
		if (RobotMap.driveController.getDPad() == 180){
			
			move = testMoveDistance.moveDistanceController.get();
		}
		
		if(RobotMap.driveController.getDPad() == 0){
			testMoveDistance.resetEcoders();
		}
		
		// Applies a ramp to the drive train if the lifter is up
		if (!RobotMap.bottomLimitSwitch.get() && rampFlag) {
			rampFlag = false;
			RobotMap.leftTop.configOpenloopRamp(1, 0); // secondsFromNeutralToFull, timeOut
			RobotMap.rightTop.configOpenloopRamp(1, 0);
		}
		
		// Disables the ramp if the lift is down
		if (RobotMap.bottomLimitSwitch.get()) {
			rampFlag = true;
			RobotMap.leftTop.configOpenloopRamp(0, 0);
			RobotMap.rightTop.configOpenloopRamp(0, 0);
		}
		
		RobotMap.differentialDrive.arcadeDrive(move, spin);
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);
		Dashboard.send("Heading Error",testHeading.headingController.getError());
		Dashboard.send("Heading Out",testHeading.headingController.get());
		Dashboard.send("PID TEST HEADING", testHeading.headingController.get());
		Dashboard.send("HEADING MIN", testHeading.headingController.getValue());
		Dashboard.send("FUSED", RobotMap.gyro.getAngle());
		Dashboard.send("Left Drive Encoder", RobotMap.leftDriveEncoder.getRaw());
		Dashboard.send("Right Drive Encoder", RobotMap.rightDriveEncoder.getRaw());
		
		
	}

}

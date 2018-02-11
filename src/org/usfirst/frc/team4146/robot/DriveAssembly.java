package org.usfirst.frc.team4146.robot;

public class DriveAssembly {
	double move, spin;
	boolean rampFlag = true;
	
	public DriveAssembly() {
		
	}
	
	public void update(double dt){
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis();
		spin = RobotMap.driveController.getDeadbandRightXAxis();
		
		RobotMap.differentialDrive.arcadeDrive(move, spin);
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);
		
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
		
	}

}

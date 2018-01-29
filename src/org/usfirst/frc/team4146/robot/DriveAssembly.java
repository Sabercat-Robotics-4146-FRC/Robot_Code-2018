package org.usfirst.frc.team4146.robot;

public class DriveAssembly {
	double move, spin;
	
	public DriveAssembly() {
		
	}
	
	public void update(double dt){
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis();
		spin = -RobotMap.driveController.getDeadbandRightXAxis();
		
		RobotMap.differentialDrive.arcadeDrive(move, spin);
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);
	}

}

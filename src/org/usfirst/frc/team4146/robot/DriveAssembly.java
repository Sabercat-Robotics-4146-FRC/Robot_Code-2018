package org.usfirst.frc.team4146.robot;

public class DriveAssembly {
	double move, spin;
	
	public DriveAssembly() {
		
	}
	
	public void update(double dt){
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis();
		spin = RobotMap.driveController.getDeadbandRightXAxis();
		
		// Manual mode is when we use the joystick to move the lifter with no drive train movement
		if(!RobotMap.manualMode){ 
			RobotMap.differentialDrive.arcadeDrive(move, spin);
		}
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);
	}

}

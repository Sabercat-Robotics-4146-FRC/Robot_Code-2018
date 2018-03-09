package org.usfirst.frc.team4146.robot;
import org.usfirst.frc.team4146.robot.PID.*;

public class DriveAssembly {
	double move, spin;
	boolean rampFlag = true;
	VisionPID vision;
	public DriveAssembly() {
		vision = new VisionPID();
	}
	
	public void update(double dt){
		
		vision.update(dt);
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis() * 0.8;
		spin = RobotMap.driveController.getDeadbandRightXAxis() * 0.8;
		
//		if (RobotMap.driveController.getRightStickPress()) {
//			//vision lock
//			spin = vision.get();
//		}
		// Applies a ramp to the drive train if the lifter is up
//		if (!RobotMap.bottomLimitSwitch.get() && rampFlag) {
//			rampFlag = false;
//			RobotMap.leftTop.configOpenloopRamp(1, 0); // secondsFromNeutralToFull, timeOut
//			RobotMap.rightTop.configOpenloopRamp(1, 0);
//		}
//		
//		// Disables the ramp if the lift is down
//		if (RobotMap.bottomLimitSwitch.get()) {
//			rampFlag = true;
//			RobotMap.leftTop.configOpenloopRamp(0, 0);
//			RobotMap.rightTop.configOpenloopRamp(0, 0);
//		}
		
		move = !RobotMap.bottomLimitSwitch.get() ? move * 0.6 : move;
		
		RobotMap.differentialDrive.arcadeDrive(move, spin);
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);
		
		
		Dashboard.send("Raw Left Drive Encoder", RobotMap.leftDriveEncoder.getRaw());
		Dashboard.send("Raw Right Drive Encoder", RobotMap.rightDriveEncoder.getRaw());
		
		Dashboard.send("Left Drive Encoder", RobotMap.leftDriveEncoder.get());
		Dashboard.send("Right Drive Encoder", RobotMap.rightDriveEncoder.get());
		
		Dashboard.send("Left Distance shit", RobotMap.leftDriveEncoder.getDistance());
		Dashboard.send("Right Distance shit", RobotMap.rightDriveEncoder.getDistance());
		
		
	}

}

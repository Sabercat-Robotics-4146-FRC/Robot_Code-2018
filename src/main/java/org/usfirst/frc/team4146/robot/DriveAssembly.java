package org.usfirst.frc.team4146.robot;

public class DriveAssembly {
	double move, spin;
	boolean rampFlag = true;
	public DriveAssembly() {

	}
	
	public void update(double dt){
		
		// Setting base movement variables
		move = RobotMap.driveController.getDeadbandLeftYAxis() *  1.0;//0.8;
		spin = RobotMap.driveController.getDeadbandRightXAxis() * 0.92;
		
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
		
		RobotMap.differentialDrive.arcadeDrive(move, spin);
		
		// Sending things to Dashboard
		Dashboard.send("Move", move);
		Dashboard.send("Spin", spin);		
	}
	
	public void setPeakOutput(double x) {
		RobotMap.leftTop.configPeakOutputForward(x, 10);
		RobotMap.leftBottom.configPeakOutputForward(x, 10);
		RobotMap.rightTop.configPeakOutputForward(x, 10);
		RobotMap.rightBottom.configPeakOutputForward(x, 10);
		
		RobotMap.leftTop.configPeakOutputReverse(-x, 10);
		RobotMap.leftBottom.configPeakOutputReverse(-x, 10);
		RobotMap.rightTop.configPeakOutputReverse(-x, 10);
		RobotMap.rightBottom.configPeakOutputReverse(-x, 10);
	}

}

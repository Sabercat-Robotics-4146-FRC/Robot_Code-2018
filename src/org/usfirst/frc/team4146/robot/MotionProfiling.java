package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MotionProfiling { 
	
	public void update() {
	
		if (RobotMap.driveController.getButtonY()) {
			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, 1.0);
		} else if (RobotMap.driveController.getButtonA()) {
			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, -1.0);
		} else {
			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, 0.0);
		}
		
	}

}

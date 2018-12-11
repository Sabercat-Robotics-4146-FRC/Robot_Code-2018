package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motion.MotionProfileStatus;
//import com.ctre.CANTalon;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Talon;

public class MotionProfiling { 
	
	//CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();
	//CANTalon.SetValueMotionProfile setValue = CANTalon.SetValueMotionProfile.Disable;
	SetValueMotionProfile setValue = SetValueMotionProfile.Disable;
	MotionProfileStatus status = new MotionProfileStatus();
	
	int state = 0;
	int loopTimeout = -1;
	int kMinPointsInTalon = 5;
	int kNumLoopsTimeout = 10;
	
	boolean buttonFlag = false;
	
	public MotionProfiling(){
		RobotMap.motionProfileTalon.changeMotionControlFramePeriod(5);
		notifer.startPeriodic(0.005);
	}
	
	// don't know how this works write own code later
	// needed to update the buffer faster than the points are use
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {  RobotMap.motionProfileTalon.processMotionProfileBuffer();    }
	}
	Notifier notifer = new Notifier(new PeriodicRunnable());

	public void reset() {
		RobotMap.motionProfileTalon.clearMotionProfileTrajectories();
		setValue = SetValueMotionProfile.Disable;
		state = 0;
		loopTimeout = -1;
	
		buttonFlag = false;
	}

//	public void update() {
//	
//		if (RobotMap.driveController.getButtonY()) {
//			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, 1.0);
//		} else if (RobotMap.driveController.getButtonA()) {
//			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, -1.0);
//		} else {
//			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, 0.0);
//		}
//		
//	}

}

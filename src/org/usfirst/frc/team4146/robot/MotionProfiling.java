package frc.robot;

import com.ctre.phoenix.motion.MotionProfileStatus;
//import com.ctre.CANTalon;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
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
	
	public void control() {
		// Get the motion profile status every loop 
		RobotMap.motionProfileTalon.getMotionProfileStatus(status);

		// track time
		if (loopTimeout < 0) {
			// do nothing, timeout is disabled 
		} else {
			if (loopTimeout == 0) {
				// something is wrong
				System.out.println("Something is wrong the time out is 0");
			} else {
				--loopTimeout;
			}
		}

		// first check if we are in MP mode 
		if (RobotMap.motionProfileTalon.getControlMode() != ControlMode.MotionProfile) {
			//we are not in MP mode
			state = 0;
			loopTimeout = -1;
		} else {
			// we are in MP control mode. That means: starting Mps, checking Mp
			// progress, and possibly interrupting MPs if thats what you want to
			// do.
			switch (state) {
				case 0: // wait for application to tell us to start an MP 
					if (buttonFlag) {
						buttonFlag = false;
	
						setValue = SetValueMotionProfile.Disable;
						startFilling();
						// MP is being sent to CAN bus, wait a small amount of time
						state = 1;
						loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1:
					// wait for MP to stream to Talon, really just the first few points
					// do we have a minimum numberof points in Talon 
					if (status.btmBufferCnt > kMinPointsInTalon) {
						// start (once) the motion profile 
						setValue = SetValueMotionProfile.Enable;
						// MP will start once the control frame gets scheduled 
						state = 2;
						loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2: // check the status of the MP 
					 // if talon is reporting things are good, keep adding to our
					 // timeout. Really this is so that you can unplug your talon in
					 // the middle of an MP and react to it.
					if (status.isUnderrun == false) {
						loopTimeout = kNumLoopsTimeout;
					}
					 // If we are executing an MP and the MP finished, start loading
					 // another. We will go into hold state so robot servo's
					 // position.
					if (status.activePointValid && status.isLast) {
						 // because we set the last point's isLast to true, we will
						 // get here when the MP is done
						setValue = SetValueMotionProfile.Hold;
						state = 0;
						loopTimeout = -1;
					}
					break;
			}
		}
		/* printfs and/or logging */
		System.out.println("Motion Profile Status: " + status);
	}
	
	// Start filling the MPs to all of the involved Talons. 
	private void startFilling() {
		// since this example only has one talon, just update that one 
		startFilling(GeneratedPath.Points, GeneratedPath.kNumPoints);        /** fix this later this isn't how we should be accessing this*/
	}

	private void startFilling(double[][] profile, int totalCnt) {

		// create an empty point
		TrajectoryPoint point = new TrajectoryPoint();

		// check to see if we got an underrun condition since last time we checked 
		if (status.hasUnderrun) {
			// better log it so we know about it 
			System.out.println("Motion profile is underrun");
			// clear the error. This flag does not auto clear, this way 
			// we never miss logging it.
			RobotMap.motionProfileTalon.clearMotionProfileHasUnderrun();
		}
		// just in case we are interrupting another MP and there is still buffer
		// points in memory, clear it.
		RobotMap.motionProfileTalon.clearMotionProfileTrajectories();

		// This is fast since it's just into our TOP buffer 
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			//point.timeDur = (int) profile[i][2];
			point.timeDur = TrajectoryPoint.TrajectoryDuration.valueOf((int) profile[i][2]);
			point.profileSlotSelect0 = 0; /* which set of gains would you like to use? */
			//point.velocityOnly = false;/* set true to not do any position
										 /* servo, just velocity feedforward
										 */
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; // set this to true on the first point 

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; // set this to true on the last point  

			RobotMap.motionProfileTalon.pushMotionProfileTrajectory(point);
		}
	}

	// Called by application to signal Talon to start the buffered MP (when it's
	// able to).
	void startMotionProfile() {
		buttonFlag = true;
	}

	// @return the output value to pass to Talon's set() routine. 0 for disable
	//         motion-profile output, 1 for enable motion-profile, 2 for hold
	//         current motion profile trajectory point.
	SetValueMotionProfile getSetValue() {
		return setValue;
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

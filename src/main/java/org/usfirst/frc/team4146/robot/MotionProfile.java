 package org.usfirst.frc.team4146.robot;

 import com.ctre.phoenix.motion.*;
 import com.ctre.phoenix.motorcontrol.ControlMode;

 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 public class MotionProfile {

	enum MotionProfileEnum{
		DISABLE,
		FILL_BUFFER,
		RUN_PROFILE,
		FINISHED
	}

	MotionProfileEnum MotionProfileState = MotionProfileEnum.DISABLE;

	/** new class type in 2019 for holding MP buffer. */
	BufferedTrajectoryPointStream bufferedStream = new BufferedTrajectoryPointStream();


	public MotionProfile(double[][] points, int numPoints) {
		initBuffer(points, numPoints);
	}

	public void update() {
		/* get joystick button and stick */
		boolean bPrintValues = RobotMap.driveController.getButtonB();
		boolean bFireMp = RobotMap.driveController.getButtonA();
		double axis = RobotMap.driveController.getDeadbandLeftYAxis();

		/* if button is up, just drive the motor in PercentOutput */
		if (bFireMp == false) {
			MotionProfileState = MotionProfileEnum.DISABLE;
		}

		switch (MotionProfileState) {
			/* drive master talon normally */
			case DISABLE:
			RobotMap.motionProfileTalon.set(ControlMode.PercentOutput, axis);
				if (bFireMp == true) {
					/* go to MP logic */
					MotionProfileState = MotionProfileEnum.FILL_BUFFER;
				}
				break;

			/* fire the MP, and stop calling set() since that will cancel the MP */
			case FILL_BUFFER:
				/* wait for 10 points to buffer in firmware, then transition to MP */
				RobotMap.motionProfileTalon.startMotionProfile(bufferedStream, 10, ControlMode.MotionProfile);
				MotionProfileState = MotionProfileEnum.RUN_PROFILE;
				System.out.println("MP started");
				break;

			/* wait for MP to finish */
			case RUN_PROFILE:
				if (RobotMap.motionProfileTalon.isMotionProfileFinished()) {
					System.out.println("MP finished");
					MotionProfileState = MotionProfileEnum.FINISHED;
				}
				break;

			/* MP is finished, nothing to do */
			case FINISHED:
				break;
		}

		// Send values to Shuffleboard
		double sensorPosition = RobotMap.motionProfileTalon.getSelectedSensorPosition(0);
		double sensorVelocity = RobotMap.motionProfileTalon.getSelectedSensorVelocity(0);
		double trajectoryPosition = RobotMap.motionProfileTalon.getActiveTrajectoryPosition(0);
		double trajectoryVelocity = RobotMap.motionProfileTalon.getActiveTrajectoryVelocity(0);
		double trajectoryArbFeedFwd = RobotMap.motionProfileTalon.getActiveTrajectoryArbFeedFwd(0);
		SmartDashboard.putNumber("Sensor Position", sensorPosition);
		SmartDashboard.putNumber("Sensor Velocity", sensorVelocity);
		SmartDashboard.putNumber("Trajectory Position", trajectoryPosition);
		SmartDashboard.putNumber("Trajectory Velocity", trajectoryVelocity);
		SmartDashboard.putNumber("Trajectory ArbFeedFwd", trajectoryArbFeedFwd);

		/* print MP values */
		Instrum.loop(bPrintValues, RobotMap.motionProfileTalon);
	}

	private void initBuffer(double[][] profile, int totalCnt) {

		boolean forward = true; // set to false to drive in opposite direction of profile (not really needed
								// since you can use negative numbers in profile).

		TrajectoryPoint point = new TrajectoryPoint(); // temp for for loop, since unused params are initialized
													   // automatically, you can alloc just one

		/* clear the buffer, in case it was used elsewhere */
		bufferedStream.Clear();

		/* Insert every point into buffer, no limit on size */
		for (int i = 0; i < totalCnt; ++i) {

			double direction = forward ? +1 : -1;
			double positionRot = profile[i][0];
			double velocityRPM = profile[i][1];
			int durationMilliseconds = (int) profile[i][2];

			/* for each point, fill our structure and pass it to API */
			point.timeDur = durationMilliseconds;
			point.position = direction * positionRot * RobotMap.kSensorUnitsPerRotation; // Convert Revolutions to
																						  // Units
			point.velocity = direction * velocityRPM * RobotMap.kSensorUnitsPerRotation / 600.0; // Convert RPM to
																								  // Units/100ms
			point.auxiliaryPos = 0;
			point.auxiliaryVel = 0;
			point.profileSlotSelect0 = RobotMap.kPrimaryPIDSlot; /* which set of gains would you like to use [0,3]? */
			point.profileSlotSelect1 = 0; /* auxiliary PID [0,1], leave zero */
			point.zeroPos = (i == 0); /* set this to true on the first point */
			point.isLastPoint = ((i + 1) == totalCnt); /* set this to true on the last point */
			point.arbFeedFwd = 0; /* you can add a constant offset to add to PID[0] output here */

			bufferedStream.Write(point);
		}
	}

 }
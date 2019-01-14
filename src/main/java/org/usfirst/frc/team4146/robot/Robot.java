package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.LifterAssembly.LifterModeEnum;

import com.ctre.phoenix.motion.SetValueMotionProfile;

/*
 * ---Things to change for competition Robot---
 * - Change the potentiometer port to 3  - done
 * - Test pot values
 * - Uncomment pid setting stuff for pids
 * 
 */

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motion.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public Robot() {
		RobotMap.ROBOT = this;
	}
	
	 /** very simple state machine to prevent calling set() while firing MP. */
	 int _state = 0;

	 /** a master talon, add followers if need be. */
	 TalonSRX _master = new TalonSRX(12);
 
	 /** gamepad for control */
	 //Joystick _joy = new Joystick(0);
 
	 /** new class type in 2019 for holding MP buffer. */
	 BufferedTrajectoryPointStream _bufferedStream = new BufferedTrajectoryPointStream();
 
	 /* talon configs */
	 TalonSRXConfiguration _config = new TalonSRXConfiguration(); // factory default settings
	 
	 /* quick and dirty plotter to smartdash */
	  PlotThread _plotThread = new PlotThread(_master);
	  
	  private void initBuffer(double[][] profile, int totalCnt) {

		boolean forward = true; // set to false to drive in opposite direction of profile (not really needed
								// since you can use negative numbers in profile).

		TrajectoryPoint point = new TrajectoryPoint(); // temp for for loop, since unused params are initialized
													   // automatically, you can alloc just one

		/* clear the buffer, in case it was used elsewhere */
		_bufferedStream.Clear();

		/* Insert every point into buffer, no limit on size */
		for (int i = 0; i < totalCnt; ++i) {

			double direction = forward ? +1 : -1;
			double positionRot = profile[i][0];
			double velocityRPM = profile[i][1];
			int durationMilliseconds = (int) profile[i][2];

			/* for each point, fill our structure and pass it to API */
			point.timeDur = durationMilliseconds;
			point.position = direction * positionRot * Constants.kSensorUnitsPerRotation; // Convert Revolutions to
																						  // Units
			point.velocity = direction * velocityRPM * Constants.kSensorUnitsPerRotation / 600.0; // Convert RPM to
																								  // Units/100ms
			point.auxiliaryPos = 0;
			point.auxiliaryVel = 0;
			point.profileSlotSelect0 = Constants.kPrimaryPIDSlot; /* which set of gains would you like to use [0,3]? */
			point.profileSlotSelect1 = 0; /* auxiliary PID [0,1], leave zero */
			point.zeroPos = (i == 0); /* set this to true on the first point */
			point.isLastPoint = ((i + 1) == totalCnt); /* set this to true on the last point */
			point.arbFeedFwd = 0; /* you can add a constant offset to add to PID[0] output here */

			_bufferedStream.Write(point);
		}
	}

	/**
	 * Runs once when the robot is powered on and called when you are basically guaranteed that
	 * all WPILIBJ stuff will work.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); // Instantiates and Declares things to be used from RobotMap.
		//RobotMap.gyro.reset();
		/* fill our buffer object with the excel points */
        initBuffer(MotionProfile.Points, MotionProfile.kNumPoints);

        /* _config the master specific settings */
        _config.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
        _config.neutralDeadband = Constants.kNeutralDeadband; /* 0.1 % super small for best low-speed control */
        _config.slot0.kF = Constants.kGains_MotProf.kF;
        _config.slot0.kP = Constants.kGains_MotProf.kP;
        _config.slot0.kI = Constants.kGains_MotProf.kI;
        _config.slot0.kD = Constants.kGains_MotProf.kD;
        _config.slot0.integralZone = (int) Constants.kGains_MotProf.kIzone;
        _config.slot0.closedLoopPeakOutput = Constants.kGains_MotProf.kPeakOutput;
        // _config.slot0.allowableClosedloopError // left default for this example
        // _config.slot0.maxIntegralAccumulator; // left default for this example
        // _config.slot0.closedLoopPeriod; // left default for this example
        _master.configAllSettings(_config);

        /* pick the sensor phase and desired direction */
        _master.setSensorPhase(true);
		_master.setInverted(false);
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 * This autonomous method mostly deals with choosing the auto to execute.
	 */
	@Override
	public void autonomous() {

	}

	/**
	 * This function is run once each time the robot enters operator control mode
	 */
	@Override
	public void operatorControl() {
		Timer timer = new Timer();
		double dt = 0.0;
		
		RobotMap.drive.setPeakOutput(1.0);
		
		while (isOperatorControl() && isEnabled()) {
			dt = timer.getDT();
			//RobotMap.drive.update(dt);
			//RobotMap.intake.update(dt);
			//RobotMap.lifter.update(dt);

			/* get joystick button and stick */
			boolean bPrintValues = RobotMap.driveController.getButtonB();
			boolean bFireMp = RobotMap.driveController.getButtonA();
			double axis = RobotMap.driveController.getDeadbandLeftYAxis();
	
			/* if button is up, just drive the motor in PercentOutput */
			if (bFireMp == false) {
				_state = 0;
			}
	
			switch (_state) {
				/* drive master talon normally */
				case 0:
					_master.set(ControlMode.PercentOutput, axis);
					if (bFireMp == true) {
						/* go to MP logic */
						_state = 1;
					}
					break;
	
				/* fire the MP, and stop calling set() since that will cancel the MP */
				case 1:
					/* wait for 10 points to buffer in firmware, then transition to MP */
					_master.startMotionProfile(_bufferedStream, 10, ControlMode.MotionProfile);
					_state = 2;
					Instrum.printLine("MP started");
					break;
	
				/* wait for MP to finish */
				case 2:
					if (_master.isMotionProfileFinished()) {
						Instrum.printLine("MP finished");
						_state = 3;
					}
					break;
	
				/* MP is finished, nothing to do */
				case 3:
					break;
			}
	
			/* print MP values */
			Instrum.loop(bPrintValues, _master);
			
			timer.update();
		}

		
	}

	/**
	 * This function is run once each time the robot enters test mode
	 */
	@Override
	public void test() {
		System.out.println("Teeeeest Mode");
		
		
	}
}

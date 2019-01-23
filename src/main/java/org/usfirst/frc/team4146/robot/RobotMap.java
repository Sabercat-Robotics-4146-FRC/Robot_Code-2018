package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motion.*;
import com.ctre.phoenix.motorcontrol.can.*;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Talon;
public class RobotMap {
	
	public static Robot ROBOT;
	
	//////Constants//////

	// Motion Profile Constants
	/**
	 * How many sensor units per rotation.
	 * Using CTRE Magnetic Encoder.
	 * @link https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
	 */
	public final static int kSensorUnitsPerRotation = 4096;

	/**
	 * Motor neutral dead-band, set to the minimum 0.1%.
	 */
	public final static double kNeutralDeadband = 0.001;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop
	 * 	                                    			  kP   kI    kD     kF             Iz    PeakOut */
	//public final static Gains kGains_MotProf = new Gains( 1.0, 0.0,  0.0, 1023.0/6800.0,  400,  1.00 ); /* measured 6800 velocity units at full motor output */
	public final static Gains kGains_MotProf = new Gains( 0.1, 0.0,  0.0, 0,  0,  0.20 ); /* measured 6800 velocity units at full motor output */

	public final static int kPrimaryPIDSlot = 0; // any slot [0,3]

//	public static final double MOVE_P0 = 0.48;
//	public static final double MOVE_P1 = 0.21;
//	public static final double MOVE_P2 = 0.15;
//	public static final double MOVE_P3 = 0.61;
//	
//	public static final double SPIN_P0 = 0.48;
//	public static final double SPIN_P1 = 0.21;
//	public static final double SPIN_P2 = 0.15;
//	public static final double SPIN_P3 = 0.61;
	
	// Lifter Constants
	public static final double LIFTER_kP = 0.015;
	public static final double LIFTER_kI = 0.0;
	public static final double LIFTER_kD = 1.5;
	
	// Intake Constants
	public static final double TILT_UP_LIMIT = 0.13; // was 0.14 on 10/9/18 then changed pot
	public static final double TILT_DOWN_LIMIT = 0.785; // was 0.785 10/6/18 // was 0.79 on robot 1 //0.78
	public static final double TILT_MID = 0.36;
	public static final double TILT_LOW_MID = 0.63;
	
	// Move Constants
	public static final double MOVE_kP = 0.02;
	public static final double MOVE_kI = 10;
	public static final double MOVE_kD = 0.0;//0.0002;
	
	public static final double SMALL_MOVE_kP = 0.03;
	public static final double SMALL_MOVE_kI = 10;
	public static final double SMALL_MOVE_kD = 0.0;
	
	// Heading Constants
	public static final double HEADING_kP = 0.018; //0.008
	public static final double HEADING_kI = 0.0;
	public static final double HEADING_kD = 0.0;
	
	public static final double HEADING_TIME_OUT = 3.5; // was 3.5 10/9/18
	public static final double HEADING_TIME_IN_TOLERENCE = 0.8;
	public static final double HEADING_TOLERENCE = (8192/360) * 2;
	
	// Heading Lock Constants
	public static final double HEADING_LOCK_kP = 0.0;
	public static final double HEADING_LOCK_kI = 0.0;
	public static final double HEADING_LOCK_kD = 0.0;
	
	// Lifter Lock Constants
	public static final double LIFTER_LOCKED_POSITION = 0.25;
	public static final double LIFTER_UNLOCKED_POSITION = 0.8;
	
	// Bar Release Constants
	public static final double BAR_HELD_POSITION = 0.05;
	public static final double BAR_RELEASE_POSITION = 0.7;
	
	//////Variables//////
	
	
	//////Declarations //////
	public static Controller driveController;
	public static Controller lifterController;
	
	// Motor Controller Declarations
	public static WPI_TalonSRX leftTop;
	public static WPI_TalonSRX leftBottom;
	public static WPI_TalonSRX rightTop;
	public static WPI_TalonSRX rightBottom;
	
	public static TalonSRX intakeTilt;
	public static TalonSRX intakeRoller;
	
	public static TalonSRX lifterFrontLeft;
	public static TalonSRX lifterBackLeft;
	public static TalonSRX lifterFrontRight;
	public static TalonSRX lifterBackRight;
	
	public static TalonSRX pigeonTalon;

	public static TalonSRX motionProfileTalonA;
	public static TalonSRX motionProfileTalonB;

	/* talon configs */
	public static TalonSRXConfiguration config; 
	
	public static Servo liftLocker;
	public static Servo barRelease;
	
	// Limit Switch Declarations
//	public static DigitalInput topLimitSwitch; // Not a thing
	public static DigitalInput bottomLimitSwitch;
	
	// Encoder Declarations
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	
	// Potentiometer Declarations
	public static AnalogPotentiometer tiltPot;
	
	// Navax Gyro Declaration
	//public static AHRS gyro;
	
	public static PigeonIMU pidgey;
	
	// Network Table Declaration
	public static NetworkTable networkTable;
	
	// Sendable Chooser Declaration
	public static SendableChooser chooser; //Sendable chooser allows us to choose the autonomous from smartdashboard
	public static SendableChooser colorChooser;
	public static SendableChooser scaleSwitchChooser;
	//public static SendableChooser switchChooser;
	
	// Game Data Declaration
	public static String gameData;
	
	// Diferential Drive Declaraion
	public static DifferentialDrive differentialDrive;
	
	////Sub-System Declarations////
	// Drive Assembly Declaration
	public static DriveAssembly drive;
	
	// Intake Assembly Declatation
	public static IntakeAssembly intake;
	
	// Lifter Assembly Declatation
	public static LifterAssembly lifter;
	
	// Autonomous Declaration
	public static Autonomous auto;
	
	// Heading Declaration
	//public static Heading heading;
	
	// Pigeon Heading Declaration
	public static PigeonHeading pigeonHeading;
	
	// Move Distance Declaration
	public static MoveDistance moveDistance;

	// Testing Profiles Declaration
	public static TestProfiles testProflies;

	
	public static void init() { // This is to be called in robitInit and instantiates stuff.
		// Camera Setup
		//CameraServer.getInstance().startAutomaticCapture();
		
		// Controllers Initialization
    	driveController = new Controller(0);
    	lifterController = new Controller(1);
    	
    	// Motor Controllers Initialization
    	// Main Drive Controllers
    	leftTop = new WPI_TalonSRX(1);
    	rightTop = new WPI_TalonSRX(2);
    	
    	// Sub Drive Controllers
    	leftBottom = new WPI_TalonSRX(3);
    	rightBottom = new WPI_TalonSRX(4);
    	
    	leftTop.setSafetyEnabled(false);
    	rightTop.setSafetyEnabled(false);
    	leftBottom.setSafetyEnabled(false);
    	rightBottom.setSafetyEnabled(false);
    	
    	// Make motors on same side follow.
    	leftBottom.follow(leftTop);
    	rightBottom.follow(rightTop);
    	
    	//setPeakOutput(1.0);
    	
//    	leftTop.configContinuousCurrentLimit(25, 0);
//    	leftTop.enableCurrentLimit(true);
//    	rightTop.configContinuousCurrentLimit(25, 0);
//    	rightTop.enableCurrentLimit(true);
    	
    	
//    	leftTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
//    	leftTop.configAllowableClosedloopError(0, 0, 10);
    	
//    	leftTop.config_kF(0, 0.0, 10);
//    	leftTop.config_kP(0, MOVE_kP, 10);
//    	leftTop.config_kI(0, MOVE_kI, 10);
//    	leftTop.config_kD(0, MOVE_kD, 10);
    	
    	// Intake Controllers Initialization
    	intakeTilt = new TalonSRX(5);
    	intakeRoller = new TalonSRX(6);
    	
    	// Lifter Controller Initialization
    	lifterFrontLeft = new TalonSRX(7);
    	lifterBackLeft = new TalonSRX(8);
    	lifterFrontRight = new TalonSRX(9);
    	lifterBackRight = new TalonSRX(10);
    	
//    	lifterFrontLeft.follow(lifterBackLeft);
//    	lifterFrontRight.follow(lifterBackLeft);
//    	lifterBackRight.follow(lifterBackLeft);
//    	
//    	lifterFrontRight.setInverted(true);
//    	lifterBackRight.setInverted(true);
    	
    	lifterFrontLeft.follow(lifterFrontRight);
    	lifterBackLeft.follow(lifterFrontRight);
    	lifterBackRight.follow(lifterFrontRight);
    	
    	lifterFrontRight.setInverted(true);
    	lifterBackRight.setInverted(true);
    	
    	lifterFrontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    	lifterFrontRight.configAllowableClosedloopError(0, 0, 10);
    	
    	lifterFrontRight.config_kF(0, 0.0, 10);
    	//lifterFrontRight.config_kP(0, LIFTER_kP, 10);
    	//lifterFrontRight.config_kI(0, LIFTER_kI, 10);
    	//lifterFrontRight.config_kD(0, LIFTER_kD, 10);
    	
    	pigeonTalon = new TalonSRX(11);
    	
    	pigeonTalon.configRemoteFeedbackFilter(lifterBackLeft.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw, 0, 10);
    	//pigeonTalon.configAllowableClosedloopError(0, 0, 10);
    	
    	// We better remember this TODO
//    	pigeonTalon.config_kP(0, 0.65, 10);
//    	pigeonTalon.config_kI(0, 0.004, 10);
//    	pigeonTalon.config_kD(0, 0.0, 10);
//    	pigeonTalon.config_IntegralZone(0, 300, 10);
    	
		motionProfileTalonA = new TalonSRX(12);

		config = new TalonSRXConfiguration(); // factory default settings
		/* _config the master specific settings */
        config.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    	config.neutralDeadband = kNeutralDeadband; /* 0.1 % super small for best low-speed control */
        config.slot0.kF = kGains_MotProf.kF;
        config.slot0.kP = kGains_MotProf.kP;
        config.slot0.kI = kGains_MotProf.kI;
        config.slot0.kD = kGains_MotProf.kD;
        config.slot0.integralZone = (int) kGains_MotProf.kIzone;
        config.slot0.closedLoopPeakOutput = kGains_MotProf.kPeakOutput;
        // config.slot0.allowableClosedloopError // left default for this example
        // config.slot0.maxIntegralAccumulator; // left default for this example
        // config.slot0.closedLoopPeriod; // left default for this example
        motionProfileTalonA.configAllSettings(config);

        /* pick the sensor phase and desired direction */
        motionProfileTalonA.setSensorPhase(true);
		motionProfileTalonA.setInverted(false);
    	
    	//motionProfileTalon.setInverted(true);
    	
		//motionProfileTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		motionProfileTalonB = new TalonSRX(13);

		motionProfileTalonB.configAllSettings(config);

        /* pick the sensor phase and desired direction */
        motionProfileTalonB.setSensorPhase(true);
		motionProfileTalonB.setInverted(false);
    	
    	// Servo Initilization
    	liftLocker = new Servo(0);
    	barRelease = new Servo(1);
    	
    	
//    	rearLeft.follow(frontLeft);
//    	
//    	frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
//    	frontLeft.setInverted(false);
//    	frontLeft.configAllowableClosedloopError(0, 0, 10); // Is this needed if it's 0?
//    	
//    	frontLeft.config_kF(0, 0.0, 10);
//    	frontLeft.config_kP(0, 0.35, 10);
//    	frontLeft.config_kI(0, 0.0003, 10);
//    	frontLeft.config_kD(0, 2.0, 10);
    	
//    	frontLeft.configClosedloopRamp(1.0, 0);
    	
    	// Limit Switch Initialization
//    	topLimitSwitch = new DigitalInput(0); // Not a thing.
    	bottomLimitSwitch = new DigitalInput(0);
    	
    	leftDriveEncoder = new Encoder(1, 2, false, Encoder.EncodingType.k4X);
    	rightDriveEncoder = new Encoder(3, 4, true, Encoder.EncodingType.k4X);
    	
    	RobotMap.rightDriveEncoder.setDistancePerPulse((6.0 * Math.PI) / 256.0);
    	RobotMap.leftDriveEncoder.setDistancePerPulse((6.0 * Math.PI) / 256.0);
    	
    	// Potentiometer Initilization
    	tiltPot = new AnalogPotentiometer(1); // Change to 3 for Robot 1.
    	
		// Navx Gyro Initialization
    	//gyro = new AHRS(SPI.Port.kMXP);
    	
    	pidgey = new PigeonIMU(lifterBackLeft);
    	
    	// NetworkTable Initialization
    	networkTable = NetworkTable.getTable("SmartDashboard");
    	Dashboard.setNetworkTable(networkTable);
    	
    	// Sendable Chooser Initialization and Setup
    	chooser = new SendableChooser();
    	
    	chooser.addDefault("Do Nothing", "Do Nothing");
    	chooser.addObject("Cross Baseline", "Cross Baseline");
    	chooser.addObject("Left", "Left");
    	chooser.addObject("Middle", "Middle");
    	chooser.addObject("Right", "Right");
    	
    	SmartDashboard.putData("Auto mode", chooser);
    	
    	
    	colorChooser = new SendableChooser();
    	
    	colorChooser.addDefault("Red", "Red");
    	colorChooser.addObject("Blue", "Blue");
    	
    	SmartDashboard.putData("Color", colorChooser);
    	
    	
    	scaleSwitchChooser = new SendableChooser();
    	
    	scaleSwitchChooser.addDefault("Switch", "Switch");
    	scaleSwitchChooser.addObject("Scale", "Scale");

    	SmartDashboard.putData("Scale or Switch", scaleSwitchChooser);
    	
//    	switchChooser = new SendableChooser();
//    	
//    	switchChooser.addDefault("No Switch", "No Switch");
//    	switchChooser.addObject("Left Switch", "Left Switch");
//    	switchChooser.addObject("Right Switch", "Right Switch");
    	
    	// Differential Drive Initialization
    	differentialDrive = new DifferentialDrive(leftTop, rightTop);  // (frontLeft, rearLeft, frontRight, rearRight);
    	differentialDrive.setSafetyEnabled(false);
    	
    	//// Sub-System Initilization ////
    	// Drive Assembly Initialization
    	drive = new DriveAssembly();
    	
    	// Intake Assembly Initialization
    	intake = new IntakeAssembly();
    	
    	// Lifter Assembly Initialization
    	lifter = new LifterAssembly();
    	
    	// Autonomous Initialization
    	auto = new Autonomous();
    	
    	// Heading Initialization
    	//heading = new Heading();
    	
    	// Pigeon Heading Initialization
    	pigeonHeading = new PigeonHeading();
    	
    	// Move Distance Initialization
		moveDistance = new MoveDistance();
		
		// Testing Profiles Initialization
		testProflies = new TestProfiles();
    	
	}
	
}

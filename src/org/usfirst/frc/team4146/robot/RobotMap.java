package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	
	public static Robot ROBOT;
	
	//////Constants//////
	
	//////Variables//////
	public static boolean manualMode = false;
	
	//////Declarations //////
	public static Controller driveController;
	
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
	
	// Limit Switch Declarations
	public static DigitalInput topLimitSwitch;
	public static DigitalInput bottomLimitSwitch;
	
	// Navax Gyro Declaration
	public static AHRS gyro;
	
	// Network Table Declaration
	public static NetworkTable networkTable;
	
	// Sendable Chooser Declaration
	public static SendableChooser chooser; //Sendable chooser allows us to choose the autonomous from smartdashboard
	
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
	
	
	public static void init() { // This is to be called in robitInit and instantiates stuff.
		
		// Controllers Initialization
    	driveController = new Controller(0);
    	
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
    	
    	// Intake Controllers Initialization
    	intakeTilt = new TalonSRX(5);
    	intakeRoller = new TalonSRX(6);
    	
    	// Lifter Controller Initialization
    	lifterFrontLeft = new TalonSRX(7);
    	lifterBackLeft = new TalonSRX(8);
    	lifterFrontRight = new TalonSRX(9);
    	lifterBackRight = new TalonSRX(10);
    	
    	lifterFrontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    	
    	lifterBackLeft.follow(lifterFrontLeft);
    	lifterFrontRight.follow(lifterFrontLeft);
    	lifterBackRight.follow(lifterFrontLeft);
    	
    	lifterFrontRight.setInverted(true);
    	lifterBackRight.setInverted(true);
    	
    	
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
    	topLimitSwitch = new DigitalInput(0);
    	bottomLimitSwitch = new DigitalInput(1);
    	
		// Navx Gyro Initialization
    	gyro = new AHRS(SPI.Port.kMXP);
    	
    	// NetworkTable Initialization
    	networkTable = NetworkTable.getTable("SmartDashboard");
    	Dashboard.setNetworkTable(networkTable);
    	
    	// Sendable Chooser Initialization and Setup
    	chooser = new SendableChooser();
    	
    	chooser.addObject("Cross Baseline", "Cross Baseline");
    	chooser.addObject("Left", "Left");
    	chooser.addObject("Middle", "Middle");
    	chooser.addObject("Right", "Right");
    	chooser.addDefault("Do Nothing", "Do Nothing");
    	
    	SmartDashboard.putData("Auto mode", chooser);
    	
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
	}
}

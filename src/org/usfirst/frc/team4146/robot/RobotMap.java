package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	
	public static Robot ROBOT;
	
	//////Declarations //////
	public static Controller driveController;
	
	// Motor Controller Declaration
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX rearLeft;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX rearRight;
	
	// Navax Gyro Declaration
	public static AHRS gyro;
	
	// Network Table Declaration
	public static NetworkTable networkTable;
	
	// Sendable Chooser Declaration
	public static SendableChooser chooser; //Sendable chooser allows us to choose the autonomous from smartdashboard
	
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
    	frontLeft = new WPI_TalonSRX(0);
    	frontRight = new WPI_TalonSRX(1);
    	
    	// Sub Drive Controllers
    	rearLeft = new WPI_TalonSRX(2);
    	rearRight = new WPI_TalonSRX(3);
    	
    	// Make motors on same side follow.
    	rearLeft.follow(frontLeft);
    	rearRight.follow(frontRight);
    	
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
    	
    	//frontLeft.configClosedloopRamp(1.0, 0);
    	
    	
		// Navx Gyro Initialization
    	gyro = new AHRS(SPI.Port.kMXP);
    	
    	// NetworkTable Initialization
    	networkTable = NetworkTable.getTable("SmartDashboard");
    	Dashboard.setNetworkTable(networkTable);
    	
    	// Sendable Chooser Initialization and Setup
    	chooser = new SendableChooser();
    	
    	chooser.addDefault("Do Nothing", "Do Nothing");
    	
    	SmartDashboard.putData("Auto mode", chooser);
    	
    	// Game Data Initialization
    	String gameData; // Should fix this...
    	
    	// Differential Drive Initialization
    	differentialDrive = new DifferentialDrive(frontLeft, frontRight);  // (frontLeft, rearLeft, frontRight, rearRight);
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

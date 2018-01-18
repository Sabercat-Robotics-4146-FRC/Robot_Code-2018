package org.usfirst.frc.team4146.robot;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	
	public static Robot ROBOT;
	
	//////Declarations //////
	public static Controller driveController;
	
	// Motor Controller Declaration
	public static TalonSRX frontLeft;
	public static TalonSRX rearLeft;
	public static TalonSRX frontRight;
	public static TalonSRX rearRight;
	
	// Navax Gyro Declaration
	public static AHRS gyro;
	
	// Network Table Declaration
	public static NetworkTable networkTable;
	
	// Sendable Chooser Declaration
	public static SendableChooser chooser; //Sendable chooser allows us to choose the autonomous from smartdashboard
	
	////Sub-System Declarations////
	// Robot Drive Declaration
	public static RobotDrive drive;
	
	// Autonomous Declaration
	public static Autonomous auto;
	
	
	public static void init() { // This is to be called in robitInit and instantiates stuff.
		
		// Controllers Initialization
    	driveController = new Controller(0);
    	
    	// Motor Controllers Initialization
    	frontLeft = new TalonSRX(0);
    	rearLeft = new TalonSRX(1);
    	frontRight = new TalonSRX(2);
    	rearRight = new TalonSRX(3);
    	
    	frontLeft.saf
    	rearLeft.setSafetyEnabled(false);
    	frontRight.setSafetyEnabled(false);
    	rearRight.setSafetyEnabled(false);
    	
		// Navx Gyro Initialization
    	gyro = new AHRS(SPI.Port.kMXP);
    	
    	// NetworkTable Initialization
    	networkTable = NetworkTable.getTable("SmartDashboard");
    	Dashboard.setNetworkTable(networkTable);
    	
    	// Sendable Chooser Initialization and Setup
    	chooser = new SendableChooser();
    	
    	chooser.addDefault("Do Nothing", "Do Nothing");
    	
    	SmartDashboard.putData("Auto mode", chooser);
    	
    	//// Sub-System Initilization ////
    	// RobotDrive Initialization
    	drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    	drive.setSafetyEnabled(false);
    	
    	// Autonomous Initiilization
    	auto = new Autonomous();
	}
}

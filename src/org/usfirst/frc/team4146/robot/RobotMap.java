package org.usfirst.frc.team4146.robot;

import com.ctre.CANTalon;
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
	
	// Moter Controller Declaration
	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon frontRight;
	public static CANTalon rearRight;
	
	// Navax Gyro Declaration
	public static AHRS gyro;
	
	// Network Table Declaration
	public static NetworkTable networkTable;
	
	// Robot Drive Declaration
	public static RobotDrive drive;
	
	// Sendable Chooser Declaration
	public static SendableChooser chooser; //Sendable chooser allows us to choose the autonomous from smartdashboard
	
	// Autonomous Declaration
	public static Autonomous auto;
	
	
	public static void init() { // This is to be called in robitInit and instantiates stuff.
		
		// Controllers Initialization
    	driveController = new Controller(0);
    	
    	// Motor Controllers Initialization
    	frontLeft = new CANTalon(0);
    	rearLeft = new CANTalon(1);
    	frontRight = new CANTalon(2);
    	rearRight = new CANTalon(3);
    	
		// Navx Gyro Initialization
    	gyro = new AHRS(SPI.Port.kMXP);
    	
    	// NetworkTable Initialization
    	networkTable = NetworkTable.getTable("SmartDashboard");
    	Dashboard.setNetworkTable(networkTable);
    	
    	// RobotDrive Initialization
    	drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    	drive.setSafetyEnabled(false);
    	
    	// Autonomous Initiilization
    	auto = new Autonomous();
    	
    	// Sendable Chooser Initialization and Setup
    	chooser = new SendableChooser();
    	
    	chooser.addDefault("Do Nothing", "Do Nothing");
    	
    	SmartDashboard.putData("Auto mode", chooser);
	}
}

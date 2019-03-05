package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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

public class RobotMap {
	
	public static Robot ROBOT;
	
	public static Controller driveController;
	
	// Motor Controller Declarations
	public static WPI_TalonSRX leftTop;
	public static WPI_TalonSRX leftBottom;
	public static WPI_TalonSRX rightTop;
	public static WPI_TalonSRX rightBottom;
	
	// Diferential Drive Declaraion
	public static DifferentialDrive differentialDrive;
	
	////Sub-System Declarations////
	// Drive Assembly Declaration
	public static DriveAssembly drive;
	
	public static void init() { // This is to be called in robitInit and instantiates stuff.
	
		// Controllers Initialization
    	driveController = new Controller(0);
    
    	// Motor Controllers Initialization
    	// Main Drive Controllers
    	leftTop = new WPI_TalonSRX(17);
    	rightTop = new WPI_TalonSRX(18);
    	
    	// Sub Drive Controllers
    	leftBottom = new WPI_TalonSRX(19);
    	rightBottom = new WPI_TalonSRX(20);
    	
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
    	
    	// Differential Drive Initialization
    	differentialDrive = new DifferentialDrive(leftTop, rightTop);  // (frontLeft, rearLeft, frontRight, rearRight);
    	differentialDrive.setSafetyEnabled(false);
    	
    	//// Sub-System Initilization ////
    	// Drive Assembly Initialization
    	drive = new DriveAssembly();
    	
	}
	
}

//package org.usfirst.frc.team4146.robot.PID;
//
//import org.usfirst.frc.team4146.robot.Dashboard;
//import org.usfirst.frc.team4146.robot.RobotMap;
//
//public class HeadingPID extends PID {
//	static double NAXV_UPDATE_RATE = 4; //Htz
//	static double HEADING_BREAK_TOLERANCE = 3; //degrees
//
//	public HeadingPID(){
//		super(NAXV_UPDATE_RATE, HEADING_BREAK_TOLERANCE);
//		setPID(RobotMap.HEADING_kP, RobotMap.HEADING_kI, RobotMap.HEADING_kD);
//		//this.setIRange(10);
//	}
////	public double getValue() {
////		//Dashboard.send("Angle Heading", RobotMap.gyro.getAngle());
////		Dashboard.send("Heading Error", this.getError());
////		Dashboard.send("Heading Get", this.get());
////		//return RobotMap.gyro.getAngle(); // ROBOT MAP THIS PLEASE. ADD UNWRAP CODE
////	}
////	public double computeError() {
////		return findSmallestAngle(getValue(), setpoint);
////	}
//	// a = where you are
//	// b = where you want to go
//	public static double findSmallestAngle( double a, double b ) {
//		return -1 * ( ( a - b ) - 360.0 * Math.round( ( a - b ) / 360.0 ) );
//	}
//}

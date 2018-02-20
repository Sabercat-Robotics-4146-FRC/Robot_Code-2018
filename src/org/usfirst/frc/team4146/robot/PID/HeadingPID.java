package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;

public class HeadingPID extends PID {
	static double NAXV_UPDATE_RATE = 40; //Htz
	static double HEADING_BREAK_TOLERANCE = 1.5; //degrees
	static double kP = 0.02;
	static double kI = 0.0;
	static double kD = 0.0;


	public HeadingPID(){
		super(NAXV_UPDATE_RATE, HEADING_BREAK_TOLERANCE);
		setPID(kP, kI, kD);
	}
	public double getValue() {
		return RobotMap.gyro.getFusedHeading(); // ROBOT MAP THIS PLEASE. ADD UNWRAP CODE
	}
	public double computeError() {
		return findSmallestAngle(getValue(), setpoint);
	}
	// a = where you are
	// b = where you want to go
	public static double findSmallestAngle( double a, double b ) {
		return -1 * ( ( a - b ) - 360.0 * Math.round( ( a - b ) / 360.0 ) );
	}
}

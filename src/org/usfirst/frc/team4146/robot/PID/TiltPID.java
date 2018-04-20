package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;

public class TiltPID extends PID {
	
	 static double ENCODER_UPDATE_RATE = 200; //Htz
	 static double TILT_BREAK_TOLERANCE = 0.0001; //degrees
	 static double kP = 5;
	 static double kI = 0.0;
	 static double kD = 0.0;
	 
	 public TiltPID() {
		 super(ENCODER_UPDATE_RATE, TILT_BREAK_TOLERANCE);
		    setPID(kP, kI, kD);
		    setSetpoint(RobotMap.TILT_MID);
	 }
	 
	 public double getValue() {
		    return RobotMap.tiltPot.get();
	 }
	 
}

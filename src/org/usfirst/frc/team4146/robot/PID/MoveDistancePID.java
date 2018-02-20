package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;

public class MoveDistancePID extends PID {
	static double ENCODER_UPDATE_RATE = 40; //Htz
	static double MOVE_DISTANCE_BREAK_TOLERANCE = 1.5; //degrees
	static double kP = 0.004;
	static double kI = 0.0;
	static double kD = 0.0;
	  
	public MoveDistancePID(){
		super(ENCODER_UPDATE_RATE, MOVE_DISTANCE_BREAK_TOLERANCE);
		setPID(kP, kI, kD);
	}
	
	public double getValue() {
		return (RobotMap.leftDriveEncoder.getRaw() + RobotMap.leftDriveEncoder.getRaw()) / 2;
	}
}
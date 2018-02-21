package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;

public class MoveDistancePID extends PID {
	static double ENCODER_UPDATE_RATE = 200; //Htz
	static double MOVE_DISTANCE_BREAK_TOLERANCE = 0.5; //inches
	
	public MoveDistancePID(){
		super(ENCODER_UPDATE_RATE, MOVE_DISTANCE_BREAK_TOLERANCE);
		setPID(RobotMap.MOVE_kP, RobotMap.MOVE_kI, RobotMap.MOVE_kD);
	}
	
	public double getValue() {
		return (RobotMap.leftDriveEncoder.getDistance() + RobotMap.rightDriveEncoder.getDistance()) / 2;
		//return RobotMap.rightDriveEncoder.getDistance();
	}
}
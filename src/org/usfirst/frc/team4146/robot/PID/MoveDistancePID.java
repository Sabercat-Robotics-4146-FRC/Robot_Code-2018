package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;
import org.usfirst.frc.team4146.robot.Dashboard;
public class MoveDistancePID extends PID {
	static double ENCODER_UPDATE_RATE = 200; //Htz
	static double MOVE_DISTANCE_BREAK_TOLERANCE = 5; //inches
	
	public MoveDistancePID(){
		super(ENCODER_UPDATE_RATE, MOVE_DISTANCE_BREAK_TOLERANCE);
		setPID(RobotMap.MOVE_kP, RobotMap.MOVE_kI, RobotMap.MOVE_kD);
	}
	
	public double getValue() {
		Dashboard.send("encoder value dist left", RobotMap.leftDriveEncoder.getDistance());
		Dashboard.send("encoder value dist right", RobotMap.rightDriveEncoder.getDistance());
		return RobotMap.leftDriveEncoder.getDistance();
		//return RobotMap.rightDriveEncoder.getDistance();
	}
}
package org.usfirst.frc.team4146.robot.PID;

import org.usfirst.frc.team4146.robot.RobotMap;
import org.usfirst.frc.team4146.robot.Dashboard;
public class VisionPID extends PID{
	static double PIXY_UPDATE_RATE = 60; //Htz
	static double HEADING_BREAK_TOLERANCE = 1.5; //degrees

	public VisionPID(){
		super(PIXY_UPDATE_RATE, HEADING_BREAK_TOLERANCE);
		setPID(RobotMap.HEADING_kP, RobotMap.HEADING_kI, RobotMap.HEADING_kD);
	}
	public double getValue() {
		return Dashboard.getDouble("gear_x") != 0 ? Dashboard.getDouble("gear_x") -160 : 0.0; // ROBOT MAP THIS PLEASE. ADD UNWRAP CODE
	}
}

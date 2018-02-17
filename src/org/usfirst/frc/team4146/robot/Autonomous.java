package org.usfirst.frc.team4146.robot;

public class Autonomous {

	////Autonomous Constants////
	// Robot size
	public static final double ROBOT_LENGTH = 39.0;
	public static final double HALF_ROBOT_LENGTH = ROBOT_LENGTH/2;
	public static final double ROBOT_WIDTH = 33.5;
	public static final double HALF_ROBOT_WIDTH = ROBOT_WIDTH/2;
	
	// Switch size
	public static final double SWITCH_WIDTH = 0.0;
	public static final double SWITCH_LENGTH = 0.0;
	public static final double HALF_SWITCH_LENGTH = SWITCH_LENGTH/2;
	public static final double SWITCH_PLATE_WIDTH = 0.0;
	public static final double HALF_SWITCH_PLATE_WIDTH = SWITCH_PLATE_WIDTH/2;
	
	// Power Cube
	public static final double POWER_CUBE = 13.0;
	
	///Red Auto Constants///
	public static final double R_LEFT_WALL_TO_SWITCH = 0.0;
	public static final double R_RIGHT_WALL_TO_SWITCH = 0.0;
	public static final double R_LEFT_PORTAL_WIDTH = 0.0;
	public static final double R_RIGHT_PORTAL_WIDTH = 0.0;
	public static final double R_AS_TO_SWITCH = 0.0;
	public static final double R_EZ_WIDTH = 0.0;
	public static final double R_PC_ZONE_LENGTH = 0.0;
	public static final double R_RIGHT_WALL_TO_EZ = 0.0;
	public static final double R_AS_TO_PLATFORM = 0.0;
	
	public static final double R_AS_TO_AUTO_LINE = 10.0;
	
	///Blue Auto Constants///
	public static final double B_LEFT_WALL_TO_SWITCH = 0.0;
	public static final double B_RIGHT_WALL_TO_SWITCH = 0.0;
	public static final double B_LEFT_PORTAL_WIDTH = 0.0;
	public static final double B_RIGHT_PORTAL_WIDTH = 0.0;
	public static final double B_AS_TO_SWITCH = 0.0;
	public static final double B_EZ_WIDTH = 0.0;
	public static final double B_PC_ZONE_LENGTH = 0.0;
	public static final double B_RIGHT_WALL_TO_EZ = 0.0;
	public static final double B_AS_TO_PLATFORM = 0.0;
	
	public static final double B_AS_TO_AUTO_LINE = 10.0;
	
	// <color><Robot Position><Switch Position>Autonomous
	
	///////// BLUE AUTOS!! /////////
	public void blueLeftLeftAutonomous() {
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH, 0.0);
		turn(90, 0.0);
		move(B_LEFT_WALL_TO_SWITCH - (B_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH), 0.0);
	}

	public void blueLeftRightAutonomous() {
		// 3 is "wiggle room"
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) , 0.0);
		turn(90, 0.0);
		move((B_LEFT_WALL_TO_SWITCH - B_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH), 0.0);
		turn(90, 0.0);
		// Check to see if having the Power Cube length messes up the robot
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH, 0.0); 
	}

	public void blueMiddleLeftAutonomous() {
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH, 0.0);
		turn(-90, 0.0);
		move(B_EZ_WIDTH + HALF_ROBOT_WIDTH, 0.0);
		turn(90, 0.0);
		move(B_PC_ZONE_LENGTH, 0.0);
	}

	public void blueMiddleRightAutonomous() {
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH, 0.0);
		turn(90, 0.0);
		move(B_RIGHT_WALL_TO_EZ - B_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH, 0.0);
		turn(-90, 0.0);
		move(B_PC_ZONE_LENGTH, 0.0);
	}

	public void blueRightRightAutonomous() {
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH, 0.0);
		turn(-90, 0.0);
		move(B_RIGHT_WALL_TO_SWITCH - (B_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH), 0.0);
	}

	public void blueRightLeftAutonomous() {
		// 3 is "wiggle room"
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) , 0.0);
		turn(-90, 0.0);
		move((B_RIGHT_WALL_TO_SWITCH - B_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH), 0.0);
		turn(-90, 0.0);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH, 0.0);
		
	}

	public void blueCrossBaseline() {
		move(B_AS_TO_AUTO_LINE, 0);
	}
	
	///////// RED AUTOS!! /////////
	public void redLeftLeftAutonomous() {
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH, 0.0);
		turn(90, 0.0);
		move(R_LEFT_WALL_TO_SWITCH - (R_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH), 0.0);
	}

	public void redLeftRightAutonomous() {
		// 3 is "wiggle room"
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) , 0.0);
		turn(90, 0.0);
		move((R_LEFT_WALL_TO_SWITCH - R_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH), 0.0);
		turn(90, 0.0);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH, 0.0); 
	}

	public void redMiddleLeftAutonomous() {
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH, 0.0);
		turn(-90, 0.0);
		move(R_EZ_WIDTH + HALF_ROBOT_WIDTH, 0.0);
		turn(90, 0.0);
		move(R_PC_ZONE_LENGTH, 0.0);
	}

	public void redMiddleRightAutonomous() {
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH, 0.0);
		turn(90, 0.0);
		move(R_RIGHT_WALL_TO_EZ - R_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH, 0.0);
		turn(-90, 0.0);
		move(R_PC_ZONE_LENGTH, 0.0);
	}

	public void redRightRightAutonomous() {
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH, 0.0);
		turn(-90, 0.0);
		move(R_RIGHT_WALL_TO_SWITCH - (R_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH), 0.0);
	}

	public void redRightLeftAutonomous() {
		// 3 is "wiggle room"
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) , 0.0);
		turn(-90, 0.0);
		move((R_RIGHT_WALL_TO_SWITCH - R_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH), 0.0);
		turn(-90, 0.0);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH, 0.0);
	}

	public void redCrossBaseline() {
		move(R_AS_TO_AUTO_LINE, 0); 
	}
	
	////Methods for Autonomous////
	public void move(double distance, double timeout) {
		
	}
	
	public void turn(double angle, double timeout) {
		
	}

}

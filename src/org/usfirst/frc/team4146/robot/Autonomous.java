package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.IntakeAssembly.IntakeTiltEnum;
import org.usfirst.frc.team4146.robot.LifterAssembly.LifterModeEnum;
import org.usfirst.frc.team4146.robot.LifterAssembly.LifterPositionEnum;

public class Autonomous {

	////Autonomous Constants////
	// Robot size
	public static final double ROBOT_LENGTH = 39.0;
	public static final double HALF_ROBOT_LENGTH = ROBOT_LENGTH/2;
	public static final double ROBOT_WIDTH = 33.5;
	public static final double HALF_ROBOT_WIDTH = ROBOT_WIDTH/2;
	
	// Switch size
	public static final double SWITCH_WIDTH = 12.0; // these need to be measured and changed
	public static final double SWITCH_LENGTH = 4.0; // change
	public static final double HALF_SWITCH_LENGTH = SWITCH_LENGTH/2;
	public static final double SWITCH_PLATE_WIDTH = 3.0; // change
	public static final double HALF_SWITCH_PLATE_WIDTH = SWITCH_PLATE_WIDTH/2;
	
	// Power Cube
	public static final double POWER_CUBE = 13.0;
	
	///Red Auto Constants///
	public static final double R_LEFT_WALL_TO_SWITCH = 7.5;
	public static final double R_RIGHT_WALL_TO_SWITCH = 7.5;
	public static final double R_LEFT_PORTAL_WIDTH = 3.0;
	public static final double R_RIGHT_PORTAL_WIDTH = 3.0;
	public static final double R_AS_TO_SWITCH = 14.0;
	public static final double R_EZ_WIDTH = 4.0;
	public static final double R_PC_ZONE_LENGTH = 3.6;
	public static final double R_RIGHT_WALL_TO_EZ = 15.0;
	public static final double R_AS_TO_PLATFORM = 18.0;
	
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
		changeLiftState(LifterPositionEnum.SWITCH);
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90);
		// TODO changeIntakeTiltState(IntakeTiltEnum.TILTED_-);
		move(B_LEFT_WALL_TO_SWITCH - (B_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
	}

	public void blueLeftRightAutonomous() {
		// 3 is "wiggle room"
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90);
		move((B_LEFT_WALL_TO_SWITCH - B_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH)
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90);
		// Check to see if having the Power Cube length messes up the robot
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH); 
	}

	public void blueMiddleLeftAutonomous() {
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90);
		move(B_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90);
		move(B_PC_ZONE_LENGTH);
	}

	public void blueMiddleRightAutonomous() {
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90);
		move(B_RIGHT_WALL_TO_EZ - B_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90);
		move(B_PC_ZONE_LENGTH);
	}

	public void blueRightRightAutonomous() {
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90);
		move(B_RIGHT_WALL_TO_SWITCH - (B_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
	}

	public void blueRightLeftAutonomous() {
		// 3 is "wiggle room"
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90);
		move((B_RIGHT_WALL_TO_SWITCH - B_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH);
		
	}

	public void blueCrossBaseline() {
		move(B_AS_TO_AUTO_LINE);
	}
	
	///////// RED AUTOS!! /////////
	public void redLeftLeftAutonomous() {
		move(1);
		//move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90);
		move(R_LEFT_WALL_TO_SWITCH - (R_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
	}

	public void redLeftRightAutonomous() {
		// 3 is "wiggle room"
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90);
		move((R_LEFT_WALL_TO_SWITCH - R_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH); 
	}

	public void redMiddleLeftAutonomous() {
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90);
		move(R_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90);
		move(R_PC_ZONE_LENGTH);
	}

	public void redMiddleRightAutonomous() {
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90);
		move(R_RIGHT_WALL_TO_EZ - R_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90);
		move(R_PC_ZONE_LENGTH);
	}

	public void redRightRightAutonomous() {
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90);
		move(R_RIGHT_WALL_TO_SWITCH - (R_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
	}

	public void redRightLeftAutonomous() {
		// 3 is "wiggle room"
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90);
		move((R_RIGHT_WALL_TO_SWITCH - R_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH);
	}

	public void redCrossBaseline() {
		move(R_AS_TO_AUTO_LINE); 
	}
	
	////Methods for Autonomous////
	public void move(double distance) {
		RobotMap.moveDistance.moveToAbsolute(distance); // Distance is in inches.
	}
	
	public void turn(double angle) {
		RobotMap.heading.turnToRelative(angle);
	}
	
	public void changeLiftState(LifterPositionEnum state){
		RobotMap.lifter.lifterMode = LifterModeEnum.AUTO_LIFT;
		RobotMap.lifter.autoLifterPosition = state;
		RobotMap.lifter.update(0.0); // 0.0 cuz we don't actually use dt
	}
	
	public void changeIntakeTiltState(IntakeTiltEnum state){
		RobotMap.intake.intakeTiltPosition = state;
		//RobotMap.intake.update(0.0); // Save for updates in heading and moveDisance to do since we have no dt here and we need it.....
	}

}

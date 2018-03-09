package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.IntakeAssembly.IntakeTiltEnum;
import org.usfirst.frc.team4146.robot.LifterAssembly.LifterModeEnum;
import org.usfirst.frc.team4146.robot.LifterAssembly.LifterPositionEnum;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Autonomous {

	////Autonomous Constants//// THESE NUMBERS ARE IN INCHES!!!!!!
	// Robot size
	public static final double ROBOT_LENGTH = 39.0;
	public static final double HALF_ROBOT_LENGTH = ROBOT_LENGTH/2;
	public static final double ROBOT_WIDTH = 33.5;
	public static final double HALF_ROBOT_WIDTH = ROBOT_WIDTH/2;
	
	// Switch size
	public static final double SWITCH_WIDTH = 155; // these need to be measured and changed
	public static final double SWITCH_LENGTH = 56; // change
	public static final double HALF_SWITCH_LENGTH = SWITCH_LENGTH/2;
	public static final double SWITCH_PLATE_WIDTH = 41.75; // change
	public static final double HALF_SWITCH_PLATE_WIDTH = SWITCH_PLATE_WIDTH/2;
	
	// Power Cube
	public static final double POWER_CUBE = 13.0;
	
	///Red Auto Constants///
	public static final double R_LEFT_WALL_TO_SWITCH = 86;
	public static final double R_RIGHT_WALL_TO_SWITCH = 84.5;
	public static final double R_LEFT_PORTAL_WIDTH = 29.4;
	public static final double R_RIGHT_PORTAL_WIDTH = 29;
	public static final double R_AS_TO_SWITCH = 140.5;
	public static final double R_EZ_WIDTH = 48.2;
	public static final double R_PC_ZONE_LENGTH = 42.25;
	public static final double R_RIGHT_WALL_TO_EZ = 174.5;
	public static final double R_AS_TO_PLATFORM = 258.5;
	
	public static final double R_AS_TO_AUTO_LINE = 122.25;
	
	///Blue Auto Constants///
	public static final double B_LEFT_WALL_TO_SWITCH = 85;
	public static final double B_RIGHT_WALL_TO_SWITCH = 85;
	public static final double B_LEFT_PORTAL_WIDTH = 27.4;
	public static final double B_RIGHT_PORTAL_WIDTH = 30.75;
	public static final double B_AS_TO_SWITCH = 140;
	public static final double B_EZ_WIDTH = 48.4;
	public static final double B_PC_ZONE_LENGTH = 42;
	public static final double B_RIGHT_WALL_TO_EZ = 175;
	public static final double B_AS_TO_PLATFORM = 261;
	
	public static final double B_AS_TO_AUTO_LINE = 122;
	
	// <color><Robot Position><Switch Position>Autonomous
	
	///////// BLUE AUTOS!! /////////
	public void blueLeftLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90);
		move(B_LEFT_WALL_TO_SWITCH - (B_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void blueLeftRightAutonomous() {
		// 3 inches is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90);
		move((B_LEFT_WALL_TO_SWITCH - B_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH)
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90);
		// Check to see if having the Power Cube length messes up the robot
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH); 
		rollOut();
	}

	public void blueMiddleLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90);
		move(B_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90);
		move(B_PC_ZONE_LENGTH);
		rollOut();
	}

	public void blueMiddleRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90);
		move(B_RIGHT_WALL_TO_EZ - B_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90);
		move(B_PC_ZONE_LENGTH);
		rollOut();
	}

	public void blueRightRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90);
		move(B_RIGHT_WALL_TO_SWITCH - (B_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void blueRightLeftAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90);
		move((B_RIGHT_WALL_TO_SWITCH - B_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH);
		rollOut();
	}

	public void blueCrossBaseline() {
		move(B_AS_TO_AUTO_LINE);
	}
	
	///////// RED AUTOS!! /////////
	public void redLeftLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90);
		move(R_LEFT_WALL_TO_SWITCH - (R_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void redLeftRightAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90);
		move((R_LEFT_WALL_TO_SWITCH - R_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH); 
		rollOut();
	}

	public void redMiddleLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90);
		move(R_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90);
		move(R_PC_ZONE_LENGTH);
		rollOut();
	}

	public void redMiddleRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90);
		move(R_RIGHT_WALL_TO_EZ - R_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90);
		move(R_PC_ZONE_LENGTH);
		rollOut();
	}

	public void redRightRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90);
		move(R_RIGHT_WALL_TO_SWITCH - (R_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void redRightLeftAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90);
		move((R_RIGHT_WALL_TO_SWITCH - R_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90);
		move((POWER_CUBE + 3 + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) - HALF_ROBOT_LENGTH);
		rollOut();
	}

	public void redCrossBaseline() {
		//move(10.188);
		move(8 * 12);
		//move(R_AS_TO_AUTO_LINE);
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
	
	public void waitTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Exception in waitTime....");
		}
	}
	
	public void rollOut() {
		RobotMap.intakeRoller.set(ControlMode.PercentOutput, -1.0);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println("Exception in RollOut....");
		}
		RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.0);
	}
	
	public static double inchesToFeet(double inches){
		return inches/12;
	}

}

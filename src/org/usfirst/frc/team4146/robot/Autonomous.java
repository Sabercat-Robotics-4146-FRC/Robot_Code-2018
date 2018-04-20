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
	public static final double SWITCH_WIDTH = 154.5; // these need to be measured and changed
	public static final double SWITCH_LENGTH = 56; // change
	public static final double HALF_SWITCH_LENGTH = SWITCH_LENGTH/2;
	public static final double SWITCH_PLATE_WIDTH = 41.5; // change
	public static final double HALF_SWITCH_PLATE_WIDTH = SWITCH_PLATE_WIDTH/2;
	
	// Power Cube
	public static final double POWER_CUBE = 13.0;
	public static final double WIGGLE_ROOM = 8.0;
	
	///Red Auto Constants///
	public static final double R_LEFT_WALL_TO_SWITCH = 84.75;
	public static final double R_RIGHT_WALL_TO_SWITCH = 84.5;
	public static final double R_LEFT_PORTAL_WIDTH = 30.0;
	public static final double R_RIGHT_PORTAL_WIDTH = 29.5;
	public static final double R_AS_TO_SWITCH = 140.0;
	public static final double R_EZ_WIDTH = 48.25;
	public static final double R_PC_ZONE_LENGTH = 41.75;
	public static final double R_RIGHT_WALL_TO_EZ = 174.5;
	public static final double R_AS_TO_PLATFORM = 258.5;
	public static final double R_AS_TO_HALF_FIELD = 324.0;
	public static final double R_LEFT_WALL_TO_SCALE_PLATE = 71.0;
	public static final double R_RIGHT_WALL_TO_SCALE_PLATE = 72.0;
	public static final double R_SWITCH_TO_SCALE_PLATE = 104.0;
	
	public static final double R_AS_TO_AUTO_LINE = 122.0;
	
	///Blue Auto Constants///
	public static final double B_LEFT_WALL_TO_SWITCH = 85.0;
	public static final double B_RIGHT_WALL_TO_SWITCH = 84.5;
	public static final double B_LEFT_PORTAL_WIDTH = 29.0;
	public static final double B_RIGHT_PORTAL_WIDTH = 29.25;
	public static final double B_AS_TO_SWITCH = 140.0;
	public static final double B_EZ_WIDTH = 48.0;
	public static final double B_PC_ZONE_LENGTH = 42.25;
	public static final double B_RIGHT_WALL_TO_EZ = 174.5;
	public static final double B_AS_TO_PLATFORM = 261;
	public static final double B_AS_TO_HALF_FIELD = 323.0;
	public static final double B_LEFT_WALL_TO_SCALE_PLATE = 72.0;
	public static final double B_RIGHT_WALL_TO_SCALE_PLATE = 71.0;
	public static final double B_SWITCH_TO_SCALE_PLATE = 104.0;
	
	public static final double B_AS_TO_AUTO_LINE = 122.0;
	
	public static final double MOVE_BACK_AFTER_MID_AUTO = -24.0;
	public static final long TIME_FOR_LIFT_TO_RAISE = 2500;
	public static final double HALF_NULL_TERRITORY = 3.0;
	public static final double MOVE_TO_SCALE = 29.0;
	
	public static Timer autoTimer = new Timer();
	
	// <color><Robot Position><Switch Position>Autonomous
	
	///////// BLUE AUTOS!! /////////
	
	// Switch Autos
	public void blueLeftLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(B_LEFT_WALL_TO_SWITCH - (B_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void blueLeftRightAutonomous() {
		// 3 inches is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90, RobotMap.HEADING_TIME_OUT);
		move((B_LEFT_WALL_TO_SWITCH - B_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH)
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90, RobotMap.HEADING_TIME_OUT);
		// Check to see if having the Power Cube length messes up the robot
		move((POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) /*- HALF_ROBOT_LENGTH*/); 
		rollOut();
	}

	public void blueMiddleLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(B_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(B_PC_ZONE_LENGTH);
		rollOut();
		move(MOVE_BACK_AFTER_MID_AUTO); // this constant is negative
		changeIntakeTiltState(IntakeTiltEnum.TILTED_DOWN);
		tiltStateUpdater();
		//TODO
	}

	public void blueMiddleRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH - B_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(B_RIGHT_WALL_TO_EZ - B_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(B_PC_ZONE_LENGTH);
		rollOut();
		move(MOVE_BACK_AFTER_MID_AUTO); // this constant is negative
		changeIntakeTiltState(IntakeTiltEnum.TILTED_DOWN);
		tiltStateUpdater();
	}

	public void blueRightRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(B_RIGHT_WALL_TO_SWITCH - (B_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void blueRightLeftAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move((B_RIGHT_WALL_TO_SWITCH - B_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move((POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH)/* - HALF_ROBOT_LENGTH*/);
		rollOut();
	}

	// Scale Autos
	public void blueRightRightNewScaleAutonomous() {
		move(B_AS_TO_HALF_FIELD - HALF_NULL_TERRITORY - HALF_ROBOT_LENGTH);
		//changeLiftState(LifterPositionEnum.SCALE);
		waitTime(TIME_FOR_LIFT_TO_RAISE);
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		turn(-35, RobotMap.HEADING_TIME_OUT);
		//move(MOVE_TO_SCALE - HALF_ROBOT_LENGTH);
		rollOut();
	}
	
	public void blueLeftLeftNewScaleAutonomous() {
		move(B_AS_TO_HALF_FIELD - HALF_NULL_TERRITORY - HALF_ROBOT_LENGTH);
		changeLiftState(LifterPositionEnum.SCALE);
		waitTime(TIME_FOR_LIFT_TO_RAISE);
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		turn(35, RobotMap.HEADING_TIME_OUT);
		//move(MOVE_TO_SCALE - HALF_ROBOT_LENGTH);
		rollOut();
	}
	
//	public void blueLeftLeftScaleAutonomous() {
//		move(B_AS_TO_HALF_FIELD - HALF_ROBOT_LENGTH);
//		changeLiftState(LifterPositionEnum.SCALE);
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(B_LEFT_WALL_TO_SCALE_PLATE - B_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH); // Do I need to subtract half robot width????
//		rollOut();
//	}
//	
//	public void blueLeftRightScaleAutonomous() {
//		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(B_LEFT_WALL_TO_SWITCH - (B_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH) + SWITCH_WIDTH);
//		changeLiftState(LifterPositionEnum.SCALE);                                             // Where do we want to put these???
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(B_SWITCH_TO_SCALE_PLATE - (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		rollOut();
//	}
//	
//	public void blueRightRightScaleAutonomous() {
//		move(B_AS_TO_HALF_FIELD - HALF_ROBOT_LENGTH);
//		changeLiftState(LifterPositionEnum.SCALE);
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(B_RIGHT_WALL_TO_SCALE_PLATE - B_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH); // Do I need to subtract half robot width????
//		rollOut();
//	}
//	
//	public void blueRightLeftScaleAutonomous() {
//		move(B_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(B_RIGHT_WALL_TO_SWITCH - (B_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH) + SWITCH_WIDTH);
//		changeLiftState(LifterPositionEnum.SCALE);                                             // Where do we want to put these???
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(B_SWITCH_TO_SCALE_PLATE - (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		rollOut();
//	}
	
	public void blueCrossBaseline() {
		move(B_AS_TO_AUTO_LINE);
	}
	
	///////// RED AUTOS!! /////////
	
	//Switch Autos
	public void redLeftLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(R_LEFT_WALL_TO_SWITCH - (R_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void redLeftRightAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(90, RobotMap.HEADING_TIME_OUT);
		move((R_LEFT_WALL_TO_SWITCH - R_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(90, RobotMap.HEADING_TIME_OUT);
		move((POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) /*- HALF_ROBOT_LENGTH*/); 
		//rollOut();
	}

	public void redMiddleLeftAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(R_EZ_WIDTH + HALF_ROBOT_WIDTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(R_PC_ZONE_LENGTH);
		rollOut();
		move(MOVE_BACK_AFTER_MID_AUTO); // this constant is negative
		changeIntakeTiltState(IntakeTiltEnum.TILTED_DOWN);
		tiltStateUpdater();
	}

	public void redMiddleRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH - R_PC_ZONE_LENGTH - ROBOT_LENGTH);
		turn(90, RobotMap.HEADING_TIME_OUT);
		move(R_RIGHT_WALL_TO_EZ - R_RIGHT_WALL_TO_SWITCH - HALF_ROBOT_WIDTH - HALF_SWITCH_PLATE_WIDTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(R_PC_ZONE_LENGTH);
		rollOut();
		move(MOVE_BACK_AFTER_MID_AUTO); // this constant is negative
		changeIntakeTiltState(IntakeTiltEnum.TILTED_DOWN);
		tiltStateUpdater();
	}

	public void redRightRightAutonomous() {
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + HALF_SWITCH_LENGTH - HALF_ROBOT_LENGTH);
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move(R_RIGHT_WALL_TO_SWITCH - (R_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH + HALF_ROBOT_LENGTH));
		rollOut();
	}

	public void redRightLeftAutonomous() {
		// 3 is "wiggle room"
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move((R_RIGHT_WALL_TO_SWITCH - R_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH) 
				+ (SWITCH_WIDTH - HALF_SWITCH_PLATE_WIDTH));
		turn(-90, RobotMap.HEADING_TIME_OUT);
		move((POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH) /*- HALF_ROBOT_LENGTH*/);
		rollOut();
	}
	
	// Scale Autos
	public void redRightRightNewScaleAutonomous() {
		move(R_AS_TO_HALF_FIELD - HALF_NULL_TERRITORY - HALF_ROBOT_LENGTH);
		changeLiftState(LifterPositionEnum.SCALE);
		waitTime(TIME_FOR_LIFT_TO_RAISE);
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		turn(-35, 1.75);
		//move(MOVE_TO_SCALE - HALF_ROBOT_LENGTH);
		rollOut();
	}
	
	public void redLeftLeftNewScaleAutonomous() {
		//move(215 - HALF_ROBOT_LENGTH); // subtracted 8
		move(R_AS_TO_HALF_FIELD - HALF_NULL_TERRITORY - HALF_ROBOT_LENGTH);
		changeLiftState(LifterPositionEnum.SCALE);
		waitTime(TIME_FOR_LIFT_TO_RAISE);
		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
		turn(35, 1.75);
		//move(MOVE_TO_SCALE - HALF_ROBOT_LENGTH);
		rollOut();
	}
	
//	public void redLeftLeftScaleAutonomous() {
//		move(R_AS_TO_HALF_FIELD - HALF_ROBOT_LENGTH);
//		changeLiftState(LifterPositionEnum.SCALE);
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(R_LEFT_WALL_TO_SCALE_PLATE - R_LEFT_PORTAL_WIDTH - HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH); // Do I need to subtract half robot width????
//		rollOut();
//	}
//	
//	public void redLeftRightScaleAutonomous() {
//		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(R_LEFT_WALL_TO_SWITCH - (R_LEFT_PORTAL_WIDTH + HALF_ROBOT_WIDTH) + SWITCH_WIDTH);
//		changeLiftState(LifterPositionEnum.SCALE);                                             // Where do we want to put these???
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(R_SWITCH_TO_SCALE_PLATE - (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		rollOut();
//	}
//	
//	public void redRightRightScaleAutonomous() {
//		move(R_AS_TO_HALF_FIELD - HALF_ROBOT_LENGTH);
//		changeLiftState(LifterPositionEnum.SCALE);
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(R_RIGHT_WALL_TO_SCALE_PLATE - R_RIGHT_PORTAL_WIDTH - HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH); // Do I need to subtract half robot width????
//		rollOut();
//	}
//	
//	public void redRightLeftScaleAutonomous() {
//		move(R_AS_TO_SWITCH + SWITCH_LENGTH + (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		turn(-90, RobotMap.HEADING_TIME_OUT);
//		move(R_RIGHT_WALL_TO_SWITCH - (R_RIGHT_PORTAL_WIDTH + HALF_ROBOT_WIDTH) + SWITCH_WIDTH);
//		changeLiftState(LifterPositionEnum.SCALE);                                             // Where do we want to put these???
//		changeIntakeTiltState(IntakeTiltEnum.TILTED_MID);
//		turn(90, RobotMap.HEADING_TIME_OUT);
//		move(R_SWITCH_TO_SCALE_PLATE - (POWER_CUBE + WIGGLE_ROOM + HALF_ROBOT_WIDTH - HALF_ROBOT_LENGTH));
//		rollOut();
//	}

	public void redCrossBaseline() {
		//move(10.188);
		//move(3 * 12);
		move(R_AS_TO_AUTO_LINE);
		//turn(90, RobotMap.HEADING_TIME_OUT);
	}
	
	////Methods for Autonomous////
	public void move(double distance) {
		RobotMap.moveDistance.moveToAbsolute(distance); // Distance is in inches.
	}
	
	public void turn(double angle, double timeOut) {
		//RobotMap.heading.turnToRelative(angle, timeOut);
		RobotMap.pigeonHeading.reletiveTurn(-angle, (int)timeOut);
	}
	
	public void changeLiftState(LifterPositionEnum state){
		RobotMap.lifter.lifterMode = LifterModeEnum.AUTO_LIFT;
		RobotMap.lifter.autoLifterPosition = state;
		
		RobotMap.lifterFrontRight.set(ControlMode.Position, RobotMap.lifterFrontRight.getSensorCollection().getPulseWidthPosition() + (130920-2496));
	}
	
	public void changeIntakeTiltState(IntakeTiltEnum state){
		RobotMap.intake.intakeTiltPosition = state;
		//RobotMap.intake.update(0.0); // Save for updates in heading and moveDisance to do since we have no dt here and we need it.....
	}
	
	public void tiltStateUpdater() {
		autoTimer.reset();
		double dt;
		double timeSinceStart = 0;
		
		while((timeSinceStart < 5) && (RobotMap.ROBOT.isAutonomous())) {
			waitTime(5);
			autoTimer.update();
			dt = autoTimer.getDT();
			timeSinceStart += dt;
			
			RobotMap.intake.update(dt);
			
		}
	}
	
	public void waitTime(long time){ // this is in mili seconds
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Exception in waitTime....");
		}
	}
	
	public void rollOut() {
		if (RobotMap.ROBOT.isAutonomous()) {
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 1.0);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.println("Exception in RollOut....");
			}
			RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0.0);
		}
	}
	
	public static double inchesToFeet(double inches){
		return inches/12;
	}

}

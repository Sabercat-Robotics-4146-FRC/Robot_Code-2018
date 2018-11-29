package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.LifterAssembly.LifterModeEnum;

/*
 * ---Things to change for competition Robot---
 * - Change the potentiometer port to 3  - done
 * - Test pot values
 * - Uncomment pid setting stuff for pids
 * 
 */

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public Robot() {
		RobotMap.ROBOT = this;
	}
	
	/**
	 * Runs once when the robot is powered on and called when you are basically guaranteed that
	 * all WPILIBJ stuff will work.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); // Instantiates and Declares things to be used from RobotMap.
		RobotMap.gyro.reset();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 * This autonomous method mostly deals with choosing the auto to execute.
	 */
	@Override
	public void autonomous() {
		
		//RobotMap.heading.tareHeadingRelative();
		
		//RobotMap.auto.move(12*12);
		
		String switchScaleSelected = (String)RobotMap.scaleSwitchChooser.getSelected();
		System.out.println("Switch Or Switch Data: " + switchScaleSelected);
		
		String colorSelected = (String)RobotMap.colorChooser.getSelected();
		System.out.println("Color Data: " + colorSelected);
		
		String autoSelected = (String)RobotMap.chooser.getSelected();
		System.out.println("Base Chooser Data: " + autoSelected);
		
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Game Data: " + RobotMap.gameData);
		
		boolean isSwitchLeft = RobotMap.gameData.charAt(0) == 'L';
		boolean isScaleLeft = RobotMap.gameData.charAt(1) == 'L';
		
		// This is for checking to see if FMS is sending a "corrupted" String.
		/*
		for(int i = 0; i < 3; i++){
			if(!RobotMap.gameData.substring(i, i + 1).equals("L") || !RobotMap.gameData.substring(i, i + 1).equals("R")){00
				autoSelected = "Cross Baseline";
				break;
			}
		}
		*/
		
		// Reseting gyro
		RobotMap.pidgey.setYaw(0, 10); // Do we want this here for competition?
		
		// <color><Robot Position><Switch Position>Autonomous
		
		if(colorSelected.equals("Red")){
			if (switchScaleSelected.equals("Switch")) {
				switch(autoSelected){
					case "Left":
						if(isSwitchLeft) {
							System.out.println("Running Red Left Left Auto.");
							RobotMap.auto.redLeftLeftAutonomous();
						} else {
							System.out.println("Running Red Left Right Auto.");
							RobotMap.auto.redLeftRightAutonomous();
						}
						break;
					case "Middle":
						if(isSwitchLeft) {
							System.out.println("Running Red Middle Left Auto.");
							RobotMap.auto.redMiddleLeftAutonomous();
						} else {
							System.out.println("Running Red Middle Right Auto.");
							RobotMap.auto.redMiddleRightAutonomous();
						}
						break;
					case "Right":
						if(isSwitchLeft) {
							System.out.println("Running Red Right Left Auto.");
							RobotMap.auto.redRightLeftAutonomous();
						} else {
							System.out.println("Running Red Right Right Auto.");
							RobotMap.auto.redRightRightAutonomous();
						}
						break;
					case "Cross Baseline":
						System.out.println("Running Red Cross Baseline Auto.");
						RobotMap.auto.redCrossBaseline();
						break;
					case "Do Nothing":
						System.out.println("Doing nothing.... :'(");
						break;
				}
			} else if (switchScaleSelected.equals("Scale")) {
				switch(autoSelected) {
					case "Left":
						if (isScaleLeft) {
							System.out.println("Running Red Left Left New Scale Auto");
							RobotMap.auto.redLeftLeftNewScaleAutonomous();
						} else {
							System.out.println("Running Red Cross Baseline Auto.");
							RobotMap.auto.redCrossBaseline();
//							if(isSwitchLeft) {
//								System.out.println("Running Red Left Left Auto.");
//								RobotMap.auto.redLeftLeftAutonomous();
//							} else {
//								System.out.println("Running Red Left Right Auto.");
//								RobotMap.auto.redLeftRightAutonomous();
//							}
						}
						break;
					case "Right":
						if (!isScaleLeft) {
							System.out.println("Running Red Right Right Scale Auto");
							RobotMap.auto.redRightRightNewScaleAutonomous();
						} else {
							System.out.println("Running Red Cross Baseline Auto.");
							RobotMap.auto.redCrossBaseline();
//							if(isSwitchLeft) {
//								System.out.println("Running Red Right Left Auto.");
//								RobotMap.auto.redRightLeftAutonomous();
//							} else {
//								System.out.println("Running Red Right Right Auto.");
//								RobotMap.auto.redRightRightAutonomous();
//							}
						}
						break;
				}
			}
		} else if(colorSelected.equals("Blue")){
			if (switchScaleSelected.equals("Switch")) {
				switch(autoSelected){
					case "Left":
						if(isSwitchLeft) {
							System.out.println("Running Blue Left Left Auto.");
							RobotMap.auto.blueLeftLeftAutonomous();
						} else {
							System.out.println("Running Blue Left Right Auto.");
							RobotMap.auto.blueLeftRightAutonomous();
						}
						break;
					case "Middle":
						if(isSwitchLeft) {
							System.out.println("Running Blue Middle Left Auto.");
							RobotMap.auto.blueMiddleLeftAutonomous();
						} else {
							System.out.println("Running Blue Middle Right Auto.");
							RobotMap.auto.blueMiddleRightAutonomous();
						}
						break;
					case "Right":
						if(isSwitchLeft) {
							//System.out.println("Running modified auto");
							//RobotMap.auto.blueCrossBaseline();
							System.out.println("Running Blue Right Left Auto.");
							RobotMap.auto.blueRightLeftAutonomous();
							//TODO
						} else {
							System.out.println("Running Blue Right Right Auto.");
							RobotMap.auto.blueRightRightAutonomous();
						}
						break;
					case "Cross Baseline":
						System.out.println("Running Blue Cross Baseline Auto.");
						RobotMap.auto.blueCrossBaseline();
						break;
					case "Do Nothing":
						System.out.println("Doing nothing.... :'(");
						break;
				}
			} else if (switchScaleSelected.equals("Scale")) {
				switch(autoSelected) {
				case "Left":
					if (isScaleLeft) {
						System.out.println("Running Blue Left Left Scale Auto");
						RobotMap.auto.blueLeftLeftNewScaleAutonomous();
					} else {
						System.out.println("Running Blue Cross Baseline Auto.");
						RobotMap.auto.blueCrossBaseline();
//						if(isSwitchLeft) {
//							System.out.println("Running Blue Left Left Auto.");
//							RobotMap.auto.blueLeftLeftAutonomous();
//						} else {
//							System.out.println("Running Blue Left Right Auto.");
//							RobotMap.auto.blueLeftRightAutonomous();
//						}
					}
					break;
				case "Right":
					if (!isScaleLeft) {
						System.out.println("Running Blue Right Right Scale Auto");
						RobotMap.auto.blueRightRightNewScaleAutonomous();
					} else {
						System.out.println("Running Blue Cross Baseline Auto.");
						RobotMap.auto.blueCrossBaseline();
//						if(isSwitchLeft) {
//							System.out.println("Running Blue Right Left Auto.");
//							RobotMap.auto.blueRightLeftAutonomous();
//						} else {
//							System.out.println("Running Blue Right Right Auto.");
//							RobotMap.auto.blueRightRightAutonomous();
//						}
					}
					break;
				}
			}
		}
	}

	/**
	 * This function is run once each time the robot enters operator control mode
	 */
	@Override
	public void operatorControl() {
		Timer timer = new Timer();
		double dt = 0.0;
		
		RobotMap.drive.setPeakOutput(1.0);
		
		// This is for testing the pigeon. Take out before competition 
//		RobotMap.pidgey.setYaw(0, 10);
//		RobotMap.pigeonTalon.set(ControlMode.Position, 0);
		
//		boolean flag = false;
		
		while (isOperatorControl() && isEnabled()) {
			dt = timer.getDT();
			//RobotMap.drive.update(dt);
			//RobotMap.intake.update(dt);
			//RobotMap.lifter.update(dt);
			RobotMap.motionProfiling.update();

			// This is for testing the pigeon. Take out before competition 
//			if(RobotMap.driveController.getButtonBack() && !flag) {
//				flag = true;
//				//RobotMap.pidgey.setYaw(RobotMap.PIGEON_TICK_CONVERSION/*5762*/, 10); // 5762 in set yaw gets 90 degrees
//				
//				ph.reletiveTurn(90, 100000000);
//			}
//			if(!RobotMap.driveController.getButtonBack()) {
//				flag = false;
//			}
//			
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				
//			}
			
			timer.update();
		}
	}

	/**
	 * This function is run once each time the robot enters test mode
	 */
	@Override
	public void test() {
		System.out.println("Teeeeest Mode");
		
//		final AnalogPotentiometer pot;
//		pot = new AnalogPotentiometer(1);
//		int i = 0;
//		while (true) {
//			if (i >=1000) {
//				System.out.println("pot value" + pot.get());
//				i = 0;
//			}
//			i++;
//		}
		
		
		
		//RobotMap.pidgey.enterCalibrationMode(CalibrationMode.Temperature, 0);//enterCalibrationMode(CalibrationMode.Temperature);
		
		/*Timer timer = new Timer();
		double dt = 0.0;
		int i = 0;
		
		double[] pidgeyData = new double[3];
		while (isTest() && isEnabled()) {
			dt = timer.getDT();
			i++;
			
			if(i >= 100){
				RobotMap.pidgey.getYawPitchRoll(pidgeyData);
				Dashboard.send("Pidgey Yaw", pidgeyData[2]);
				System.out.println("Yaw: " + pidgeyData[0]);
				
				i = 0;
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
			}
			timer.update();
			
		}*/
		
	}
}

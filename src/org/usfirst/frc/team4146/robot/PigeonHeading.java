package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class PigeonHeading {
	double[] pidgeyData = new double[3];
	double theoreticalAngle = 0; // It's where you want to go absolutely.
	Timer timer = new Timer();
	
	public PigeonHeading(){
		
	}
	
	public void update(double dt) {
		RobotMap.pidgey.getYawPitchRoll(pidgeyData);
		
		// Dashboard Sends
		Dashboard.send("Pidgey Yaw", pidgeyData[0]);
		Dashboard.send("PID Out?", RobotMap.pigeonTalon.getMotorOutputPercent());
		Dashboard.send("Pig error", RobotMap.pigeonTalon.getClosedLoopError(0));
		Dashboard.send("Pig Sensor Pos", RobotMap.pigeonTalon.getSelectedSensorPosition(0));
		Dashboard.send("Pig PW", RobotMap.pigeonTalon.getSensorCollection().getPulseWidthPosition());
	}
	
	public void absoluteTurn(double angle, long seconds){
		double timeInTolerance = 0;
		double timeSinceStart = 0;
		double dt = 0;
		
		timer.reset();
		
		System.out.println("Setting motots to follow pigeon talon...");
		
		RobotMap.leftTop.follow(RobotMap.pigeonTalon);
		RobotMap.rightTop.follow(RobotMap.pigeonTalon);
		
		RobotMap.leftTop.setInverted(true);
		RobotMap.leftBottom.setInverted(true);
		RobotMap.rightTop.setInverted(true);
		RobotMap.rightBottom.setInverted(true);
		
		
		theoreticalAngle = angle;
		RobotMap.pigeonTalon.set(ControlMode.Position, angle);
		
		while(timeInTolerance < RobotMap.HEADING_TIME_IN_TOLERENCE && timeSinceStart < seconds && RobotMap.ROBOT.isEnabled()){
			dt = timer.getDT(); // Could put this in update....
			update(0); // dt not currently used in update method so 0.
			
			// Checks if still in tolerance and adds dt to timeInTolerance, else sets it to 0.
			if(Math.abs(RobotMap.pigeonTalon.getClosedLoopError(0)) <= RobotMap.HEADING_TOLERENCE){
				Dashboard.send("Turn in Tolerance", true);
				timeInTolerance += dt;
			} else {
				Dashboard.send("Turn in Tolerance", false);
				timeInTolerance = 0;
			}
			
			// Wait a bit to free up CPU.
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
			}
			
			// Adds to time since start for timeout.
			timeSinceStart += dt;
			
			timer.update();
		}
		
		System.out.println("Setting motots back...");
		
		RobotMap.leftTop.follow(RobotMap.leftTop);
		RobotMap.rightTop.follow(RobotMap.rightTop);
		
		RobotMap.leftTop.setInverted(false);
		RobotMap.leftBottom.setInverted(false);
		RobotMap.rightTop.setInverted(false);
		RobotMap.rightBottom.setInverted(false);
		
		System.out.println("Done turning in " + timeSinceStart + ".");
	}
	
	public void reletiveTurn(double angle, long seconds){
		theoreticalAngle += angle;
		absoluteTurn(theoreticalAngle, seconds);
	}

}

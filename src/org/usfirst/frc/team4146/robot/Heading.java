package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.PID.*;

public class Heading {
	public HeadingPID headingController;
	  double theoreticalAngle; // It's where you want to go absolutely.
	  
	  public Heading(){
	    theoreticalAngle = 0;
	    headingController = new HeadingPID();
	  }
	  
	  public void turnToAbsolute(double absoluteAngle) {
		  Timer autoHeadingTimer = new Timer();
		  double dt = 0.0;
		  
		  // Start the heading controller
		  headingController.setSetpoint(absoluteAngle);
		  headingController.flush();
		  
		  
		  while (headingController.getTimeInTolerance() < 1.0 && RobotMap.ROBOT.isAutonomous() && RobotMap.ROBOT.isEnabled()) {
			  dt = autoHeadingTimer.getDT();
			  
			  if(RobotMap.ROBOT.isAutonomous()){
				  RobotMap.intake.update(dt);
			  }

			  headingController.update(dt);
			  RobotMap.differentialDrive.arcadeDrive(0.0, headingController.get());
			  
			  Dashboard.send("Gyro", RobotMap.gyro.getFusedHeading());
			  
			  autoHeadingTimer.update();
		  }
		  System.out.println("Done Turning!");
	  }

	  public void turnToRelative(double relativeAngle) {
	    theoreticalAngle += relativeAngle;
	    turnToAbsolute(theoreticalAngle);
	  }

	  public void tareHeadingRelative() {
	    theoreticalAngle = RobotMap.gyro.getFusedHeading(); // r-map this please
	  }
}


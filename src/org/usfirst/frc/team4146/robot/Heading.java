package org.usfirst.frc.team4146.robot;

import org.usfirst.frc.team4146.robot.PID.*;

public class Heading {
	public HeadingPID headingController;
	  double theoreticalAngle; // It's where you want to go absolutely.
	  
	  public Heading(){
	    theoreticalAngle = 0;
	    headingController = new HeadingPID();
	  }
	  
	  public void turnToAbsolute(double absoluteAngle, double timeOut) {
		  Timer autoHeadingTimer = new Timer();
		  double dt = 0.0;
		  double timeAccumulator = 0;
		  // Start the heading controller
		  headingController.setSetpoint(absoluteAngle);
		  headingController.flush();
		  headingController.reset();
		  //RobotMap.gyro.reset();
		  
		  System.out.println("Gyro Setpoint:" + headingController.setpoint);
		  while (headingController.getTimeInTolerance() < 1.0 && timeAccumulator < timeOut && RobotMap.ROBOT.isAutonomous() && RobotMap.ROBOT.isEnabled()) {
			  dt = autoHeadingTimer.getDT();
			  timeAccumulator += dt;
			  
			  
			  RobotMap.intake.update(dt);

			  headingController.update(dt);
			  RobotMap.differentialDrive.arcadeDrive(0.0, MoveDistance.clamp(headingController.get(), 0.7));
			  
			  try {
				  Thread.sleep(5);
			  } catch (InterruptedException e) {
				  
			  }
			  
			  autoHeadingTimer.update();
		  }
		  System.out.println("Time: " + timeAccumulator);
		  System.out.println("Gyro Position:" + RobotMap.gyro.getAngle());
		  RobotMap.differentialDrive.arcadeDrive(0.0, 0.0);
		  System.out.println("Done Turning!");
	  }

	  public void turnToRelative(double relativeAngle, double timeOut) {
		  theoreticalAngle += relativeAngle;
		  turnToAbsolute(theoreticalAngle, timeOut);
	  }

	  public void tareHeadingRelative() {
		  theoreticalAngle = RobotMap.gyro.getFusedHeading(); // r-map this please
	  }
}


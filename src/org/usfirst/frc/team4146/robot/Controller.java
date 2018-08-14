package org.usfirst.frc.team4146.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The Controller class is a wrapper for the WPI Joystick class for the use of Xbox 360 Controllers (was Logitech Controllers)
 * 
 * @author GowanR
 * @author JacobE
 * @author EliA
 * @version 2.2.2
 * // (MAJOR.MINOR.PATCH)
 * 
 */
public class Controller {
	Joystick joy;
	
	// Constant Variables //
	
	// Define buttons
	public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;
    public static final int LEFT_STICK_PRESS = 9;
    public static final int RIGHT_STICK_PRESS = 10;
    
    // Define Axes
    public static final int LEFT_X_AXIS = 0;
    public static final int LEFT_Y_AXIS = 1;
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;
    public static final int RIGHT_X_AXIS = 4;
    public static final int RIGHT_Y_AXIS = 5;
    
    /**
     * Used by getDeadband functions. Values within the distance of this value from zero will be set to 0
     * 
     * @see #getDeadbandLeftXAxis()
     * @see #getDeadbandLeftYAxis()
     * @see #getDeadbandRightXAxis()
     * @see #getDeadbandRightYAxis()
     */
    public static final double CONTROLLER_DEADBAND  = 0.15;
    
	/**
	 * Constructor takes the controller number (0 or 1 with two controllers) and instantiates a joystick for that port
	 * 
	 * @param number integer to define which port a controller is using
	 */
	public Controller(int number) {
		joy = new Joystick(number);
	}
	
	/**
	 * Returns whether the "A" button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the "A" button is pressed
	 */
	public boolean getButtonA() {
		return joy.getRawButton(A_BUTTON);
	}
	
	/**
	 * Returns whether the "B" button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the "B" button is pressed
	 */
	public boolean getButtonB() {
		return joy.getRawButton(B_BUTTON);
	}
	
	/**
	 * Returns whether the "X" button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the "X" button is pressed
	 */
	public boolean getButtonX() {
		return joy.getRawButton(X_BUTTON);
	}
	
	/**
	 * Returns whether the "Y" button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the "Y" button is pressed
	 */
	public boolean getButtonY() {
		return joy.getRawButton(Y_BUTTON);
	}
	
	/**
	 * Returns whether the left bumper is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the left bumper button is pressed
	 */
	public boolean getLeftBumper() {
		return joy.getRawButton(LEFT_BUMPER) ;
	}
	
	/**
	 * Returns whether the right bumper is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the right bumper button is pressed
	 */
	public boolean getRightBumper() {
		return joy.getRawButton(RIGHT_BUMPER);
	}
	
	/**
	 * Returns whether the left trigger is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the left trigger button is pressed
	 */
	public boolean getLeftTriggerBool() {
		return joy.getRawAxis(LEFT_TRIGGER) > 0;
	}
	
	/**
	 * Returns whether the right trigger is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the right trigger button is pressed
	 */
	public boolean getRightTriggerBool() {
		return joy.getRawAxis(RIGHT_TRIGGER) > 0;
	}
	
	/**
	 * Returns the value at which the left trigger is being pushed on the controller.
	 * 
	 * @return a double value of the left trigger button
	 */
	public double getLeftTrigger() {
		return joy.getRawAxis(LEFT_TRIGGER);
	}
	
	/**
	 * Returns the value at which the right trigger is being pushed on the controller.
	 * 
	 * @return a double value of the right trigger button
	 */
	public double getRightTrigger() {
		return joy.getRawAxis(RIGHT_TRIGGER);
	}
	
	/**
	 * Returns whether the back button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the back button is pressed
	 */
	public boolean getButtonBack() {
		return joy.getRawButton(BACK_BUTTON);
	}
	
	/**
	 * Returns whether the start button is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the start button is pressed
	 */
	public boolean getButtonStart() {
		return joy.getRawButton(START_BUTTON);
	}
	
	/**
	 * Returns whether the left stick is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the left stick button is pressed
	 */
	public boolean getLeftStickPress() {
		return joy.getRawButton(LEFT_STICK_PRESS);
	}
	
	/**
	 * Returns whether the right stick is being pushed on the controller.
	 * 
	 * @return a boolean value of whether the right stick button is pressed
	 */
	public boolean getRightStickPress() {
		return joy.getRawButton(RIGHT_STICK_PRESS);
	}
	
	/**
	 * Returns the POV (Point of View) joystick value on the controller.
	 * 
	 * @return an int of degrees (ie: right is 90, top left is 315)(-1 when not pressed).
	 */
	public int getDPad() {
		return joy.getPOV(0);
	}
	
	/**
	 * Returns the POV value on the controller in a boolean.
	 * 
	 * @return a boolean true if DPad is pressed.
	 */
	public boolean getDPadBool() {
		return joy.getPOV(0) != -1;
	}
	
	//Non-Deadbanded Axes:
	
	/**
	 * Returns the value of the x axis of the left stick. (-1.0 to 1.0, 0.0 is centered)
	 * 
	 * @return a double of the x axis position for the left stick
	 */
	public double getLeftXAxis() {
		return joy.getRawAxis(LEFT_X_AXIS);
	}
	
	/**
	 * Returns the value of the y axis of the left stick. (-1.0 to 1.0, 0.0 is centered)
	 * Value is inverted to make it so forward is positive because the default is backwards is positive
	 * 
	 * @return a double of the y axis position for the left stick
	 */
	public double getLeftYAxis() {
		return -(joy.getRawAxis(LEFT_Y_AXIS));
	}
	
	/**
	 * Returns the value of the x axis of the right stick. (-1.0 to 1.0, 0.0 is centered)
	 * 
	 * @return a double of the x axis position for the right stick
	 */
	public double getRightXAxis() {
		return joy.getRawAxis(RIGHT_X_AXIS);
	}
	
	/**
	 * Returns the value of the y axis of the right stick. (-1.0 to 1.0, 0.0 is centered)
	 * Value is inverted to make it so forward is positive because the default is backwards is positive
	 * 
	 * @return a double of the y axis position for the right stick
	 */
	public double getRightYAxis() {
		return -(joy.getRawAxis(RIGHT_Y_AXIS));
	}
	
	//Deadbanded Axes:
	
	/**
	 * Returns the value of the x axis of the left stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
	 * CONTROLLER_DEADBAND is applied. Meaning -{@link #CONTROLLER_DEADBAND} to {@link #CONTROLLER_DEADBAND} values are set to 0
	 * 
	 * @return a double from the result of {@link #joystickDeadband(double)} for the x axis position of the left stick
	 */
	public double getDeadbandLeftXAxis() {
		return joystickDeadband(joy.getRawAxis(LEFT_X_AXIS));
	}
	
	/**
	 * Returns the value of the y axis of the left stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
	 * Value is inverted to make it so forward is positive because the default is backwards is positive.
	 * CONTROLLER_DEADBAND is applied. Meaning -{@link #CONTROLLER_DEADBAND} to {@link #CONTROLLER_DEADBAND} values are set to 0
	 * 
	 * @return a double from the result of {@link #joystickDeadband(double)} for the y axis position of the left stick
	 */
	public double getDeadbandLeftYAxis() {
		return joystickDeadband(-(joy.getRawAxis(LEFT_Y_AXIS)));
	}
	
	/**
	 * Returns the value of the x axis of the right stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
	 * CONTROLLER_DEADBAND is applied. Meaning -{@link #CONTROLLER_DEADBAND} to {@link #CONTROLLER_DEADBAND} values are set to 0
	 * 
	 * @return a double from the result of {@link #joystickDeadband(double)} for the x axis position of the right stick
	 */
	public double getDeadbandRightXAxis() {
		return joystickDeadband(joy.getRawAxis(RIGHT_X_AXIS));
	}
	
	/**
	 * Returns the value of the y axis of the right stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
	 * Value is inverted to make it so forward is positive because the default is backwards is positive.
	 * CONTROLLER_DEADBAND is applied. Meaning -{@link #CONTROLLER_DEADBAND} to {@link #CONTROLLER_DEADBAND} values are set to 0
	 * 
	 * @return a double from the result of {@link #joystickDeadband(double)} for the y axis position of the right stick
	 */
	public double getDeadbandRightYAxis() {
		return joystickDeadband(-(joy.getRawAxis(RIGHT_Y_AXIS)));
	}
	
	/**
	 * Takes the value of a joystick axis and returns it deadbanded. (-1.0 to 1.0, 0.0 is centered)
	 * If value is between -{@link #CONTROLLER_DEADBAND} and {@link #CONTROLLER_DEADBAND} then value is set to 0.0
	 * 
	 * @param joystickInput the value given to the function to be deadbanded
	 * @return the input deadbanded.
	 */
	private double joystickDeadband(double joystickInput) {
		if(Math.abs(joystickInput) < CONTROLLER_DEADBAND) { 
			return 0.0;
		} else {
			return joystickInput;
		}
	}
	
	// Rumble Code!!! //
	
	/**
	 * Used to set each rumble level for an individual side.
	 * 
	 * @param side must be either RumbleType.kLeftRumble or RumbleType.kRightRumble
	 * @param r is the strength of the rumble from 0 (being off) to 1 (being full power)
	 */
	public void rumble(RumbleType side, double r){
		joy.setRumble(side, r);
		joy.setRumble(side, r);
	}
	
	/**
	 * Used to set rumble level for both sides at once.
	 * 
	 * @param r is the strength of the rumble from 0 (being off) to 1 (being full power)
	 */
	public void rumble(double r){
		joy.setRumble(RumbleType.kLeftRumble, r);
		joy.setRumble(RumbleType.kRightRumble, r);
	}
	
	/**
	 * Used just in case we need the raw joystick.
	 *
	 * @return Joystick used for the controller
	 */
	public Joystick getJoystick() {
		return joy;
	}
	
}
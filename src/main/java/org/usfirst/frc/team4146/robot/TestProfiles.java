package org.usfirst.frc.team4146.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TestProfiles {

    MotionProfile motorAProfile;
    MotionProfile motorBProfile;

    enum TestingMotorsEnum {
        DO_NOTHING,
        MOTOR_A,
        MOTOR_B,
        BOTH_MOTORS
    }

    TestingMotorsEnum motorState = TestingMotorsEnum.DO_NOTHING;

    public TestProfiles() {
        motorAProfile = new MotionProfile(RobotMap.motionProfileTalonA, GeneratedProfiles.motorA, GeneratedProfiles.kNumPointsMotorA);
        motorBProfile = new MotionProfile(RobotMap.motionProfileTalonB, GeneratedProfiles.motorB, GeneratedProfiles.kNumPointsMotorB);
    }
    
    public void update() {

        if (RobotMap.driveController.getButtonA()) {
            motorState = TestingMotorsEnum.MOTOR_A;
        }
        if (RobotMap.driveController.getButtonB()) {
            motorState = TestingMotorsEnum.MOTOR_B;
        } 
        if (RobotMap.driveController.getButtonX()) {
            motorState = TestingMotorsEnum.BOTH_MOTORS;
        } 
        if(!RobotMap.driveController.getButtonA() && !RobotMap.driveController.getButtonB() && !RobotMap.driveController.getButtonX()) {
            motorState = TestingMotorsEnum.DO_NOTHING;
        }

        switch(motorState) {
            case DO_NOTHING:
                motorAProfile.reset();
                motorBProfile.reset();
                RobotMap.motionProfileTalonA.set(ControlMode.PercentOutput, RobotMap.driveController.getDeadbandLeftYAxis());
                RobotMap.motionProfileTalonB.set(ControlMode.PercentOutput, RobotMap.driveController.getDeadbandRightYAxis());
                break;

            case MOTOR_A:
                motorAProfile.start();
                motorAProfile.update();
                break;

            case MOTOR_B:
                motorBProfile.start();
                motorBProfile.update();
                break;

            case BOTH_MOTORS:
                motorAProfile.start();
                motorBProfile.start();
                motorAProfile.update();
                motorBProfile.update();
                break;
        }

    }

}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final double TIME_FOR_SHOOTER = 3;
  public static final double TIME_FOR_FEEDER = 2;
  public static final double TIME_FOR_DRIVE_TRAIN_COMMANDS = 6;

  public static class MotorSpeeds {
    // driveTrain
    public static final double DRIVE_TRAIN_SPEED = 0.35;// from -1 to 1

    // other mechanisms
    public static double INTAKE_SPEED = 0.5;
    public static double FEEDER_SPEED = 0.5;
    public static double SHOOTER_SPEED = 0.5;

  }

  public static class OperatorConstants {
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int BUTTON_BOARD_PORT = 1;
  }

  public static class ButtonBoardBindings {
    public static final int INTAKE_IN_BTN = 1;
    public static final int INTAKE_OUT_BTN = 2;
    public static final int FEEDER_IN_BTN = 3;
    public static final int FEEDER_OUT_BTN = 4;
    public static final int SHOOT_BTN = 5;
    public static final int STOP_ALL_BTN = 6;
    public static final int AUTO_INTAKE_BTN = 7;
    public static final int AUTO_SHOOT_BTN = 8;
    public static final int RUN_ALL_BTN = 10;
  }

  public static class DriveTrainConstants {
    // TODO: verify motor ids
    public static final int LEFT_MASTER_ID = 1;
    public static final int LEFT_FOLLWER_ID = 2;
    public static final int RIGHT_MASTER_ID =3;
    public static final int RIGHT_FOLLOWER_ID = 4;
  }

  public static class SubsystemMotors {
    public static final int INTAKE_MOTOR_ID = 30;
    public static final int FEEDER_MOTOR_ID = 25;

    public static final int SHOOTER_BOTTOM_MOTOR_ID = 22;
    public static final int SHOOTER_TOP_MOTOR_ID = 23;
  }

  public static class CompressorConstants {
    public static final int COMPRESSOR_PORT = 20;

    // DoubelSolenoid channels for DriveTrain
    public static final int FORWARD_CHANNEL = 0;
    public static final int REVERSE_CHANNEL = 1;
  }

  public static class LimelightConstants {
    public static final double HEIGHT_OF_TARGET_INCHES = 48;// approximately

    // limelight degree rotated from perfectly vertical?
    public static final double LIMELIGHT_MOUNT_ANGLE_DEG = 0;// TODO: verify
    public static final double LIMELIGHT_HEIGHT_INCHES = 13;// TODO: modify

    public static final double DISTANCE_TO_DRIVE = 114.2;// in inches
    
  }

  public static class RoborioPorts {
    public static final int FEEDER_LIMIT_SWITCH_PORT = 0;
    public static final int FEEDER_SENSOR_SWITCH_PORT = 1;
  }
}

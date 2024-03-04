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
  public static class OperatorConstants {
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int BUTTON_BOARD_PORT = 1;
  }

  public static class ButtonBoardBindings {
    public static final int INTAKE_IN_BTN = 0;
    public static final int INTAKE_OUT_BTN = 0;
    public static final int FEEDER_IN_BTN = 0;
    public static final int FEEDER_OUT_BTN = 0;
    public static final int SHOOT_BTN = 0;
    public static final int STOP_ALL_BTN = 0;
    public static final int AUTO_INTAKE_BTN = 0;
    public static final int AUTO_SHOOT_BTN = 0;
    
  }

  public static class DriveTrainConstants {
    // TODO: verify motor ids
    public static final int RIGHT_MASTER_ID = 0;
    public static final int LEFT_MASTER_ID = 2;
    public static final int RIGHT_FOLLOWER_ID = 1;
    public static final int LEFT_FOLLWER_ID = 3;

    // DoubelSolenoid channels
    public static final int FORWARD_CHANNEL = 0;
    public static final int REVERSE_CHANNEL = 1;
  }

  public static class SubsystemMotors {
    // TODO: change motor ids
    public static final int INTAKE_MOTOR_ID = 20;
    public static final int FEEDER_MOTOR_ID = 21;

    public static final int SHOOTER_BOTTOM_MOTOR_ID = 22;
    public static final int SHOOTER_TOP_MOTOR_ID = 23;
  }

  public static class RoborioPorts {
    public static final int FEEDER_LIMIT_SWITCH_PORT = 0;
  }
}

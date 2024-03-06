// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimelightConstants;

public class Limelight extends SubsystemBase {
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry ty = table.getEntry("ty");// vertical offset entry
  private NetworkTableEntry tv = table.getEntry("tv");


  // value of limelight table 'y' value(angle from center of cam to target)
  private double verticalOffsetValue = ty.getDouble(0.0);// default value of 0.0

  private double goalHeight = LimelightConstants.HEIGHT_OF_TARGET_INCHES;// in inches
  private double camHeight = LimelightConstants.LIMELIGHT_HEIGHT_INCHES;// in inches
  private double camAngle = LimelightConstants.LIMELIGHT_MOUNT_ANGLE_DEG;// in degrees

  private double angleToGoal = camAngle + verticalOffsetValue;
  private double angleToGoalRadians = angleToGoal * (Math.PI / 180);

  // distance calculation
  private double distanceFromTarget = (goalHeight - camHeight) / Math.tan(angleToGoalRadians);

  /** Creates a new Limelight. */
  public Limelight() {}

  public double getDistanceFromTarget(){
    return distanceFromTarget;
  }

  public boolean getValidTarget() {
    boolean validTarget = tv.getBoolean(false);
    return validTarget;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance From Target", distanceFromTarget);
    
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class DriveForward extends Command {
  private DriveTrain driveTrain;
  private Limelight limelight;
  private Timer timer = new Timer();

  private double startTime;
  private double distanceFromTarget;
  private boolean end = false;

  /** Creates a new DriveForward. */
  public DriveForward(DriveTrain driveTrain, Limelight limelight) {
    this.driveTrain = driveTrain;
    this.limelight = limelight;

    addRequirements(driveTrain, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.stop();
    timer.reset();
    startTime = timer.get();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(startTime < Constants.TIME_FOR_DRIVE_TRAIN_COMMANDS) {
      distanceFromTarget = limelight.getDistanceFromTarget();
      
      while(distanceFromTarget < LimelightConstants.DISTANCE_TO_DRIVE) {// change to distance checking
        driveTrain.driveForward(MotorSpeeds.DRIVE_TRAIN_SPEED);// may have to decrease speeds
        startTime = timer.get();
      }

      if (distanceFromTarget == LimelightConstants.DISTANCE_TO_DRIVE) {
        driveTrain.stopMotors();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}

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

public class DriveBackWards extends Command {
  private DriveTrain driveTrain;
  private Limelight limelight;

  private double distanceFromTarget;
  private boolean end = false;

  private Timer timer = new Timer();
  private double startTime;
  
  /** Creates a new DriveBackWards. */
  public DriveBackWards(DriveTrain driveTrain, Limelight limelight) {
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
    startTime = timer.get();// time in seconds
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (startTime < Constants.TIME_FOR_DRIVE_TRAIN_COMMANDS) {// safety mechanism to stop driveTrain if it seems to surpass certain time limit
      distanceFromTarget = limelight.getDistanceFromTarget();   
      
      while (distanceFromTarget < LimelightConstants.DISTANCE_TO_DRIVE){
        driveTrain.driveBackWard(MotorSpeeds.DRIVE_TRAIN_SPEED);
      }
      if (distanceFromTarget >= LimelightConstants.DISTANCE_TO_DRIVE) {
        driveTrain.stopMotors();
        end = true;
      }
    }

    if (startTime >= Constants.TIME_FOR_DRIVE_TRAIN_COMMANDS) {
      driveTrain.stopMotors();
      end = true;
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

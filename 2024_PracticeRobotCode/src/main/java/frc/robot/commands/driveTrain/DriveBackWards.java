// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.subsystems.DriveTrain;

public class DriveBackwards extends Command {
  private DriveTrain driveTrain;

  /** Creates a new DriveBackwards. */
  public DriveBackwards(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.driveBackWards(MotorSpeeds.AUTO_DRIVE_TRAIN_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

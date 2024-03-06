// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrain.distanceBased;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

/**
 * stops motors of driveTrain after ending or being interrupted
 */
public class DriveToSpeaker extends Command {
  private DriveTrain driveTrain;
  private Limelight limelight;

  private double distanceFromSpeaker;
  private boolean end = false;

  /** Creates a new DriveForward. */
  public DriveToSpeaker(DriveTrain driveTrain, Limelight limelight) {
    this.driveTrain = driveTrain;
    this.limelight = limelight;

    addRequirements(driveTrain, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (limelight.getValidTarget() == true) {
      distanceFromSpeaker = limelight.getDistanceFromTarget();
      
      if(distanceFromSpeaker < LimelightConstants.DISTANCE_TO_DRIVE_FORWARD_AUTO) {// change to distance checking
        driveTrain.driveForward(MotorSpeeds.AUTO_DRIVE_TRAIN_SPEED);// may have to decrease speeds
      }else if(distanceFromSpeaker >= LimelightConstants.DISTANCE_TO_DRIVE_FORWARD_AUTO){
        driveTrain.stopMotors();
        end = true;
      }
    }else {
      driveTrain.stopMotors();
      end = true;// nay cause problems in auto(crashing into speaker)
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}

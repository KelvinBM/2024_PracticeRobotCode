// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.StopAll;
import frc.robot.commands.combined.IntakeToFeeder;
import frc.robot.commands.driveTrain.distanceBased.DriveAwayFromSpeaker;
import frc.robot.commands.driveTrain.distanceBased.DriveToSpeaker;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

/**
 * Temporarily for two piece auto
 */
public class AutoScoringUsingDistances extends SequentialCommandGroup {
  private Climber climber;// just a placeholder, may cause problems with command

  /** Creates a new AutoScoring. */
  public AutoScoringUsingDistances(DriveTrain driveTrain, Limelight limelight, Shooter shooter, Feeder feeder, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoShoot(shooter, feeder),
      new WaitCommand(0),
      new ParallelCommandGroup(// to drive away and pick up note
        new DriveAwayFromSpeaker(driveTrain, limelight),
        new IntakeToFeeder(intake, feeder)
      ),
      new WaitCommand(0),
      new DriveToSpeaker(driveTrain, limelight),
      new AutoShoot(shooter, feeder),
      new WaitCommand(0),
      new StopAll(intake, feeder, shooter, climber)
    );
  }
}

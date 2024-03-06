// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.StopAll;
import frc.robot.commands.driveTrain.DriveBackwards;
import frc.robot.commands.driveTrain.DriveForward;
import frc.robot.commands.feeder.RunFeeder;
// import frc.robot.commands.feeder.FeedWithTimer;
// import frc.robot.commands.feeder.RunFeeder;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.ShootWithTimer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoScoringUsingTimers extends SequentialCommandGroup {
  /** Creates a new AutoScoringUsingTimers. */
  public AutoScoringUsingTimers(DriveTrain driveTrain, Shooter shooter, Feeder feeder, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootWithTimer(shooter, feeder),
      new WaitCommand(0),
      // new FeedWithTimer(feeder),
      new ParallelCommandGroup(// to drive backwards and pickup at the same time
        new DriveBackwards(driveTrain), 
        new ParallelCommandGroup(
          new RunIntake(intake),
          new RunFeeder(feeder)
        ).until(() -> feeder.getFeederSwitchStatus() == false)
      ).withTimeout(4),
      new WaitCommand(0),
      new DriveForward(driveTrain),
      new AutoShootWithWait(shooter, feeder),
      new WaitCommand(0),
      new StopAll(intake, feeder, shooter)
    );
  }
}
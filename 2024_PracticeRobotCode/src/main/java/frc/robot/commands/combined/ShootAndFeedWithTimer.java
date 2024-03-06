// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.combined;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

/**
 * Ends but does not stop the shooter and feeder motors after ending
 */
public class ShootAndFeedWithTimer extends Command {
  private Shooter shooter;
  private Feeder feeder;
  private Timer timer = new Timer();

  private double startTime;
  private boolean end = false;
  
  /** Creates a new ShootWithTimer. */
  public ShootAndFeedWithTimer(Shooter shooter, Feeder feeder) {
    this.shooter = shooter;
    this.feeder = feeder;

    addRequirements(shooter, feeder);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.stop();
    timer.reset();
    startTime = timer.get();// returns time in seconds
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    startTime = timer.get();// can also use Timer.getFPGATimestamp

    if (startTime <= Constants.TIME_UNTIL_FEEDING_SHOOTER) {
      shooter.runShooter();
    } else if (startTime >= Constants.TIME_UNTIL_FEEDING_SHOOTER) {
      feeder.runFeeder();
    }

    if (startTime >= Constants.TIME_IN_SECS_TO_STOP) {
      feeder.stopFeeder();
      shooter.stopShooter();
      end = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    shooter.stopShooter();
    feeder.stopFeeder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.feeder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;

/**
 * Does not stop the feeder's motor, use one of the stop commands in order to stop the motor
 */
public class FeedWithTimer extends Command {
  private Feeder feeder;
  private Timer timer =  new Timer();

  private double startTime;
  private boolean end = false;

  /** Creates a new FeedWithTimer. */
  public FeedWithTimer(Feeder feeder) {
    this.feeder = feeder;

    addRequirements(feeder);
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
    startTime = timer.get();// returns time in seconds

    if (startTime <= Constants.TIME_FOR_FEEDER) {
      feeder.runFeeder();
    } else if (startTime >= Constants.TIME_FOR_FEEDER) {
      // feeder.stopFeeder();
      end = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    // feeder.stopFeeder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}

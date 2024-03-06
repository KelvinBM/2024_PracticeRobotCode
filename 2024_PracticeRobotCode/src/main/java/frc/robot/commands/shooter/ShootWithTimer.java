// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

/**
 *  Does not stop the shooter's motor
 */
public class ShootWithTimer extends Command {
  private Shooter shooter;
  private Timer timer = new Timer();

  private double startTime;
  private boolean end = false;
  /** Creates a new ShootWithTimer. */
  public ShootWithTimer(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(shooter);
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
    startTime = timer.get();

    if (startTime <= Constants.TIME_FOR_SHOOTER){
      shooter.runShooter();
    } else if (startTime >= Constants.TIME_FOR_SHOOTER){
      // shooter.stopShooter();
      end = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    // shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}

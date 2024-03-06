// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.combined;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class StopShooterAndFeeder extends Command {
  private Feeder feeder;
  private Shooter shooter;

  /** Creates a new StopFeederAndShooter. */
  public StopShooterAndFeeder(Feeder feeder, Shooter shooter) {
    this.feeder = feeder;
    this.shooter = shooter;

    addRequirements(feeder, shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    feeder.stopFeeder();
    shooter.stopShooter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.stopFeeder();
    shooter.stopShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

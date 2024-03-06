// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.Constants.SubsystemMotors;

public class Climber extends SubsystemBase {
  private TalonFX climberMotor = new TalonFX(SubsystemMotors.CLIMBER_MOTOR_ID);

  /** Creates a new Climber. */
  public Climber() {
    climberMotor.setInverted(false);// TODO: verify if its correct
  }

  public void climberUp() {
    climberMotor.set(MotorSpeeds.CLIMBER_UP_SPEED);
  }

  public void climberDown() {
    climberMotor.set(MotorSpeeds.CLIMBER_DOWN_SPEED);
  }

  public void stopClimber() {
    climberMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

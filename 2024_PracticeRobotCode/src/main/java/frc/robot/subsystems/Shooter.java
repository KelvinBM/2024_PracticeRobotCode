// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemMotors;

public class Shooter extends SubsystemBase {

  private TalonFX shooterTopMotor = new TalonFX(SubsystemMotors.SHOOTER_TOP_MOTOR_ID);
  private TalonFX shooterBottomMotor = new TalonFX(SubsystemMotors.SHOOTER_BOTTOM_MOTOR_ID);

  /** Creates a new Shooter. */
  public Shooter() {
    shooterTopMotor.setInverted(true);
    shooterBottomMotor.setInverted(true);
  }

  public void runShooter(double speed){
    shooterTopMotor.set(speed);
    shooterBottomMotor.set(speed);
  }

  public void stopShooter(){
    shooterTopMotor.stopMotor();
    shooterBottomMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

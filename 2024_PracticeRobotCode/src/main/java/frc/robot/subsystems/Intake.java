// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemMotors;

public class Intake extends SubsystemBase {

  private CANSparkMax intakeMotor = new CANSparkMax(SubsystemMotors.INTAKE_MOTOR_ID, MotorType.kBrushless);

  /** Creates a new Intake. */
  public Intake() {}

  public void runIntake(double speed){
    intakeMotor.set(speed);
  }

  public void reverseIntake(double speed){
    intakeMotor.set(-speed);
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

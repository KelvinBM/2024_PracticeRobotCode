// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RoborioPorts;
import frc.robot.Constants.SubsystemMotors;

public class Feeder extends SubsystemBase {

  private TalonFX feederMotor = new TalonFX(SubsystemMotors.FEEDER_MOTOR_ID);
  private DigitalInput feederLimitSwitch = new DigitalInput(RoborioPorts.FEEDER_LIMIT_SWITCH_PORT);
  
  /** Creates a new Feeder. */
  public Feeder() {
    feederMotor.setInverted(true);
  }

  public void runFeeder(double speed) {
    feederMotor.set(speed);
  }

  public void reverseFeeder(double speed) {
    feederMotor.set(-speed);
  }

  public void stopFeeder() {
    feederMotor.stopMotor();
  }

  /**
   * 
   * @return true if not pressed, false if pressed
   */
  public boolean getFeederSwitchStatus() {
    return feederLimitSwitch.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Feeder Limit Switch", getFeederSwitchStatus());
    // This method will be called once per scheduler run
  }
}

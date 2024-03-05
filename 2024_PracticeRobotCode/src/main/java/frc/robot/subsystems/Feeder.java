// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.Constants.RoborioPorts;
import frc.robot.Constants.SubsystemMotors;

public class Feeder extends SubsystemBase {

  private TalonFX feederMotor = new TalonFX(SubsystemMotors.FEEDER_MOTOR_ID);
  private DigitalInput feederLimitSwitch = new DigitalInput(RoborioPorts.FEEDER_LIMIT_SWITCH_PORT);
  private DigitalInput feederLightSensor = new DigitalInput(RoborioPorts.FEEDER_SENSOR_SWITCH_PORT);
  
  /** Creates a new Feeder. */
  public Feeder() {
    feederMotor.setInverted(true);
  }

  public void runFeeder() {
    feederMotor.set(MotorSpeeds.FEEDER_SPEED);
  }

  public void reverseFeeder() {
    feederMotor.set(-MotorSpeeds.FEEDER_SPEED);
  }

  public void stopFeeder() {
    feederMotor.stopMotor();
  }

  /**
   * 
   * @return true if not pressed, false if pressed
   */
  public boolean getFeederSwitchStatus() {
    // can also do !feederLimitSwitch.get() to get 
    // boolean values in a more understandable way
    return feederLimitSwitch.get();
  }

  public boolean getFeederSensorStatus(){
    return feederLightSensor.get();
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("Feeder Limit Switch", getFeederSwitchStatus());
    SmartDashboard.putBoolean("Sensor", getFeederSensorStatus());
    // This method will be called once per scheduler run
  }
}

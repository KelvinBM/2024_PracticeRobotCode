// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.commands.driveTrain.ArcadeDrive;

public class DriveTrain extends SubsystemBase {

  private TalonFX rightMaster = new TalonFX(DriveTrainConstants.RIGHT_MASTER_ID);
  private TalonFX leftMaster = new TalonFX(DriveTrainConstants.LEFT_MASTER_ID);
  private TalonFX rightFollower = new TalonFX(DriveTrainConstants.RIGHT_FOLLOWER_ID);
  private TalonFX leftFollower = new TalonFX(DriveTrainConstants.LEFT_FOLLWER_ID);

  private DifferentialDrive arcadeDrive = new DifferentialDrive(leftMaster, rightMaster);

  private Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);

  private DoubleSolenoid driveTrainSolenoid = new DoubleSolenoid(
                                                PneumaticsModuleType.REVPH, 
                                                DriveTrainConstants.FORWARD_CHANNEL, 
                                                DriveTrainConstants.REVERSE_CHANNEL
                                              );

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    // use StrictFollower class so followers don't have the same inversion as their masters(could have their own inversion set)
    rightFollower.setControl(new Follower(rightMaster.getDeviceID(), false));
    leftFollower.setControl(new Follower(leftMaster.getDeviceID(), false));

    rightMaster.setInverted(true);

    initDriveTrain();

    // TODO: ask Gagan to see if these are good values 
    compressor.enableAnalog(90, 120);
  }

  public void initDriveTrain(){
    rightMaster.clearStickyFaults();
    leftMaster.clearStickyFaults();
    rightFollower.clearStickyFaults();
    leftFollower.clearStickyFaults();
  }

  public void arcadeDrive(double xSpeed, double zRotation){
    arcadeDrive.arcadeDrive(xSpeed, zRotation);
  }

  public void highGear(){
    // may have to change
    driveTrainSolenoid.set(Value.kReverse);
  }

  public void lowGear(){
    // may have to change
    driveTrainSolenoid.set(Value.kForward);
  }

  public void driveForward(double speed){
    rightMaster.set(speed);
    leftMaster.set(speed);
  }

  public void driveBackWard(double speed){
    rightMaster.set(-speed);
    leftMaster.set(-speed);
  }

  public void stopArcadeDrive(){
    rightMaster.stopMotor();
    leftMaster.stopMotor();
    rightFollower.stopMotor();
    leftFollower.stopMotor();
  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("Compressor On", compressor.isEnabled());

    // This method will be called once per scheduler run
  }
}

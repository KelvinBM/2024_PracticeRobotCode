// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ButtonBoardBindings;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.StopAll;
import frc.robot.commands.auto.AutoScoringUsingDistances;
import frc.robot.commands.auto.AutoShootWithTimers;
import frc.robot.commands.auto.AutoShootWithWait;
import frc.robot.commands.combined.IntakeToFeeder;
import frc.robot.commands.combined.RunAll;
import frc.robot.commands.driveTrain.ArcadeDrive;
import frc.robot.commands.driveTrain.HighGear;
import frc.robot.commands.driveTrain.LowGear;
import frc.robot.commands.driveTrain.distanceBased.DriveAwayFromSpeaker;
import frc.robot.commands.driveTrain.distanceBased.DriveToSpeaker;
import frc.robot.commands.feeder.ReverseFeeder;
import frc.robot.commands.feeder.RunFeeder;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  // The robot's subsystems
  private final DriveTrain driveTrain = new DriveTrain();

  private final Limelight limelight = new Limelight();
  
  private final Intake intake = new Intake();
  private final Feeder feeder = new Feeder();
  private final Shooter shooter = new Shooter();

  /** controllers & ButtonBoard **/
  private final CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);
  private final Joystick buttonBoard = new Joystick(OperatorConstants.BUTTON_BOARD_PORT);

  private JoystickButton intakeInBtn, intakeOutBtn, feederInBtn, feederOutBtn,
                         shootBtn, autoIntakeBtn, autoShootBtn, stopAllBtn,
                         runAllBtn;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // button bindings
    // TODO: change button values to match with buttons on btnBoard
    intakeInBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.INTAKE_IN_BTN);
    intakeOutBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.INTAKE_OUT_BTN);
    feederInBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.FEEDER_IN_BTN);
    feederOutBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.FEEDER_OUT_BTN);
    shootBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.SHOOT_BTN);
    stopAllBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.STOP_ALL_BTN);
    autoIntakeBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.AUTO_INTAKE_BTN);
    autoShootBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.AUTO_SHOOT_BTN);
    runAllBtn = new JoystickButton(buttonBoard, ButtonBoardBindings.RUN_ALL_BTN);

    // commands
    driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, xboxController));
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    // new Trigger(xboxController.a()).onTrue(null);

    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // xboxController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    /** button board bindings **/
    // autoShootBtn.onTrue(new AutoShootWithTimers(shooter, feeder));
    autoShootBtn.onTrue(new AutoShootWithWait(shooter, feeder));
    intakeInBtn.whileTrue(new RunIntake(intake));
    intakeOutBtn.whileTrue(new ReverseIntake(intake));
    feederInBtn.whileTrue(new RunFeeder(feeder));
    feederOutBtn.whileTrue(new ReverseFeeder(feeder));
    shootBtn.whileTrue(new RunShooter(shooter));
    autoIntakeBtn.onTrue(
      new IntakeToFeeder(intake, feeder).until(() -> feeder.getFeederSwitchStatus() == false)
    );
    stopAllBtn.onTrue(new StopAll(intake, feeder, shooter));
    runAllBtn.whileTrue(new RunAll(intake, feeder, shooter));


    /** xbox controller bindings **/
    // xboxController.a().onTrue(new HighGear(driveTrain));
    // xboxController.x().onTrue(new LowGear(driveTrain));
    xboxController.y().onTrue(new DriveToSpeaker(driveTrain, limelight));
    xboxController.x().onTrue(new DriveAwayFromSpeaker(driveTrain, limelight));
    xboxController.b().onTrue(new StopAll(intake, feeder, shooter));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new AutoScoringUsingDistances(driveTrain, limelight, shooter, feeder, intake);
    // return 
    //   new AutoShootWithTimers(shooter, feeder)
    //   .andThen(new DriveAwayFromSpeaker(driveTrain, limelight).alongWith())
    // ;
  }
}

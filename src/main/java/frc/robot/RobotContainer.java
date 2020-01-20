/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.chassis.BasicDrive;
import frc.robot.commands.climber.BasicClimb;
import frc.robot.commands.intake.BasicIntake;
import frc.robot.commands.shooter.BasicShoot;
import frc.robot.commands.spinner.BasicSpin;

import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spinner;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystem defined here!!!
  private final Chassis m_chassis = new Chassis();
  private final Climber m_climber = new Climber();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Spinner m_spinner = new Spinner();

  // Command defined here!!!
  private final BasicDrive m_basicDrive = new BasicDrive(m_chassis);
  private final BasicClimb m_basicClimb = new BasicClimb(m_climber);
  private final BasicIntake m_basicIntake = new BasicIntake(m_intake);
  private final BasicShoot m_basicShoot = new BasicShoot(m_shooter);
  private final BasicSpin m_basicSpin = new BasicSpin(m_spinner);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set Default Command!
    m_chassis.setDefaultCommand(m_basicDrive);
    //m_climber.setDefaultCommand();
    //m_intake.setDefaultCommand();
    //m_shooter.setDefaultCommand();
    //m_spinner.setDefaultCommand();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
     //An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.commands.chassis.AimDrive;
import frc.robot.commands.chassis.AssistDrive;
import frc.robot.commands.chassis.BasicDrive;
import frc.robot.commands.climber.BasicClimb;
import frc.robot.commands.intake.BasicIntake;
import frc.robot.commands.shooter.BasicPIDShoot;
import frc.robot.commands.shooter.BasicShoot;
import frc.robot.commands.spinner.BasicSpin;
import frc.robot.commands.CP.*;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  public final XboxController driverJoystick = new XboxController(0);
  public final XboxController driverJoystick2 = new XboxController(1);
  // Subsystem defined here!!!
  private final Chassis m_chassis = new Chassis();
  private final Climber m_climber = new Climber();
  private final Intake  m_intake  = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Spinner m_spinner = new Spinner();
  private final CPbot m_cp = new CPbot();
  // Command defined here!!!
  //private final AutoMove m_autoCommand = new AutoMove(m_auto)
  private final BasicDrive m_basicDrive = new BasicDrive(m_chassis);
  private final AssistDrive m_assistDrive = new AssistDrive(m_chassis);
  private final AimDrive m_aimDrive = new AimDrive(m_chassis);
  private final BasicClimb m_basicClimb = new BasicClimb(m_climber);
  private final BasicIntake m_basicIntake = new BasicIntake(m_intake);
  private final BasicPIDShoot m_basicPIDShoot = new BasicPIDShoot(m_shooter);
  private final BasicShoot m_basicShoot = new BasicShoot(m_shooter);
  private final BasicSpin m_basicSpin = new BasicSpin(m_spinner);
  private final CPbot2whole m_wholecp = new CPbot2whole(m_cp);

  // Button defined here!!!
  private final JoystickButton buttonY = new JoystickButton(driverJoystick,Constants.buttonY);
  private final JoystickButton buttonA = new JoystickButton(driverJoystick,Constants.buttonA);
  private final JoystickButton buttonX = new JoystickButton(driverJoystick,Constants.buttonX);
  private final JoystickButton buttonB = new JoystickButton(driverJoystick,Constants.buttonB);
  
  private final JoystickButton buttonRB = new JoystickButton(driverJoystick,Constants.buttonRB);
  private final JoystickButton buttonLB = new JoystickButton(driverJoystick,Constants.buttonLB);
  private final JoystickButton buttonBack = new JoystickButton(driverJoystick,Constants.buttonBack);
  private final JoystickButton buttonOption = new JoystickButton(driverJoystick,Constants.buttonOption);
  private final JoystickButton buttonJR = new JoystickButton(driverJoystick,Constants.buttonJR);
  private final JoystickButton buttonJL = new JoystickButton(driverJoystick,Constants.buttonJL);

  public RobotContainer() {
    // Configure the button bindings
    //configureButtonBindings();
    // Set Default Command!
    m_cp.setDefaultCommand(m_wholecp);
    //m_chassis.setDefaultCommand(m_basicDrive);
    //m_climber.setDefaultCommand(m_basicClimb);
    //m_intake.setDefaultCommand();
    //m_shooter.setDefaultCommand(m_basicShoot);
    //m_spinner.setDefaultCommand();
  }

  private void configureButtonBindings() {
    //Trig command defined here!!!!
    buttonB.whenPressed(m_aimDrive);
    //buttonX.whenPressed(() -> m_chassis.(func));
  }

  /*
  public Command getAutonomousCommand() {
     //An ExampleCommand will run in autonomous
    //return m_autoCommand;
  }
  */
  
  public int getRawPOV(){
    return driverJoystick.getPOV();
  }
  public double getRawAxis(int AxisNumber){
    return driverJoystick.getRawAxis(AxisNumber);
  }
  public boolean getRawButton(int ButtonNumber){
    return driverJoystick.getRawButton(ButtonNumber);
  }

  public double getRX(){
    double rawData = getRawAxis(Constants.axisJRX);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisRXScale * Math.pow(Math.abs(rawData),Constants.axisRXExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisRXScale * -Math.pow(Math.abs(rawData),Constants.axisRXExp);
    }
    if(Constants.axisRXInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }
  public boolean isRXDeadzone(){
    double rawRX = getRawAxis(Constants.axisJRX);
    return (rawRX < Constants.joystickDeadZone && rawRX > -Constants.joystickDeadZone);
  }

  public double getRY(){
    double rawData = getRawAxis(Constants.axisJRY);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisRYScale * Math.pow(Math.abs(rawData),Constants.axisRYExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisRYScale * -Math.pow(Math.abs(rawData),Constants.axisRYExp);
    }
    if(Constants.axisRYInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }
  public boolean isRYDeadzone(){
    double rawRY = getRawAxis(Constants.axisJRY);
    return (rawRY < Constants.joystickDeadZone && rawRY > -Constants.joystickDeadZone);
  }

  public double getLX(){
    double rawData = getRawAxis(Constants.axisJLX);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisLXScale * Math.pow(Math.abs(rawData),Constants.axisLXExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisLXScale * -Math.pow(Math.abs(rawData),Constants.axisLXExp);
    }
    if(Constants.axisLXInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }
  public boolean isLXDeadzone(){
    double rawLX = getRawAxis(Constants.axisJLX);
    return (rawLX < Constants.joystickDeadZone && rawLX > -Constants.joystickDeadZone);
  }

  public double getLY(){
    double rawData = getRawAxis(Constants.axisJLY);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisLYScale * Math.pow(Math.abs(rawData),Constants.axisLYExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisLYScale * -Math.pow(Math.abs(rawData),Constants.axisLYExp);
    }
    if(Constants.axisLYInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }
  public boolean isLYDeadzone(){
    double rawLY = getRawAxis(Constants.axisJLY);
    return (rawLY < Constants.joystickDeadZone && rawLY > -Constants.joystickDeadZone);
  }

  public void SetRumble(double Intensity){
    driverJoystick.setRumble(RumbleType.kLeftRumble, Intensity);
    driverJoystick.setRumble(RumbleType.kRightRumble, Intensity);
  }
  public void SetRumble(double leftIntensity,double rightIntensity){
    driverJoystick.setRumble(RumbleType.kLeftRumble, leftIntensity);
    driverJoystick.setRumble(RumbleType.kRightRumble, rightIntensity);
  }
}

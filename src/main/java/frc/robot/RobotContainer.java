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
import frc.robot.commands.chassis.DistanceDrive;
import frc.robot.commands.climber.BasicClimb;
import frc.robot.commands.cpbot.BasicCp;
import frc.robot.commands.intake.BasicIntake;
import frc.robot.commands.shooter.BasicPIDShoot;
import frc.robot.commands.shooter.BasicShoot;
import frc.robot.commands.shooter.MeasureShoot;
import frc.robot.commands.spinner.BasicSpin;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  //Controller defined
  public final XboxController joystickA = new XboxController(0);
  public final XboxController joystickB = new XboxController(1);

  // Subsystem defined
  private final Chassis m_chassis = new Chassis();
  private final Climber m_climber = new Climber();
  private final Intake  m_intake  = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Spinner m_spinner = new Spinner();

  private final Cpbot m_cpbot = new Cpbot();

  // Command defined
  //private final AutoMove m_autoCommand = new AutoMove(m_auto)
  private final BasicDrive m_basicDrive = new BasicDrive(m_chassis);
  private final AssistDrive m_assistDrive = new AssistDrive(m_chassis);
  private final AimDrive m_aimDrive = new AimDrive(m_chassis);
  private final DistanceDrive m_distanceDrive = new DistanceDrive(m_chassis);
  //private final BasicClimb m_basicClimb = new BasicClimb(m_climber);
  private final BasicIntake m_basicIntake = new BasicIntake(m_intake);
  private final BasicPIDShoot m_basicPIDShoot = new BasicPIDShoot(m_shooter);
  private final BasicShoot m_basicShoot = new BasicShoot(m_shooter);
  private final MeasureShoot m_measureShoot = new MeasureShoot(m_shooter);
  private final BasicSpin m_basicSpin = new BasicSpin(m_spinner);
  private final BasicCp m_BasicCp = new BasicCp(m_cpbot);

  // Button defined here!!!
  private final JoystickButton buttonY = new JoystickButton(joystickA,Constants.buttonY);
  private final JoystickButton buttonA = new JoystickButton(joystickA,Constants.buttonA);
  private final JoystickButton buttonX = new JoystickButton(joystickA,Constants.buttonX);
  private final JoystickButton buttonB = new JoystickButton(joystickA,Constants.buttonB);
  
  private final JoystickButton buttonRB = new JoystickButton(joystickA,Constants.buttonRB);
  private final JoystickButton buttonLB = new JoystickButton(joystickA,Constants.buttonLB);
  private final JoystickButton buttonBack = new JoystickButton(joystickA,Constants.buttonBack);
  private final JoystickButton buttonOption = new JoystickButton(joystickA,Constants.buttonOption);
  private final JoystickButton buttonJR = new JoystickButton(joystickA,Constants.buttonJR);
  private final JoystickButton buttonJL = new JoystickButton(joystickA,Constants.buttonJL);

  public RobotContainer() {
    // Configure the button bindings
    //configureButtonBindings();
    // Set Default Command!
    //m_cp.setDefaultCommand(m_wholecp);
    m_chassis.setDefaultCommand(m_distanceDrive);
   // m_climber.setDefaultCommand(m_basicClimb);
    //m_intake.setDefaultCommand();
    //m_shooter.setDefaultCommand(m_basicShoot);
    //m_spinner.setDefaultCommand();
  }

  private void configureButtonBindings() {
    //Trig command defined here!!!!
    //buttonB.whenPressed(m_aimDrive);
    //buttonX.whenPressed(() -> m_chassis.(func));
  }

  /*
  public Command getAutonomousCommand() {
     //An ExampleCommand will run in autonomous
    //return m_autoCommand;
  }
  */

  //Joystick-A
  public int getARawPOV(){
    return joystickA.getPOV();
  }
  public double getARawAxis(int AxisNumber){
    return joystickA.getRawAxis(AxisNumber);
  }
  public boolean getARawButton(int ButtonNumber){
    return joystickA.getRawButton(ButtonNumber);
  }

  public double getARX(){
    double rawData = getARawAxis(Constants.axisJRX);
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
  public boolean isARXDeadzone(){
    double rawRX = getARawAxis(Constants.axisJRX);
    return (rawRX < Constants.joystickDeadZone && rawRX > -Constants.joystickDeadZone);
  }

  public double getARY(){
    double rawData = getARawAxis(Constants.axisJRY);
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
  public boolean isARYDeadzone(){
    double rawRY = getARawAxis(Constants.axisJRY);
    return (rawRY < Constants.joystickDeadZone && rawRY > -Constants.joystickDeadZone);
  }

  public double getALX(){
    double rawData = getARawAxis(Constants.axisJLX);
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
  public boolean isALXDeadzone(){
    double rawLX = getARawAxis(Constants.axisJLX);
    return (rawLX < Constants.joystickDeadZone && rawLX > -Constants.joystickDeadZone);
  }

  public double getALY(){
    double rawData = getARawAxis(Constants.axisJLY);
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
  public boolean isALYDeadzone(){
    double rawLY = getARawAxis(Constants.axisJLY);
    return (rawLY < Constants.joystickDeadZone && rawLY > -Constants.joystickDeadZone);
  }

  public void SetARumble(double Intensity){
    joystickA.setRumble(RumbleType.kLeftRumble, Intensity);
    joystickA.setRumble(RumbleType.kRightRumble, Intensity);
  }
  public void SetARumble(double leftIntensity,double rightIntensity){
    joystickA.setRumble(RumbleType.kLeftRumble, leftIntensity);
    joystickA.setRumble(RumbleType.kRightRumble, rightIntensity);
  }

  
  //Joystick-B
  public int getBRawPOV(){
    return joystickB.getPOV();
  }
  public double getBRawAxis(int AxisNumber){
    return joystickB.getRawAxis(AxisNumber);
  }
  public boolean getBRawButton(int ButtonNumber){
    return joystickB.getRawButton(ButtonNumber);
  }

  public double getBRX(){
    double rawData = getARawAxis(Constants.axisJRX);
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
  public boolean isBRXDeadzone(){
    double rawRX = getARawAxis(Constants.axisJRX);
    return (rawRX < Constants.joystickDeadZone && rawRX > -Constants.joystickDeadZone);
  }

  public double getBRY(){
    double rawData = getBRawAxis(Constants.axisJRY);
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
  public boolean isBRYDeadzone(){
    double rawRY = getBRawAxis(Constants.axisJRY);
    return (rawRY < Constants.joystickDeadZone && rawRY > -Constants.joystickDeadZone);
  }

  public double getBLX(){
    double rawData = getBRawAxis(Constants.axisJLX);
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
  public boolean isBLXDeadzone(){
    double rawLX = getBRawAxis(Constants.axisJLX);
    return (rawLX < Constants.joystickDeadZone && rawLX > -Constants.joystickDeadZone);
  }

  public double getBLY(){
    double rawData = getBRawAxis(Constants.axisJLY);
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
  public boolean isBLYDeadzone(){
    double rawLY = getBRawAxis(Constants.axisJLY);
    return (rawLY < Constants.joystickDeadZone && rawLY > -Constants.joystickDeadZone);
  }

  public void SetBRumble(double Intensity){
    joystickB.setRumble(RumbleType.kLeftRumble, Intensity);
    joystickB.setRumble(RumbleType.kRightRumble, Intensity);
  }
  public void SetBRumble(double leftIntensity,double rightIntensity){
    joystickB.setRumble(RumbleType.kLeftRumble, leftIntensity);
    joystickB.setRumble(RumbleType.kRightRumble, rightIntensity);
  }
}

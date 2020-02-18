/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DriverStation;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.controller.PIDController;

public class Spinner extends SubsystemBase {

  private boolean risePIDEnable = false;
  private double risePIDSetpoint;

  private final WPI_TalonSRX spinMotor;
  private final WPI_TalonSRX riseMotor;

  private final ColorSensorV3 colorSensor;
  private final ColorMatch colorMatcher;

  private final Encoder m_Encoder;
  private PIDController risePIDControl;
  private String gameData;
  private boolean positionControlEnable;
  private boolean rotationControlEnable;
  //private Color detectedColor;
  ColorMatchResult match;
  /**
   * Creates a new Spinner.
   */
  public Spinner() {    
    spinMotor = new WPI_TalonSRX(Constants.spinnerSpinMotor);
    riseMotor = new WPI_TalonSRX(Constants.spinnerriseMotor);

    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    colorMatcher = new ColorMatch();
    m_Encoder = new Encoder(Constants.m_EncoderPinA,Constants.m_EncoderPinB,Constants.m_EncoderReverse);
    positionControlEnable = false;
    rotationControlEnable = false;
    risePIDControl = new PIDController(0.1, 0, 0);
    colorMatcher.addColorMatch(Constants.kBlueTarget);
    colorMatcher.addColorMatch(Constants.kGreenTarget);
    colorMatcher.addColorMatch(Constants.kRedTarget);
    colorMatcher.addColorMatch(Constants.kYellowTarget);
    risePIDControl.enableAutoStop(10,0.25);
    m_Encoder.reset();
  }


  @Override
  public void periodic() {
    if(spinnerPIDIsEnable()){
      risePIDOutput(risePIDControl.calculate(getrisePIDMeasurment(), risePIDSetpoint));
    }
    //gameData = DriverStation.getInstance().getGameSpecificMessage();(!?????????????????????????????)
    // This method will be called once per scheduler run
  }

  public void setRiseMotorSpeed(double speed){
    riseMotor.set(speed);
  }

  public void motorRun(){
    risePIDOutput(risePIDControl.calculate(getrisePIDMeasurment(), risePIDSetpoint));
  }
  public double get(){
    
    return m_Encoder.getDistance();
  }

  public String getGameData(){
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    return gameData;
  }

  public Color getColor(){
    return colorSensor.getColor();
  }

  public ColorMatchResult match(Color detected) {
    return colorMatcher.matchClosestColor(detected);
  }


  public void startMotor(){
    spinMotor.set(Constants.spinnerSpinMotorSpeed);
  }

  public void stopMotor(){
    spinMotor.stopMotor();
  }

  public void positionControlEnable(){
    positionControlEnable = true;
  }

  public boolean positionControlIsEnable(){
    return positionControlEnable;
  }

  public void positionControlDisable(){
    positionControlEnable = false;
  }

  public boolean rotationControlIsEnable(){
    return rotationControlEnable;
  }
  
  public void rotationControlEnable(){
    rotationControlEnable = true;
  }

  public void rotationControlDisable(){
    rotationControlEnable = false;
  }

  public boolean spinnerPIDIsEnable(){
    return risePIDEnable;
  }

  
  public void risePIDEnable(){
    risePIDEnable = true;
  }

  public boolean atSetpoint(){
    return risePIDControl.finished();
  }

  public void risePIDDisable(){
    risePIDEnable = false;
    risePIDOutput(0);
  }

  public double getrisePIDMeasurment(){
    return m_Encoder.get();
  }

  public void setriseSetpoint(double setpoint){
    risePIDSetpoint = setpoint;
    //risePIDControl.
  }

  /*
  public double getSetpoint(){
    return risePIDControl.getSetpoint();
  }
  */

  public void risePIDOutput(double output){
    riseMotor.setVoltage(output);
  }

  public void risePIDReset(){
    m_Encoder.reset();
    risePIDControl.reset();
  }

  public void setRiseMotor(double riseMotorSpeed){
    riseMotor.set(riseMotorSpeed);
  }

  @Override
  public void setDefaultCommand(final Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

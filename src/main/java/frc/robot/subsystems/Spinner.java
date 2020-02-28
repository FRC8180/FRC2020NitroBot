/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Spinner:(靖凱)
//Spin:1LB/1RB
//Lift:1A/1Y

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
//import frc.robot.Robot;
import frc.robot.Utility;

public class Spinner extends SubsystemBase {

  private PIDController liftPID;
  private boolean liftPIDEnable = false;
  private double liftPIDSetpoint = 0;
  
  private WPI_VictorSPX liftMotor;
  private WPI_VictorSPX spinMotor;
  
  private Encoder liftEncoder;
  private DigitalInput zeroLimitSw;

  private ColorSensorV3 m_colorSensor;
  private ColorMatch colorMatcher;
  private String gameData;
  private boolean positionControlEnable;
  private boolean rotationControlEnable;
  private Color detectedColor;
  private ColorMatchResult match;

  public Spinner() {

    liftPID = new PIDController(Constants.spinnerLiftPIDKp, Constants.spinnerLiftPIDKi, Constants.spinnerLiftPIDKd);
    liftEncoder = new Encoder(Constants.spinnerLiftEncoderPinA, Constants.spinnerLiftEncoderPinB, Constants.spinnerLiftEncoderInverted);

    liftMotor = new WPI_VictorSPX(Constants.spinnerLiftMotorID);
    spinMotor = new WPI_VictorSPX(Constants.spinnerSpinMotorID);
    liftMotor.setInverted(Constants.spinnerLiftMotorInverted);
    spinMotor.setInverted(Constants.spinnerSpinMotorInverted);
    m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    colorMatcher = new ColorMatch();

    zeroLimitSw = new DigitalInput(Constants.spinnerZeroLimitSwID);

    m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    colorMatcher = new ColorMatch();
    positionControlEnable = false;
    rotationControlEnable = false;

    colorMatcher.addColorMatch(Constants.kBlueTarget);
    colorMatcher.addColorMatch(Constants.kGreenTarget);
    colorMatcher.addColorMatch(Constants.kRedTarget);
    colorMatcher.addColorMatch(Constants.kYellowTarget);
  }

  @Override
  public void periodic() {
    if(liftPIDEnable){
      liftPIDOutput(liftPID.calculate(liftPIDMeasurment(), liftPIDSetpoint));
    }
    //gameData = DriverStation.getInstance().getGameSpecificMessage();
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    super.setDefaultCommand(defaultCommand);
  }

  public String getGameData(){
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    return gameData;
  }

  public Color getColor(){
    detectedColor = m_colorSensor.getColor();
    return detectedColor;
  }

  public ColorMatchResult match(Color detected) {
    match = colorMatcher.matchClosestColor(detected);
    return match;
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

  public void setLiftMotorSpeed(double spd){
    /*
    if(getZeroLimitSw()){
      liftMotor.set(0);
    }else{
      liftMotor.set(spd);
    }
    */
    liftMotor.set(spd);
  }
  public void setLiftMotorVoltage(double voltage){
    liftMotor.setVoltage(voltage);
  }
  public void setLiftMotorStop(){
    liftMotor.set(0);
  }

  public void setSpinMotorSpeed(double spd){
    spinMotor.set(spd);
  }
  public void setSpinMotorVoltage(double voltage){
    spinMotor.setVoltage(voltage);
  }
  public void setSpinMotorStop(){
    spinMotor.set(0);
  }

  
  public double getLiftEncoder(){
    return liftEncoder.get();
  }
  public void resetLiftEncoder(){
    liftEncoder.reset();
  }


  public boolean getZeroLimitSw(){
    return zeroLimitSw.get();
  }


  //Intake lift control PID function
  public void liftPIDEnable(){
    liftPIDEnable = true;
    liftPID.reset();
  }
  public void liftPIDDisable(){
    liftPIDEnable = false;
    liftPIDOutput(0);
  }
  public void liftPIDReset(){
    liftPIDSetpoint = 0;
    liftPID.reset();
  }
  public boolean liftPIDIsEnable(){
    return liftPIDEnable;
  }
  public void liftPIDSetSetpoint(double setpoint){
    liftPIDSetpoint = setpoint;
  }
  public void liftPIDSetTolerance(double value){
    liftPID.setTolerance(value);
  }
  public double liftPIDMeasurment(){
    return getLiftEncoder();
  }
  public void liftPIDOutput(double output){
    double voltage = output;
    voltage = Utility.Constrain(voltage, -Constants.spinnerLiftPIDMaxOutputVoltage, Constants.spinnerLiftPIDMaxOutputVoltage);
    setLiftMotorVoltage(voltage);
  }
  public boolean liftPIDIsStable(){
    return liftPID.atSetpoint();
  }

}

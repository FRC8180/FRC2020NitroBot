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
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DriverStation;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class Spinner extends SubsystemBase {

  //private boolean rasePIDEnable = false;
  //private double rasePIDSetpoint = 0;

  private final WPI_TalonSRX spinMotor;
  private final WPI_TalonSRX raseMotor;

  private final ColorSensorV3 colorSensor;
  private final ColorMatch colorMatcher;

  //private final Encoder m_Encoder;
  //private final PIDController rasePIDControl;
  //private Timer timer;
  //private double rasePreviousRotation = 0;
  //private double rasePreviousTime = 0;
  private String gameData;
  //private Color detectedColor;
  ColorMatchResult match;
  /**
   * Creates a new Spinner.
   */
  public Spinner() {    
    spinMotor = new WPI_TalonSRX(Constants.spinnerSpinMotor);
    raseMotor = new WPI_TalonSRX(Constants.spinnerRaseMotor);

    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    colorMatcher = new ColorMatch();
    //m_Encoder = new Encoder(Constants.m_EncoderPinA,Constants.m_EncoderPinB,Constants.m_EncoderReverse);

    //rasePIDControl = new PIDController(0.1, 0, 0);
    colorMatcher.addColorMatch(Constants.kBlueTarget);
    colorMatcher.addColorMatch(Constants.kGreenTarget);
    colorMatcher.addColorMatch(Constants.kRedTarget);
    colorMatcher.addColorMatch(Constants.kYellowTarget);

    //m_Encoder.reset();
  }

  @Override
  public void periodic() {
    //gameData = DriverStation.getInstance().getGameSpecificMessage();(!?????????????????????????????)

    //if(rasePIDEnable){
    //  upperPIDOutput(rasePIDControl.calculate(getUpperPIDMeasurment(), rasePIDSetpoint));
    //}
    
    // This method will be called once per scheduler run
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
  /*
  public boolean spinnerPIDIsEnable(){
    return rasePIDEnable;
  }
  
  public void rasePIDEnable(){
    rasePIDEnable = true;
    rasePIDControl.reset();
  }

  public void upperPIDDisable(){
    rasePIDEnable = false;
    upperPIDOutput(0);
  }

  public void setUpperSetpoint(double setpoint){
    rasePIDSetpoint = setpoint;
  }

  public void upperPIDOutput(double output){
    raseMotor.setVoltage(output);
  }

  public void upperPIDReset(){
    rasePIDControl.reset();
  }

  public double getUpperPIDMeasurment(){
    double rotation = m_Encoder.get();
    double time = timer.get();
    double deltaRotation = rotation - rasePreviousRotation;
    double deltaTime = time - rasePreviousTime;
    rasePreviousRotation = rotation;
    rasePreviousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterUpperEncoderPPR;
    return RPS;
  }

  */

  @Override
  public void setDefaultCommand(final Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

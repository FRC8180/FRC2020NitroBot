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

public class Spinner extends SubsystemBase {
  private final WPI_TalonSRX Motor_spinner;
  private final ColorSensorV3 m_colorSensor;
  private final ColorMatch m_colorMatcher;
  private final I2C.Port i2cPort;
  int counter;
  Color target;
  Color detectedColor;
  String gameData;
  String colorString;
  ColorMatchResult match;
  /**
   * Creates a new Spinner.
   */
  public Spinner() {    
    i2cPort = I2C.Port.kOnboard;
    Motor_spinner = new WPI_TalonSRX(Constants.spinnerMotorPin); 
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    m_colorMatcher.addColorMatch(Constants.kBlueTarget);
    m_colorMatcher.addColorMatch(Constants.kGreenTarget);
    m_colorMatcher.addColorMatch(Constants.kRedTarget);
    m_colorMatcher.addColorMatch(Constants.kYellowTarget);
    
  }

  @Override
  public void periodic() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    // This method will be called once per scheduler run
  }

  public String getGameData(){
    //gameData = DriverStation.getInstance().getGameSpecificMessage();
    return gameData;
  }

  

  public Color getColor(){
    detectedColor = m_colorSensor.getColor();
    return detectedColor;
  }
  public ColorMatchResult match(Color detected) {
    match = m_colorMatcher.matchClosestColor(detected);

    return match;
  }

  public void startMotor(){
    Motor_spinner.set(Constants.spinnerMotorSpeed);
  }

  public void stopMotor(){
    Motor_spinner.stopMotor();
  }


  @Override
  public void setDefaultCommand(final Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

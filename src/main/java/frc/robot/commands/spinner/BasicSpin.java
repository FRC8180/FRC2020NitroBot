/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.spinner;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Spinner;

public class BasicSpin extends CommandBase {

  private final Spinner spinner;
  public BasicSpin(Spinner subsystem) {
    spinner = subsystem;
    addRequirements(subsystem);
  }

  private int counter = 0;
  private Color color_inf;
  private Color target;
  private Color targetColor;
  private Color detectedColor;
  private String gameData;
  private String colorString;
  private ColorMatchResult match;
  private ColorMatchResult match_inf;

  @Override
  public void initialize() {
    spinner.stopMotor();
    //spinner.risePIDDisable();
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("PID",spinner.get());
    gameData = spinner.getGameData();
    showColorInf();
    if(gameData.length() > 0 && Robot.m_oi.getRawButton(Constants.buttonRB)){
      showColorInf();
      spinner.risePIDReset();
      PID(1024);
      getColorMatchResult();
      switch (gameData.charAt(0)){
        case 'B' :
         targetColor = Constants.kRedTarget;
          break;
        case 'G' :
          targetColor = Constants.kYellowTarget;
          break;
        case 'Y' :
          targetColor = Constants.kGreenTarget;
          break;
        case 'R' :
          targetColor = Constants.kGreenTarget;
          break;
      }
      PID(0);
    }

    if(Robot.m_oi.getRawButton(Constants.buttonLB)){
      spinner.risePIDReset();
      PID(-1024);
      spinner.startMotor();
      counter = 0;
    }

    if(spinner.positionControlIsEnable()){
      showColorInf();
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      if(match.color != target && match.confidence > 0.96){
        counter++;
        target = match.color;
      }
      if(counter > 25){
        spinner.stopMotor();
        spinner.positionControlDisable();
      }
      PID(0);
    }

    if(spinner.rotationControlIsEnable()){
      spinner.startMotor();
      counter =0;
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      target = match.color;
      if(match.color == targetColor){
        spinner.rotationControlDisable();
        spinner.stopMotor();
      }else if(match.color != target){
        target = match.color;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    spinner.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public ColorMatchResult getColorMatchResult(){
    detectedColor = spinner.getColor();
    return spinner.match(detectedColor);
  }

  public void PID(int setpoint){
    spinner.setriseSetpoint(setpoint);
    spinner.risePIDEnable();
    while(true){
      spinner.motorRun();
      SmartDashboard.putNumber("get", spinner.get());
      if(spinner.atSetpoint()){
        spinner.risePIDDisable();
        break;
      }
    }
  }

  public void showColorInf(){
    color_inf = spinner.getColor();
    match_inf = spinner.match(color_inf);
    if (match_inf.color == Constants.kBlueTarget) {
      colorString = "Blue";
    } else if (match_inf.color == Constants.kRedTarget) {
      colorString = "Red";
    } else if (match_inf.color == Constants.kGreenTarget) {
      colorString = "Green";
    } else if (match_inf.color == Constants.kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    SmartDashboard.putNumber("Red", color_inf.red);
    SmartDashboard.putNumber("Green", color_inf.green);
    SmartDashboard.putNumber("Blue", color_inf.blue);
    SmartDashboard.putNumber("Confidence", match_inf.confidence);
    SmartDashboard.putNumber("Counter", counter);
    SmartDashboard.putString("Detected Color", colorString);
  }
}


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

public class AssistSpin extends CommandBase {

  private boolean disable = false;
  
  Spinner spinner;
  public AssistSpin(Spinner subsystem) {
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
    spinner.resetLiftEncoder();
    spinner.liftPIDEnable();
  }

  @Override
  public void execute() {
    if(disable){
      spinner.liftPIDEnable();
      disable = false;
    }

    /*
    if(Robot.m_oi.getARawButton(Constants.buttonA)){
      spinner.setSpinMotorSpeed(0.5);
    }else{
      spinner.setSpinMotorSpeed(0);
    }
    */
    if(Robot.m_oi.getARawButton(Constants.buttonY)){
      spinner.liftPIDSetSetpoint(angleToPulse(0));
    }else if(Robot.m_oi.getARawButton(Constants.buttonA)){
      spinner.liftPIDSetSetpoint(angleToPulse(90));
    }

    gameData = spinner.getGameData();
    if(gameData.length() > 0 && Robot.m_oi.getARawButton(Constants.buttonRB)){
      showColorInf();
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
      spinner.rotationControlEnable();
    }

    if(Robot.m_oi.getARawButton(Constants.buttonLB)){
      counter = 0;
      spinner.positionControlEnable();
      spinner.setSpinMotorSpeed(Constants.spinnerSpinMotorSpd);
    }

    if(spinner.rotationControlIsEnable()){
      spinner.setSpinMotorSpeed(Constants.spinnerSpinMotorSpd);
      counter =0;
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      target = match.color;
      if(match.color == targetColor){
        spinner.rotationControlDisable();
        spinner.setSpinMotorSpeed(0);
      }else if(match.color != target){
        target = match.color;
      }
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
        spinner.setSpinMotorSpeed(0);
        spinner.positionControlDisable();
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    spinner.liftPIDDisable();
    spinner.setLiftMotorStop();
    spinner.setSpinMotorStop();
    disable = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public double angleToPulse(double angle){
    return (int)((angle / 360) * (Constants.spinnerLiftMotorGearRatio * 7));
  }

  public ColorMatchResult getColorMatchResult(){
    detectedColor = spinner.getColor();
    return spinner.match(detectedColor);
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

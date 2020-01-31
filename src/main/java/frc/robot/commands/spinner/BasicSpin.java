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
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Spinner;

public class BasicSpin extends CommandBase {

  private final Spinner spinner;
  public BasicSpin(Spinner subsystem) {
    spinner = subsystem;
    addRequirements(subsystem);
  }

  int counter;
  Color color_inf;
  Color target;
  Color detectedColor;
  String gameData;
  String colorString;
  ColorMatchResult match;
  ColorMatchResult match_inf;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    spinner.stopMotor();
    counter = 0;
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gameData = spinner.getGameData();
    if(gameData.length() > 0 && Robot.m_oi.getRawButton(Constants.buttonRB)){
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      switch (gameData.charAt(0)){
        
        case 'B' :
        rotationControl(Constants.kRedTarget);
        break;
        
        case 'G' :
        rotationControl(Constants.kYellowTarget);
        break;

        case 'Y' :
        rotationControl(Constants.kGreenTarget);
        break;
       
        case 'R' :
        rotationControl(Constants.kGreenTarget);
        break;
      }
    }
    if(Robot.m_oi.getRawButton(Constants.buttonLB)){
      counter = 0;
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      target = match.color;
      while(counter < 24){
        showColorInf();
        detectedColor = spinner.getColor();
        match = spinner.match(detectedColor);
        spinner.startMotor();
        if(match.color != target && match.confidence > 0.96){
          counter++;
          detectedColor = spinner.getColor();
          match = spinner.match(detectedColor);
          target = match.color;
        }
      }
      spinner.stopMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinner.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void rotationControl(Color target){
    spinner.startMotor();
    while(match.color != target){
      detectedColor = spinner.getColor();
      match = spinner.match(detectedColor);
      showColorInf();
    }
    spinner.stopMotor();
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


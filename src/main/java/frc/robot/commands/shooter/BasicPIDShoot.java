/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class BasicPIDShoot extends CommandBase {

  private int upperRPS = 0;
  private int lowerRPS = 0;
  private boolean buttonYStatus = false;
  private boolean buttonBStatus = false;
  private boolean buttonXStatus = false;
  private boolean buttonAStatus = false;
  

  private final Shooter shooter;
  public BasicPIDShoot(Shooter subsystem) {
    shooter = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    shooter.upperPIDEnable();
    shooter.lowerPIDEnable();
  }

  @Override
  public void execute(){
    System.out.println(Robot.m_oi.getRawButton(Constants.buttonRB));
    SmartDashboard.putNumber("UpperTargetRPS", upperRPS);
    SmartDashboard.putNumber("UpperNowRPS", shooter.getUpperPIDMeasurment());
    SmartDashboard.putNumber("LowerTargetRPS", lowerRPS);
    SmartDashboard.putNumber("LowerNowRPS", shooter.getLowerPIDMeasurment());
    
    if(Robot.m_oi.getRawButton(Constants.buttonY) && !buttonYStatus){
      upperRPS += 5;
      buttonYStatus = true;
    }else if(Robot.m_oi.getRawButton(Constants.buttonY)){
      buttonYStatus = false;
    }
    if(Robot.m_oi.getRawButton(Constants.buttonB) && !buttonBStatus){
      upperRPS -= 5;
      buttonBStatus = true;
    }else if(Robot.m_oi.getRawButton(Constants.buttonB)){
      buttonBStatus = false;
    }

    if(Robot.m_oi.getRawButton(Constants.buttonX) && !buttonXStatus){
      lowerRPS += 5;
      buttonXStatus = true;
    }else if(Robot.m_oi.getRawButton(Constants.buttonX)){
      buttonXStatus = false;
    }
    if(Robot.m_oi.getRawButton(Constants.buttonA) && !buttonAStatus){
      lowerRPS -= 5;
      buttonAStatus = true;
    }else if(Robot.m_oi.getRawButton(Constants.buttonA)){
      buttonAStatus = false;
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("LOLOLOLL");
    shooter.upperPIDDisable();
    shooter.lowerPIDDisable();
  }
  
  @Override
  public boolean isFinished() {
    return false;
  }
}

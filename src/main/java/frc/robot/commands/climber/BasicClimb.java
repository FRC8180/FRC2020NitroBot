/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class BasicClimb extends CommandBase {

  private final Climber climber;
  public BasicClimb(Climber subsystem) {
    climber = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {

    double LY = Robot.m_oi.getBRawAxis(Constants.axisJLY);
    double RY = Robot.m_oi.getBRawAxis(Constants.axisJRY);

    if(Math.abs(LY) > Constants.joystickDeadZone){
      climber.setHookMotorSpeed(LY);
    }else{
      climber.setHookMotorSpeed(0);
    }

    if(Math.abs(RY) > Constants.joystickDeadZone){
      climber.setLiftMotorSpeed(RY);
    }else{
      climber.setLiftMotorSpeed(0);
    }
    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

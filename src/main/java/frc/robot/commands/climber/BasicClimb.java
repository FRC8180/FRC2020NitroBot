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
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    if(Robot.m_oi.getRawButton(Constants.buttonX)){
      climber.encoderReset();
      climber.setSetPoint(Constants.liftSetPoint);
    }
    */
    if(Robot.m_oi.getRawAxis(Constants.axisJRY) > 0.2 || Robot.m_oi.getRawAxis(Constants.axisJRY) <0.2){
      climber.setLiftMotorSpeed(Robot.m_oi.getRawAxis(Constants.axisJRY)*0.5);
    }

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

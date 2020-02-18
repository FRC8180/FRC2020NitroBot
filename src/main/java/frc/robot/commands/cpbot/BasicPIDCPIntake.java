/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cpbot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.CPIntake;

public class BasicPIDCPIntake extends CommandBase {
  /**
   * Creates a new BasicPIDCPIntake.
   */
  private final CPIntake cpIntake;
  public BasicPIDCPIntake(CPIntake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    cpIntake = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_oi.getRawButton(Constants.buttonA)){
      cpIntake.setIntakeMotorSpeed(Constants.CPintakeMotorSpeed_in);
    }else if(Robot.m_oi.getRawButton(Constants.buttonB)){
      cpIntake.setIntakeMotorSpeed(Constants.CPintakeMotorSpeed_out);
    }else if(Robot.m_oi.getRawAxis(Constants.axisJRY) > 0.15 || Robot.m_oi.getRawAxis(Constants.axisJRY) < -0.15){
      cpIntake.setRiseMotorSpeed(Robot.m_oi.getRawAxis(Constants.axisJRY));
    }else{
      cpIntake.setRiseMotorSpeed(0);
      cpIntake.setIntakeMotorSpeed(0);
    }
    SmartDashboard.putNumber("Encoder", cpIntake.getrisePIDMeasurment());
    if(Robot.m_oi.getRawButton(Constants.buttonX)){
      cpIntake.risePIDReset();
    }
    if(Robot.m_oi.getRawButton(Constants.buttonRB)){
      cpIntake.setriseSetpoint(1000);
    }
    if(Robot.m_oi.getRawButton(Constants.buttonLB)){
      cpIntake.setriseSetpoint(0);
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

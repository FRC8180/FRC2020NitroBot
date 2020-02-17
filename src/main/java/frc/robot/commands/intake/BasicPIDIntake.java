/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class BasicPIDIntake extends CommandBase {

  private final Intake intake;
  public BasicPIDIntake(Intake subsystem) {
    intake = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.intakeRiserPIDDisable();
    intake.setIntakeMotorSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_oi.getRawButton(Constants.buttonA) && !intake.intakeRiserPIDIsEnable()){
      intake.encoderReset();
      intake.setSetPoint(256);
      intake.intakeRiserPIDEnable();
      intake.setIntakeMotorSpeed(Constants.intakeMotorSpeed);
    }else if(Robot.m_oi.getRawButton(Constants.buttonA) && intake.intakeRiserPIDIsEnable()){
      intake.setIntakeMotorSpeed(0);
      intake.setSetPoint(0);
      intake.intakeRiserPIDEnable();
    }
    if(intake.intakeRiserPIDIsEnable()){
      if(intake.atSetpoint()){
        intake.intakeRiserPIDDisable();
      }
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

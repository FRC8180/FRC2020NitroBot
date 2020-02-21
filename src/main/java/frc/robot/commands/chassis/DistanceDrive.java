/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Chassis;

public class DistanceDrive extends CommandBase {
  
  private boolean disable = false;

  Chassis chassis;
  public DistanceDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    chassis.rightPIDEnable();
  }

  @Override
  public void execute() {

    if(disable){
      chassis.rightPIDEnable();
      disable = false;
    }

    if(Robot.m_oi.getARawButton(Constants.buttonY)){
      chassis.rightPIDSetSetpoint(3);
    }else if(Robot.m_oi.getARawButton(Constants.buttonB)){
      chassis.rightPIDSetSetpoint(0);
    }else if(Robot.m_oi.getARawButton(Constants.buttonA)){
      chassis.rightPIDSetSetpoint(-3);
    }
  }

  @Override
  public void end(boolean interrupted) {
    chassis.rightPIDDisable();
    chassis.setMotorStop();
    disable = false;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

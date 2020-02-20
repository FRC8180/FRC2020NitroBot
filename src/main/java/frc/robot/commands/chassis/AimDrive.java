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

public class AimDrive extends CommandBase {
  
  private boolean disable = false;
  //private boolean PIDBugIgnore = true;

  Chassis chassis;
  public AimDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    chassis.aimPIDEnable();
    chassis.aimPIDSetTolerance(1);
  }

  @Override
  public void execute(){
    if(disable){
      chassis.aimPIDEnable();
      disable = false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    chassis.aimPIDDisable();
    chassis.setMotorStop();
    disable = true;
    //PIDBugIgnore = true;
  }

  @Override
  public boolean isFinished() {
    /*
    boolean finished = chassis.aimPIDIsStable();
    if(finished && PIDBugIgnore){
      PIDBugIgnore = false;
      return false;
    }else if(finished && !PIDBugIgnore){
      return true;
    }
    return false;
    */
    return Robot.m_oi.getARawButton(Constants.buttonBack);
  }
}

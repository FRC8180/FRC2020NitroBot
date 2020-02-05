/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Utility;
import frc.robot.subsystems.Chassis;

public class AssistDrive extends CommandBase {

  private Timer timer;

  private boolean disable = false;
  private double previousTime = 0;

  Chassis chassis;
  public AssistDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    chassis.setLockAngle(chassis.getRawAngle());
    timer = new Timer();
  }

  @Override
  public void execute() {
    if(disable = true){
      chassis.setLockAngle(chassis.getRawAngle());
      chassis.PIDEnable();
      disable = false;
    }

    double joystickLY = Robot.m_oi.getLY();
    double joystickRX = Robot.m_oi.getRX();

    if(Robot.m_oi.isRXDeadzone()){
      if(chassis.PIDIsEnable()){
      }else{
        if(timer.get() >= previousTime + 0.25){
          chassis.setLockAngle(chassis.getRawAngle());
          chassis.PIDEnable();
        }
        double Lspd = Utility.Constrain(joystickLY, -1, 1);
        double Rspd = Utility.Constrain(joystickLY, -1, 1);
        chassis.setMotorSpeed(Lspd, Rspd);
      }
    }else{
      chassis.PIDDisable();
      previousTime = timer.get();
      double Lspd = Utility.Constrain((joystickLY + joystickRX), -1, 1);
      double Rspd = Utility.Constrain((joystickLY - joystickRX), -1, 1);
      chassis.setMotorSpeed(Lspd, Rspd);
    }
  }

  @Override
  public void end(boolean interrupted) {
    chassis.PIDDisable();
    chassis.setMotorStop();
    disable = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

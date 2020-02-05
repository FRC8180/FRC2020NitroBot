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

public class BasicDrive extends CommandBase {

  public double Gryo_HeadingAngle;
  private final Chassis chassis;
  public BasicDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double Joystick_LY = Robot.m_oi.getRawLY();
    double Joystick_RX = Robot.m_oi.getRawRX();

    double rspd = 0;
    double lspd = 0;
    boolean deadzone = Robot.m_oi.getRXState();
    if(deadzone){
      /* if(PIDEnable){
         double angle = (((((chassis.get_Angle() + (360 - Gryo_HeadingAngle)) % 360) + 180) % 360) - 180);
        // double pid = PID.PID(angle, kp, ki, kd);
         rspd = Joystick_LY - pid;
         lspd = Joystick_LY + pid;
       }*/
       //else{
         rspd = Joystick_LY;
         lspd = Joystick_LY;
       //}
     }
     else{
       rspd = Joystick_LY - Joystick_RX;
       lspd = Joystick_LY + Joystick_RX;
     }
     chassis.setMotorSpeed(lspd*Constants.outputPercent, rspd*Constants.outputPercent);
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.setMotorSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

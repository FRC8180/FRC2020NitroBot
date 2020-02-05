/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Vision.Vision;
import frc.robot.subsystems.Chassis;
//import frc.robot.system.PID_System;

public class AimDrive extends CommandBase {
  /**
   * Creates a new PIDDrive.
   */
  //public PID_System PID = new PID_System();
  //public double Gryo_HeadingAngle;
  
  public Timer pid_timer = new Timer();
  public boolean PIDEnable = false;
  /*
  public double kp = 0.04;
  public double ki = 0.0;
  public double kd = 0.1;
  */

  private final Chassis chassis;
  public AimDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*
    PID.Init();
    PID.Enable_PID(true);
    PID.Enable_AntiWindUp(true, 1);
    PID.Enable_AutoStop(false, 0, 0);
    PID.Enable_TimeOut(false, 0);
    PIDEnable = true;
    */
    //Gryo_HeadingAngle = chassis.get_Angle();
    pid_timer.reset();
    pid_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double Joystick_LY = Robot.m_oi.getRawLY();
    double Joystick_RX = Robot.m_oi.getRawRX();
    Vision vision = new Vision();
    double rspd = 0;
    double lspd = 0;
    //boolean deadzone = Robot.m_oi.getRXState();
    //double angle = (((((chassis.get_Angle() + (360 - Gryo_HeadingAngle)) % 360) + 180) % 360) - 180);
    //System.out.println(angle);
    double visionAngle = vision.getVisionResult("h_angle");
    System.out.println(visionAngle);
    double pid = chassis.aimPID();
    rspd = -pid;
    lspd = pid;
    chassis.setMotorSpeed(lspd*Constants.outputPercent, rspd*Constants.outputPercent);
    /*
    if(deadzone){
     if(PIDEnable){
        double angle = (((((chassis.get_Angle() + (360 - Gryo_HeadingAngle)) % 360) + 180) % 360) - 180);
        double pid = PID.PID(angle, kp, ki, kd);
        rspd = Joystick_LY - pid;
        lspd = Joystick_LY + pid;
      }
      else{
        rspd = Joystick_LY;
        lspd = Joystick_LY;
      }
    }
    else{
      rspd = Joystick_LY - Joystick_RX;
      lspd = Joystick_LY + Joystick_RX;
    }
    */
    
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

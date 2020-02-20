/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Robot;

public class Climber extends SubsystemBase {
  private WPI_TalonSRX hookA = new WPI_TalonSRX(20);//(Constants.hookAID);
  private WPI_TalonSRX hookB = new WPI_TalonSRX(30);//(Constants.hookBID);
  private WPI_TalonSRX hanger = new WPI_TalonSRX(1);//(Constants.hangerID);
  private WPI_TalonSRX rotate = new WPI_TalonSRX(2);

  public Climber() {
    hookA.follow(hookB);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(Robot.m_oi.driverJoystick2.getAButton()){
      hookA.set(1);
      hookB.set(1);
    }
    else{
      hookA.set(0);
      hookB.set(0);
    }
    if(Robot.m_oi.driverJoystick2.getBButton()){
      hanger.set(0.3);
    }
    else{
      hanger.set(0);
    }
    if(Robot.m_oi.driverJoystick.getRawAxis(3)>0.2){
      hanger.set(-Robot.m_oi.driverJoystick.getRawAxis(3));
    }else if(Robot.m_oi.driverJoystick.getRawAxis(2)>0.2){
      hanger.set(Robot.m_oi.driverJoystick.getRawAxis(2));
    }
    else{
      hanger.set(0);
    }
    if(Robot.m_oi.driverJoystick.getRawAxis(1)>0.2||Robot.m_oi.driverJoystick.getRawAxis(1)<-0.2){
      rotate.set(-Robot.m_oi.driverJoystick.getRawAxis(1));
    }
    else{
      rotate.set(0);
    } 
    /*if(Robot.m_oi.driverJoystick2.getRawAxis(1)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(1)<-0.2){
      hookA.set(-Robot.m_oi.driverJoystick2.getRawAxis(1));
    }
    else{
      hookA.set(0);
    }
    if(Robot.m_oi.driverJoystick2.getRawAxis(5)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(5)<-0.2){
      hanger.set(-Robot.m_oi.driverJoystick2.getRawAxis(5));
    }
    else{
      hanger.set(0);
    }*/
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

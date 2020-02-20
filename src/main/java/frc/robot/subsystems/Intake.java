/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Intake功能(靖凱)
//手動Lift:1RT/1LT
//單向Spin:1X 反向1B
//一鍵Encoder:

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {

  private WPI_VictorSPX intakeLift = new WPI_VictorSPX(Constants.intakeLiftID);
  private WPI_VictorSPX intakeSpin = new WPI_VictorSPX(Constants.intakeSpinID);

  public Intake() {

  }

  @Override
  public void periodic() {
    /*
    if(Robot.m_oi.driverJoystick.getXButton()){
      intakeSpin.set(Constants.intakeSpinSpeed);
    }
    else if(Robot.m_oi.driverJoystick.getBButton()){
      intakeSpin.set(-1*Constants.intakeSpinSpeed);
    }
    else{
      intakeSpin.set(0);
    }

    if(Robot.m_oi.driverJoystick.getRawAxis(3)>0.2){
      intakeLift.set(Robot.m_oi.driverJoystick.getRawAxis(3));
    }
    else if(Robot.m_oi.driverJoystick.getRawAxis(2)>0.2){
      intakeLift.set(-Robot.m_oi.driverJoystick.getRawAxis(2));
    }
    else{
      intakeLift.set(0);
    }
    */
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private WPI_TalonSRX intakeLift = new WPI_TalonSRX(Constants.intakeLiftID);
  private WPI_TalonSRX intakeSpin = new WPI_TalonSRX(Constants.intakeSpinID);
  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(Robot.m_oi.driverJoystick.getXButton()){//單向Spin
      intakeSpin.set(Constants.intakeSpinSpeed);
    }
    if(Robot.m_oi.driverJoystick.getBButton()){
      intakeSpin.set(-1*Constants.intakeSpinSpeed);
    }
    else{
      intakeSpin.set(0);
    }

    if(Robot.m_oi.driverJoystick.getRawAxis(3)<-0.2){
      intakeLift.set(Robot.m_oi.driverJoystick.getRawAxis(3));
    }
    else if(Robot.m_oi.driverJoystick.getRawAxis(2)<-0.2){
      intakeLift.set(-Robot.m_oi.driverJoystick.getRawAxis(2));
    }
    else{
      intakeLift.set(0);
    }
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

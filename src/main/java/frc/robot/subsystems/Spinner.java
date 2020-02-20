/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Spinner:(靖凱)
//Spin:1LB/1RB
//Lift:1A/1Y


package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
import frc.robot.Robot;

public class Spinner extends SubsystemBase {
  /**
   * Creates a new Spinner.
   */
  private WPI_VictorSPX spinnerLift = new WPI_VictorSPX(Constants.spinnerLiftID);
  private WPI_VictorSPX spinnerSpin = new WPI_VictorSPX(Constants.spinnerSpinID);

  public Spinner() {

  }

  @Override
  public void periodic() {
    /*
    // This method will be called once per scheduler run
    if(Robot.m_oi.driverJoystick.getYButton()){//單向Spin
      spinnerLift.set(1);
    }
    else if(Robot.m_oi.driverJoystick.getAButton()){
      spinnerLift.set(-1);
    }
    else{
      spinnerLift.set(0);
    }

    if(Robot.m_oi.driverJoystick.getRawButton(5)){
      spinnerSpin.set(1);
    }
    else if(Robot.m_oi.driverJoystick.getRawButton(6)){
      spinnerSpin.set(-1);
    }
    else{
      spinnerSpin.set(0);
    }
    */
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

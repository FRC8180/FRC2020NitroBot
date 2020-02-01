/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Chassis extends SubsystemBase {
  private WPI_TalonSRX motorRF;
  private WPI_TalonSRX motorRB;
  private WPI_TalonSRX motorLF;
  private WPI_TalonSRX motorLB;

  public Chassis() {
    motorRF = new WPI_TalonSRX(Constants.chassisMotorRFID);
    motorRB = new WPI_TalonSRX(Constants.chassisMotorRBID);
    motorLF = new WPI_TalonSRX(Constants.chassisMotorLFID);
    motorLB = new WPI_TalonSRX(Constants.chassisMotorLBID);
    motorRF.setInverted(Constants.chassisMotorRInverted);
    motorLF.setInverted(Constants.chassisMotorLInverted);
    motorRB.follow(motorRF);
    motorLB.follow(motorLF);
  }

  public void setMotorSpeed(double Lspd, double Rspd){
    motorLF.set(Lspd);
    motorRF.set(Rspd);
  }

  public void setMotorVoltage(double Lvoltage, double Rvoltage){
    motorLF.setVoltage(Lvoltage);
    motorRF.setVoltage(Rvoltage);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

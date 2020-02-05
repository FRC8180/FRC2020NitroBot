/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import frc.robot.Vision.Vision;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Chassis extends SubsystemBase {
  private WPI_TalonSRX motorRF;
  private WPI_TalonSRX motorRB;
  private WPI_TalonSRX motorLF;
  private WPI_TalonSRX motorLB;

  private AHRS ahrs;

  public Chassis() {
    motorRF = new WPI_TalonSRX(Constants.chassisMotorRFID);
    //motorRB = new WPI_TalonSRX(Constants.chassisMotorRBID);
    motorLF = new WPI_TalonSRX(Constants.chassisMotorLFID);
    //motorLB = new WPI_TalonSRX(Constants.chassisMotorLBID);
    //motorRF.setInverted(Constants.chassisMotorRInverted);
    //motorLF.setInverted(Constants.chassisMotorLInverted);
    //motorRB.follow(motorRF);
    //motorLB.follow(motorLF);
    
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }

  public void setMotorSpeed(double Lspd, double Rspd){
    if(Constants.chassisMotorLInverted){
      motorLF.set(-Lspd);
    }
    else{
      motorLF.set(Lspd);
    }

    if(Constants.chassisMotorRInverted){
      motorRF.set(-Rspd);
    }
    else{
      motorRF.set(Rspd);
    }
    
    
  }

  public void setMotorVoltage(double Lvoltage, double Rvoltage){
    motorLF.setVoltage(Lvoltage);
    motorRF.setVoltage(Rvoltage);
  } 

  public double get_Angle() {
    return 0; //ahrs.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

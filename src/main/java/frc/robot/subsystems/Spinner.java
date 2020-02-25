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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
//import frc.robot.Robot;

public class Spinner extends SubsystemBase {
  
  private WPI_VictorSPX liftMotor;
  private WPI_VictorSPX spinMotor;

  private DigitalInput zeroLimitSw;

  public Spinner() {
    liftMotor = new WPI_VictorSPX(Constants.spinnerLiftMotorID);
    spinMotor = new WPI_VictorSPX(Constants.spinnerSpinMotorID);
    liftMotor.setInverted(Constants.spinnerLiftMotorInverted);
    spinMotor.setInverted(Constants.spinnerSpinMotorInverted);
    zeroLimitSw = new DigitalInput(Constants.spinnerZeroLimitSwID);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    super.setDefaultCommand(defaultCommand);
  }

  public void setLiftMotorSpeed(double spd){
    /*
    if(getZeroLimitSw()){
      liftMotor.set(0);
    }else{
      liftMotor.set(spd);
    }
    */
    liftMotor.set(spd);
  }
  public void setLiftMotorStop(){
    liftMotor.set(0);
  }

  public void setSpinMotorSpeed(double spd){
    spinMotor.set(0);
  }
  public void setSpinMotorStop(){
    spinMotor.set(0);
  }

  public boolean getZeroLimitSw(){
    return zeroLimitSw.get();
  }

}

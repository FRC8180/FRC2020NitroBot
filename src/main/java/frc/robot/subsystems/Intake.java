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
//import frc.robot.Robot;

public class Intake extends SubsystemBase {

  private WPI_VictorSPX liftMotor;
  private WPI_VictorSPX spinMotor;

  public Intake() {
    liftMotor = new WPI_VictorSPX(Constants.intakeLiftMotorID);
    spinMotor = new WPI_VictorSPX(Constants.intakeSpinMotorID);
    liftMotor.setInverted(Constants.intakeLiftMotorInverted);
    spinMotor.setInverted(Constants.intakeSpinMotorInverted);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    super.setDefaultCommand(defaultCommand);
  }

  public void setLiftMotorSpeed(double spd){
    liftMotor.set(spd);
  }
  public void setLiftMotorStop(){
    liftMotor.set(0);
  }

  public void setSpinMotorSpeed(double spd){
    spinMotor.set(spd);
  }
  public void setSpinMotorStop(){
    spinMotor.set(0);
  }
}

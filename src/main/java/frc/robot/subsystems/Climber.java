/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import java.security.PublicKey;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private WPI_TalonSRX liftMotor;
  private Encoder liftEcoder;
  //private DigitalInput liftUpSwitch;
  //private DigitalInput liftDownSwitch;
  private PIDController liftPIDControl;
  private double liftPIDSetpoint;
  private boolean liftPIDIsEnable;

  public Climber() {
    //liftUpSwitch = new DigitalInput(0);
    //liftDownSwitch = new DigitalInput(0);
    liftEcoder = new Encoder(Constants.liftEncoderPinA,Constants.liftEncoderPinB,Constants.liftlEncoderReverse);
    liftPIDControl = new PIDController(0.1, 0, 0);
    liftMotor = new WPI_TalonSRX(Constants.liftMotorID);
    liftPIDIsEnable = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /*
    if(liftPIDIsEnable){
      liftPIDOutput(liftPIDControl.calculate(getliftPIDMeasurment(), liftPIDSetpoint));
    }
    */
  }

  public void setLiftMotorSpeed(double speed){
    liftMotor.set(speed);
  }

/*
  public void encoderReset(){
    liftEcoder.reset();
  }

  public void setSetPoint(double setPoint){
    liftPIDSetpoint = setPoint;
  }

  public void liftPIDEnable(){
    liftPIDIsEnable = true;
  }

  public void liftPIDDisable(){
    liftPIDIsEnable = false;
    liftPIDOutput(0);
  }

  public void liftPIDOutput(double output){
    liftMotor.setVoltage(output);
  } 

  public double getliftPIDMeasurment(){
    return liftEcoder.get();
  }
*/
  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

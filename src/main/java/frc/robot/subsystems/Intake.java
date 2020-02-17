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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private WPI_TalonSRX intakeMotor ;
  private WPI_TalonSRX intakeRiserMotor ;
  private Encoder intakeRiserEcoder;
  private PIDController intakeRiserPIDControl;
  private double intakeRiserPIDSetpoint;
  private boolean intakeRiserPIDIsEnable;
  private boolean intakeRiserIsRising;

  public Intake() {
    intakeMotor = new WPI_TalonSRX(Constants.intakeMotorID);
    intakeRiserMotor = new WPI_TalonSRX(Constants.intakeRiserMotorID); 
    intakeRiserEcoder = new Encoder(Constants.intakeRiserEncoderPinA,Constants.intakeRiserEncoderPinB,Constants.intakeRiserlEncoderReverse);
    intakeRiserPIDControl = new PIDController(0.1, 0, 0);
    intakeRiserPIDIsEnable = false;
    intakeRiserPIDControl.setTolerance(10);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(intakeRiserPIDIsEnable){
      intakeRiserPIDOutput(intakeRiserPIDControl.calculate(getintakeRiserPIDMeasurment(), intakeRiserPIDSetpoint));
    }
  }
  public void setIntakeMotorSpeed(double speed){
    intakeMotor.set(speed);
  }
  public boolean intakeRiserIsRising(){
    if(atSetpoint() && (intakeRiserPIDSetpoint == 256)){
      intakeRiserIsRising = true;
    }else if(atSetpoint() && (intakeRiserPIDSetpoint == 0)){
      intakeRiserIsRising = false;
    }else{
      intakeRiserPIDEnable();
      if(atSetpoint()){
        intakeRiserPIDDisable();
      }
      if(atSetpoint() && (intakeRiserPIDSetpoint == 256)){
        intakeRiserIsRising = true;
      }else if(atSetpoint() && (intakeRiserPIDSetpoint == 0)){
        intakeRiserIsRising = false;
      }
    }
    return intakeRiserIsRising;
  }

  public void encoderReset(){
    intakeRiserEcoder.reset();
  }

  public double getSetpoint(){
    return intakeRiserPIDSetpoint;
  }

  public void setSetPoint(double setPoint){
    intakeRiserPIDSetpoint = setPoint;
  }

  public void intakeRiserPIDEnable(){
    intakeRiserPIDIsEnable = true;
  }

  public boolean intakeRiserPIDIsEnable() {
    return intakeRiserPIDIsEnable;
  }

  public void intakeRiserPIDDisable(){
    intakeRiserPIDIsEnable = false;
    intakeRiserPIDOutput(0);
  }

  public boolean atSetpoint(){
    return intakeRiserPIDControl.atSetpoint();
  }

  public void intakeRiserPIDOutput(double output){
    intakeRiserMotor.setVoltage(output);
  } 

  public double getintakeRiserPIDMeasurment(){
    return intakeRiserEcoder.get();
  }

  public void setIntakeRiserMotorSpeed(double speed) {
    intakeRiserMotor.set(speed);
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

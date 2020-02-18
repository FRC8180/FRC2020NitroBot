/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.PIDController;


public class CPIntake extends SubsystemBase {
  /**
   * Creates a new CPIntake.
   */

  private WPI_TalonSRX intake;
  private WPI_TalonSRX rise;
  private Encoder riseEncoder;
  private PIDController risePIDController;

  private boolean risePIDEnable = false;
  private double risePIDSetpoint;
  
  
  public CPIntake() {
    intake = new WPI_TalonSRX(Constants.CPintakeMotor);
    rise = new WPI_TalonSRX(Constants.CPrise);
    riseEncoder =new Encoder(Constants.CPIntakeEncoderPinA,Constants.CPIntakeEncoderPinB,Constants.CPIntakeEncoderReverse);
    risePIDController =new PIDController(0.1, 0, 0);
    risePIDController.enableAutoStop(10,0.25);
  }

  @Override
  public void periodic() {
    if(risePIDIsEnable()){
      risePIDOutput(risePIDController.calculate(getrisePIDMeasurment(), risePIDSetpoint));
    }
    // This method will be called once per scheduler run
  }

  //PID
  public boolean risePIDIsEnable(){
    return risePIDEnable;
  }

  public void risePIDEnable(){
    risePIDEnable = true;
  }

  public boolean atSetpoint(){
    return risePIDController.finished();
  }

  public void risePIDDisable(){
    risePIDEnable = false;
    risePIDOutput(0);
  }

  public double getrisePIDMeasurment(){
    return riseEncoder.get();
  }

  public void setriseSetpoint(double setpoint){
    risePIDSetpoint = setpoint;
  }
  
  public void risePIDOutput(double output){
    rise.setVoltage(output);
  }

  public void risePIDReset(){
    riseEncoder.reset();
    risePIDController.reset();
  }

  //basic
  public void setIntakeMotorSpeed(double speed){
    intake.set(speed);
  }

  public void setRiseMotorSpeed(double speed){
    rise.set(speed);
  }

  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Shooter(鵬嘉)
//Shoot(High):2LT
//Container:2LX
//
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  
  private final PIDController upperPID;
  private final PIDController lowerPID;
  private boolean upperPIDEnable = false;
  private boolean lowerPIDEnable = false;
  private double upperPIDSetpoint = 0;
  private double lowerPIDSetpoint = 0;

  private WPI_TalonSRX upperMotor = new WPI_TalonSRX(Constants.shooterUpperMotorID);
  private WPI_TalonSRX lowerMotor = new WPI_TalonSRX(Constants.shooterLowerMotorID);;
  private WPI_TalonSRX container = new WPI_TalonSRX(Constants.shooterContainerID);
  private WPI_TalonSRX smallMotor = new WPI_TalonSRX(Constants.shooterSmallID);  
  private Encoder upperEncoder;
  private Encoder lowerEncoder;
  private Timer timer;

  private double upperPreviousRotation = 0;
  private double upperPreviousTime = 0;
  private double lowerPreviousRotation = 0;
  private double lowerPreviousTime = 0;

  public Shooter() {
    upperPID = new PIDController(0.1, 0, 0.15);
    lowerPID = new PIDController(1, 0.1, 0.01);

    //upperMotor = new WPI_TalonSRX(Constants.shooterUpperMotorID);
    //lowerMotor = new WPI_TalonSRX(Constants.shooterLowerMotorID);
    upperMotor.setInverted(Constants.shooterUpperMotorInverted);
    lowerMotor.setInverted(Constants.shooterLowerMotorInverted);

    upperEncoder = new Encoder(Constants.shooterUpperEncoderPinA,Constants.shooterUpperEncoderPinB,Constants.shooterUpperEncoderDirectionInverted);
    lowerEncoder = new Encoder(Constants.shooterLowerEncoderPinA,Constants.shooterLowerEncoderPinB,Constants.shooterLowerEncoderDirectionInverted);
    upperEncoder.reset();
    lowerEncoder.reset();

    timer = new Timer();
    timer.reset();
    timer.start();
  }

  @Override
  public void periodic() {
    if(Robot.m_oi.driverJoystick2.getRawAxis(2)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(2)<-0.2){
      upperMotor.set(-0.35);
      lowerMotor.set(0.8);
    }
    else{
      upperMotor.set(0);
      lowerMotor.set(0);      
    }
    if(Robot.m_oi.driverJoystick2.getRawAxis(3)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(3)<-0.2){
      smallMotor.set(0.3);
    }
    else{
      smallMotor.set(0);     
    }
    if(Robot.m_oi.driverJoystick2.getRawAxis(0)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(0)<-0.2){
      container.set(-Robot.m_oi.driverJoystick2.getRawAxis(0));
    }
    else{
      container.set(0);
    }

    /*if(upperPIDEnable){
      upperPIDOutput(upperPID.calculate(getUpperPIDMeasurment(), upperPIDSetpoint));
    }
    if(lowerPIDEnable){
      lowerPIDOutput(lowerPID.calculate(getLowerPIDMeasurment(), lowerPIDSetpoint));
    }*/

  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }

  public boolean upperPIDIsEnable(){
    return upperPIDEnable;
  }

  public boolean lowerPIDIsEnable(){
    return lowerPIDEnable;
  }
  
  public void upperPIDEnable(){
    upperPIDEnable = true;
    upperPID.reset();
  }

  public void lowerPIDEnable(){
    lowerPIDEnable = true;
    lowerPID.reset();
  }

  public void upperPIDDisable(){
    upperPIDEnable = false;
    upperPIDOutput(0);
  }
  
  public void lowerPIDDisable(){
    lowerPIDEnable = false;
    lowerPIDOutput(0);
  }

  public void setUpperPIDSetpoint(double setpoint){
    upperPIDSetpoint = setpoint;
  }

  public void setLowerPIDSetpoint(double setpoint){
    lowerPIDSetpoint = setpoint;
  }

  public void upperPIDReset(){
    upperPID.reset();
  }

  public void lowerPIDReset(){
    lowerPID.reset();
  }
  
  public void upperPIDOutput(double output){
    upperMotor.setVoltage(output);
  }

  public void lowerPIDOutput(double output){
    lowerMotor.setVoltage(output);
  }

  public double getUpperPIDMeasurment(){
    double rotation = upperEncoder.get();
    double time = timer.get();
    double deltaRotation = rotation - upperPreviousRotation;
    double deltaTime = time - upperPreviousTime;
    upperPreviousRotation = rotation;
    upperPreviousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterUpperEncoderPPR;
    return RPS;
  }

  public double getLowerPIDMeasurment(){
    double rotation = lowerEncoder.get();
    double time = timer.get();
    double deltaRotation = rotation - lowerPreviousRotation;
    double deltaTime = time - lowerPreviousTime;
    lowerPreviousRotation = rotation;
    lowerPreviousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterLowerEncoderPPR;
    return RPS;
  }
  
  public void setUpperSpeed(double speed){
    upperPIDEnable = false;
    upperMotor.set(speed);
  }

  public void setLowerSpeed(double speed){
    lowerPIDEnable = false;
    lowerMotor.set(speed);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//BasicDrive:
//drive:1LY 1RX

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Network;
import frc.robot.Robot;


public class Chassis extends SubsystemBase {
  private PIDController PID;
  private boolean PIDEnable = false;
  private double PIDSetpoint = 0;

  private PIDController aimPID;
  private boolean aimPIDEnable = false;
  private double aimPIDSetpoint = 0;

  private Timer timer;
  private AHRS navx;
  private Network network;

  private WPI_TalonSRX motorRF = new WPI_TalonSRX(Constants.chassisMotorRFID);
  private WPI_TalonSRX motorRB = new WPI_TalonSRX(Constants.chassisMotorRBID);
  private WPI_TalonSRX motorLF = new WPI_TalonSRX(Constants.chassisMotorLFID);
  private WPI_TalonSRX motorLB = new WPI_TalonSRX(Constants.chassisMotorLBID);

  private double lockAngle = 0;

  public Chassis() {
    PID = new PIDController(0.1, 0, 0.01);
    aimPID = new PIDController(0.025, 0, 0.01);

    motorRF = new WPI_TalonSRX(Constants.chassisMotorRFID);
    motorRB = new WPI_TalonSRX(Constants.chassisMotorRBID);
    motorLF = new WPI_TalonSRX(Constants.chassisMotorLFID);
    motorLB = new WPI_TalonSRX(Constants.chassisMotorLBID);
    motorRF.setInverted(Constants.chassisMotorRInverted);
    motorLF.setInverted(Constants.chassisMotorLInverted);
    motorRB.follow(motorRF);
    motorLB.follow(motorLB);

    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();

    timer = new Timer();
    timer.reset();
    timer.start();

    network = new Network();
  }

  @Override
  public void periodic() {
    if(PIDEnable){
      PIDOutput(PID.calculate(PIDMeasurment(), PIDSetpoint));
    }
    if(aimPIDEnable){
      aimPIDOutput(aimPID.calculate(aimPIDMeasurment(), aimPIDSetpoint));
    }
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }

  //Motor control function
  public void setMotorSpeed(double Lspd, double Rspd){
    if(Robot.m_oi.getARawButton(Constants.buttonOption)){
      motorLF.set(Lspd * Constants.chassisMotorSlowModeSpeedScale);
      motorRF.set(Rspd * Constants.chassisMotorSlowModeSpeedScale);
    }else{
      motorLF.set(Lspd * Constants.chassisMotorNormalModeSpeedScale);
      motorRF.set(Rspd * Constants.chassisMotorNormalModeSpeedScale);
    }
  }
  public void setMotorVoltage(double Lvoltage, double Rvoltage){
    motorLF.setVoltage(Lvoltage);
    motorRF.setVoltage(Rvoltage);
  }
  public void setMotorStop(){
    setMotorSpeed(0, 0);
  }

  //NavX function
  public double getRawAngle(){
    return navx.getAngle() % 360;
  }
  public double getCalAngle(){
    return (((((getRawAngle() + (360 - lockAngle)) % 360) + 180) % 360) - 180);
  }
  public void setLockAngle(double angle){
    lockAngle = angle;
  }

  //Heading control PID function
  public void PIDEnable(){
    PIDEnable = true;
    PID.reset();
  }
  public void PIDDisable(){
    PIDEnable = false;
    PIDOutput(0);
  }
  public void PIDReset(){
    PIDSetpoint = 0;
    PID.reset();
  }
  public boolean PIDIsEnable(){
    return PIDEnable;
  }
  public void PIDSetSetpoint(double setpoint){
    PIDSetpoint = setpoint;
  }
  public void PIDSetTolerance(double value){
    PID.setTolerance(value);
  }
  public double PIDMeasurment(){
    return getCalAngle();
  }
  public void PIDOutput(double output){
    double Rspd = Robot.m_oi.getALY() - output;
    double Lspd = Robot.m_oi.getALY() + output;
    setMotorSpeed(Lspd, Rspd);
  }

  //Aim target PID function
  public void aimPIDEnable(){
    aimPIDEnable = true;
    aimPID.reset();
  }
  public void aimPIDDisable(){
    aimPIDEnable = false;
    aimPIDOutput(0);
  }
  public void aimPIDReset(){
    aimPID.reset();
  }
  public boolean aimPIDIsEnable(){
    return aimPIDEnable;
  }
  public void aimPIDSetSetpoint(double setpoint){
    aimPIDSetpoint = setpoint;
  }
  public void aimPIDSetTolerance(double value){
    aimPID.setTolerance(value);
  }
  public double aimPIDMeasurment(){
    return (network.ntGetDouble("Vision", "h_angle"));
  }
  public void aimPIDOutput(double output){
    double Rspd = Robot.m_oi.getALY() + output;
    double Lspd = Robot.m_oi.getALY() - output;
    setMotorSpeed(Lspd, Rspd);
  }
  public boolean aimPIDIsStable(){
    return aimPID.atSetpoint();
  }
}

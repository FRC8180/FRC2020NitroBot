/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Network;
import frc.robot.PIDController;
import frc.robot.Robot;


public class Chassis extends SubsystemBase {
  private PIDController PID;
  private double PIDSetpoint = 0;

  private PIDController aimPID;
  private boolean aimPIDEnable = false;
  private double aimPIDSetpoint = 0;

  private Timer timer;
  private AHRS navx;
  private Network network;

  private WPI_TalonSRX motorRF;
  private WPI_TalonSRX motorRB;
  private WPI_TalonSRX motorLF;
  private WPI_TalonSRX motorLB;

  private double lockAngle = 0;

  public Chassis() {

    PID = new PIDController(0.1, 0, 0.01);
    PID.enable();

    aimPID = new PIDController(0.025, 0, 0.01);
    aimPID.enable();
    aimPID.enableAutoStop(1, 0.25);
    aimPID.enableTimeOut(2);

    motorRF = new WPI_TalonSRX(Constants.chassisMotorRFID);
    motorRB = new WPI_TalonSRX(Constants.chassisMotorRBID);
    motorLF = new WPI_TalonSRX(Constants.chassisMotorLFID);
    motorLB = new WPI_TalonSRX(Constants.chassisMotorLBID);
    motorRF.setInverted(Constants.chassisMotorRInverted);
    motorLF.setInverted(Constants.chassisMotorLInverted);
    motorRB.follow(motorRF);
    motorLB.follow(motorLF);

    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();

    timer = new Timer();
    timer.reset();
    timer.start();

    network = new Network();
  }

  @Override
  public void periodic() {
    PIDOutput(PID.calculate(PIDMeasurment(), PIDSetpoint));
    aimPIDOutput(aimPID.calculate(aimPIDMeasurment(), aimPIDSetpoint));
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }


  public void PIDEnable(){
    PID.enable();
  }
  public void PIDDisable(){
    PID.disable();
    PIDOutput(0);
  }
  public void PIDReset(){
    PIDSetpoint = 0;
    PID.reset();
  }
  public boolean PIDIsEnable(){
    return PID.isEnable();
  }
  public void PIDSetSetpoint(double setpoint){
    PIDSetpoint = setpoint;
  }
  public double PIDMeasurment(){
    return getCalAngle();
  }
  public void PIDOutput(double output){
    double Rspd = Robot.m_oi.getLY() - output;
    double Lspd = Robot.m_oi.getLY() + output;
    setMotorSpeed(Lspd, Rspd);
  }


  public void setMotorSpeed(double Lspd, double Rspd){
    if(Robot.m_oi.getRawAxis(Constants.axisRT) > Constants.chassisPIDRestartTime){
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


  public double getRawAngle(){
    return navx.getAngle() % 360;
  }
  public double getCalAngle(){
    return (((((getRawAngle() + (360 - lockAngle)) % 360) + 180) % 360) - 180);
  }
  public void setLockAngle(double angle){
    lockAngle = angle;
  }

  
  public void aimPIDEnable(){
    aimPID.enable();
  }
  public void aimPIDDisable(){
    aimPID.disable();
    aimPIDOutput(0);
  }
  public void aimPIDReset(){
    aimPID.reset();
  }
  public boolean aimPIDIsEnable(){
    return aimPID.isEnable();
  }
  public void aimPIDSetSetpoint(double setpoint){
    aimPIDSetpoint = setpoint;
  }
  public double aimPIDMeasurment(){
    return (network.ntGetDouble("Vision", "h_angle"));
  }
  public void aimPIDOutput(double output){
    double Rspd = Robot.m_oi.getLY() + output;
    double Lspd = Robot.m_oi.getLY() - output;
    setMotorSpeed(Lspd, Rspd);
  }
  public boolean aimPIDIsStable(){
    return aimPID.finished();
  }
}

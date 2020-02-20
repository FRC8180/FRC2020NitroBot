/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class PIDController {

  private Timer timer;

  private boolean funcEnable = true;
  private boolean funcAntiWindup = false;
  private boolean funcAutoStop = false;
  private boolean funcTimeOut = false;

  private byte steadyStatus = 0;
  private double steadyTolerance = 0;
  private double steadyHoldTime = 0;
  private double steadyPreviousTime = 0;

  private double maxOutput = 0;

  private double timeOut = 0;
  
  private double Kp = 0;
  private double Ki = 0;
  private double Kd = 0;

  private double error = 0;
  private double previousError = 0;
  private double intergral = 0;
  private double derivative = 0;

  public PIDController(double Kp, double Ki, double Kd){
    timer = new Timer();
    timer.reset();
    timer.start();

    this.Kp = Kp;
    this.Ki = Ki;
    this.Kd = Kd;
  }

  public void reset(){
    error = 0;
    previousError = 0;
    intergral = 0;
    derivative = 0;
  }

  public void enable(){
    funcEnable = true;
    this.reset();
  }
  public void disable(){
    funcEnable = false;
  }
  public boolean isEnable(){
    return funcEnable;
  }

  //AntiWindup Setting
  public void enableAntiWindup(double maxOutput){
    funcAntiWindup = true;
    this.maxOutput = maxOutput;
  }
  public void disableAntiWindup(){
    funcAntiWindup = false;
    this.maxOutput = 0;
  }
  public boolean isEnableAntiWindup(){
    return funcAntiWindup;
  }

  //AutoStop Setting
  public void enableAutoStop(double steadyTolerance, double steadyHoldTime){
    funcAutoStop = true;
    this.steadyTolerance = steadyTolerance;
    this.steadyHoldTime = steadyHoldTime;
  }
  public void disableAutoStop(){
    funcAutoStop = false;
    this.steadyTolerance = 0;
    this.steadyHoldTime = 0;
  }
  public boolean isEnableAutoStop(){
    return funcAutoStop;
  }

  //TimeOut Setting
  public void enableTimeOut(double timeOut){
    funcTimeOut = true;
    this.timeOut = timeOut;
  }
  public void disableTimeOut(){
    funcTimeOut = false;
    this.timeOut = 0;
  }
  public boolean isEnableTimeOut(){
    return funcTimeOut;
  }

  public double calculate(double measurment, double setpoint){
    if(funcEnable){
      error = setpoint - measurment;
      intergral = intergral + error;
      derivative = error - previousError;
      previousError = error;
      double output = Kp * error + Ki * intergral + Kd * derivative;
      if(funcAntiWindup){
        //Same Sign Detect
        if((error > 0 && output > 0) || (error < 0 && output < 0)){
          //Saturation Detect
          if(output > maxOutput){
            intergral = 0;
            output = Kp * error + Kd * derivative;
          }
        }
      }
      return output;
    }else{
      return 0;
    }
  }

  public boolean finished(){
    double nowTime = timer.get();
    if(funcAutoStop){
      boolean nowSteady = Math.abs(error) < steadyTolerance;
      boolean nowSteadyFinished = (nowTime - steadyPreviousTime) > steadyHoldTime;
      if(!nowSteady){
        steadyStatus = 0;
      }else if(steadyStatus == 0 && nowSteady){
        steadyStatus = 1;
        steadyPreviousTime = nowTime;
      }else if(steadyStatus == 1 && nowSteadyFinished){
        steadyStatus = 0;
        return true;
      }
    }
    if(funcTimeOut){
      if(nowTime > timeOut){
        return true;
      }
    }
    return false;
  }
}

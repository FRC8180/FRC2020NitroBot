/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Climber(鵬嘉)
//hook:2LY
//hanger:2RY
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Robot;

public class Climber extends SubsystemBase {

  private WPI_TalonSRX hookF;
  private WPI_TalonSRX hookB;
  private WPI_TalonSRX lift;

  private DigitalInput liftTopLimitSw;
  private DigitalInput liftBottomSwLimitSw;

  public Climber() {
    hookF = new WPI_TalonSRX(Constants.climberHookFrontMotorID);
    hookB = new WPI_TalonSRX(Constants.climberHookBackMotorID);
    lift = new WPI_TalonSRX(Constants.climberLiftMotorID);
    hookF.setInverted(Constants.climberHookMotorInverted);
    lift.setInverted(Constants.climberLiftMotorInverted);
    hookB.follow(hookF);

    liftTopLimitSw = new DigitalInput(0);
    liftBottomSwLimitSw = new DigitalInput(0);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }

  //Hook motor function
  public void setHookMotorSpeed(double spd){
    hookF.set(spd);
  }
  public void setHookMotorVoltage(double voltage){
    hookF.setVoltage(voltage);
  }
  public void setHookMotorStop(){
    hookF.set(0);
  }

  //Hanger motor function
  public void setLiftMotorSpeed(double spd){
    /* LimitSw Protect Feature
    if(liftTopLimitSw.get() || liftBottomSwLimitSw.get()){
      lift.set(0);
    }else{
      lift.set(spd);
    }
    */
    lift.set(spd);
  }
  public void setLiftMotorVoltage(double voltage){
    /* LimitSw Protect Feature
    if(liftTopLimitSw.get() || liftBottomSwLimitSw.get()){
      lift.setVoltage(0);
    }else{
      lift.setVoltage(voltage);
    }
    */
    lift.setVoltage(voltage);
  }
  public void setLiftMotorStop(){
    lift.set(0);
  }
  
}

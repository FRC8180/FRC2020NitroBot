/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Shooter extends PIDSubsystem {
  
  private WPI_TalonSRX upperMotor;
  private WPI_TalonSRX lowerMotor;
  private Encoder lowerEncoder;
  private Timer timer;

  private double previousRotation;
  private double previousTime;
  public Shooter() {
    super(new PIDController(1.5, 0.15, 0.05));
    upperMotor = new WPI_TalonSRX(Constants.shooterUpperMotor);
    lowerMotor = new WPI_TalonSRX(Constants.shooterLowerMotor);
    upperMotor.setInverted(Constants.shooterUpperMotorInvert);
    lowerMotor.setInverted(Constants.shooterLowerMotorInvert);
    lowerEncoder = new Encoder(7,8,true);
    lowerEncoder.reset();
    timer = new Timer();
    timer.reset();
    timer.start();
    enable();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    lowerMotor.setVoltage(output);
  }

  @Override
  public double getMeasurement() {
    double rotation = (lowerEncoder.get());
    double time = timer.get();
    double deltaRotation = rotation - this.previousRotation;
    double deltaTime = time - this.previousTime; 
    this.previousRotation = rotation;
    this.previousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterLowerEncoderPPR; 
    return RPS;
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }

  //public void setSetpoint(double value) {
    //setSetpoint(value);
  //}

  public void setLowerSpeed(double speed){
    lowerMotor.set(speed);
  }

}

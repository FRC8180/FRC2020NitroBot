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



public class CPIntake extends SubsystemBase {
  /**
   * Creates a new CPIntake.
   */

  private WPI_TalonSRX intake;
  private WPI_TalonSRX rase;
  
  public CPIntake() {
    intake = new WPI_TalonSRX(Constants.CPintakeMotor);
    rase = new WPI_TalonSRX(Constants.CPRase);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntakeMotorSpeed(double speed){
    intake.set(speed);
  }

  public void setRaseMotorSpeed(double speed){
    rase.set(speed);
  }

  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Robot;

public class CPbot extends SubsystemBase {
  private WPI_TalonSRX CPR = new WPI_TalonSRX(Constants.CPLID);
  private WPI_TalonSRX CPL = new WPI_TalonSRX(Constants.CPRID);
  private WPI_TalonSRX CPLift = new WPI_TalonSRX(Constants.CPLiftID);
  private WPI_TalonSRX CPIntake = new WPI_TalonSRX(Constants.CPIntakeID);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(CPL, CPR);
  public CPbot() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_robotDrive.arcadeDrive(Robot.m_oi.driverJoystick.getRawAxis(1), Robot.m_oi.driverJoystick.getRawAxis(4));
    
    if(Robot.m_oi.driverJoystick.getXButton()){
      CPIntake.set(1);
    }
    else if(Robot.m_oi.driverJoystick.getYButton()){
      CPIntake.set(-1);
    }
    else{
      CPIntake.set(0);
    }

    if(Robot.m_oi.driverJoystick.getRawAxis(5)>0.2||Robot.m_oi.driverJoystick.getRawAxis(5)<-0.2){
      CPLift.set(-Robot.m_oi.driverJoystick.getRawAxis(5));
    }
    else{
      CPLift.set(0);
    }
    
    /*if(Robot.m_oi.driverJoystick2.getRawAxis(1)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(1)<-0.2){
      hookA.set(-Robot.m_oi.driverJoystick2.getRawAxis(1));
    }
    else{
      hookA.set(0);
    }
    if(Robot.m_oi.driverJoystick2.getRawAxis(5)>0.2||Robot.m_oi.driverJoystick2.getRawAxis(5)<-0.2){
      hanger.set(-Robot.m_oi.driverJoystick2.getRawAxis(5));
    }
    else{
      hanger.set(0);
    }*/
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}

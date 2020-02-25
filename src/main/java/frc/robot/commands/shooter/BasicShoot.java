/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.Utility;
import frc.robot.subsystems.Shooter;

public class BasicShoot extends CommandBase {

  private final Shooter shooter;
  public BasicShoot(Shooter subsystem) {
    shooter = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    shooter.upperPIDDisable();
    shooter.lowerPIDDisable();
  }

  @Override
  public void execute() {
    double BLT = Robot.m_oi.getBRawAxis(Constants.axisLT);
    double ALX = Robot.m_oi.getARawAxis(Constants.axisJLX);

    if(Math.abs(BLT) > Constants.joystickDeadZone){
      shooter.setFeedMotorSpeed(0.3);
      shooter.setUpperMotorSpeed(1);
      shooter.setLowerMotorSpeed(1);
    }else{
      shooter.setFeedMotorSpeed(0);
      shooter.setUpperMotorSpeed(0);
      shooter.setLowerMotorSpeed(0);     
    }

    if(Math.abs(ALX) > Constants.joystickDeadZone){
      shooter.setContainerMotorSpeed(ALX);
    }else{
      shooter.setContainerMotorSpeed(0);
    }

    /*
    double upperSpeed = Robot.m_oi.getRawAxis(Constants.axisRT);
    double lowerSpeed = Robot.m_oi.getRawAxis(Constants.axisLT);
    SmartDashboard.putNumber("upperSpeed", upperSpeed);
    SmartDashboard.putNumber("lowerSpeed", lowerSpeed);
    shooter.setUpperSpeed(upperSpeed);
    shooter.setLowerSpeed(lowerSpeed);
    SmartDashboard.putNumber("upperNowSpeed", shooter.getUpperPIDMeasurment());
    SmartDashboard.putNumber("lowerNowSpeed", shooter.getLowerPIDMeasurment());
    SmartDashboard.putBoolean("Status", true);
    */
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setContainerMotorStop();
    shooter.setFeedMotorStop();
    shooter.setUpperMotorStop();
    shooter.setLowerMotorStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

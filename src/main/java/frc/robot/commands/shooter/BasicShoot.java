/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class BasicShoot extends CommandBase {

  private boolean finish = false;
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
    SmartDashboard.putBoolean("Status", true);
    shooter.setUpperSpeed(Robot.m_oi.getRawAxis(Constants.axisRT));
    shooter.setLowerSpeed(Robot.m_oi.getRawAxis(Constants.axisLT));
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Status", false);
    shooter.setUpperSpeed(0);
    shooter.setLowerSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return Robot.m_oi.getRawButton(Constants.buttonBack);
  }
}

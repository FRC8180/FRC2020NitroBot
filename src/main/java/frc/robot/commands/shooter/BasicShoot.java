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
  /**
   * Creates a new BasicShoot.
   */
  private Shooter shooter;
  public BasicShoot(Shooter subsystem) {
    shooter = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_oi.getRawButton(Constants.buttonB)){
      shooter.setSetpoint(25);
      shooter.enable();
    }else{
      shooter.setSetpoint(0);
      shooter.disable();
    }
    System.out.println(shooter.getMeasurement());
    SmartDashboard.putNumber("Now", shooter.getMeasurement());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setLowerSpeed(0);
    //
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;//Robot.m_oi.getRawButton(Constants.buttonOption);
  }
}

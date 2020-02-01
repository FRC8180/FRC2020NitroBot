/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ShooterSpeedTest extends CommandBase {
  /**
   * Creates a new ShooterSpeedTest.
   */
  
  private boolean finish = false;
  private Timer timer;
  private Shooter shooter;
  public ShooterSpeedTest(Shooter subsystem) {
    shooter = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.reset();
    timer.start();
    //shooter.upperPIDDisable();
    shooter.lowerPIDDisable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shooter.setUpperVoltage(timer.get());
    shooter.setLowerVoltage(timer.get());
    SmartDashboard.putBoolean("status", true);
    SmartDashboard.putNumber("upperRPS", shooter.getUpperPIDMeasurment());
    SmartDashboard.putNumber("lowerRPS", shooter.getLowerPIDMeasurment());
    finish = (timer.get()>=7);
    System.out.println(finish);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setLowerVoltage(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;//timer.get()>=10;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class AssistDrive extends CommandBase {

  private boolean disable = false;

  Chassis chassis;
  public AssistDrive(Chassis subsystem) {
    chassis = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    if(disable = true){
      
      disable = false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    disable = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

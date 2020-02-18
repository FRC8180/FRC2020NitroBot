/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2049 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {
    //Joystick index defined
    public static final byte buttonY = 4;
    public static final byte buttonA = 1;
    public static final byte buttonX = 3;
    public static final byte buttonB = 2;
    public static final byte buttonRB = 6;
    public static final byte buttonLB = 5;
    public static final byte buttonBack = 7;
    public static final byte buttonOption = 8;
    public static final byte buttonJR = 10;
    public static final byte buttonJL = 9;
    public static final byte axisJRX = 4;
    public static final byte axisJRY = 5;
    public static final byte axisJLX = 0;
    public static final byte axisJLY = 1;
    public static final byte axisRT = 3;
    public static final byte axisLT = 2;

    //Joystick Setting
    public static final boolean axisRXInvert = false;
    public static final boolean axisRYInvert = true;
    public static final boolean axisLXInvert = false;
    public static final boolean axisLYInvert = true;

    public static final double joystickDeadZone = 0.2;
    public static final double axisRXExp = 1;
    public static final double axisRXScale = 1;
    public static final double axisRYExp = 1;
    public static final double axisRYScale = 1;
    public static final double axisLXExp = 1;
    public static final double axisLXScale = 1;
    public static final double axisLYExp = 1;
    public static final double axisLYScale = 1;

    //Chassis Setting
    public static final byte chassisMotorRFID = 4; //ID
    public static final byte chassisMotorRBID = 11;  //ID
    public static final byte chassisMotorLFID = 3; //ID
    public static final byte chassisMotorLBID = 13; //ID
    public static final boolean chassisMotorRInverted = true;
    public static final boolean chassisMotorLInverted = false;
    public static final double chassisMotorNormalModeSpeedScale = 0.5;
    public static final double chassisMotorSlowModeSpeedScale = 0.3;
    public static final double chassisPIDRestartTime = 0.25;

    //Shooter Setting
    public static final byte shooterUpperMotorID = 2; //ID
    public static final byte shooterLowerMotorID = 1; //ID
    public static final byte helperID = 1; //ID
    public static final boolean shooterUpperMotorInverted = true;
    public static final boolean shooterLowerMotorInverted = false;
    public static final double shooterUpperMotorMaxPIDRPS = 60;
    public static final double shooterLowerMotorMaxPIDRPS = 60;
    public static final int shooterUpperEncoderPPR = 2048;
    public static final int shooterLowerEncoderPPR = 2048;
    public static final byte shooterUpperEncoderPinA = 7;
    public static final byte shooterUpperEncoderPinB = 6;
    public static final boolean shooterUpperEncoderDirectionInverted = true;
    public static final byte shooterLowerEncoderPinA = 9;
    public static final byte shooterLowerEncoderPinB = 8;
    public static final boolean shooterLowerEncoderDirectionInverted = false;
    
    //Climber Setting
    public static final byte hookAID = 1; //ID
    public static final byte hookBID = 2; //ID
    public static final byte hangerID = 3; //ID
    
    //Intake Setting
    public static final byte intakeLiftID = 1; //ID
    public static final byte intakeSpinID = 2; //ID

    //Rotator Setting
    public static final byte colorLiftID = 1; //ID
    public static final byte colorSpinID = 2; //ID




}

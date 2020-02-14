/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2049 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

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

    public static final double axisRXExp = 1;
    public static final double axisRXScale = 1;
    public static final double axisRYExp = 1;
    public static final double axisRYScale = 1;
    public static final double axisLXExp = 1;
    public static final double axisLXScale = 1;
    public static final double axisLYExp = 1;
    public static final double axisLYScale = 1;

    //Shooter Setting
    public static final byte shooterUpperMotorID = 85;
    public static final byte shooterLowerMotorID = 25;
    public static final boolean shooterUpperMotorInvert = true;
    public static final boolean shooterLowerMotorInvert = false;
    public static final double shooterUpperMotorMaxPIDRPS = 60;
    public static final double shooterLowerMotorMaxPIDRPS = 60;
    public static final int shooterUpperEncoderPPR = 2048;
    public static final int shooterLowerEncoderPPR = 2048;
    public static final byte shooterUpperEncoderPinA = 7;
    public static final byte shooterUpperEncoderPinB = 6;
    public static final boolean shooterUpperEncoderDirectionInvert = true;
    public static final byte shooterLowerEncoderPinA = 9;
    public static final byte shooterLowerEncoderPinB = 8;
    public static final boolean shooterLowerEncoderDirectionInvert = false;
    public static final byte inputMotorID = 3;

    //Spinner Setting
    public static final byte spinnerSpinMotor = 88;
    public static final double spinnerSpinMotorSpeed = 1;
    public static final byte spinnerriseMotor = 4;

    public static final byte m_EncoderPinA = 1;
    public static final byte m_EncoderPinB = 2;
    public static final boolean m_EncoderReverse = false;
    public static final Color kBlueTarget = ColorMatch.makeColor(0.135, 0.429, 0.400);   // Adjust parameters by distance
    public static final Color kGreenTarget = ColorMatch.makeColor(0.183, 0.554, 0.260);  
    public static final Color kRedTarget = ColorMatch.makeColor(0.460, 0.370, 0.161);   
    public static final Color kYellowTarget = ColorMatch.makeColor(0.326, 0.537, 0.137); 
    
    //CPIntake setting
    public static final byte CPintakeMotor = 88;
    public static final byte CPrise = 1;
    public static final double CPintakeMotorSpeed_in = 1;
    public static final double CPintakeMotorSpeed_out = -CPintakeMotorSpeed_in;

    //Climber setting
    public static final byte liftEncoderPinA = 8;
    public static final byte liftEncoderPinB = 9;
    public static final boolean  liftlEncoderReverse = true;
    public static final double liftSetPoint = 1234;
    public static final byte liftMotorID = 99; 

    //intake setting
    public static final byte intakeMotorID = 98;
    public static final byte intakeRiserMotorID = 98;
    public static final byte intakeRiserEncoderPinA = 6;
    public static final byte intakeRiserEncoderPinB = 5;
    public static final boolean intakeRiserlEncoderReverse = false;
    public static final double intakeMotorSpeed = 0.8;
}
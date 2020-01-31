/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
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
    public static final byte shooterUpperMotor = 1;
    public static final byte shooterLowerMotor = 2;
    public static final boolean shooterUpperMotorInvert = false;
    public static final boolean shooterLowerMotorInvert = false;
    public static final int shooterUpperEncoderPPR = 2048;
    public static final int shooterLowerEncoderPPR = 2048;

    //Spinner Setting
    public static final byte spinnerMotorPin = 4;
    public static final double spinnerMotorSpeed = 0.5;
    public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);   // Adjust parameters by distance
    public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);  
    public static Color kRedTarget = ColorMatch.makeColor(0.531, 0.335, 0.124);   
    public static Color kYellowTarget = ColorMatch.makeColor(0.320, 0.542, 0.133); 

}

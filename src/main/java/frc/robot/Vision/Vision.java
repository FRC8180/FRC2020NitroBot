/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Vision {
    public boolean getState(String name) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("PublicVision");
        NetworkTableEntry data = table.getEntry(name);
        //"State": state
        boolean result = data.getBoolean(false);
        System.out.println(result);
        return result;
    }
    public double getVisionResult(String name) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("PublicVision");
        
        //"x": Center_x
        //"y": Center_y
        //"width": width
        //"height": height
        //"h_angle": Angle to target
        //"v_angle": Angle to target
        //"distance": Distance to target
        NetworkTableEntry data = table.getEntry(name);
        double result = data.getDouble(0.0);
        System.out.println(result);
        return result;
    }
}

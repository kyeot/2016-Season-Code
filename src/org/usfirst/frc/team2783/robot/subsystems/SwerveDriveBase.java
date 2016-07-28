package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SwerveDriveBase extends Subsystem {
	private CANTalon frontRightWheel;
	private CANTalon frontRightSwivel;
	
	private CANTalon frontLeftWheel;
	private CANTalon frontLeftSwivel;
	
	private CANTalon backRightWheel;
	private CANTalon backRightSwivel;
	
	private CANTalon backLeftWheel;
	private CANTalon backLeftSwivel;
    
    public SwerveDriveBase() {
    	super();
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


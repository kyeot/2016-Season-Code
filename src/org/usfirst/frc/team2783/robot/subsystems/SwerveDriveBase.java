package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;

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
	
	private CANTalon rearRightWheel;
	private CANTalon rearRightSwivel;
	
	private CANTalon rearLeftWheel;
	private CANTalon rearLeftSwivel;
    
    public SwerveDriveBase() {
    	super();
    	
    	frontRightWheel = new CANTalon(RobotMap.FRONT_RIGHT_WHEEL);
    	frontRightSwivel = new CANTalon(RobotMap.FRONT_RIGHT_SWIVEL);
    	
    	frontLeftWheel = new CANTalon(RobotMap.FRONT_LEFT_WHEEL);
    	frontLeftSwivel = new CANTalon(RobotMap.FRONT_LEFT_SWIVEL);
    	
    	rearRightWheel = new CANTalon(RobotMap.REAR_RIGHT_WHEEL);
    	rearRightSwivel = new CANTalon(RobotMap.REAR_RIGHT_SWIVEL);
    	
    	rearLeftWheel = new CANTalon(RobotMap.REAR_LEFT_WHEEL);
    	rearLeftSwivel = new CANTalon(RobotMap.REAR_LEFT_SWIVEL);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


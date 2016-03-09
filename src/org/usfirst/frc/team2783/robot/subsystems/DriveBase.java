package org.usfirst.frc.team2783.robot.subsystems;

import java.awt.Button;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SteerableTankDrive;
import org.usfirst.frc.team2783.robot.OI;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	private RobotDrive driveBase = new RobotDrive(
			new CANTalon(RobotMap.FRONT_LEFT_MOTOR_ID), 
			new CANTalon(RobotMap.REAR_LEFT_MOTOR_ID),
			new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_ID), 
			new CANTalon(RobotMap.REAR_RIGHT_MOTOR_ID)
			);
	
	public DriveBase() {
		super();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new SteerableTankDrive());
    }
    
    public void tankDrive(double leftValue, double rightValue) {
    	if(OI.xBoxController.getRawButton(2) == true) {
    		driveBase.tankDrive(leftValue * 0.30, rightValue * 0.30);
    	} else {
    		driveBase.tankDrive(leftValue, rightValue);
    	}
    }
}

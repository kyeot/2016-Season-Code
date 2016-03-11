package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SteerableTankDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	private CANTalon frontLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon rearLeftMotor;
	private CANTalon rearRightMotor;
	
	public DriveBase() {
		super();
		
		frontLeftMotor = new CANTalon(RobotMap.FRONT_LEFT_MOTOR_ID);
		frontRightMotor = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_ID); 
		rearLeftMotor = new CANTalon(RobotMap.REAR_LEFT_MOTOR_ID);
		rearRightMotor = new CANTalon(RobotMap.REAR_RIGHT_MOTOR_ID);
		
		frontRightMotor.setInverted(true);
		rearRightMotor.setInverted(true);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new SteerableTankDrive());
    }
    
    public void tankDrive(double leftValue, double rightValue) {
    	if(OI.xBoxController.getRawButton(2) == true) {
    		leftValue = leftValue * 0.5;
    		rightValue = rightValue * 0.5;
    	}
    	
    	frontLeftMotor.set(leftValue);
    	rearLeftMotor.set(leftValue);
    	
    	frontRightMotor.set(rightValue);
    	rearRightMotor.set(rightValue);
    }
}

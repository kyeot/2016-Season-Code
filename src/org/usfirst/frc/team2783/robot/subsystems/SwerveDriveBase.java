package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SwerveDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
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
        setDefaultCommand(new SwerveDrive());
    }
    
    public void tankDrive(double leftValue, double rightValue) {
    	if (DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().getMatchTime() < 4) {
    		setBrake(true);
    	} else {
    		setBrake(false);
    	}
    	
    	frontRightWheel.set(rightValue);
    	rearRightWheel.set(rightValue);
    	
    	frontLeftWheel.set(leftValue);
    	rearLeftWheel.set(leftValue);
    }
    
    public void setBrake(boolean brake) {
    	frontRightWheel.enableBrakeMode(brake);
    	frontRightSwivel.enableBrakeMode(brake);
    	
    	frontLeftWheel.enableBrakeMode(brake);
    	frontLeftSwivel.enableBrakeMode(brake);
    	
    	rearRightWheel.enableBrakeMode(brake);
    	rearRightSwivel.enableBrakeMode(brake);
    	
    	rearLeftWheel.enableBrakeMode(brake);
    	rearLeftSwivel.enableBrakeMode(brake);
    }
    
    //TODO: better names
    public void frontRightDrive(double value) {
    	frontRightWheel.set(value);
    }
    
    public void frontRightTwist(double value) {
    	frontRightSwivel.set(value);
    }
    
    public void frontLeftDrive(double value) {
    	frontLeftWheel.set(value);
    }
    
    public void frontLeftTwist(double value) {
    	frontLeftSwivel.set(value);
    }
    
    public void rearRightDrive(double value) {
    	rearRightWheel.set(value);
    }
    
    public void rearRightTwist(double value) {
    	rearRightSwivel.set(value);
    }
    
    public void rearLeftDrive(double value) {
    	rearLeftWheel.set(value);
    }
    
    public void rearLeftTwift(double value) {
    	rearLeftSwivel.set(value);
    }
}


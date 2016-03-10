package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SteerableTankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	
	private RobotDrive driveBase = new RobotDrive(
			new CANTalon(RobotMap.FRONT_LEFT_MOTOR_ID), 
			new CANTalon(RobotMap.REAR_LEFT_MOTOR_ID),
			new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_ID), 
			new CANTalon(RobotMap.REAR_RIGHT_MOTOR_ID)
			);
	
	private AHRS navSensor;
	
	public DriveBase() {
		super();
		
		try {
	         navSensor = new AHRS(SPI.Port.kMXP);
	     } catch (RuntimeException ex ) {
	         DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }
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
    
    public AHRS getNavSensor() {
    	return navSensor;
    }
    
    public Boolean isMoving() {
    	return navSensor.isMoving();
    }
    
    public Double getHeading() {
    	if (navSensor.isMagnetometerCalibrated()) {
    		return (double)navSensor.getFusedHeading();
    	} else {
    		return (double)navSensor.getYaw() + 180.0;
    	}
    }
}

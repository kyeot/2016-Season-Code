package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SteerableTankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem{
	private CANTalon frontLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon rearLeftMotor;
	private CANTalon rearRightMotor;
	
	private AHRS navSensor;
	
	public DriveBase() {
		super();
		
		try {
	         navSensor = new AHRS(SPI.Port.kMXP);
	     } catch (RuntimeException ex ) {
	         DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }

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
    	
    	//if (DriverStation.getInstance().getMatchTime() < 4) {
    	if (DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().getMatchTime() < 4) {
    		setBrake();
    	} else {
    		setCoast();
    	}
    	
    	frontLeftMotor.set(leftValue);
    	rearLeftMotor.set(leftValue);
    	
    	frontRightMotor.set(rightValue);
    	rearRightMotor.set(rightValue);
    	
    	SmartDashboard.putNumber("Gyro", getYaw());
    }
    
    public AHRS getNavSensor() {
    	if (navSensor != null) {
    		return navSensor;
    	} else {
    		return null;
    	}
    }
    
    public Boolean isMoving() {
    	if (navSensor != null) {
    		return navSensor.isMoving();
    	} else {
    		return null;
    	}
    }
    
    public Double getHeading() {
    	if (navSensor != null && navSensor.isMagnetometerCalibrated()) {
    		return (double)navSensor.getFusedHeading();
    	} else {
    		return null;
    	}
	}
    
    public Double getYaw() {
    	return (double)navSensor.getYaw();
    }
     
    public void setCoast() {
    	frontLeftMotor.enableBrakeMode(false);
    	frontRightMotor.enableBrakeMode(false);
    	rearLeftMotor.enableBrakeMode(false);
    	rearRightMotor.enableBrakeMode(false);
    }
    
    public void setBrake() {
    	frontLeftMotor.enableBrakeMode(true);
    	frontRightMotor.enableBrakeMode(true);
    	rearLeftMotor.enableBrakeMode(true);
    	rearRightMotor.enableBrakeMode(true);
	}
}

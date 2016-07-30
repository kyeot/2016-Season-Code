package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SwerveDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
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
	
	private AHRS navSensor;
    
    public SwerveDriveBase() {
    	super();
    	
    	try {
	         navSensor = new AHRS(SPI.Port.kMXP);
	     } catch (RuntimeException ex ) {
	         DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }
    	
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
    
    public void swerveDrive(double dirMotion, double strMotion, double rotMotion) {
    	dirMotion = (dirMotion*(Math.sin(getNavSensor().getAngle()))) + (strMotion*(Math.cos(getNavSensor().getAngle())));
    	strMotion = -(dirMotion*(Math.cos(getNavSensor().getAngle()))) + (strMotion*(Math.sin(getNavSensor().getAngle())));
    	
    	//TODO:find robot's wheelbase for L and trackwidth for W
    	double L = 0.0;
    	double W = 0.0;
    	double R = Math.sqrt((L*L) + (W*W));
    	
    	double A = strMotion - rotMotion*(L/R);
    	double B = strMotion + rotMotion*(L/R);
    	double C = dirMotion - rotMotion*(W/R);
    	double D = dirMotion + rotMotion*(W/R);
    	
    	double frontRightWheelSpeed = Math.sqrt((B*B) + (C*C));
    	double frontLeftWheelSpeed = Math.sqrt((B*B) + (D*D));
    	double rearLeftWheelSpeed = Math.sqrt((A*A) + (D*D));
    	double rearRightWheelSpeed = Math.sqrt((A*A) + (C*C));
    	
    	double t = 180/Math.PI;
    	
    	double frontRightAngle = Math.atan2(B, C)*t;
    	double frontLeftAngle = Math.atan2(B, D)*t;
    	double rearLeftAngle = Math.atan2(A, C)*t;
    	double rearRightAngle = Math.atan2(A, C)*t;
    	 
    	double max = frontRightWheelSpeed;
    	if(max < frontLeftWheelSpeed) {max = frontLeftWheelSpeed;}
    	if(max < rearLeftWheelSpeed) {max = rearLeftWheelSpeed;}
    	if(max < rearRightWheelSpeed) {max = rearRightWheelSpeed;}
    	//I'm so sorry Jake
    	
    	if(max > 1) {
    		frontRightWheelSpeed /= max;
    		frontLeftWheelSpeed /= max;
    		rearLeftWheelSpeed /= max;
    		rearRightWheelSpeed /= max;
    	}
    	
    	frontRightWheel.set(frontRightWheelSpeed);
    	frontLeftWheel.set(frontLeftWheelSpeed);
    	rearLeftWheel.set(rearLeftWheelSpeed);
    	rearRightWheel.set(rearRightWheelSpeed);
    	
    	frontRightSetAngle(frontRightAngle);
    	frontLeftSetAngle(frontLeftAngle);
    	rearLeftSetAngle(rearLeftAngle);
    	rearRightSetAngle(rearRightAngle);
    	
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
    
    public AHRS getNavSensor() {
    	if (navSensor != null) {
    		return navSensor;
    	} else {
    		return null;
    	}
    }
    
    //TODO: finish method to have swivel motors turn the wheel to a specific angle
    public void frontRightSetAngle(double angle) {
    	
    }
    
    public void frontLeftSetAngle(double angle) {
    	
    }
    
    public void rearRightSetAngle(double angle) {
    	
    }
    
    public void rearLeftSetAngle(double angle) {
    	
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


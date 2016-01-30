package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ShootOnly;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterBase extends Subsystem {
	CANTalon shooterWheel;
	CANTalon hAxisMotor;
	public ShooterBase() {
		super();
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.setControlMode(0); // value for quad encoder. Does not work(FeedbackDevice.AnalogEncoder)3 equals analog encoder
		hAxisMotor = new CANTalon(RobotMap.SHOOTER_HORIZONTAL_AXIS_MOTOR);
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShootOnly());
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
	}
	
	public void setHorizontalAxis(double degrees) {
		shooterWheel.set(degrees);
	}
}

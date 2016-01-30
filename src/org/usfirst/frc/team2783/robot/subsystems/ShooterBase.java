package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ShootOnly;
import org.usfirst.frc.team2783.robot.commands.ShootRotation;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterBase extends Subsystem {
	CANTalon shooterWheel;
	CANTalon horizontalAxis;
	CANTalon verticalAxis;
	
	public ShooterBase() {
		super();
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.setControlMode(0); // value for quad encoder. Does not work(FeedbackDevice.AnalogEncoder)3 equals analog encoder
		horizontalAxis = new CANTalon(RobotMap.SHOOTER_HORIZONTAL_AXIS_MOTOR);
		horizontalAxis.setControlMode(0); // value for quad encoder. Does not work(FeedbackDevice.AnalogEncoder)3 equals analog encoder
		verticalAxis = new CANTalon(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR);
		verticalAxis.setControlMode(0); // value for quad encoder. Does not work(FeedbackDevice.AnalogEncoder)3 equals analog encoder
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShootRotation());
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
	}
	
	public void setHorizontalAxis(double rpmOutput) {
		horizontalAxis.set(rpmOutput);
	}
	public void setVerticalAxis(double rpmOutput) {
		verticalAxis.set(rpmOutput);
	}
}

package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.AbsoluteMagenticEncoderTest;
import org.usfirst.frc.team2783.robot.commands.ShootRotation;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANSpeedController.ControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterBase extends Subsystem {
	CANTalon shooterWheel;
	CANTalon horizontalAxis;
	CANTalon verticalAxis;
	
	public ShooterBase() {
		super();
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.changeControlMode(TalonControlMode.Speed); // value for quad encoder. Does not work(FeedbackDevice.AnalogEncoder)3 equals analog encoder
		shooterWheel.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		horizontalAxis = new CANTalon(RobotMap.SHOOTER_HORIZONTAL_AXIS_MOTOR);
		horizontalAxis.changeControlMode(TalonControlMode.Position);
		horizontalAxis.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		verticalAxis = new CANTalon(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR);		
		verticalAxis.changeControlMode(TalonControlMode.Position);
		verticalAxis.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new AbsoluteMagenticEncoderTest());
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
	}
	
	public void setHorizontalAxis(double degrees) {
		//Testing for correct input using Abs Mag Encoder
		verticalAxis.setPosition(degrees);
		//horizontalAxis.set(rpmOutput);
	}
	public void setVerticalAxis(double output) {
		verticalAxis.set(output);
	}
}

package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.BasicShooterDrive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterBase extends Subsystem {
	CANTalon shooterWheel;
	CANTalon verticalAxisMotor;
	AnalogInput verticalAbsEncoder = new AnalogInput(1);
	CANTalon ballElevatorMotor;

	public ShooterBase() {
		super();
		
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheel.changeControlMode(TalonControlMode.Speed);
		shooterWheel.configEncoderCodesPerRev(20);
		shooterWheel.reverseSensor(true);
		shooterWheel.setPID(1.5, 0.0017, 0, 0, 500, 4, 0);
		shooterWheel.configPeakOutputVoltage(12, -12);
		shooterWheel.clearIAccum();
		
		verticalAxisMotor = new CANTalon(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR);
		
		ballElevatorMotor = new CANTalon(RobotMap.BALL_ELEVATOR_MOTOR_ID);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new BasicShooterDrive());		
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
	}
	
	// outputs value to vertical axis motor
	public void setVerticalVbus(double output) {
		verticalAxisMotor.set(output);
	}
	
	//returns encoder value
	public double getVerticalValueDegrees() {
		// turns voltage signal from encoder to degrees
		return (verticalAbsEncoder.getVoltage() / 5) * 360;
	}
	
	public void setLiftVbus(double input) {
		ballElevatorMotor.set(input);
	}
}

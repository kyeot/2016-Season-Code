package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.BasicShooterDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterBase extends Subsystem {
	
	CANTalon shooterWheelMotor;
	VictorSP verticalAxisMotor;
	VictorSP ballElevatorMotor;
	AnalogInput absoluteEncoder;

	public ShooterBase() {
		super();
		
		//Instantiate and configure the shooter wheel's controller for RPM speed control
		shooterWheelMotor = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheelMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
		shooterWheelMotor.configEncoderCodesPerRev(20);
		shooterWheelMotor.setInverted(true);
		shooterWheelMotor.reverseSensor(true);
		shooterWheelMotor.setPID(1.5, 0.0017, 0, 0, 500, 4, 0);
		shooterWheelMotor.configPeakOutputVoltage(12, -12);
		shooterWheelMotor.clearIAccum();
		
		//Instantiate the motor controller for the vertical angle adjuster
		verticalAxisMotor = new VictorSP(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR_PWM_PORT);
		
		//Instantiate the motor controller for the elevator that lifts the ball into the shooter
		ballElevatorMotor = new VictorSP(RobotMap.BALL_ELEVATOR_PWM_PORT);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new BasicShooterDrive());		
	}
	
	public void setWheelSpeedRpm(double rpmOutput) {
		shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
		shooterWheelMotor.set(rpmOutput);
	}
	
	public void setWheelSpeedVbus(double vbusOutput) {
		shooterWheelMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterWheelMotor.set(vbusOutput);
		SmartDashboard.putNumber("Shooter Speed", vbusOutput);
	}
	
	public void setVerticalAxisVbus(double vbusOutput) {
		verticalAxisMotor.set(vbusOutput);
		if (absoluteEncoder != null) {
			double range = absoluteEncoder.getAverageVoltage() * 72;
			SmartDashboard.putNumber("Shooter Angle", range);
		}
	}
	
	public void setBallElevatorVbus(double vbusOutput) {
		ballElevatorMotor.set(vbusOutput);
	}
}

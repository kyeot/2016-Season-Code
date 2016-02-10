package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.BasicShooterDrive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class ShooterBase extends Subsystem {
	CANTalon shooterWheel;
	CANTalon pitch;
	CANTalon yaw;
	AnalogInput horizontalAbsEncoder = new AnalogInput(0);
	AnalogInput verticalAbsEncoder = new AnalogInput(1);

	public ShooterBase() {
		super();
		
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheel.changeControlMode(TalonControlMode.Speed);
		shooterWheel.configEncoderCodesPerRev(40);
		//shooterWheel.configNominalOutputVoltage(0, 0);
		//shooterWheel.configPeakOutputVoltage(12, -12);
		shooterWheel.reverseSensor(true);
		shooterWheel.setP(16.0);
		shooterWheel.setI(0.35);
		shooterWheel.setD(0.0);
		shooterWheel.setF(0);	
		
		pitch = new CANTalon(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR);
		
		yaw = new CANTalon(RobotMap.SHOOTER_HORIZONTAL_AXIS_MOTOR);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new BasicShooterDrive());		
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
		System.out.println(shooterWheel.getPosition());
		System.out.println(shooterWheel.getEncVelocity());
	}
	
	public void setVerticalVbus(double output) {
		yaw.set(output);
	}
	
	public void setHorizontalVbus(double output) {
		pitch.set(output);
	}
	
	public double getHorizontalValueDegree() {
		return (horizontalAbsEncoder.getVoltage() / 5) * 360;			
	}
	
	public double getVerticalValueDegrees() {
		return (verticalAbsEncoder.getVoltage() / 5) * 360;
	}
}

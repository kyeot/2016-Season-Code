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
	CANTalon verticalAxisMotor;
	CANTalon horizontalAxisMotor;
	AnalogInput horizontalAbsEncoder = new AnalogInput(0);
	AnalogInput verticalAbsEncoder = new AnalogInput(1);

	public ShooterBase() {
		super();
		
		shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);
		shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheel.changeControlMode(TalonControlMode.Speed);
		shooterWheel.configEncoderCodesPerRev(40);
		shooterWheel.reverseSensor(true);
		shooterWheel.setP(16.0); //change these values
		shooterWheel.setI(0.35); //change these values
		shooterWheel.setD(0.0); // change these values
		shooterWheel.setF(0);	
		
		verticalAxisMotor = new CANTalon(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR);
		
		horizontalAxisMotor = new CANTalon(RobotMap.SHOOTER_HORIZONTAL_AXIS_MOTOR);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new BasicShooterDrive());		
	}
	
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
		System.out.println(shooterWheel.getPosition());
		System.out.println(shooterWheel.getEncVelocity());
	}
	
	// outputs value to vertical axis motor
public void setVerticalVbus(double output) {
		verticalAxisMotor.set(output);
	}
	
	// outputs value to horizontal axis motor
	public void setHorizontalVbus(double output) {
		horizontalAxisMotor.set(output);
	}
	
	// returns encoder value
	public double getHorizontalValueDegrees() {
		// turns voltage signal from encoder to degrees
		return (horizontalAbsEncoder.getVoltage() / 5) * 360;			
	}
	
	//returns encoder value
	public double getVerticalValueDegrees() {
		// turns voltage signal from encoder to degrees
		return (verticalAbsEncoder.getVoltage() / 5) * 360;
	}
}

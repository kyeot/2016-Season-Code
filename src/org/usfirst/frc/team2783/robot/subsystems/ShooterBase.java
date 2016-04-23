package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.BasicShooterDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterBase extends Subsystem {
	
	CANTalon shooterWheelMotor;
	VictorSP verticalAxisMotor;
	VictorSP ballElevatorMotor;
	AnalogInput absoluteEncoder;
	
	DigitalInput topLimitSwitch;
	DigitalInput bottomLimitSwitch;
	
	Encoder quadEncoder;

	Double ENCODER_TICKS_FOR_ADUJSTER_TRAVEL = 100.0;
	
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
		
		topLimitSwitch = new DigitalInput(0);
		bottomLimitSwitch = new DigitalInput(1);
		
		//Instantiate the motor controller for the vertical angle adjuster
		verticalAxisMotor = new VictorSP(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR_PWM_PORT);
		
		//Instantiate the motor controller for the elevator that lifts the ball into the shooter
		ballElevatorMotor = new VictorSP(RobotMap.BALL_ELEVATOR_PWM_PORT);
		
		//Instantiates a quadrature encoder
		quadEncoder = new Encoder(new DigitalInput(2), new DigitalInput(3));
		quadEncoder.reset();
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
		if(topLimitSwitch.get()){
			if(vbusOutput > 0) {
				vbusOutput = 0;
			}
		} else if(bottomLimitSwitch.get()){
			if(vbusOutput < 0) {
				vbusOutput = 0;
			}
		}
		System.out.println(vbusOutput);
		if(Math.abs(vbusOutput) > 0.2){
			verticalAxisMotor.set(vbusOutput);
		} else {
			verticalAxisMotor.set(0.0);
		}
		
		System.out.println(getQuadEncoderPercent());
		
		if (absoluteEncoder != null) {
			double range = absoluteEncoder.getAverageVoltage() * 72;
			SmartDashboard.putNumber("Shooter Angle", range);
			
		}		
	}
	
	public Double getQuadEncoderPercent(){
		return Math.abs(quadEncoder.getDistance() / ENCODER_TICKS_FOR_ADUJSTER_TRAVEL);
	}
	
	public void resetQuadEncoder(){
		quadEncoder.reset();
	}
	
	public void setBallElevatorVbus(double vbusOutput) {
		ballElevatorMotor.set(vbusOutput);
	}
	
	public double getVerticalAxisAngle(){
		if (quadEncoder != null) {
			return (getQuadEncoderPercent());
		} else {
			return -1.0;
		}
	}
	
	public Boolean isBottomLimit() {
		return bottomLimitSwitch.get();
	}
	
	public Boolean isTopLimit() {
		return topLimitSwitch.get();
	}
}

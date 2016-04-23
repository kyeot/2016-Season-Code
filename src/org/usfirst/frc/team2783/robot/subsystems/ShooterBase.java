package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.BasicShooterDrive;
import org.usfirst.frc.team2783.robot.util.EdgeDetect;
import org.usfirst.frc.team2783.robot.util.EdgeDetect.EdgeType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterBase extends PIDSubsystem {
	
	CANTalon shooterWheelMotor;
	VictorSP ballElevatorMotor;
	Encoder quadEncoder;
		
	VictorSP verticalAxisMotor;
	AnalogInput verticalEncoder;
	
	final public static double kp = 0.1;
	final public static double ki = 0.01;
	final public static double kd = 0.0;
	
	EdgeDetect verticalAxisInputChangeFromZero;

	DigitalInput topLimitSwitch;
	DigitalInput bottomLimitSwitch;
	
	Double ENCODER_TICKS_FOR_ADUJSTER_TRAVEL = 100.0;
	
	public ShooterBase() {
		super(kp, ki, kd);
		
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
		
		//Instantiate the motor controller for the elevator that lifts the ball into the shooter
		ballElevatorMotor = new VictorSP(RobotMap.BALL_ELEVATOR_PWM_PORT);
		
		//Instantiate the motor controller for the vertical angle adjuster and set up it's feeback and PID
		verticalAxisMotor = new VictorSP(RobotMap.SHOOTER_VERTICAL_AXIS_MOTOR_PWM_PORT);
		verticalEncoder = new AnalogInput(0);
		getPIDController().setContinuous(true);
		getPIDController().setInputRange(0, 360);
		getPIDController().setOutputRange(-1, 1);
		getPIDController().disable();
		verticalAxisInputChangeFromZero = new EdgeDetect(EdgeType.RISING_EDGE_DETECT);

		topLimitSwitch = new DigitalInput(0);
		bottomLimitSwitch = new DigitalInput(1);
		
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
		// If the adjuster is set to an angle and the input just changed from zero
		if (getPIDController().isEnabled() && verticalAxisInputChangeFromZero.isEdge(vbusOutput != 0)) {
			// Disable the PID
			getPIDController().disable();
		} 
		
		// If the PID controller isn't enabled
		if (!getPIDController().isEnabled()) {
			driveVerticalAxis(vbusOutput);
		}
		
		SmartDashboard.putNumber("Shooter Angle", getVerticalAxisAngle());
	}
	
	private void driveVerticalAxis(double vbusOutput) {
		// If the bottom limit switch is pressed
		if (bottomLimitSwitch.get()) {
			// And the adjuster is being driven up
			if (vbusOutput > 0) {
				verticalAxisMotor.set(vbusOutput);
				
			// And the adjuster is being driven downw
			} else {
				verticalAxisMotor.set(0.0);
			}
			
		// If the top limit is pressed
		} else if (topLimitSwitch.get()) {
			// And the adjuster is being driven down
			if (vbusOutput < 0) {
				verticalAxisMotor.set(vbusOutput);
				
			// And the adjuster is being driven up
			} else {
				verticalAxisMotor.set(0.0);
			}
			
		// If no switches are pressed, drive as normal
		} else {
			verticalAxisMotor.set(vbusOutput);
		}		
	}
	
	public Double getQuadEncoderPercent(){
		return Math.abs(quadEncoder.getDistance() / ENCODER_TICKS_FOR_ADUJSTER_TRAVEL);
	}
	
	public void resetQuadEncoder(){
		quadEncoder.reset();
	}
	
	public void setVerticalAxisAngle(Double angle) {
		getPIDController().enable();
		setSetpoint(angle);
		verticalAxisInputChangeFromZero.isEdge(false);
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

	@Override
	protected double returnPIDInput() {
		return getVerticalAxisAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		driveVerticalAxis(output);
	}
}

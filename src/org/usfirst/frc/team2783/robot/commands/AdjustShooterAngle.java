package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class AdjustShooterAngle extends PIDCommand{

	private final static double kp = 1.2;
	private final static double ki = 0.01;
	private final static double kd = 0.001;
	
	Double setpoint;
	
	public AdjustShooterAngle(double setpoint){
		super(kp, ki, kd, 0.01);
		requires(Robot.retriever);
		
		this.setpoint = setpoint;
	}
	
	@Override
	protected void initialize() {
		setSetpoint(setpoint);
	}

	@Override
	protected void execute() {
	
	}

	@Override
	protected boolean isFinished() {
		
		return false;
	}

	@Override
	protected void end() {
				
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected double returnPIDInput() {
		return Robot.shooterBase.getVerticalAxisVbusAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(output > 1 || output < -1){
			output /= Math.abs(output);
		}
		Robot.shooterBase.setVerticalAxisVbus(output);
	}

}

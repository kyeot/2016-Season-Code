package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class SetYaw extends PIDCommand {

	private final static double kp = 0.1; // default values must be changed
	private final static double ki = 0.001; // default values must be changed
	private final static double kd = 0.0; // default values must be changed
		
    public SetYaw() {
    	//passing stated value to PID super class
    	super(kp, ki, kd);
    	//allows sensor to be rotated though its min and max values (0-1023) to turn to a position
		getPIDController().setContinuous(true);
    	requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	protected double returnPIDInput() {
		//gives position of vertical axis of shooter in degrees
		return Robot.shooterBase.getHorizontalValueDegrees();
	}

	protected void usePIDOutput(double output) {
		//sets motor to Vbus value based on PID and process variable
		Robot.shooterBase.setHorizontalVbus(output);
	}
}
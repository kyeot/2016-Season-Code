package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAtAndControl extends Command {
	
	private Double wheelSpeed = 0.0;

    public ShooterAtAndControl(Double wheelSpeed) {
    	requires(Robot.shooterBase);
    	
    	this.wheelSpeed = wheelSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Set the wheel speed to the value stored when instatiated
    	Robot.shooterBase.setWheelSpeedVbus(wheelSpeed);
    	
		// Set the vertical angle adjuster based on axis 5 and a scaler
    	double verticalAxisAdjusterVbus = -OI.manipulator.getRawAxis(5) * 0.75; 
    	Robot.shooterBase.setVerticalAxisVbus(verticalAxisAdjusterVbus);
    	
    	// Set the elevator speed based upon two axes
    	Double lifterSpeed = OI.manipulator.getRawAxis(3) - OI.manipulator.getRawAxis(2);
    	Robot.shooterBase.setBallElevatorVbus(lifterSpeed);
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
}

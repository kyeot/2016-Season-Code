package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AngleAdjusterToLimit extends Command {
	
	public enum Limit {
		BOTTOM_LIMIT,
		TOP_LIMIT
	}
	
	private Limit limitToAdjustTo;

    public AngleAdjusterToLimit(Limit limitToAdjustTo) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterBase);
        
    	this.limitToAdjustTo = limitToAdjustTo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (limitToAdjustTo) {
    		default:
	    	case BOTTOM_LIMIT:
	    		Robot.shooterBase.setVerticalAxisVbus(-0.9);
	    		break;
	    	case TOP_LIMIT:
	    		Robot.shooterBase.setVerticalAxisVbus(1.0);
	    		break;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	switch (limitToAdjustTo) {
    		default:
	    	case BOTTOM_LIMIT:
	    		return Robot.shooterBase.isBottomLimit();
	    	case TOP_LIMIT:
	    		return Robot.shooterBase.isTopLimit();
	    		
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterBase.setVerticalAxisVbus(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooterBase.setVerticalAxisVbus(0.0);
    }
}

package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateAngleAdjuster extends Command {

    public CalibrateAngleAdjuster() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterBase.setVerticalAxisVbus(-0.65);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooterBase.isBottomLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterBase.resetQuadEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooterBase.setVerticalAxisVbus(0.0);
    }
}

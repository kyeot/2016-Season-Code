package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireShooter extends Command {
	
	private long commandStartedAt;

    public FireShooter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandStartedAt = Utility.getFPGATime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterBase.setLiftVbus(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Utility.getFPGATime() > (3000000 + commandStartedAt);
    }

    // Called once after isFinished returns true
    protected void end() {
    	commandStartedAt = 0;
    	Robot.shooterBase.setLiftVbus(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

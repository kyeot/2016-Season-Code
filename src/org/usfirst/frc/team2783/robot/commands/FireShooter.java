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
    	Robot.shooterBase.setBallElevatorVbus(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// If the command has been running for 3 seconds, it's done
        return Utility.getFPGATime() > (3000000 + commandStartedAt);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Clean up the values the command used
    	commandStartedAt = 0;
    	Robot.shooterBase.setBallElevatorVbus(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

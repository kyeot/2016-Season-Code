package org.usfirst.frc.team2783.robot.commands.autonomous;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.subsystems.BallRetriever.ArmDirection;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoArm extends Command {

	private long commandStartedAt;
	
	private ArmDirection direction;
	private double runTime;
	
    public AutoArm(ArmDirection direction, double runTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.retriever);
    	
    	this.direction = direction;
    	//Run Time is in Seconds
    	this.runTime = runTime;  	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandStartedAt = Utility.getFPGATime();
       }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.retriever.setArm(direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Run command for 6 seconds
        return Utility.getFPGATime() > (runTime * 1000000 + commandStartedAt);
    }

    // Called once after isFinished returns true
    protected void end() {
    	commandStartedAt = 0;
    	Robot.retriever.setArm(ArmDirection.ARM_STOP);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

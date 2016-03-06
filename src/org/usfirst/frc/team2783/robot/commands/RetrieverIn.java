package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.util.MovingAverage;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetrieverIn extends Command {
	
	MovingAverage retrieverIntakeCurrent;

    public RetrieverIn() {
    	requires(Robot.retriever);
    	
    	retrieverIntakeCurrent = new MovingAverage(4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.retriever.setRetrieverVbus(0.75, true);
    	
    	Double currentCurrent = (Robot.PDBoard.getCurrent(3) + Robot.PDBoard.getCurrent(11)) / 2;
    	Double movingAverage = retrieverIntakeCurrent.addValue(currentCurrent);
    	System.out.println("Retriever Current: " + movingAverage);
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

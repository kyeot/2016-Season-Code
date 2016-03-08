package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.util.MovingAverage;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetrieverIn extends Command {
	
	Boolean motorStarted;
	Boolean intook;
	MovingAverage retrieverIntakeCurrent;

    public RetrieverIn() {
    	requires(Robot.retriever);
    	
    	motorStarted = false;
    	intook = false;
    	retrieverIntakeCurrent = new MovingAverage(12);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motorStarted = false;
    	intook = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.retriever.setRetrieverVbus(0.75, true);
    	
    	Double currentCurrent = (Robot.PDBoard.getCurrent(3) + Robot.PDBoard.getCurrent(11)) / 2;
    	Double movingAverage = retrieverIntakeCurrent.addValue(currentCurrent);
    	if (movingAverage > 2 && movingAverage < 6) {
    		if (!motorStarted && movingAverage < 3) {
        		motorStarted = true;
        		System.out.println("Started");
        	} else if (motorStarted && movingAverage > 4) {
        		intook = true;
        	}
		}
    	
    	
    	System.out.println("Retriever Current: " + movingAverage);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return intook;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intook = false;
    	Robot.retriever.setRetrieverVbus(0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

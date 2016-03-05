package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ContinueRetrieverSpeed extends Command {

    public ContinueRetrieverSpeed() {
    	requires(Robot.retriever);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Robot.retriever.controllerSafety();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

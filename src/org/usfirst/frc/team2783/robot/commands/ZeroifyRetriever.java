package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroifyRetriever extends Command {

    public ZeroifyRetriever() {
    	requires(Robot.retriever);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Robot.retriever.setRetrieverVbus(1);
    	Robot.retriever.setRetrieverArmVbus(0);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

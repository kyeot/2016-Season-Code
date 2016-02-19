package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2783.robot.Robot;

/**
 *
 */
public class TurnBandsOnPickUpArm extends Command {
	
    public TurnBandsOnPickUpArm() {
    	requires(Robot.retriever);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.retriever.setRetrieverVbus(1);
	}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

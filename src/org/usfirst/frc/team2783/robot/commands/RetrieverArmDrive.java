package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetrieverArmDrive extends Command {
	
	private double currentSpeed;
	
    public RetrieverArmDrive() {
    	requires(Robot.retriever);
    	requires(Robot.shooterBase);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Math.abs(OI.manipulator.getY()) < 0.1) {
    		currentSpeed = 0;
    	} else {
    		currentSpeed = -OI.manipulator.getY();
    	}
    	
    	System.out.println(currentSpeed);
    	
    	Robot.shooterBase.setLiftVbus(0);
		Robot.retriever.setRetrieverVbus(currentSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

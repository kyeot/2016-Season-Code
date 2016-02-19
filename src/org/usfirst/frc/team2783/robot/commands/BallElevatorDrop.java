package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallElevatorDrop extends Command {
	
    public BallElevatorDrop() {
    	requires(Robot.shooterBase);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(-1);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shooterBase.setLiftVbus(0);;
    }
    
     protected void interrupted() {
    }
}

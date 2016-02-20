package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class BallElevatorLift extends Command {
		
    public BallElevatorLift() {
    	requires(Robot.shooterBase);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(1);;
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    }
}

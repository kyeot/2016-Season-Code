package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2783.robot.Robot;

/**
 *
 */
public class BandsOnArm extends Command {
	
    public BandsOnArm() {
    	requires(Robot.pickUp);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.pickUp.suckUpMotor.set(1);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2783.robot.Robot;

/**
 *
 */
public class TurnBandsOnArm extends Command {
	
	boolean temp2 = false;
	
    public TurnBandsOnArm() {
    	requires(Robot.pickUp);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.pickUp.suckUpMotor.set(1);
        if(Robot.oi.liftJoy.getRawButton(2) == temp2){
        	temp2 = !temp2;
        }
    }

    protected boolean isFinished() {
        return temp2;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

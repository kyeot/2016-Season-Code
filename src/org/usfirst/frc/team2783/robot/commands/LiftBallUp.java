package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class LiftBallUp extends Command {
	
	boolean temp = false;
	
    public LiftBallUp() {
    	requires(Robot.pickUp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pickUp.liftUpMotor.set(1);
    	if(Robot.oi.liftJoy.getRawButton(1) == temp){
    		temp = !temp;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return temp;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickUp.liftUpMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

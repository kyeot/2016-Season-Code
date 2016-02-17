package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class BallElevatorLift extends Command {
	
	boolean temp = false;
	
    public BallElevatorLift() {
    	requires(Robot.pickUp);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pickUp.BallElevatorMotor.set(1);
    	if(Robot.oi.liftJoy.getRawButton(1) == temp){
    		temp = !temp;
    	}
    }

    protected boolean isFinished() {
        return temp;
    }
    
    protected void end() {
    	Robot.pickUp.BallElevatorMotor.set(0);
    }

    protected void interrupted() {
    }
}

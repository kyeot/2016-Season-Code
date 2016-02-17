package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallElevatorDrop extends Command {
	
	boolean temp3 = false;

    public BallElevatorDrop() {
    	requires(Robot.pickUp);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pickUp.liftUpMotor.set(-1);
    	if(Robot.oi.liftJoy.getRawButton(1) == temp3){
    		temp3 = !temp3;
    	}
    }

    protected boolean isFinished() {
        return temp3;
    }

    protected void end() {
    	Robot.pickUp.liftUpMotor.set(0);
    }

    protected void interrupted() {
    }
}

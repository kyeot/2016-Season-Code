package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class BallElevatorLift extends Command {
	
	boolean temp = false;
	
    public BallElevatorLift() {
    	requires(Robot.shooterBase);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(1);;
    	if(OI.manipulator.getRawButton(1) == temp){
    		temp = !temp;
    	}
    }

    protected boolean isFinished() {
        return temp;
    }
    
    protected void end() {
    	Robot.shooterBase.setLiftVbus(0);;
    }

    protected void interrupted() {
    }
}

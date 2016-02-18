package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallElevatorDrop extends Command {
	
	boolean temp3 = false;

    public BallElevatorDrop() {
    	requires(Robot.shooterBase);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(-1);
    	if(OI.manipulator.getRawButton(7) == temp3){
    		temp3 = !temp3;
    	}
    }

    protected boolean isFinished() {
        return temp3;
    }

    protected void end() {
    	Robot.shooterBase.setLiftVbus(0);;
    }
    
     protected void interrupted() {
    }
}

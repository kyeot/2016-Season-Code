package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetrieverWheelsPushOut extends Command {
	
	boolean temp = false;

    public RetrieverWheelsPushOut() {
    	requires(Robot.retriever);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(-1);;
    	if(OI.manipulator.getRawButton(5) == temp){
    		temp = !temp;
    	}
    }

    protected boolean isFinished() {
    	return temp;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

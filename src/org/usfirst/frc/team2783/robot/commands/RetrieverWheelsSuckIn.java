package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetrieverWheelsSuckIn extends Command {
	
	boolean temp = false;

    public RetrieverWheelsSuckIn() {
    	requires(Robot.retriever);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterBase.setLiftVbus(1);;
    	if(OI.manipulator.getRawButton(4) == temp){
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

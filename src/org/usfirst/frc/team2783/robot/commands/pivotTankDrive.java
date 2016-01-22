package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class pivotTankDrive extends Command {

    public pivotTankDrive() {
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftOutput = 0, rightOutput = 0;
    	
    	//Uses the Dpad value of 270 to pivot to the left
    	if(OI.xBoxController.getPOV() == 270){
    		//Sets the left value opposite to right in order to pivot left
    		leftOutput = -(OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2));
    		rightOutput = OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2);
    		
    	//Uses the Dpad value of 90 to pivot to the right
		} else if(OI.xBoxController.getPOV() == 90) {
			//Sets the right value opposite to left in order to pivot right
			leftOutput = OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2);
    		rightOutput = -(OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2));
		}
    	//Sends pivot value to drive train
    	Robot.driveBase.tankDrive(leftOutput, rightOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

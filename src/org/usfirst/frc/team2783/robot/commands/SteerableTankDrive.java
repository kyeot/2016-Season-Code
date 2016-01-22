package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public class SteerableTankDrive extends Command{
			
		    public SteerableTankDrive() {
		    	requires(Robot.driveBase);
		    }

		    // Called just before this Command runs the first time
		    protected void initialize() {
		    }

		    // Called repeatedly when this Command is scheduled to run
		    protected void execute() {
		    	//Declares throttle value based on xbox controllers, and skewValue on left joystick
		    	Double throttleValue = OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2);
		    	Double skewValue = 0.5 * OI.xBoxController.getRawAxis(0);
		    	
		    	//Sets left and right values using throttle and skew
		    	Double leftOutput = throttleValue + (throttleValue * skewValue);
		    	Double rightOutput = throttleValue - (throttleValue * skewValue);
		    	
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

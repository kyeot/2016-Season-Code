package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootOnly extends Command {

    public ShootOnly() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 // manipulatorInput will be a number -1 to 1
    	double manipulatorInput = OI.manipulator.getRawAxis(3);
    	// Assigns input to number 0 to 1 or 0 to 100%
    	double manipulatorInputPercent = (manipulatorInput -= 1)/2;
    	//5000 is the max rpm for motor/wheel
    	double wheelRpm = manipulatorInputPercent * 5000;
    	
    	Robot.shooterBase.setWheelControlMode(1);
    	Robot.shooterBase.setWheelSpeed(wheelRpm);
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

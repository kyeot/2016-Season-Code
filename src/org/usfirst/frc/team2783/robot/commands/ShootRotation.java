package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootRotation extends Command {

    public ShootRotation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   	 // manipulatorInput for wheel will be a number -1 to 1
   	double manipulatorWheelInput = OI.manipulator.getRawAxis(2);
   	// Assigns input to number 0 to 2
   	manipulatorWheelInput += 1;
   	//4000 is the supposed max rpm for motor/wheel (0 to 2 * 2000 = 0 to 4000)
   	double wheelRpm = manipulatorWheelInput * 2000;
   	
   	Robot.shooterBase.setWheelSpeed(wheelRpm);
   	
   	// manipulator input for horizontal rotation
   	double manipulatorAxisInput = OI.manipulator.getRawAxis(1); //CHANGEME default value set for joystick
   	//
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

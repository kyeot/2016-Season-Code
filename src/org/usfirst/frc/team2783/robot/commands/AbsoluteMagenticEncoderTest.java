package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AbsoluteMagenticEncoderTest extends Command {

    public AbsoluteMagenticEncoderTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// manipulatorInput for wheel -1 to 1
    	double manipulatorWheelInput = OI.manipulator.getRawAxis(2);
    	// Assigns input to number 0 to 2
    	manipulatorWheelInput += 1;
    	//4000 is the supposed max rpm for motor/wheel (0 to 2 * 2000 = 0 to 4000)
    	double wheelRpm = manipulatorWheelInput * 2000;
   	
    	Robot.shooterBase.setWheelSpeed(wheelRpm);
   	
    	// manipulator input for horizontal rotation -1 to 1
    	double manipulatorHorizontalAxisInput = OI.manipulator.getRawAxis(1); 
    	double horizontalAxisRpmInput = manipulatorHorizontalAxisInput;
   	
    	Robot.shooterBase.setHorizontalAxis(horizontalAxisRpmInput);
   	
    	// manipulator input for vertical rotation -1 to 1
    	double manipulatorAxisInput = OI.manipulator.getRawAxis(2); 
    	double verticalAxisRpmInput = manipulatorAxisInput;
   	
    	Robot.shooterBase.setVerticalAxis(verticalAxisRpmInput);
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

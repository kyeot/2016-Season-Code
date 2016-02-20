package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicShooterDrive extends Command {

    public BasicShooterDrive() {
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
    	double manipulatorWheelOutput = OI.manipulator.getRawAxis(3);
    	// sets variable range between 0 to 2
    	manipulatorWheelOutput = ((manipulatorWheelOutput + 1) / 2.0) * 4800.0;
    	// gives variable range applicable Vbus output: new range = 0 to 1, acceptable range = -1 to 1
    	// outputs value to shooter wheel
    	Robot.shooterBase.setWheelSpeed(manipulatorWheelOutput);
    	
    	// manipulator input for vertical rotation -1 to 1
    	double manipulatorAxisInput = OI.manipulator.getRawAxis(2); 
    	// outputs value to vertical motor
    	Robot.shooterBase.setVerticalVbus(manipulatorAxisInput);
    	
    	Robot.shooterBase.setLiftVbus(0);
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

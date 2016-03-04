package org.usfirst.frc.team2783.robot.commands;

import java.util.function.DoubleToLongFunction;

import javax.xml.parsers.DocumentBuilder;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicShooterDrive extends Command {
	
	private Double wheelSpeed;

    public BasicShooterDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterBase);
    	
    	wheelSpeed = 0.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Shooter wheel speed control
    	Double manipulatorWheelOutput = -OI.manipulator.getRawAxis(1);
    	manipulatorWheelOutput = ((manipulatorWheelOutput + 1) / 2.0);
    	
    	if ((Math.abs(manipulatorWheelOutput - 0.5) > 0.1) && OI.manipulator.getRawButton(2)) {
    		wheelSpeed = manipulatorWheelOutput;
    		Robot.shooterBase.setWheelSpeedVbus(wheelSpeed);
    	} else if (Math.abs(manipulatorWheelOutput - 0.5) > 0.1) {
    		Robot.shooterBase.setWheelSpeedVbus(manipulatorWheelOutput);
    	} else {
    		Robot.shooterBase.setWheelSpeedVbus(wheelSpeed);
    	}
    	
    	// manipulator input for vertical rotation -1 to 1
    	double manipulatorAxisInput = -OI.manipulator.getRawAxis(5) * 0.75; 
    	// outputs value to vertical motor
    	Robot.shooterBase.setVerticalVbus(manipulatorAxisInput);
    	
    	Double lifterSpeed = OI.manipulator.getRawAxis(3) - OI.manipulator.getRawAxis(2);
    	Robot.shooterBase.setLiftVbus(lifterSpeed);
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

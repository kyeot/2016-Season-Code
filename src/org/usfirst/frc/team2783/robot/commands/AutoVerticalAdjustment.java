package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.commands.SetVerticalAxisAngle;
import org.usfirst.frc.team2783.robot.subsystems.ShooterBase;
import org.usfirst.frc.team2783.robot.vision.VisionData;

/**
 *
 */
public class AutoVerticalAdjustment extends Command {
	
	ShooterBase shooter = new ShooterBase();
	
	VisionData visionData = new VisionData();
	
	private double distance = visionData.getDistanceToGoal();
	
	private double angle(){
		return distance;
	}
	
    public AutoVerticalAdjustment() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.shooterBase);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setVerticalAxisAngle(angle());
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

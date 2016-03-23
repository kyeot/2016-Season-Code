package org.usfirst.frc.team2783.robot.commands.autonomous;

import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShoot extends Command {
	
	private long commandStartedAt;
	private double elevatorWait;
	private double runTime;
	private double elevatorSpeed;
	private double shooterSpeed;

    public AutoShoot(double shooterSpeed, double elevatorSpeed, double elevatorWait, double runTime) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterBase);
        this.elevatorWait = elevatorWait;
        this.runTime = runTime;
        this.elevatorSpeed = elevatorSpeed;
        this.shooterSpeed = shooterSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandStartedAt = Utility.getFPGATime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterBase.setWheelSpeedVbus(shooterSpeed);
    	if(Utility.getFPGATime() > (elevatorWait * 1000000 + commandStartedAt)){
    		Robot.shooterBase.setBallElevatorVbus(elevatorSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// If the command has been running for 3 seconds, it's done
        return Utility.getFPGATime() > (runTime * 1000000 + commandStartedAt);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Clean up the values the command used
    	commandStartedAt = 0;
    	Robot.shooterBase.setBallElevatorVbus(0);
    	Robot.shooterBase.setWheelSpeedVbus(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team2783.robot.commands.visionenhanced;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.vision.VisionData;
import org.usfirst.frc.team2783.util.MovingAverage;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class PivotToTarget extends PIDCommand {
	
	final public static double kp = 0.1;
	final public static double ki = 0.01;
	final public static double kd = 0.0;
	
	VisionData visionData;
	MovingAverage targetHorizontalCenter;
	MovingAverage error;
		
	VisionData.ImageSize imageSize;

    public PivotToTarget() {
    	super(kp, ki, kd);
    	
        requires(Robot.driveBase);
        
        visionData = new VisionData();
        
        targetHorizontalCenter = new MovingAverage(3);
        error = new MovingAverage(5);
        
        setSetpoint(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	imageSize = visionData.getImageSize();
    	targetHorizontalCenter.clearValues();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Double centerX = visionData.getLargestGoal().getCenterX();
		targetHorizontalCenter.addValue(centerX);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(error.addValue(getPIDController().getError())) < 0.20;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveBase.tankDrive(0.0, 0.0);
    }

	@Override
	protected double returnPIDInput() {
		return targetHorizontalCenter.getAverage() / imageSize.getX();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (Math.abs(output) > 0.4) {
			Robot.driveBase.tankDrive(-output, output);
		} else {
			Robot.driveBase.tankDrive(-0.65, 0.65);
		}
	}
}

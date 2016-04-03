package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.vision.Contour;
import org.usfirst.frc.team2783.robot.vision.VisionData;
import org.usfirst.frc.team2783.robot.vision.VisionData.ImageSize;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutoHorizontalAdjustment extends PIDCommand {
	
	private final static double kp = 1;
	private final static double ki = 0.1;
	private final static double kd = 0;
	
	private VisionData visionData;
	private ImageSize imageSize;
	
	double X = imageSize.getX();
	double Y = imageSize.getY();
	
	double goalWidth = visionData.getLargestGoal().getWidth();
	
	double Error = X/2 - goalWidth/2;
	
	public AutoHorizontalAdjustment() {
    	super(kp, ki, kd);
    	
    	visionData = new VisionData();
    }

    protected void initialize() {
    	setSetpoint(goalWidth/2);
    }
    
    protected void execute() {
    	System.out.println(Error);
    }

    protected boolean isFinished() {
    	if(Error == 0){
    		return true;
    	}
    	
    	else{return false;}
    }

    protected void end() {
    }

    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		return X/2;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.driveBase.tankDrive(output, -output);
	}
}

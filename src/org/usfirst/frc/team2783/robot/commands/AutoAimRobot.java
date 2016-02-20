package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class AutoAimRobot extends PIDCommand {
	
	private final static double kp = 0.05;
	private final static double kd = 0.0005;
	private final static double ki = 0.0;
	
	private NetworkTable gripTapeTracking;
	private NetworkTable gripImageSize;
	
	private Integer xImageRes = 640;
	
	private Integer largestParticleIndex;
	private Double largestParticleArea;
	private Double largestParticleWidth;
	private Double largestParticleCenterY;
	
	private Double distanceToGoal;

    public AutoAimRobot() {
    	super(kp, kd, ki);
    	
        requires(Robot.driveBase);
        requires(Robot.shooterBase);
    	
    	//TODO: Update xImageRes from network tables?
    	//Set the range of the PID's input variable
    	setInputRange(0, xImageRes);
    	    	
    	gripTapeTracking = NetworkTable.getTable("GRIP/tapeTrackingCountours");
    	//gripImageSize = NetworkTable.getTable("GRIP/tapeTrackingImageSize");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Get the index of the particle with the largest area
		largestParticleIndex = null;
		largestParticleArea = 0.0;
		double[] areas = gripTapeTracking.getNumberArray("area", new double[0]);
		for (int i = 0; i < areas.length; i++) {
			Double area = areas[i];
			if (area > largestParticleArea) {
				largestParticleIndex = i;
				largestParticleArea = area;
			}
		}
		
		//If there is a largest particle
		try {
			//Get associated information
			largestParticleWidth = gripTapeTracking.getNumberArray("width", new double[0])[largestParticleIndex];
			largestParticleCenterY = gripTapeTracking.getNumberArray("centerY", new double[0])[largestParticleIndex];
			
			//Calculate distance to goal
			distanceToGoal = (20 * xImageRes) / 
					(2 * largestParticleWidth * Math.tan(((RobotMap.PROCESSING_CAMERA_FOV / 2) * Math.PI) / 180));
			//Adjust calculated distance based upon standard error
			distanceToGoal = distanceToGoal * 1.12;
			
			//Make sure the PIDcontroller is enables
			if (!getPIDController().isEnabled()) {
				getPIDController().enable();
			}
		
		//If there isn't, clear out all of the associated variables and stop the PID
		} catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
			largestParticleIndex = null;
			largestParticleArea = null;
			largestParticleWidth = null;
			largestParticleCenterY = null;
			distanceToGoal = null;
			
			//Make sure the PID controller is disabled
			//Make sure the PIDcontroller is enables
			if (getPIDController().isEnabled()) {
				getPIDController().reset();
			}
		}
		
		//Update the PID's setpoint to direct the robot to 
		//match with the center of the processed image
    	setSetpoint(xImageRes/2);

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

    //TODO: Return the current goal position based on vision processing and retrieved from nt
	@Override
	protected double returnPIDInput() {
		//Scale the input based upon image resolution to account for changes in image size
		if (largestParticleCenterY != null) {
			Double proportionalProcessVariable = largestParticleCenterY / xImageRes;
			return proportionalProcessVariable;
		} else {
			return -1;
		}
	}

	//TODO: Set the robot's rotational position based on output
	@Override
	protected void usePIDOutput(double output) {
		Robot.driveBase.tankDrive(output, -output);
	}
}

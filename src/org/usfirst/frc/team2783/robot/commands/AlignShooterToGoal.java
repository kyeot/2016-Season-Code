package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class AlignShooterToGoal extends PIDCommand {
	
	private final static double kp = 0.1;
	private final static double kd = 0.001;
	private final static double ki = 0.0;
	
	NetworkTable gripTapeTracking;
	NetworkTable gripImageSize;
	
	private int xImageRes = 640;

    public AlignShooterToGoal() {
    	super(kp, kd, ki);
    	
    	//TODO: Require shooter subsystem once someone programs it
        //requires(Robot.shooter);
    	
    	//TODO: Update xImageRes from network tables?
    	//Set the range of the PID's input variable
    	setInputRange(0, xImageRes);
    	
    	//TODO: Update setpoint when xImageRes is updated
    	//Set the PID's setpoint to be the center of the image frame
    	setSetpoint(xImageRes/2);
    	
    	gripTapeTracking = NetworkTable.getTable("GRIP/tapeTrackingCountours");
    	gripImageSize = NetworkTable.getTable("GRIP/tapeTrackingImageSize");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
		double[] areas = gripTapeTracking.getNumberArray("area", new double[0]);
		for (double area : areas) {
			
		}
		return 0;
	}

	//TODO: Set the shooter's lateral aiming motor (or position the robot) based on output
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}

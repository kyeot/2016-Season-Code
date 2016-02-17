package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class GyroCorrectedTankDrive extends PIDCommand {

	final public static double kp = 0.1;
	final public static double ki = 0.01;
	final public static double kd = 0.0;
		
    public GyroCorrectedTankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(kp, ki, kd);
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ahrs.zeroYaw();
    	setSetpoint(0.0);
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

	@Override
	protected double returnPIDInput() {
		return Robot.ahrs.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		double leftOutput = 0, rightOutput = 0;
		
		if(OI.xBoxController.getPOV() == 0){
			leftOutput = (OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2) + output);
			rightOutput = (OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2) - output);
		} else if(OI.xBoxController.getPOV() == 180){
			leftOutput = -(OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2) - output);
			rightOutput = -(OI.xBoxController.getRawAxis(3) - OI.xBoxController.getRawAxis(2) + output);
		}
		
		Robot.driveBase.tankDrive(leftOutput, rightOutput);
	}
}

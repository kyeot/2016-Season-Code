package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicShooterDrive extends Command {
	
	private Double wheelSpeed = 0.0;

    public BasicShooterDrive() {
    	requires(Robot.shooterBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 *  Control shooter wheel from operator
    	 *  Moving the joystick up and down changes the speed of the shooter. 
    	 *  Clicking button 2 saves the current speed, so that when the joystick
    	 *  is zeroed (let go of by a driver), the saved speed is recalled until 
    	 *  the joystick is again moved or the saved speed is updated.
    	 */
    	Double manipulatorWheelControlAxis = -OI.manipulator.getRawAxis(1);
    	// If the joystick is within 10% of the vertical center of the axis, it's centered
    	Boolean isCentered = Math.abs(manipulatorWheelControlAxis) < 0.2;
    	// Map the input value (1 -> -1) to a more... Useful range (0 -> 1)
    	Double scaledManipulatorWheelOutput = ((manipulatorWheelControlAxis + 1) / 2.0);
    	
    	// If button 2 is being pushed, 
    	// save the current speed (wheelSpeed) and update the motor accordingly
    	if (OI.manipulator.getRawButton(2)) {
    		wheelSpeed = scaledManipulatorWheelOutput;
    		Robot.shooterBase.setWheelSpeedVbus(wheelSpeed);
    		
		// If the joystick is centered, and button 2 is not being pressed, 
		// update the wheel speed with the most recent save value (wheelSpeed)
    	} else if (isCentered) {
    		Robot.shooterBase.setWheelSpeedVbus(wheelSpeed);
    		
		// If the joystick is neither centered nor button two is pressed, 
    	// update the motor with the joystick's value
    	} else {
    		Robot.shooterBase.setWheelSpeedVbus(scaledManipulatorWheelOutput);
    	}
    	
    	
		// Set the vertical angle adjuster based on axis 5 and a scaler
    	double verticalAxisAdjusterVbus = -OI.manipulator.getRawAxis(5) * 0.75; 
    	Robot.shooterBase.setVerticalAxisVbus(verticalAxisAdjusterVbus);
    	
    	
    	// Set the elevator speed based upon two axes
    	Double lifterSpeed = OI.manipulator.getRawAxis(3) - OI.manipulator.getRawAxis(2);
    	Robot.shooterBase.setBallElevatorVbus(lifterSpeed);
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
    	this.wheelSpeed = 0.0;
    }
}

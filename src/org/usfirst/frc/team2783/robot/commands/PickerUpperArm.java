package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.OI;
import org.usfirst.frc.team2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PickerUpperArm extends Command {

	
    public PickerUpperArm() {
    	requires(Robot.pickUp);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Double throttleValue = OI.liftJoy.getRawAxis(3);
    	Robot.pickUp.setPercentVBusArm(throttleValue);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

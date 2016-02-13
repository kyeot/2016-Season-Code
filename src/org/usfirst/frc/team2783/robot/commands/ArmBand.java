package org.usfirst.frc.team2783.robot.commands;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2783.robot.RobotMap;

/**
 *
 */
public class ArmBand extends Command {
	
	CANTalon liftUpTalon = new CANTalon (RobotMap.LIFT_UP_MOTOR_ID);
	
    public ArmBand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean temp = true;
    		if(temp == true){
    			liftUpTalon.set(100);
    			temp = false;
    		}
    		else if(temp == false){
    			liftUpTalon.set(0);
    			temp = true; 
    		}
    		
    		Double throttleValue = OI.liftJoy.getRawAxis(3);
        	System.out.println(throttleValue);
    		
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

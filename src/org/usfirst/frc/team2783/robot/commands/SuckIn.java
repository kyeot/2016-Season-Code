package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team2783.robot.RobotMap;

/**
 *
 */
public class SuckIn extends Command {

	CANTalon SuckTalon = new CANTalon (RobotMap.SUCK_IN_MOTOR_ID);
	
    public SuckIn() {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	boolean armBand = true;
        		if(armBand == true){
        			SuckTalon.set(100);
        			armBand = false;
        		}
        		else if(armBand == false){
        			SuckTalon.set(0);
        			armBand = true; 
        		}
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

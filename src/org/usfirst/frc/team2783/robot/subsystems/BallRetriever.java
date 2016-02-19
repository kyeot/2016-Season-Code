package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ZeroBallRetriever;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	private CANTalon retrieverArm;
	private CANTalon retriever;

    public BallRetriever() {
    	retrieverArm = new CANTalon(RobotMap.BALL_RETRIEVER_ARM_MOTOR_ID);
    	retriever = new CANTalon(RobotMap.BALL_RETRIEVER_MOTOR_ID);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ZeroBallRetriever());
	}
    
    public void setRetrieverVbus(double input){
    	retriever.set(input);
    }
    
    public void setRetrieverArmVbus(double input){
    	retrieverArm.set(input);
    }
}

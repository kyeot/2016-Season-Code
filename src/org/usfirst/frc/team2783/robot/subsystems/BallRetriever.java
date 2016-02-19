package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ZeroBallRetriever;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	private VictorSP retrieverArm;
	private VictorSP retriever;

    public BallRetriever() {
    	retrieverArm = new VictorSP(RobotMap.BALL_RETRIEVER_ARM_MOTOR_PWM_PORT);
    	retriever = new VictorSP(RobotMap.BALL_RETRIEVER_MOTOR_PWM_PORT);
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

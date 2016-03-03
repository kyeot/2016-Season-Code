package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ContinueRetrieverSpeed;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	private VictorSP leftRetriever;
	private VictorSP rightRetriever;

    public BallRetriever() {
    	leftRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_LEFT_MOTOR_PWM_PORT);
    	
    	rightRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_RIGHT_MOTOR_PWM_PORT);
    	rightRetriever.setInverted(true);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ContinueRetrieverSpeed());
	}
    
    public void setRetrieverVbus(double input){
    	leftRetriever.set(input);
    	rightRetriever.set(input);
    }
    
    public void controllerSafety() {
    	leftRetriever.Feed();
    	rightRetriever.Feed();
    }
}

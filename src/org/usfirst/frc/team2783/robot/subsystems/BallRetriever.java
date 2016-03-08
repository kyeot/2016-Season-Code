package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SetAllZero;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	private VictorSP leftRetriever;
	private VictorSP rightRetriever;
	private VictorSP armControl;
	
	private double lastRetrieverSpeed = 0.0;
	
    public BallRetriever() {
    	leftRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_LEFT_MOTOR_PWM_PORT);
    	
    	rightRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_RIGHT_MOTOR_PWM_PORT);
    	rightRetriever.setInverted(true);
    	
    	armControl = new VictorSP(RobotMap.RETRIEVER_ARM_MOTOR_PWM_PORT);
    	armControl.setInverted(true);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetAllZero());
	}
    
	public void setRetrieverVbus(double input) {
    	setRetrieverVbus(input, false);
	}
	
    public void setRetrieverVbus(double input, boolean save){
    	if (save) {
    		lastRetrieverSpeed = input;
    	}
    	leftRetriever.set(input);
    	rightRetriever.set(input);
    	
    	SmartDashboard.putNumber("Ball Retriever Speed", input);
    }
    
    public void setRetrieverArmVbus(double input){
    	armControl.set(input);
    }
    
    public void continueRetrieverSpeed() {
    	setRetrieverVbus(lastRetrieverSpeed);
    }
    
    public void controllerSafety() {
    	leftRetriever.Feed();
    	rightRetriever.Feed();
    	armControl.Feed();
    }
}

package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.SetAllZero;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class BallRetriever extends Subsystem {
	
	private VictorSP leftRetriever;
	private VictorSP rightRetriever;
	private VictorSP armControl;
	private DigitalInput armForwardLimit;
	private DigitalInput armReverseLimit;
	
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
    }
    
    public void setRetrieverArmVbus(double input) {
    	if(armForwardLimit.get() == false && armReverseLimit.get() == false){
    		armControl.set(input);
    	} else if(armForwardLimit.get() == true) {
    		if(input < 0) {
    			armControl.set(input);
    		}
    	} else if(armReverseLimit.get() == true) {
    		if(input > 0) {
    			armControl.set(input);
    		}
    	}	
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

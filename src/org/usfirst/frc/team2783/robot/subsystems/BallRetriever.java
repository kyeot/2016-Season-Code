package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.UpdateRetriever;
import org.usfirst.frc.team2783.robot.util.DiscreteToggle;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class BallRetriever extends Subsystem {
	
	public enum RetrieverDirection {
		RET_IN,
		RET_OUT;
	}
	
	public enum ArmDirection {
		ARM_UP,
		ARM_DOWN,
		ARM_STOP;
	}
	
	private VictorSP leftRetriever;
	private VictorSP rightRetriever;
	private VictorSP armControl;
	private DigitalInput armForwardLimit;
	private DigitalInput armReverseLimit;
	
	public ArmDirection armDirection;
	
	private DiscreteToggle retrieverInToggle;
	private DiscreteToggle retrieverOutToggle;
	
    public BallRetriever() {
    	leftRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_LEFT_MOTOR_PWM_PORT);
    	
    	rightRetriever = new VictorSP(RobotMap.BALL_RETRIEVER_RIGHT_MOTOR_PWM_PORT);
    	rightRetriever.setInverted(true);
    	
    	armControl = new VictorSP(RobotMap.RETRIEVER_ARM_MOTOR_PWM_PORT);
    	armControl.setInverted(true);
    	
    	retrieverInToggle = new DiscreteToggle();
    	retrieverOutToggle = new DiscreteToggle();
    	
    }
    
	@Override
	protected void initDefaultCommand() {		
		setDefaultCommand(new UpdateRetriever());
	}
    
	public void toggleRetriever(RetrieverDirection direction) {
		if(direction == RetrieverDirection.RET_IN) {
			retrieverInToggle.toggle();
			retrieverOutToggle.setValue(false);
		} else if(direction == RetrieverDirection.RET_OUT) {
			retrieverOutToggle.toggle();
			retrieverInToggle.setValue(false);
		}
	}
    
    public void setArm(ArmDirection direction) {
		this.armDirection = direction;
    }
    
    public void updateRetriever() {
    	
    	if(this.retrieverInToggle.getValue()) {
    		leftRetriever.set(0.75);
    		rightRetriever.set(0.75);
    	} else if(this.retrieverOutToggle.getValue()) {
    		leftRetriever.set(-0.75);
    		rightRetriever.set(-0.75);
    		
    	} else {
    		leftRetriever.set(0);
    		rightRetriever.set(0);
    	}
    	
    	if(armDirection == ArmDirection.ARM_UP) {
    		armControl.set(0.65);
    	} else if(armDirection == ArmDirection.ARM_DOWN){
    		armControl.set(-0.65);
    	} else {
    		armControl.set(0);
    	}
    }
    
}

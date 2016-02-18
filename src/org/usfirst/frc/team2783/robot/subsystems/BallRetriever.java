package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.PickerUpperArm;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	public CANTalon PickupArmMotor;
	public CANTalon TurnBandsOnArmMotor;
	public CANTalon BallElevatorMotor;

    public BallRetriever() {
    	PickupArmMotor = new CANTalon(RobotMap.TURN_PICK_UP_ARM_MOTOR_ID);
    	TurnBandsOnArmMotor = new CANTalon(RobotMap.TURN_BANDS_ON_ARM_MOTOR_ID);
    	BallElevatorMotor = new CANTalon(RobotMap.LIFT_BALL_UP_MOTOR_ID);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PickerUpperArm());
	}
    
    public void setLiftVbus(double input){
    	PickupArmMotor.set(input);
    }
    
    public void setRetrieverVbus(double input){
    	TurnBandsOnArmMotor.set(input);
    }
    
    public void setRetrieverArmVbus(double input){
    	BallElevatorMotor.set(input);
    }
}

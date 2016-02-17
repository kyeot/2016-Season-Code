package org.usfirst.frc.team2783.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.PickerUpperArm;

/**
 *
 */
public class BallRetriever extends Subsystem {
	
	public CANTalon PickupArmMotor = new CANTalon(RobotMap.TURN_PICK_UP_ARM_MOTOR_ID);
	public CANTalon TurnBandsOnArmMotor = new CANTalon(RobotMap.TURN_BANDS_ON_ARM_MOTOR_ID);
	public CANTalon BallElevatorMotor = new CANTalon(RobotMap.LIFT_BALL_UP_MOTOR_ID);

    public BallRetriever() {
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PickerUpperArm());
	}
    
    public void setPercentVBusLift(double input){
    	PickupArmMotor.set(input);
    }
    
    public void setPercentVBusSuck(double input){
    	TurnBandsOnArmMotor.set(input);
    }
    
    public void setPercentVBusArm(double input){
    	BallElevatorMotor.set(input);
    }
}

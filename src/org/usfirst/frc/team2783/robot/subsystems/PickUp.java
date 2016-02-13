package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.Robot;
import org.usfirst.frc.team2783.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.PickUpArm;

/**
 *
 */
public class PickUp extends Subsystem {
	
	CANTalon armMotor = new CANTalon(RobotMap.TURN_ARM_MOTOR_ID);
	CANTalon suckUpMotor = new CANTalon(RobotMap.SUCK_IN_MOTOR_ID);
	CANTalon liftUpMotor = new CANTalon(RobotMap.LIFT_UP_MOTOR_ID);

    public PickUp() {
    }
    
    public void setPrecentVBusLift(double input){
    	liftUpMotor.set(input);
    }
    
    public void setPrecentVBusSuck(double input){
    	suckUpMotor.set(input);
    }
    
    public void setPercentVBusArm(double input){
    	armMotor.set(input);
    }
    

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PickUpArm());
	}
}

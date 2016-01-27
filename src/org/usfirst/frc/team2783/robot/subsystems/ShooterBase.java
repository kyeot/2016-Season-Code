package org.usfirst.frc.team2783.robot.subsystems;

import org.usfirst.frc.team2783.robot.RobotMap;
import org.usfirst.frc.team2783.robot.commands.ShootOnly;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterBase extends Subsystem {
	private CANTalon shooterWheel = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR_ID);

	
	public ShooterBase() {
		super();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShootOnly());
	}
	public void setWheelSpeed(double rpmOutput) {
		shooterWheel.set(rpmOutput);
	}

}

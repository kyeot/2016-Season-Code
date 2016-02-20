package org.usfirst.frc.team2783.robot;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc.team2783.robot.commands.AutoAimRobot;
import org.usfirst.frc.team2783.robot.commands.BallElevatorDrop;
import org.usfirst.frc.team2783.robot.commands.BallElevatorLift;
import org.usfirst.frc.team2783.robot.commands.FireShooter;
import org.usfirst.frc.team2783.robot.commands.PivotTankDrive;
import org.usfirst.frc.team2783.robot.triggers.Dpad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick xBoxController = new Joystick(RobotMap.XBOX_CONTROLLER_ID);
	public static Joystick manipulator = new Joystick(RobotMap.SHOOTER_JOYSTICK_ID);

	Trigger pivotLeftTrigger = new Dpad(xBoxController, 270);
	Trigger pivotRightTrigger = new Dpad(xBoxController, 90);
	
	public Button fireShooterButton = new JoystickButton(manipulator, 1);
	public Button dropElevatorButton = new JoystickButton(manipulator, 7);
	public Button liftElevatorButton = new JoystickButton(manipulator, 8);
	
	public Button autoAlignButton = new JoystickButton(manipulator, 3);
	
	public OI() {
		pivotLeftTrigger.whileActive(new PivotTankDrive());
		pivotRightTrigger.whileActive(new PivotTankDrive());
		
		fireShooterButton.whenPressed(new FireShooter());
		dropElevatorButton.whileHeld(new BallElevatorDrop());		
		liftElevatorButton.whileHeld(new BallElevatorLift());
		
		autoAlignButton.whenPressed(new AutoAimRobot());
	}
	
}

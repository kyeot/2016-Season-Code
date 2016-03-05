package org.usfirst.frc.team2783.robot;

import org.usfirst.frc.team2783.robot.commands.AutoAimRobot;
import org.usfirst.frc.team2783.robot.commands.AutoAimThenShoot;
import org.usfirst.frc.team2783.robot.commands.LiftArm;
import org.usfirst.frc.team2783.robot.commands.LowerArm;
import org.usfirst.frc.team2783.robot.commands.PivotTankDrive;
import org.usfirst.frc.team2783.robot.commands.RetrieverIn;
import org.usfirst.frc.team2783.robot.commands.RetrieverOut;
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
	
	public Button LiftBallElevatorButton = new JoystickButton(manipulator, 1);
	public Button ReverseBandsOnArmButton = new JoystickButton(manipulator, 7);
	public Button retrieverInButton = new JoystickButton(manipulator, 5);
	public Button retrieverOutButton = new JoystickButton(manipulator, 6);
	public Button liftArm = new JoystickButton(manipulator, 4);
	public Button lowerArm = new JoystickButton(manipulator, 3);
	
	public Button alignThenShootButton = new JoystickButton(manipulator, 3);
	public Button autoAlignButton = new JoystickButton(manipulator, 4);
	
	public OI() {
		pivotLeftTrigger.whileActive(new PivotTankDrive());
		pivotRightTrigger.whileActive(new PivotTankDrive());
		
		alignThenShootButton.whenPressed(new AutoAimThenShoot());
		autoAlignButton.whenPressed(new AutoAimRobot());

		retrieverInButton.toggleWhenPressed(new RetrieverIn());
		retrieverOutButton.toggleWhenPressed(new RetrieverOut());
		
		liftArm.whileActive(new LiftArm());
		lowerArm.whileActive(new LowerArm());
	}
	
}

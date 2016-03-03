package org.usfirst.frc.team2783.robot;

import org.usfirst.frc.team2783.robot.commands.BallElevatorDrop;
import org.usfirst.frc.team2783.robot.commands.BallElevatorLift;
import org.usfirst.frc.team2783.robot.commands.RetrieverWheelsSuckIn;
import org.usfirst.frc.team2783.robot.commands.RetrieverWheelsPushOut;
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
	public static Joystick manipulatorXboxController = new Joystick(RobotMap.MANIPULATOR_XBOX_ID);

	Trigger pivotLeftTrigger = new Dpad(xBoxController, 270);
	Trigger pivotRightTrigger = new Dpad(xBoxController, 90);
	
	public Button liftBallElevatorButton = new JoystickButton(manipulatorXboxController, 2);
	public Button reverseBallElevatorButton = new JoystickButton(manipulatorXboxController, 3);
	
	public Button suckBallInBumper = new JoystickButton(manipulatorXboxController, 4);
	public Button pushBallOutBumper = new JoystickButton(manipulatorXboxController, 5);
	
	
	public OI() {
		pivotLeftTrigger.whileActive(new PivotTankDrive());
		pivotRightTrigger.whileActive(new PivotTankDrive());

		suckBallInBumper.toggleWhenPressed(new RetrieverWheelsSuckIn());
		pushBallOutBumper.toggleWhenPressed(new RetrieverWheelsPushOut());
		liftBallElevatorButton.toggleWhenPressed(new BallElevatorLift());
		reverseBallElevatorButton.toggleWhenPressed(new BallElevatorDrop());
	}
	
}

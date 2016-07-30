package org.usfirst.frc.team2783.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick xBoxController = new Joystick(RobotMap.XBOX_CONTROLLER_ID);
	public static Joystick manipulator = new Joystick(RobotMap.SHOOTER_JOYSTICK_ID);

	//Trigger pivotLeftTrigger = new Dpad(xBoxController, 270);
	//Trigger pivotRightTrigger = new Dpad(xBoxController, 90);
	//Trigger moveForwardTrigger = new Dpad(xBoxController, 0);
	//Trigger moveDownwardTrigger = new Dpad(xBoxController, 180);
	//Button fineControlButton = new JoystickButton(xBoxController, 2);
	
	//Trigger fullSpeedShooterTrigger = new Dpad(manipulator, 0);
	//Trigger cancelFullSpeedShooterTrigger = new Dpad(manipulator, 180);
	//Button reverseShooter = new JoystickButton(xBoxController, 5);

	//Button adjusterToBottom = new JoystickButton(manipulator, 7);
	//Button adjusterToTop = new JoystickButton(manipulator, 8);
	
	//public Button ReverseBandsOnArmButton = new JoystickButton(manipulator, 7);
	//public Button retrieverInButton = new JoystickButton(manipulator, 5);
	//public Button retrieverOutButton = new JoystickButton(manipulator, 6);
	//public Button liftArm = new JoystickButton(manipulator, 4);
	//public Button lowerArm = new JoystickButton(manipulator, 3);
	
	public OI() {
		//pivotLeftTrigger.whileActive(new PivotTankDrive());
		//pivotRightTrigger.whileActive(new PivotTankDrive());
		
		//fineControlButton.toggleWhenPressed(new FineControl());
		
		//retrieverInButton.whenPressed(new ToggleRetriever(RetrieverDirection.RET_IN));
		//retrieverOutButton.whenPressed(new ToggleRetriever(RetrieverDirection.RET_OUT));
		
		//liftArm.whenPressed(new SetArm(ArmDirection.ARM_UP));
		//liftArm.whenReleased(new SetArm(ArmDirection.ARM_STOP));
		//lowerArm.whenPressed(new SetArm(ArmDirection.ARM_DOWN));
		//lowerArm.whenReleased(new SetArm(ArmDirection.ARM_STOP));
		
		//Command fullSpeedShooter = new ShooterAtAndControl(1.0);
		//fullSpeedShooterTrigger.whenActive(fullSpeedShooter);
		//cancelFullSpeedShooterTrigger.cancelWhenActive(fullSpeedShooter);
		//reverseShooter.whileHeld(new ShooterAtAndControl(-1.0));

		//adjusterToTop.whenPressed(new AngleAdjusterToLimit(Limit.TOP_LIMIT));
		//adjusterToBottom.whenPressed(new AngleAdjusterToLimit(Limit.BOTTOM_LIMIT));
		
		//Gyro drive commands [untested]
		//moveForwardTrigger.whileActive(new GyroCorrectedTankDrive());
		//moveDownwardTrigger.whileActive(new GyroCorrectedTankDrive());
	}
	
}

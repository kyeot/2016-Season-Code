package org.usfirst.frc.team2783.robot;

import org.usfirst.frc.team2783.robot.commands.LiftBall;
import org.usfirst.frc.team2783.robot.commands.PivotTankDrive;
import org.usfirst.frc.team2783.robot.commands.BandsOnArm;
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
	
	public static Joystick liftJoy = new Joystick(1);
	
	Button button1 = new JoystickButton(liftJoy, 1);
	Button button2 = new JoystickButton(liftJoy, 2);
	
	
	public static Joystick xBoxController = new Joystick(RobotMap.XBOX_CONTROLLER_ID);
	
	Trigger pivotLeftTrigger = new Dpad(xBoxController, 270);
	Trigger pivotRightTrigger = new Dpad(xBoxController, 90);
	
	public OI() {
		button1.toggleWhenPressed(new LiftBall());
		button2.toggleWhenPressed(new BandsOnArm());
		pivotLeftTrigger.whileActive(new PivotTankDrive());
		pivotRightTrigger.whileActive(new PivotTankDrive());
		                                                                         
	}
	
}


package org.usfirst.frc.team2783.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	final public static int XBOX_CONTROLLER_ID = 0;
	final public static int SHOOTER_JOYSTICK_ID = 1;
	
	final public static int FRONT_LEFT_MOTOR_ID = 16;
	final public static int REAR_LEFT_MOTOR_ID = 1;
	final public static int FRONT_RIGHT_MOTOR_ID = 15;
	final public static int REAR_RIGHT_MOTOR_ID = 14;
	
	final public static int SHOOTER_WHEEL_MOTOR_ID = 13; 
	final public static int SHOOTER_VERTICAL_AXIS_MOTOR_PWM_PORT = 4;
	final public static int BALL_ELEVATOR_PWM_PORT = 2;
	
	final public static int BALL_RETRIEVER_LEFT_MOTOR_PWM_PORT = 1;
	final public static int BALL_RETRIEVER_RIGHT_MOTOR_PWM_PORT = 3;
	final public static int RETRIEVER_ARM_MOTOR_PWM_PORT = 0;
	
}

package org.usfirst.frc.team2783.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class Dpad extends Trigger {
    
	Joystick joystick;
	double povTargetAngle;
	
	public Dpad(Joystick joystick, double povTargetAngle){
		this.joystick = joystick;
		this.povTargetAngle = povTargetAngle;
	}
	
    public boolean get(){
        return joystick.getPOV(0) == this.povTargetAngle;
    }
}

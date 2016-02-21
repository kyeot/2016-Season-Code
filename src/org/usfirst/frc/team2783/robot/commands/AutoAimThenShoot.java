package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAimThenShoot extends CommandGroup {
    
    public  AutoAimThenShoot() {
    	addSequential(new AutoAimRobot());
    	addSequential(new FireShooter());
    }
}

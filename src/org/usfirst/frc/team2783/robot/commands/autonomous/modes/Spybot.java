package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.FireShooter;
import org.usfirst.frc.team2783.robot.commands.autonomous.AutoArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Spybot extends CommandGroup {

    public Spybot() {
    	addSequential(new AutoArm(-0.7, 1.2));
    	addSequential(new FireShooter());
    }
}
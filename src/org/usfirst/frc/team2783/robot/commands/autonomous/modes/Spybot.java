package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.FireShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Spybot extends CommandGroup {

    public Spybot() {
    	addSequential(new FireShooter());
    }
}
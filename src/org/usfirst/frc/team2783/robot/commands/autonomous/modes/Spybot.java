package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoArm;
import org.usfirst.frc.team2783.robot.commands.autonomous.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Spybot extends CommandGroup {

    public Spybot() {
    	addParallel(new AutoArm(-0.7, 1.2));
    	addSequential(new AutoShoot(1, 1, 3, 2.5));
    }
}
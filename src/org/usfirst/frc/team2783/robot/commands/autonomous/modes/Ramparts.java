package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Ramparts extends CommandGroup {

    public Ramparts() {
    	 addSequential(new AutoDrive(0.60, 0.65, 4.6));
    }
}
   
package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Moat extends CommandGroup {

    public Moat() {
    	 addSequential(new AutoDrive(1.0, 1.0, 3.3));
    }
}
   
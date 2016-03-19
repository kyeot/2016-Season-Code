package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReachDefense extends CommandGroup {

    public ReachDefense() {
    	 addSequential(new AutoDrive(0.4, 0.4, 1.8));
    }
}
   
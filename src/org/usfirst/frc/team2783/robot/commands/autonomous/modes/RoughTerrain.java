package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrain extends CommandGroup {

    public RoughTerrain() {
        addSequential(new AutoDrive(0.7, 0.7, 2.0));
    }
}
package org.usfirst.frc.team2783.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public Autonomous() {
        addSequential(new AutoDrive(-0.6, -0.6, 6));
        
    }

}
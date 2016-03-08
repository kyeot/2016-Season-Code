package org.usfirst.frc.team2783.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BasicReverseAuto extends CommandGroup {

    public BasicReverseAuto() {
        addSequential(new AutoDrive(-0.6, -0.6, 6));
        
    }

}
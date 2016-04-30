package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoArm;
import org.usfirst.frc.team2783.robot.commands.autonomous.AutoDrive;
import org.usfirst.frc.team2783.robot.subsystems.BallRetriever.ArmDirection;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Portcullis extends CommandGroup {
    
    public  Portcullis() {
    	addParallel(new AutoArm(ArmDirection.ARM_DOWN, 0.8));
    	addSequential(new AutoDrive(0.75, 0.75, 2.85));
    }
}

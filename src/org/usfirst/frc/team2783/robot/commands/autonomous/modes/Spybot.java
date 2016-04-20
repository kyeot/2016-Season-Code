package org.usfirst.frc.team2783.robot.commands.autonomous.modes;

import org.usfirst.frc.team2783.robot.commands.autonomous.AutoArm;
import org.usfirst.frc.team2783.robot.commands.autonomous.AutoShoot;
import org.usfirst.frc.team2783.robot.subsystems.BallRetriever.ArmDirection;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Spybot extends CommandGroup {

    public Spybot() {
    	addParallel(new AutoArm(ArmDirection.ARM_DOWN, 0.8));
    	addSequential(new AutoShoot(1));
    }
}
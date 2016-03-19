package org.usfirst.frc.team2783.robot;

import java.io.IOException;

import org.usfirst.frc.team2783.robot.commands.autonomous.modes.Moat;
import org.usfirst.frc.team2783.robot.commands.autonomous.modes.Ramparts;
import org.usfirst.frc.team2783.robot.commands.autonomous.modes.ReachDefense;
import org.usfirst.frc.team2783.robot.commands.autonomous.modes.RoughTerrain;
import org.usfirst.frc.team2783.robot.subsystems.BallRetriever;
import org.usfirst.frc.team2783.robot.subsystems.DriveBase;
import org.usfirst.frc.team2783.robot.subsystems.ShooterBase;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Command autonomous;
	public static final DriveBase driveBase = new DriveBase();
	public static final ShooterBase shooterBase = new ShooterBase();
	public static final BallRetriever retriever = new BallRetriever();
	public static NetworkTable smartDashTable;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {
		oi = new OI();
		
		//Start GRIP in a new process
		try {
			Runtime.getRuntime().exec(new String[] {
			        "/usr/local/frc/JRE/bin/java", "-jar",
			        "/home/lvuser/grip.jar", "/home/lvuser/project.grip" });
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		CameraServer usbCameraServer = CameraServer.getInstance();
        usbCameraServer.setQuality(50);
        
        //the camera name (ex "cam0") can be found through the roborio web interface
        usbCameraServer.startAutomaticCapture("cam1");
        this.smartDashTable = NetworkTable.getTable("SmartDashboard");
        
        //Populate Autonomous chooser
        String[] autonomousList = {"None", "Reach Defense", "Rough Terrain", "Ramparts", "Moat"};
        this.smartDashTable.putStringArray("Auto List", autonomousList);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */

    public void disabledInit(){
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called when the robot enters autonomous.
	 */
	
    public void autonomousInit() {
    	
    	//Gets the autonomous selector value from the dashboard
    	String autoSelected = SmartDashboard.getString("Auto Selector", "None");
    	
    	//Switches the autonomous mode based on the value from the SmartDashboard
		switch(autoSelected) {
			case "Reach Defense":
				autonomous = new ReachDefense();
				break;
			case "Rough Terrain":
				autonomous = new RoughTerrain();
				break;
			case "Ramparts":
				autonomous = new Ramparts();
				break;
			case "Moat": 
				autonomous = new Moat();
				break;
			case "None":
			default:
				autonomous = null;
				break;
		} 
    	
    	if(autonomous != null) {
    		autonomous.start();
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}

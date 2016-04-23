package org.usfirst.frc.team2783.robot.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomLogger {
	
	File loggingFile;
	BufferedWriter writer = null;
	
	public CustomLogger(){
		
		try {
			loggingFile = new File("/src/org/usfirst/frc/team2783/robot/util/logFile.txt");
			writer = new BufferedWriter(new FileWriter(loggingFile, true));
			System.out.println(loggingFile.getCanonicalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(String dataName, String data){
		try {
			writer.write(dataName + ": " + data);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(String dataName, int data){
		try {
			writer.write(dataName + ": " + data);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(String dataName, double data){
		try {
			writer.write(dataName + ": " + data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(String dataName, boolean data){
		try {
			writer.write(dataName + ": " + data);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void endLog(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}

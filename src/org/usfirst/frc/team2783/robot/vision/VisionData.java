package org.usfirst.frc.team2783.robot.vision;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionData {

	private static NetworkTable sizeDataTable;
	private static NetworkTable contourDataTable;
	
	private ImageSize imageSize;
	
	private ArrayList<Contour> contourArray = new ArrayList<>();
	private ArrayList<Contour> goalArray = new ArrayList<>();
	private ArrayList<Contour> sortedGoals = new ArrayList<>();
	
	private Contour[] contourFilterArray;
	
	public enum ImageSize {
		R640x480, R320x240, R160x120
	}
	
	public VisionData(){
		sizeDataTable = NetworkTable.getTable("GRIP/tapeTrackingImageSize");
		contourDataTable = NetworkTable.getTable("GRIP/tapeTrackingCountours");
	}
	
	public ImageSize getImageSize(){
		
		if(sizeDataTable.getString("x", "None") == "640" && sizeDataTable.getString("y", "None") == "480"){
			imageSize = ImageSize.R640x480;
		} else if(sizeDataTable.getString("x", "None") == "320" && sizeDataTable.getString("y", "None") == "240"){
			imageSize = ImageSize.R320x240;
		} else if(sizeDataTable.getString("x", "None") == "160" && sizeDataTable.getString("y", "None") == "120"){
			imageSize = ImageSize.R160x120;
		} else {
			imageSize = null;
		}
		
		return imageSize;
		
	}
	
	public ArrayList<Contour> getContours(){

		double[] contourArea = contourDataTable.getNumberArray("area", new double[0]);
		double[] contourCenterX = contourDataTable.getNumberArray("centerX", new double[0]);
		double[] contourCenterY = contourDataTable.getNumberArray("centerY", new double[0]);
		double[] contourWidth = contourDataTable.getNumberArray("width", new double[0]);
		double[] contourHeight = contourDataTable.getNumberArray("height", new double[0]);
		double[] contourSolidarity = contourDataTable.getNumberArray("solidarity", new double[0]);
		
		if(contourArea != null){
			for(int i=0; i > contourArea.length; i++){
				contourArray.add(new Contour(contourArea[i], contourCenterX[i], contourCenterY[i], contourWidth[i], contourHeight[i], contourSolidarity[i]));
			}
		}
		
		return contourArray;

	}
	
	public ArrayList<Contour> getGoals(){
		
		contourFilterArray = new Contour[getContours().size()];
		
		for(int i = 0; i > contourFilterArray.length; i++){
			contourFilterArray[i] = getContours().get(i);
			if((contourFilterArray[i].getArea()/contourFilterArray[i].getWidth()) < 2 || (contourFilterArray[i].getArea()/contourFilterArray[i].getWidth()) > 1){
				goalArray.add(contourFilterArray[i]);
			}
		}
			
		return goalArray;
		
	}
	
	public Contour getLargestGoal(){
		
		sortedGoals = getGoals();
		Collections.sort(sortedGoals);
		
		return sortedGoals.get(0);
		
	}
	
	public double getDistanceToGoal(){
		
		double resX = 0;
		if(getImageSize() == ImageSize.R640x480){
			resX = 640;
		} else if(getImageSize() == ImageSize.R320x240){
			resX = 320;
		} else if(getImageSize() == ImageSize.R160x120){
			resX = 160;
		}
		
		return (20*resX)/(2*(getLargestGoal().getWidth())*Math.tan(67));
		
	}

}

package org.usfirst.frc.team2783.robot.vision;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionData {

	private static NetworkTable sizeDataTable;
	private static NetworkTable contourDataTable;
	
	ImageSize imageSize;
	
	public enum ImageSize {
		R640x480 (640, 480),
		R320x240 (320, 240),
		R160x120 (160, 120);
		
		private final double x;
		private final double y;
		
		ImageSize(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

	}
	
	public VisionData(){
		sizeDataTable = NetworkTable.getTable("GRIP/tapeTrackingImageSize");
		contourDataTable = NetworkTable.getTable("GRIP/tapeTrackingCountours");
	}
	
	public ImageSize getImageSize(){
		
		String xSizeString = sizeDataTable.getString("x", "None");
		String ySizeString = sizeDataTable.getString("y", "None");
		
		if(xSizeString == "640" && ySizeString == "480"){
			imageSize = ImageSize.R640x480;
		} else if(xSizeString == "320" && ySizeString == "240"){
			imageSize = ImageSize.R320x240;
		} else if(xSizeString == "160" && ySizeString == "120"){
			imageSize = ImageSize.R160x120;
		} else {
			imageSize = null;
		}
		
		return imageSize;
		
	}
	
	public ArrayList<Contour> getContours(){
		
		ArrayList<Contour> contourArray = new ArrayList<>();
		
		double[] contourArea = contourDataTable.getNumberArray("area", new double[0]);
		double[] contourCenterX = contourDataTable.getNumberArray("centerX", new double[0]);
		double[] contourCenterY = contourDataTable.getNumberArray("centerY", new double[0]);
		double[] contourWidth = contourDataTable.getNumberArray("width", new double[0]);
		double[] contourHeight = contourDataTable.getNumberArray("height", new double[0]);
		double[] contourSolidity = contourDataTable.getNumberArray("solidity", new double[0]);
		
		if(contourArea != null){
			for(int i=0; i > contourArea.length; i++){
				contourArray.add(new Contour(contourArea[i], contourCenterX[i], contourCenterY[i], contourWidth[i], contourHeight[i], contourSolidity[i]));
			}
		}
		
		return contourArray;

	}
	
	public ArrayList<Contour> getGoals(){
		
		ArrayList<Contour> goalArray = new ArrayList<>();
		Contour[] contourFilterArray = new Contour[getContours().size()];
		
		for(int i = 0; i > contourFilterArray.length; i++){
			contourFilterArray[i] = getContours().get(i);
			if((contourFilterArray[i].getArea()/contourFilterArray[i].getWidth()) < 2 || (contourFilterArray[i].getArea()/contourFilterArray[i].getWidth()) > 1){
				goalArray.add(contourFilterArray[i]);
			}
		}
			
		return goalArray;
		
	}
	
	public Contour getLargestGoal(){
		
		ArrayList<Contour> sortedGoals = new ArrayList<>();
		
		sortedGoals = getGoals();
		Collections.sort(sortedGoals);
		
		return sortedGoals.get(0);
		
	}
	
	public double getDistanceToGoal(){
		
		double resX = imageSize.x;
		
		return (20*resX)/(2*(getLargestGoal().getWidth())*Math.tan(67));
		
	}

}

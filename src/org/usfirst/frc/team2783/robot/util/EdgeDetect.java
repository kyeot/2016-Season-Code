package org.usfirst.frc.team2783.robot.util;

public class EdgeDetect {
	
	EdgeType edgeType;
	Boolean lastVal;

	public enum EdgeType {
		RISING_EDGE_DETECT,
		FALLING_EDGE_DETECT
	};
	
	public EdgeDetect() {
		this(EdgeType.RISING_EDGE_DETECT, false);
	}
	
	public EdgeDetect(EdgeType edgeType) {
		this(edgeType, false);
	}
	
	public EdgeDetect(EdgeType edgeType, Boolean startVal) {
		this.edgeType = edgeType;
		this.lastVal = startVal;
	}
	
	public Boolean isEdge(Boolean newVal) {
		return isEdge(newVal, this.edgeType);
	}
	
	public Boolean isEdge(Boolean newVal, EdgeType edgeType) {
		Boolean isEdge = false;
		switch (edgeType) {
			default:
			case RISING_EDGE_DETECT:
				isEdge = newVal && !lastVal;
				break;
			case FALLING_EDGE_DETECT:
				isEdge = !newVal && lastVal;
				break;
		}
		
		lastVal = newVal;
		return isEdge;
	}
}

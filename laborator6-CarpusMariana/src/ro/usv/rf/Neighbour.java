package ro.usv.rf;

import java.util.ArrayList;
import java.util.Arrays;

public class Neighbour implements Comparable<Neighbour> {

	double grade; // nota 
	double distance; // distanta pana la punctul cautat
	String family; // insufficient, sufficient,
	
	public int compareTo(Neighbour compareNeighbor) {
		
		
		return Double.compare(this.distance, compareNeighbor.distance);
					
	}	
	

}

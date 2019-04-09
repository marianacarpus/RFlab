package ro.usv.rf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistanceUtils {
	
	
	protected static String calculateKNN(String[][] learningSet, double gradeUnknown, int k)
	{
			Neighbour[] neighbors = new Neighbour[learningSet.length];
			
			for(int i=0;i<learningSet.length;i++)
			{
				neighbors[i] = new Neighbour();
				neighbors[i].grade = Double.valueOf(learningSet[i][0]);
				neighbors[i].family = learningSet[i][1];
				neighbors[i].distance = Math.abs(neighbors[i].grade - gradeUnknown);
			}
			
			Arrays.sort(neighbors);
			
			ArrayList<Integer> frequencyNumber = new ArrayList<Integer>();
			ArrayList<String> frequencyName = new ArrayList<String>();
			frequencyNumber.add(1);
			frequencyName.add(neighbors[0].family);
			//System.out.println("0 : " + neighbors[0].grade + " & " + neighbors[0].family);
			
			for(int i=1;i<k;i++)
			{
				//System.out.println(i + " : " + neighbors[i].grade + " & " + neighbors[i].family);

				int pos = frequencyName.indexOf(neighbors[i].family);
				if(pos == -1)
				{
					frequencyNumber.add(1);
					frequencyName.add(neighbors[i].family);
				}
				else
				{
					int value = frequencyNumber.get(pos);
					frequencyNumber.set(pos, value+1);
				}
			}
			
			int index = 0;
			int max = Integer.MIN_VALUE;
			
			for(int i=0;i<frequencyNumber.size();i++)
			{
				if(frequencyNumber.get(i) > max)
				{
					max = frequencyNumber.get(i);
					index = i;
				}
			}
			
			return frequencyName.get(index);
	}
	
	
	public static double calculateDistanceEuclidianGeneralizedString(double[] pattern1, String[] pattern2)
	{
		double sum = 0D;
		for(int i=0;i<2;i++)
		{
			sum+=(Math.pow(pattern1[i]-Double.valueOf(pattern2[i]),2));
		}
		return Math.sqrt(sum);
		//return Math.sqrt(Math.pow(pattern1[0]-pattern2[0],2) + Math.pow(pattern1[1]- pattern2[1], 2));
	}
	
	
	
	
	
}

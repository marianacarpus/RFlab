package ro.usv.rf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathUtils {

	protected static Map<Double, Integer> getDistinctClasses(Double [] classes)
	{
		Map<Double, Integer> counterMap = new HashMap<Double, Integer>();
		for (int j = 0; j < classes.length; j++) {
			if (counterMap.containsKey(classes[j])) {
				int count = counterMap.get(classes[j]);
				counterMap.put((classes[j]), ++count);
			} else {
				counterMap.put((classes[j]), 1);
			}
		}
		return counterMap;
	}
	
	private static double[] getClassesWeightedAverage(List<double[]>elements, int nr)
	{
		double[] avg=new double[elements.get(0).length];
		double sum;
		for(int i=0;i<elements.get(i).length;i++){
				sum=0.0;
				for(int j=0;j<elements.size();j++)
					 sum+=elements.get(j)[i];
			avg[i]=sum/nr;
		}
		return avg;	
	}
	
	protected static List<double[]> getAVG(Double[]classes, List<double[]>patternList)
	{
		List<double[]>classesElements;
		List<double[]>avgList=new ArrayList<double[]>();
		Map<Double, Integer> map=getDistinctClasses(classes);
		int index=0;
		for(Double key:map.keySet())
		{
			classesElements=new ArrayList<double[]>();
			for(int i=index;i<(int)map.get(key)+index;i++)
				classesElements.add(patternList.get(i));
			index+=(int)map.get(key);
			avgList.add(getClassesWeightedAverage(classesElements,(int)map.get(key)));
		}
		return avgList;
	}
	
	protected static double[][] getWeightsMatrix(List<double[]> avgList, int size)
	{
		double[]avg=new double[size];
		int avgListL=avgList.size();
		int avgListC=avgList.get(0).length;
		double[][]W=new double[size][size];
		double sum;
		for(int i=0;i<avgListL;i++)
		{
			sum=0.0;
			for(int j=0;j<avgListC;j++)
			{
				sum+=Math.pow(avgList.get(i)[j], 2);
				W[i][j]=avgList.get(i)[j];	
			}
			avg[i]=sum*(-0.5);
			W[i][avgListC]=avg[i];
		}
		for (int i = 0; i < W.length; i++) {
			System.out.println();
			for (int j = 0; j < W[0].length; j++) {
				System.out.print(W[i][j]+ " ");
			}
		}
		return W;
	}
	
	protected static int getPatternClass(double[][]W,double[]pattern)
	{
		double psimax=Double.MIN_VALUE;
		int iClasa=0;
		for(int i=0;i<W.length;i++)
		{
			double psik=W[i][W.length-1];
			for(int j=0;j<W[0].length;j++)
			{
				psik+=pattern[j]*W[i][j];
				if(psik>psimax)
				{
					psimax=psik;
					iClasa=i;
				}
			}
		}
		return iClasa+1;
	}
}

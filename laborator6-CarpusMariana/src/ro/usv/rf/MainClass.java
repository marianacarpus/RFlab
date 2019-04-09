package ro.usv.rf;

public class MainClass {
	
	
	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			double[] grades= {3,80, 5.75,6.25, 7.25, 8.5};
			int[] k_nn= {1,3,5,7,9,13,17};
			for(int k=0; k<k_nn.length;k++)
			{
				for(int g=0; g<grades.length;g++)
				{
					System.out.println("grade "+grades[g]+" k= "+k_nn[k]+" calificativ " + DistanceUtils.calculateKNN(learningSet,grades[g] , k_nn[k])); 
				}
			}
		
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}

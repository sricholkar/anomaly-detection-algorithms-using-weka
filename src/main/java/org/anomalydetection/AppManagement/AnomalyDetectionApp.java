package org.anomalydetection.AppManagement;

import org.anomalydetection.KNN.KNearestNeighbor;
import org.anomalydetection.decisiontree.DecisionTree;
import org.anomalydetection.kMeans.KMeans;
import org.anomalydetection.naivebayes.NBayes;
import org.anomalydetection.oneclassSVM.OneClassSVM;

public class AnomalyDetectionApp implements AnomalyDetectionAppMBean {

	private String algorithm;


	public void setAlgorithm(String algorithm) {
		// TODO Auto-generated method stub
		this.algorithm = algorithm;
		
	}

	public String getAlgorithm() {
		// TODO Auto-generated method stub
		return algorithm;
	}


	@Override
	public void anomalyDetection(String algorithm) throws Exception {
		// TODO Auto-generated method stub
		switch(algorithm) {
		
		case "DecisionTree":
			DecisionTree.main(null);
			break;
		case "NaiveBayes":
			NBayes.main(null);
			break;
		case "KNearestNeighbor":
			KNearestNeighbor.main(null);
			break;
		case "KMeans":
			KMeans.main(null);
			break;
		case "OneClassSVM":
			OneClassSVM.main(null);
			break;
		default:
			System.out.println("Choose anyone of the algorithm from the below: \n");
			System.out.println("1. DecisionTree \n");
			System.out.println("2. NaiveBayes \n");
			System.out.println("3. KNearestNeighbor \n");
			System.out.println("4. KMeans \n");
			System.out.println("5. OneClassSVM");
			break;
	}
	}
}

package org.anomalydetection.KNN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.anomalydetection.csv2arff.ArffConversion;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class KNearestNeighbor {

	private static File classpath = new File(ArffConversion.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final String trainData = classpath.getParent() + "/resources/alpine_train.arff";
	public static final String testData = classpath.getParent() + "/resources/alpine_test.arff";
	
	public static void main(String[] args) throws Exception {
		
		Instances trainingData = getData(trainData);
//        Instances testingData = getData(testData);
//        int classIndex = testingData.numAttributes()-1;
//        System.out.println(classIndex);
        
        //K-Nearest Neighbor Algorithm
        Classifier ibk = new IBk(1);
        
        ibk.buildClassifier(trainingData);
//        SerializationHelper.write("knn.model", ibk);;
        /**
         * train the algorithm with trainingData and evaluate the model with testingData
         */
//        Evaluation eval = new Evaluation(trainingData);
//        eval.evaluateModel(ibk, testingData);
//        /** Print the algorithm summary */
//		System.out.println("** K-Nearest Neighbors Evaluation with Datasets **");
//		System.out.println(eval.toSummaryString());
//		System.out.print(" the expression for the input data as per alogorithm is ");
//		System.out.println(ibk);
//		System.out.println(eval.toMatrixString());
//		System.out.println(eval.toClassDetailsString());

	}
	
	private static Instances getData(String trainData) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(trainData));
    	Instances dataset = new Instances(reader);
    	dataset.setClassIndex(dataset.numAttributes()-1);
    	dataset.deleteAttributeAt(0);
    	reader.close();
		return dataset;
    	
    }
}

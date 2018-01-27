package org.anomalydetection.kMeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.anomalydetection.csv2arff.ArffConversion;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class KMeans {

	private static File classpath = new File(ArffConversion.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final String trainData = classpath.getParent() + "/resources/alpine_train.arff";
//	public static final String testData = classpath.getParent() + "/resources/alpine_test.arff";
	
	public static void main(String[] args) throws Exception {
		
		Instances trainingData = getData(trainData);
//        Instances testingData = getData(testData);
        
        SimpleKMeans kMeans = new SimpleKMeans();
        kMeans.setNumClusters(3);
        kMeans.buildClusterer(trainingData);
        
//        Instances centroids = kMeans.getClusterCentroids();
//        for (int i = 0; i < centroids.numInstances(); i++) { 
//            System.out.println( "Centroid " + i+1 + ": " + centroids.instance(i)); 
//          }
//        
//     // get cluster membership for each instance 
//        for (int i = 0; i < testingData.numInstances(); i++) { 
//          System.out.println( testingData.instance(i) + " is in cluster " + kMeans.clusterInstance(testingData.instance(i)) + 1); 
//
//        } 
	}
	
	private static Instances getData(String trainData) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(trainData));
    	Instances dataset = new Instances(reader);
    	dataset.deleteAttributeAt(0);
    	dataset.deleteAttributeAt(3);
    	reader.close();
		return dataset;
    	
    }

}

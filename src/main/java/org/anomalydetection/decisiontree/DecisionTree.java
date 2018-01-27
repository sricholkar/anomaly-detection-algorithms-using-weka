package org.anomalydetection.decisiontree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.anomalydetection.csv2arff.ArffConversion;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class DecisionTree {

	private static File classpath = new File(ArffConversion.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final String trainData = classpath.getParent() + "/resources/alpine_train.arff";
	public static final String testData = classpath.getParent() + "/resources/alpine_test.arff";
	
	public static void main(String[] args) throws Exception {
		
		Instances trainingData = getData(trainData);
		Instances train = normalize(trainingData);
		
        Instances testingData = getData(testData);
        Instances test = normalize(testingData);
        
        //J48 Algorithm
        Classifier classifier = new J48();
//        File file = new File(DecisionTree.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//        String path = file.getParentFile().getAbsolutePath() + "/resources/j48.model";
        classifier.buildClassifier(train);
//        SerializationHelper.write("target/resources/j48_alpine.model", classifier);
        /**
         * train the algorithm with trainingData and evaluate the model with testingData
         */
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);
        /** Print the algorithm summary */
		System.out.println("** Decision Tress Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);
		System.out.println(eval.toMatrixString());
		System.out.println(eval.toClassDetailsString());
	}
	
	private static Instances getData(String trainData) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(trainData));
    	Instances dataset = new Instances(reader);
    	dataset.setClassIndex(dataset.numAttributes()-1);
    	dataset.deleteAttributeAt(0);
    	reader.close();
		return dataset;
    }
	
	private static Instances normalize(Instances data) throws Exception {
		Normalize norm = new Normalize();
		norm.setInputFormat(data);
		return Filter.useFilter(data, norm);
	}

}

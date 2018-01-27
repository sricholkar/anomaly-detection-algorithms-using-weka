package org.anomalydetection.traintestsplit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TrainTestSplit {

	public static String input = "target/myTempData.arff";
	public static void main(String[] args) throws IOException {
		
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File(input));
		Instances dataset = loader.getDataSet();
		
		int trainSize = (int) (dataset.numInstances()*0.8);
		int testSize = dataset.numInstances()-trainSize;
		
		Instances trainData= new Instances(dataset, 0, trainSize);
		Instances testData = new Instances(dataset, trainSize, testSize);
		
		BufferedWriter trainWriter = new BufferedWriter(new FileWriter("target/trainData.arff"));
		trainWriter.write(trainData.toString());
		trainWriter.flush();
		trainWriter.close();
		
		BufferedWriter testWriter = new BufferedWriter(new FileWriter("target/testData.arff"));
		testWriter.write(testData.toString());
		testWriter.flush();
		testWriter.close();
	}
}

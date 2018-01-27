package org.anomalydetection.classify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class Classification {
	private static File classpath = new File(Classification.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final String testData = "target/testData.arff";
	
	public static void main(String[] args) throws Exception {
		  NaiveBayes trainedModel = (NaiveBayes) SerializationHelper.read(classpath.getParent() + "/resources/nb.model");
		  Instances test = getData(testData);
		  Normalize norm = new Normalize();
			norm.setInputFormat(test);
			Instances testD = Filter.useFilter(test, norm);
		  System.out.println(testD);
		  System.out.println(trainedModel.classifyInstance(testD.get(4)));

	}
	private static Instances getData(String Data) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(Data));
    	Instances dataset = new Instances(reader);
    	dataset.setClassIndex(dataset.numAttributes()-1);
    	dataset.deleteAttributeAt(0);
    	reader.close();
		return dataset;
    	
    }

}

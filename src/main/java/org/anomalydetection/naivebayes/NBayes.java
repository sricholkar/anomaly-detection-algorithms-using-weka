package org.anomalydetection.naivebayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.anomalydetection.csv2arff.ArffConversion;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class NBayes 
{
	private static File classpath = new File(ArffConversion.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final String trainData = classpath.getParent() + "/resources/full_train.arff";
//	public static final String testData = classpath.getParent() + "/resources/alpine_test.arff";
	
    public static void main( String[] args ) throws Exception
    {
        Instances trainingData = getData(trainData);
//        Instances testingData = getData(testData);
        
        Instances train = normalize(trainingData);
//        Instances test = normalize(testingData);
        
        NaiveBayes nb = new NaiveBayes();
        
        nb.buildClassifier(train);
        SerializationHelper.write("nb_full.model", nb);
//        
//        Evaluation eval = new Evaluation(train);
//        eval.evaluateModel(nb, test);
//        System.out.println(eval.toSummaryString("\nResults\n=============\n",true));
//		System.out.println(eval.toClassDetailsString());
//        System.out.println(Arrays.deepToString(eval.confusionMatrix()) + " " + eval.pctCorrect() + " " + eval.toMatrixString());
//		System.out.println(eval.toMatrixString());

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

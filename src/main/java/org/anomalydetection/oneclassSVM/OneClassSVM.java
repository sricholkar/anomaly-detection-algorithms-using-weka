package org.anomalydetection.oneclassSVM;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.anomalydetection.normalize.Normalize;

import au.com.bytecode.opencsv.CSVReader;
import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;


public class OneClassSVM {

	private static final String CLASSPATH = OneClassSVM.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	
	private static String getFilePath(String resource) {
		File file = new File(CLASSPATH);
		String filepath = file.getParentFile().getAbsolutePath() + resource;
		return filepath;
	}
	public static void main(String[] args) throws IOException {
		
		
		String trainDataFilePath = getFilePath("/resources/alpine_train.csv");
//		String testDataFilePath = getFilePath("/resources/myTempData_test.csv");
		
		double[][] trainFeaturesData = getFeaturesData(trainDataFilePath);
		double[] trainLabelData = getLabelData(trainDataFilePath);
		
//		double[][] testFeaturesData = getFeaturesData(testDataFilePath);
//		double[] testLabelData = getLabelData(testDataFilePath);
	
		double[][] normalizedTrainFeatures = data2normalize(trainFeaturesData);
//		double[][] normalizedTestFeatures = data2normalize(testFeaturesData);
		
		svm_model model = svmTrain(normalizedTrainFeatures, trainLabelData);

//		 double[] ypred = svmPredict(normalizedTestFeatures, model); 
//		 int numOfSuccesses = 0;
//		 int anomaly = 0;
//		 for (int i = 0; i < ypred.length; i++ ) {
////			 
//////			 if(testLabelData[i] == ypred[i]) {
//////				numOfSuccesses++;
//////				System.out.println(numOfSuccesses);
//////				System.out.println(ypred.length);
//////				System.out.println((((double)numOfSuccesses)/((double)ypred.length)) * 100);
//////			 }
////			 
////			 if(ypred[i] == -1) {
////				 anomaly++;
////				 System.out.println(anomaly);
////			 }
//			 if (ypred[i] == -1 && testLabelData[i] == -1) {
//				 numOfSuccesses++;
//				 System.out.println(numOfSuccesses);
//			 }
//		 }
		 
//		 for (int i = 0; i < normalizedTestFeatures.length; i++){
//			    System.out.println("(Actual:" + testLabelData[i] + " Prediction:" + ypred[i] + ")"); 
//		}  
	}

	private static double[][] data2normalize(double[][] FeaturesData) {
		
		Normalize norm = new Normalize();
		double[][] normalizedFeaturesData = norm.normalize(FeaturesData);
		return normalizedFeaturesData;
	}
	
	private static List<String[]> data2List(String DataFilePath) throws IOException {
		CSVReader csvreader = new CSVReader(new FileReader(DataFilePath), ',', '\'', 1);
	    List<String[]> content = csvreader.readAll();
		return content;
	}
	
	private static double[][] getFeaturesData(String DataFilePath) throws IOException {
	    List<String[]> content = data2List(DataFilePath);
		int numLine = 0;
		double[][] featuresData = new double[content.size()][];
		for (String[] data : content) {
			String[] featuresInStringArr = {data[1], data[2], data[3]};
			double[] featuresIndoubleArr = Arrays.stream(featuresInStringArr).mapToDouble(Double::parseDouble).toArray();
			featuresData[content.indexOf(data)] = featuresIndoubleArr;
			numLine++;
		}
		return featuresData;
	}
	
	private static double[] getLabelData(String DataFilePath) throws IOException {
	    List<String[]> content = data2List(DataFilePath);
		double[] Label = new double[content.size()];
		for (String[] data : content) {
			Label[content.indexOf(data)] = Double.valueOf(data[4]);
		}
		return Label;
	}
	static svm_model svmTrain(double[][] xtrain, double[] ytrain) {
        svm_problem prob = new svm_problem();
        int recordCount = xtrain.length;
        int featureCount = xtrain[0].length;
        prob.y = new double[recordCount];
        prob.l = recordCount;
        prob.x = new svm_node[recordCount][featureCount];     

        for (int i = 0; i < recordCount; i++){            
            double[] features = xtrain[i];
            prob.x[i] = new svm_node[features.length];
            for (int j = 0; j < features.length; j++){
                svm_node node = new svm_node();
                node.index = j;
                node.value = features[j];
                prob.x[i][j] = node;
            }           
            prob.y[i] = ytrain[i];
        }               

        svm_parameter param = new svm_parameter();
//        param.probability = 1;
//        param.gamma = 0.01;
        param.nu = 0.01;
//        param.C = 100;
        param.svm_type = svm_parameter.ONE_CLASS;
        param.kernel_type = svm_parameter.LINEAR;       
        param.cache_size = 20000;
        param.eps = 0.001;      

        svm_model model = svm.svm_train(prob, param);

        return model;
    }  

  static double[] svmPredict(double[][] xtest, svm_model model) 
  {

      double[] yPred = new double[xtest.length];

      for(int k = 0; k < xtest.length; k++){

        double[] fVector = xtest[k];

        svm_node[] nodes = new svm_node[fVector.length];
        for (int i = 0; i < fVector.length; i++)
        {
            svm_node node = new svm_node();
            node.index = i;
            node.value = fVector[i];
            nodes[i] = node;
        }

        int totalClasses = 2;       
        int[] labels = new int[totalClasses];
        svm.svm_get_labels(model,labels);

        double[] prob_estimates = new double[totalClasses];
        yPred[k] = svm.svm_predict_probability(model, nodes, prob_estimates);

      }

      return yPred;
  }
}
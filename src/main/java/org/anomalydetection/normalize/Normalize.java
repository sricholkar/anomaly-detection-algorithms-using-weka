package org.anomalydetection.normalize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Normalize {

	private static final int MAX = 1;
	private static final int MIN = -1;
	public double[][] normalize(double[][] features) {
		
		double[][] normalizedFeatureArrays = new double[features.length][features[0].length];

		List<Double> feature1 = new ArrayList<>();
		List<Double> feature2 = new ArrayList<>();
		List<Double> feature3 = new ArrayList<>();
		
		for (double[] feature: features) {
			feature1.add(feature[0]);
			feature2.add(feature[1]);
			feature3.add(feature[2]);
		}
		
		double feature1MaxArray = Collections.max(feature1);
		double feature1MinArray = Collections.min(feature1);
		double feature2MaxArray = Collections.max(feature2);
		double feature2MinArray = Collections.min(feature2);
		double feature3MaxArray = Collections.max(feature3);
		double feature3MinArray = Collections.min(feature3);
		
		double[] normalizedFeature1Array = normalizeArray(feature1, feature1MinArray, feature1MaxArray);
		double[] normalizedFeature2Array = normalizeArray(feature2, feature2MinArray, feature2MaxArray);
		double[] normalizedFeature3Array = normalizeArray(feature3, feature3MinArray, feature3MaxArray);

		
		for (int i = 0; i < features.length; i++) {
			normalizedFeatureArrays[i][0] = normalizedFeature1Array[i];
			normalizedFeatureArrays[i][1] = normalizedFeature2Array[i];
			normalizedFeatureArrays[i][2] = normalizedFeature3Array[i];
		}
		
		return normalizedFeatureArrays;
	}
	
	private double[] normalizeArray(List<Double> featureList, double featureMin, double featureMax) {
		double[]  normalizedFeatureArray = new double[featureList.size()];
		for (double temp: featureList) {
			double normalizedFeatureValue = normalizeValue(temp, featureMin, featureMax);
			normalizedFeatureArray[featureList.indexOf(temp)] = normalizedFeatureValue;
		}
		return normalizedFeatureArray;
	}

	
	private static double normalizeValue(double xvalue, double MaxArray, double MinArray) {
		double normalizedXValue = ((xvalue - MinArray)*(MAX - MIN)/(MaxArray - MinArray)) + MIN ;
		return normalizedXValue;
	}
}

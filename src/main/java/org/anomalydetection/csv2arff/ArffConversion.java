package org.anomalydetection.csv2arff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class ArffConversion {

	private static File classpath = new File(ArffConversion.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	private static String input = classpath.getParent() + "/resources/alpine_test.csv";
	private static String output = classpath.getParent() + "/resources/alpine_test.arff";
//	private static String input = File.listRoots()[2]+ "workspace\\restserver\\target\\resources\\full_train.csv";
//	private static String output = File.listRoots()[2]+ "workspace\\restserver\\target\\resources\\full_train.arff";

	public static void main(String[] args) throws Exception {
		System.out.println(input);
		File inputFile = new File(input);
		System.out.println(inputFile.exists());
			
			//load CSV
			CSVLoader loader = new CSVLoader();
			loader.setSource(inputFile);
			Instances dataset = loader.getDataSet();
		
			//save Arff
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			writer.write(dataset.toString());
			writer.flush();
			writer.close();

	}
}

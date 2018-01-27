package org.anomalydetection.AppManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import weka.core.Memory;


public class AnomalyDetectionAppManagement {

	/**
	 * Algorithms
	 * - DecisionTree
	 * - NaiveBayes
	 * - KNearestNeighbor
	 * - KMeans
	 * - OneClassSVM
	 */
	
	private static String algorithm = "OneClassSVM";
	private static final double Mb = 1024 * 1024;
	private static final double Gb = 1024 * 1024 * 1024;
	
	public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
//		double beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		Memory memory = new Memory();
        System.out.println(memory.getInitial());
        double startTime = System.currentTimeMillis();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		AnomalyDetectionApp adMBean = new AnomalyDetectionApp();
		adMBean.setAlgorithm(algorithm);
		ObjectName name = new ObjectName("org.anomalydetection.AppManagement:type=AnomalyDetectionApp");
		mBeanServer.registerMBean(adMBean, name);
		try {
			adMBean.anomalyDetection(algorithm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		double afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//		double actualMemUsed=afterUsedMem-beforeUsedMem;
//		System.out.println("Max Runtime Memory: " + Runtime.getRuntime().maxMemory() / (Gb) + "Gb");
//		System.out.println(Runtime.getRuntime().totalMemory() / Mb + "Mb");
//		System.out.println("Memory Consumption (Runtime Memory): "+ actualMemUsed/(Mb) + "Mb");
        double stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
        System.out.println("Runtime of the Program in Milliseconds: " + elapsedTime);
//        Memory _heapUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		System.out.println("Heap Memory Usage: " + memory.getCurrent());
		System.out.println(memory.getMax());
		System.out.println(memory.isOutOfMemory());



		Thread.sleep(5000);
	}
}

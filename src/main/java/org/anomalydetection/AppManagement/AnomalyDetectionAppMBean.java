package org.anomalydetection.AppManagement;

public interface AnomalyDetectionAppMBean {

	public void setAlgorithm(String algorithm);
	public String getAlgorithm();
	
	public void anomalyDetection(String algorithm) throws Exception;
}

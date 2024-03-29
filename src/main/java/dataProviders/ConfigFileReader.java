package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;
import enums.EnvironmentType;
import enums.PlatformType;

public class ConfigFileReader {	
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";

	public ConfigFileReader(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try { properties.load(reader); }
			catch (IOException e) { e.printStackTrace(); }
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		}finally {
			try { if(reader != null) reader.close(); }
			catch (IOException ignore) {}
		}
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}
	
	public String getApplicationUrl() {
		String env = properties.getProperty("environment");
		String url = null;
		
		if(env.equalsIgnoreCase("stage")) {
			url = properties.getProperty("stageURL");
		}
		else if(env.equalsIgnoreCase("devtest"))  {
			url = properties.getProperty("devTestURL");
		}
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
		return url;
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("edge")) return DriverType.EDGE;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
	
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("stage")) return EnvironmentType.STAGE;
		else if(environmentName.equals("devtest")) return EnvironmentType.DEVTEST;
		else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}
	
	public PlatformType getExecutionPlatform() {
		String platformName = properties.getProperty("platform");
		if(platformName == null || platformName.equalsIgnoreCase("local")) return PlatformType.LOCAL;
		else if(platformName.equals("remote")) return PlatformType.REMOTE;
		else throw new RuntimeException("Platform Type Key value in Configuration.properties is not matched : " + platformName);
	}
	
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}
	
	public String getTestDataResourcePath(){
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) return testDataResourcePath;
		else throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");		
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public String getScreenshotsPath(){
		String reportConfigPath = properties.getProperty("screenshotsPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("ScreenShots Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}

}

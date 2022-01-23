package com.ms.genericfunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

	Properties properties;
	
	public Property(String configFilePath)
	{
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
		    properties.load(fis);
		    fis.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String ReadProperty(String propkey)
	{
	 try
	 {
		String propval = properties.getProperty(propkey);
		return propval;
	 }
	catch(Exception e)
	 {
		e.printStackTrace();
	 }
	 return "Key not found";
	}
	
	public String getReportConfigPath()
	{
		String reportConfigPath = properties.getProperty("reportConfigPath");
	  if(reportConfigPath!=null)
	 {
		return reportConfigPath;
	 }
	  
	  else throw new RuntimeException("Report config Path not Specified");
	}
}

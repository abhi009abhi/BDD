package com.ms.mailerUtility;

import java.io.File;

import org.jboss.netty.util.internal.SystemPropertyUtil;

public class GetReportPath {

	public static String pathOfReport()
	{
		String initialFileName=null;
		try
		{
			System.out.println("Inside try");
			File folder = new File("Report");
			File[] listOfFiles = folder.listFiles();
			for(File file:listOfFiles)
			{
				System.out.println("Inside for");
				if(file.getName().startsWith("cucumber-html-report"))
				{
					initialFileName = file.getName();
				}
			}	
			System.out.println("Report foler is "+initialFileName);
		}
		catch(Exception e)
		{
			System.out.println("unable to copy report");
			
		}
		System.out.println("Report foler is "+initialFileName);
	return initialFileName;	
	}
	
}

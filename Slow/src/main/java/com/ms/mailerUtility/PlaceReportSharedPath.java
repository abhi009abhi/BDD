package com.ms.mailerUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class PlaceReportSharedPath extends ReadJSONFile{
	
	public static String DestReport = config.ReadProperty("Destination_Report");
    public static String srcReport  = config.ReadProperty("Source_Report");
    
    
    public static void copyReport() throws InterruptedException
    {
    	System.out.println("Taking rScr Report from "+srcReport);
    	Thread.sleep(10000);
    	String srcExtensionReport =""+GetReportPath.pathOfReport();
    	String sourceReport = srcReport+srcExtensionReport;
    	
    	System.out.println("Source rpoert is "+sourceReport);
    	
    	File sourceFile = new File(sourceReport);
    	File destFile =  new File(DestReport);
    	
    	long start = System.nanoTime();
     	
    	
    	try
    	{
    		copyFileUsingFileUtils(sourceFile,destFile);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	System.out.println("Time taken by appache commons iO =" +(System.nanoTime()-start));
    }
    
    private static void copyFileUsingFileUtils(File src,File dest) throws IOException
    {
    	FileUtils.copyDirectoryToDirectory(src, dest);
    }
}

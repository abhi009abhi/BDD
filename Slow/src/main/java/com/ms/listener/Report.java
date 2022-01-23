package com.ms.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Report {

	public static void moveFolder(String folderName)
	{
		
		File newFolderInArchive = new File("Report/Archives/"+folderName);
	    try
		{
	    	System.out.println(folderName +"was found");
	    	System.out.println(newFolderInArchive);
	    	//newFolderInArchive.mkdir();
	    	Files.move(new File("Report/"+folderName).toPath(),newFolderInArchive.toPath(),StandardCopyOption.REPLACE_EXISTING);
	    	
		}
	    catch(IOException e)
	    {
	    	System.out.println("File not moved"+e.getMessage());
	    	//e.printStackTrace();
	    	
	    }
	
	}
	
	public static void moveOldReportToArchive()
	{
		File folder = new File("Report");
		File[] listOfFiles = folder.listFiles();
	
		for(File file:listOfFiles)
		{
			if(file.isDirectory()&&file.getName().startsWith("cucumber-html-report"))
			{
				System.out.println("File Name is "+file.getName());
				moveFolder(file.getName());
			}
		}	
	}
}

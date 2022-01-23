package com.ms.runner;

import java.io.File;
import java.io.IOException;

public class TriggerBat {
	private static String PATH_TO_GRADLE_PROJECT = "/";
    private static String GRADLEW_EXECUTABLE = "gradlew.bat";
    private static String BALNK = " ";
    private static String GRADLE_TASK = "clean build";

	public static void main (String[] args) throws IOException
{
//		ProcessBuilder pb = new ProcessBuilder("cmd","/C","Start","runTest.bat");
//		File dir = new File(System.getProperty("user.dir"));
//		pb.directory(dir);
//		Process p = pb.start();
//	}/	
	

  
    
        String command = System.getProperty("user.dir")+"/"+ GRADLEW_EXECUTABLE + BALNK + GRADLE_TASK;
        try
        {
            Runtime.getRuntime().exec(command);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
   
}}

package com.ms.mailerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.ms.genericfunction.Property;
import com.ms.stepDefinition.Hooks;

public class MyJSONReader {

	public static Property config = new Property(System.getProperty("user.dir")+"/src/main/resources/"+"config.properties");
	public static String parentDir =System.getProperty("user.dir")+"Report";		
	public static String sharedFolder = "path of shared folder";
	public static String sharedPath ="";
	public static String appName =config.ReadProperty("appName");
	public static String env1 =config.ReadProperty("env");
	
	@Test
	public void test()
	{
		creatMailBody("JSON2Read/cucumber.json");
	}
	
	public static void creatMailBody(String reportPath)
	{
		String AppName = appName;
		String ENV = env1;
		String htmlFirstPart ="";
		String htmlStepsPart ="";
		String htmlStepsPartTemp ="";
		String htmllastPart="";
		int stepNum=0;
		String finalizedHTML=htmlFirstPart;
		String scenarioResult="PASS";
		String stepInfo="";
		String scenarioInfo="";
		String sceanrioErrorMsg="";
		double sceanrioDuration=0.0;
		
		String statusColor="";
		String stepResult="";
		String stepErrorMsg="";
		double testCaseDurationinSecond=0.0;
		double testcaseDurationinMilliSecond=0.0;
		
		String scenarioName="";
		String status="";
		double duration=0.0;
		double totalDuration=0;
		
		try
		{
			JSONParser parser =new JSONParser();
			JSONArray jsonArrayFile= (JSONArray)parser.parse(new FileReader("JSON2Read/cucumber.json"));
			Iterator<JSONObject> jsonArrayFileIterator =(Iterator<JSONObject>)jsonArrayFile.iterator();	
			
			while(jsonArrayFileIterator.hasNext())
			{
								
				JSONArray elements = (JSONArray)jsonArrayFileIterator.next().get("elements");				
				Iterator<JSONObject> elementsIterator = (Iterator<JSONObject>)elements.iterator();
				System.out.println("JSON Array element is "+elements);
				while(elementsIterator.hasNext()){
					
					htmlStepsPartTemp= "";
					htmlStepsPartTemp="";
					totalDuration=0;
					JSONObject  jsonObjectElements =elementsIterator.next(); 
					scenarioName =(String)jsonObjectElements.get("name");
					String id = (String)jsonObjectElements.get("id");
					JSONArray jsonSteps =(JSONArray)jsonObjectElements.get("steps");
					Iterator<JSONObject> jsonStepsIterator = (Iterator<JSONObject>)jsonSteps.iterator();
					System.out.println("JSON array Json Steps "+ jsonSteps);
					
					sceanrioErrorMsg="";
					scenarioInfo="";
					
				
					while(jsonStepsIterator.hasNext())
					{
												
						
						JSONObject jsonResult=(JSONObject)jsonStepsIterator.next().get("result");
						
						if(jsonResult.get("Status")!=null)
						
							status=(String)jsonResult.get("status");
							
						
						if(jsonResult.get("duration")!=null)
						
							duration=(long)jsonResult.get("duration");
						
						
						if(status.equalsIgnoreCase("passed")){
						
						     statusColor="green";
						     stepResult="PASS";
						     totalDuration=totalDuration+duration;
						     stepInfo ="";
						     if(scenarioInfo=="") {
						    
						    	 scenarioInfo=stepInfo;
						     }	 
						}else
						{
							statusColor="red";
							stepResult="FAIL";
							if(jsonResult.get("error_message")!=null)
							
								sceanrioErrorMsg = jsonResult.get("error_msg").toString().split("\n")[0];
							    totalDuration =totalDuration+duration;
							    stepInfo="";
							    stepInfo="";
							    scenarioResult="FAIL";
							    scenarioInfo=stepInfo;
							    break;
						}
					}
					testcaseDurationinMilliSecond=totalDuration/100000;
					testCaseDurationinSecond=testcaseDurationinMilliSecond/1000;
					sceanrioDuration=testCaseDurationinSecond/60;
					String p1=htmlStepsPartTemp.replace("{STEP_NUM}","TC"+(++stepNum));
					String p2=p1.replace("{STEP_NAME}",scenarioName);
					String p3=p2.replace("{STEP_RESULT}",scenarioInfo);
					String p4=p3.replace("{STEP_COMMENTS}",sceanrioErrorMsg);
					String p5=p4.replace("{STEP_DURATION}",""+Math.floor(sceanrioDuration*100)/100);
					
					htmlStepsPartTemp=p5;
					htmlStepsPart=htmlStepsPart+htmlStepsPartTemp;
					
					
				}//while elements iterator
			}//while main Elements
			
			 Date date = new Date();
			    DateFormat dateFormat2= new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
				String tDate= dateFormat2.format(date);	
				
				htmlFirstPart =htmlFirstPart.replace("{ENV}",ENV);
				htmlFirstPart =htmlFirstPart.replace("{APP_NAME}",appName);
				htmlFirstPart =htmlFirstPart.replace("{EXEC_DATE}",tDate);
				htmllastPart =htmllastPart.replace("{APP_NAME}",appName);
				htmllastPart =htmllastPart.replace("{ENV}",ENV);
				
				String Cucumber_report_Folder="";
				File directory= new File(parentDir);
				File[] subdirs= directory.listFiles((FilenameFilter)DirectoryFileFilter.DIRECTORY);
				for(File dir:subdirs)
				{
					if(dir.getName().startsWith("cucumber-html-reports")||dir.getName().startsWith("ReRunReports"))
					Cucumber_report_Folder = dir.getName();	
				}	
				
				sharedPath ="file:"+sharedFolder+"/"+Cucumber_report_Folder+"/cucumber-html-reports/report-tag_RFB.html";
				htmllastPart = htmllastPart.replace("{RPRT_PATH}",sharedPath);
				
				Hooks.runEnded= new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				htmllastPart = htmllastPart.replace("{EXEC_START}", Hooks.runStartedDate);
			    htmllastPart = htmllastPart.replace("{EXEC_END}", Hooks.runEndedDate);
			    
			    Calendar c = Calendar.getInstance();
			    TimeZone tz = c.getTimeZone();
			    htmllastPart = htmllastPart.replace("{TZ}",tz.getDisplayName());
			    
			    finalizedHTML = htmlFirstPart+htmlStepsPart+htmllastPart;
			
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}
	System.out.println(finalizedHTML);	
	}//create mail body
	public JSONArray jsonArray=null;
	
	//@Test
	public JSONArray readElementLevel(JSONArray arrJson,String keyElements,Set<String> keys) throws FileNotFoundException, IOException, ParseException
	{
	   
		JSONArray arr2Return=null;
		JSONObject jObj=null;
		
		
		jObj=(JSONObject) arrJson.get(0);
		System.out.println("looking for key "+keyElements);
		System.out.println("looking inside key Set "+keys);
		
		for(String s:keys)
		{
			if(s.equalsIgnoreCase(keyElements))
			{
				arr2Return=(JSONArray)jObj.get(s);
				break;
			}	
		}
		return arr2Return;
	}
	
	
	
	//@Test
	public void getJsonArrayElemets() throws FileNotFoundException, IOException, ParseException
	{
	   
		JSONParser parser =new JSONParser();
		JSONArray jsonArrayFile= (JSONArray)parser.parse(new FileReader("JSON2Read/cucumber.json"));
		JSONObject  jsonFileObject= getJsonObject(jsonArrayFile);	
		Set<String> jsonFileKeys =  getJsonKeys(jsonFileObject);
		//insideJsonElement(jsonFileObject,jsonFileKeys);
		
		JSONArray jsonArrayKeyElements = (JSONArray)jsonFileObject.get("elements");
		JSONObject jsonObjectKeyElements= getJsonObject(jsonArrayKeyElements);
		Set<String> jsonKeyElementsKeys =  getJsonKeys(jsonObjectKeyElements);
		//insideJsonElement(jsonObjectKeyElements,jsonKeyElementsKeys);
		
		
		JSONArray jsonArrayElementsSteps = (JSONArray)jsonObjectKeyElements.get("steps");
		JSONObject jsonObjectElementsSteps= getJsonObject(jsonArrayElementsSteps);
		Set<String> jsonKeyElementsSteps =  getJsonKeys(jsonObjectElementsSteps);
		insideJsonElement(jsonObjectElementsSteps,jsonKeyElementsSteps);
	}
	
	public Set<String> getJsonKeys(JSONObject jo)
	{
		    
		Set<String> keysJson = (Set<String>)jo.keySet();	
		System.out.println(keysJson);
		
		return keysJson;
	}
	
	public JSONObject getJsonObject(JSONArray arr)
	{
		
		int jsonArraySize = arr.size();
		JSONObject jo = (JSONObject) arr.get(0);
		return jo;
	}
	
	
	public  void insideJsonElement(JSONObject jo,Set<String> keys)
    {
	   for(String s:keys)
	   {
		   System.out.println("keys is "+s);
		   System.out.println("element type for key "+jo.get(s).getClass());
		   System.out.println("value of Element "+jo.get(s));
	   } 
	}
}

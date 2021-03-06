package com.sample.framework;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public final class Driver {

	private Driver() {
		// TODO Auto-generated constructor stub
	}
	
	private static ConcurrentHashMap<String,WebDriver> driverThreadMap= new ConcurrentHashMap<String,WebDriver>();
	
	private static final Map <String,Class<?>> driverMap=new HashMap<String,Class<?>>(){
		{
		 put("chrome",ChromeDriver.class);
		 put("firefox",FirefoxDriver.class);
		 put("ie",InternetExplorerDriver.class);
		 put("safari",SafariDriver.class);
		 put("opera",OperaDriver.class);
		}
		
	};
	
	public static String getThreadName() {
		return Thread.currentThread().getName()+"-"+Thread.currentThread().getId();
	}
	public static void add(String browser, Capabilities capabilities) throws Exception {
		
		Class<?> driverClass=driverMap.get(browser);
		WebDriver driver=(WebDriver) driverClass.getConstructor(Capabilities.class).newInstance(capabilities);
		String Threadname=getThreadName();
		driverThreadMap.put(Threadname, driver)	;
		
	}
    
	public static WebDriver current() {
		String ThreadName=getThreadName();
		return  driverThreadMap.get(ThreadName);
	}
}

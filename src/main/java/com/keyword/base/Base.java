package com.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	
	public WebDriver setup(String browserName)
	{
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes"))
			{
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--headless");
				driver = new ChromeDriver(op);
			}else
			{
				driver = new ChromeDriver();
			}
			
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	
	public Properties init_properties() throws IOException
	{
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\AMIT123\\eclipse-workspace\\KeywordDrivernFrameWork\\src\\main\\java\\com\\keyword\\config\\config.properties");
			prop.load(file);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return prop;
	}
	
	
	
	
	
	
	
	
	
}

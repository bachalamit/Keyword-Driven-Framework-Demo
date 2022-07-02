package com.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.keyword.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
	
	public WebElement element; 
	public final String Scenario_Sheet_Path = "C:\\Users\\AMIT123\\eclipse-workspace\\KeywordDrivernFrameWork"
			+ "\\src\\main\\java\\com\\keyword\\scenarios\\OrangeHRMLoginTestcase.xlsx";
	
	
	public void startExecution(String sheetName) {
		
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(Scenario_Sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		int k=0;
		for(int i=0; i<sheet.getLastRowNum();i++)
		{
			try {
				
			
			String locatorType = sheet.getRow(i+1).getCell(k+1).toString().trim();
			String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+4).toString().trim();
			
			switch (action) {
			case "open browser":
				base = new Base();
				try {
					prop = base.init_properties();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(value.isEmpty() || value.equals("NA"))
				{
					driver = base.setup(prop.getProperty("browser"));
				}else {
					driver = base.setup(value);
				}	
				break;

			case "enter url":
				if(value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				}else {
					driver.get(value);
				}
				break;
			case "quit":
				driver.quit();
				break;
			
			
			default:
				break;
			}
			
			switch (locatorType) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("getText")) {
					String eleText = element.getText();
					System.out.println("Text from element is : " + eleText);
				}
				locatorType=null;
				break;
				
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("getText")) {
					String eleText = element.getText();
					System.out.println("Text from element is : " + eleText);
				}
				locatorType=null;
				break;
			
			case "className":
				element = driver.findElement(By.className(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("getText")) {
					String eleText = element.getText();
					System.out.println("Text from element is : " + eleText);
				}
				locatorType=null;
				break;
				


			default:
				break;
			}
		}
		catch (Exception e) {

		}
	}
	}
}

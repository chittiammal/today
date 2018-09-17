package com.flipkart;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FK {


		public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

			System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\Browser Drivers\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Actions act = new Actions(driver);
	/*		Properties prop = new Properties();
			prop.load(new FileInputStream("D:\\Karunakar\\Flipkart\\src\\FK\\Login.properties"));

			String strurl = prop.getProperty("URL");
			String strun = prop.getProperty("UN");
			String strpwd = prop.getProperty("PWD");
*/
			driver.get("https://www.flipkart.com/");
			Thread.sleep(3000);
			WebElement Objuser = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")));
			WebElement Objpswd = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input"));

			Objuser.clear();
			Objuser.sendKeys("username");
			Objpswd.sendKeys("password");
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
			Thread.sleep(2000);

			WebElement objElec = driver.findElement(By.linkText("Electronics"));
			act.moveToElement(objElec).perform();
			Thread.sleep(2000);
			// click on samsung
			WebElement objsamsung = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Samsung")));
			objsamsung.click();
			Thread.sleep(3000);
			// click o view all
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("VIEW ALL"))).click();
			Thread.sleep(3000);
			// click on Price -- Low to High
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='container']/div/div[1]/div/div[2]/div/div[2]/div[1]/div/div[2]/div[4]"))).click();
			Thread.sleep(2000);

			// Webelement for device names
			List<WebElement> objdevices = driver.findElements(By.xpath("//*[@id='container']/div/div[1]/div/div[2]/div/div[2]/div[2]/div/div/div/a/div[2]//following::div[@class='_3wU53n']"));

			// Webelement for device price
			List<WebElement> objprices = driver.findElements(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div[2]/div/div[2]/div[2]/div/div/div/a/div[2]//following::div[@class='_1vC4OE _2rQ-NK']"));

			// write data into excel sheet
			XSSFWorkbook xwb = new XSSFWorkbook();
			XSSFSheet sheet = xwb.createSheet("Sheet1");
			
			int row;
			int column=0;
			
			int column1=1;
			
			FileOutputStream fos = new FileOutputStream("D:\\MobileInfo.xlsx");
			
			Row r11 = sheet.createRow(0);
			
			r11.createCell(column).setCellValue("Name");
			
			r11.createCell(column1).setCellValue("price");
			
			for (row = 1; row < objdevices.size(); row++) {
				Row r1 = sheet.createRow(row);
				//Row r2 = sheet.createRow(row1);
				
				r1.createCell(column).setCellValue(objdevices.get(row).getText());
			
				r1.createCell(column1).setCellValue(objprices.get(row).getText());
				


				System.out.println(objdevices.get(row).getText());
			}

			
		
			xwb.write(fos);
			
			
			/*for (row1 = 0; row1 < objprices.size(); row1++) {
				
				

				System.out.println(objprices.get(row1).getText());
			}  
			xwb.write(fos);
			*/
			/*
			 * Iterator<WebElement> itr = objdevices.iterator(); while(itr.hasNext()) {
			 * String devicename=itr.next().getText(); System.out.println(devicename); }
			 * 
			 * Iterator<WebElement> itr1 = objprices.iterator(); while(itr1.hasNext()) {
			 * String devicprice=itr1.next().getText(); System.out.println(devicprice); }
			 */

		}


}

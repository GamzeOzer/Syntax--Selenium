package com.groupHomewok;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.CommonMethods;

public class TaskOneDatePicker extends CommonMethods{
/*
 * TC 1: Users Application Form Fill
 * 1.Open chrome browser
 * 2.Go to “http://uitestpractice.com/”
 * 3.Click on “Forms” link
 * 4.Fill out the entire form
 * 5.Close the browser
 * 
 * MUST USE Functions: 
 */
	public static void main(String[] args) throws InterruptedException {
		String str="http://uitestpractice.com/";
		setUpDriver("chrome",str);
		
		driver.findElement(By.xpath("//a[text()='Form']")).click();
		sendText(driver.findElement(By.id("firstname")),"Kemal");
		sendText(driver.findElement(By.id("lastname")),"Berk");
		
		List<WebElement>list=driver.findElements(By.cssSelector("label.radio-inline"));
		for(WebElement radiobut:list) {
			String radio=radiobut.getText();
			if(radio.equalsIgnoreCase("Married")) {
				radiobut.click();
			}
		}
		
		List<WebElement> hobbies=driver.findElements(By.cssSelector("label.checkbox-inline"));
		for(WebElement hobby:hobbies) {
			String hobbyText=hobby.getText();
			if(hobbyText.equalsIgnoreCase("Reading")) {
				hobby.click();
			}
		}
		
		WebElement country=driver.findElement(By.id("sel1"));
		selectValueFromDD(country, 5);
		
		driver.findElement(By.cssSelector("input#datepicker")).click();
		WebElement month=driver.findElement(By.cssSelector("select.ui-datepicker-month"));
		selectValueFromDD(month, 0);
		
		WebElement year=driver.findElement(By.cssSelector("select.ui-datepicker-year"));
		selectValueFromDD(year, "1983");
		
		
		String expectedDate="8";
		List<WebElement> cells=driver.findElements(By.cssSelector("table.ui-datepicker-calendar tbody tr td"));
		for(WebElement cell:cells) {
			String celltext=cell.getText();
			if(celltext.equals(expectedDate)) {
				cell.click();
			}
		}
		
	    sendText(driver.findElement(By.id("phonenumber")), "12344556787");
		sendText(driver.findElement(By.cssSelector("input#username")),"KemalBrk");
		sendText(driver.findElement(By.cssSelector("input#email")),"kemal@gmail.com");
		sendText(driver.findElement(By.xpath("//textarea[@id='comment']")),"qwertyui duygiuhio ro785ro87yiujh");
		sendText(driver.findElement(By.cssSelector("input#pwd")),"0987654");
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
	
		Thread.sleep(2000);
	driver.close();
	}

}
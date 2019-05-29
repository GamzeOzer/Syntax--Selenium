package com.groupHomewok;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.CommonMethods;

public class TaskTwoDatePicker extends CommonMethods {

	public static void main(String[] args) throws InterruptedException {
		// TC 1: Table headers and rows verification
//		1.Open browser and go to “http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx”

		String url = "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx";
		setUpDriver("chrome", url);
		sendText(driver.findElement(By.cssSelector("[id$='_username']")), "Tester");
		sendText(driver.findElement(By.cssSelector("[id*='_pass']")), "test");
		driver.findElement(By.cssSelector("[id*='_login']")).click();
		List<WebElement> row = driver.findElements(By.cssSelector("[id*='_orderGrid'] tbody tr "));
		int count = row.size();

		// Create an Order
		driver.findElement(By.partialLinkText("Order")).click();
		selectValueFromDD(driver.findElement(By.xpath("//select[contains(@id,'_ddlProduct')]")), "FamilyAlbum");
		sendText(driver.findElement(By.xpath("//input[contains(@id,'Quantity')]")), "60");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
		sendText(driver.findElement(By.cssSelector("[id*='Name']")), "Ramiz Mahir");
		sendText(driver.findElement(By.cssSelector("[id$='TextBox2']")), "RailRoad Stret");
		sendText(driver.findElement(By.cssSelector("[id*='TextBox3']")), "Edinburg");
		sendText(driver.findElement(By.cssSelector("[id$='TextBox4']")), "UK");
		sendText(driver.findElement(By.cssSelector("[id*='TextBox5']")), "78345");
		driver.findElement(By.cssSelector("[id$='_cardList_1']")).click();
		sendText(driver.findElement(By.cssSelector("[id*='TextBox6']")), "3456789012");
		sendText(driver.findElement(By.cssSelector("[id$='TextBox1']")), "11/22");
		driver.findElement(By.partialLinkText("Process")).click();
		driver.findElement(By.partialLinkText("View all orders")).click();

		// 4.Verify order of that person is displayed in the table “List of All Orders”

		List<WebElement> namecolls = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td[2]"));
		Iterator<WebElement> it = namecolls.iterator();
		while (it.hasNext()) {
			String name = it.next().getText();
			if (name.equalsIgnoreCase("Ramiz Mahir")) {
				System.out.println("person is displayed in the table");
			}

		}
		// Verify a new order is created..
		row = driver.findElements(By.cssSelector("[id*='_orderGrid'] tbody tr "));
		if ((count + 1) == row.size()) {
			System.out.println("A new order is created");
		} else {
			System.out.println("There is no new order");
		}

		// Click on edit of that specific order

		for (int i = 1; i <= row.size(); i++) {
			WebElement ele = driver
					.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid'] /tbody/ tr[" + i + "]"));
			if (ele.getText().contains("Ramiz Mahir")) {
				driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid'] /tbody/ tr[" + i + "]/td[13]"))
						.click();
				break;
			}
		}

		// Update street address
		sendText(driver.findElement(By.cssSelector("[id*='TextBox2']")), "STREET");
		driver.findElement(By.partialLinkText("Update")).click();
		driver.findElement(By.partialLinkText("View all orders")).click();

		// Verify each order details
		for (int i = 1; i <= row.size(); i++) {
			WebElement ele = driver
					.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid'] /tbody/ tr[" + i + "]"));
			System.out.println(ele.getText());
		}

		// Verify in the table that street has been updated
		for (int i = 1; i <= row.size(); i++) {
			WebElement ele = driver
					.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid'] /tbody/ tr[" + i + "]"));

			if (ele.getText().contains("Ramiz Mahir")) {
				ele = driver.findElement(
						By.xpath("//table[@id='ctl00_MainContent_orderGrid'] /tbody/ tr[" + i + "]/td[6]"));

				if (ele.getText().contains("STREET")) {

					System.out.println("Street has been updated!");
					break;
				}
			}
		}

		driver.quit();

	}
}
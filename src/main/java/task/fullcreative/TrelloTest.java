package task.fullcreative;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TrelloTest {

	@Test
	public void taskTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.get("https://trello.com");
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("user")).sendKeys("gokuls2381@gmail.com",Keys.TAB,Keys.ENTER);


		try {
			driver.findElement(By.id("password")).sendKeys("RussianRoulette@2381",Keys.TAB,Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("password"))));
			element.sendKeys("RussianRoulette@2381",Keys.TAB,Keys.ENTER);
		}

		driver.findElement(By.xpath("//span[normalize-space()='Create new board']")).click();
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("TaskBoarad");
		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(),'TaskBoarad')]"))));

		driver.switchTo().activeElement().sendKeys("List A",Keys.ENTER);
		driver.switchTo().activeElement().sendKeys("List B", Keys.ENTER);
		driver.findElement(By.xpath("//h2[contains(text(),'List A')]/parent::div/following-sibling::div/a/span")).click();
		driver.switchTo().activeElement().sendKeys("Card A",Keys.ENTER);
		WebElement source= driver.findElement(By.xpath("//span[contains(text(),'Card A')]"));
		WebElement target = driver.findElement(By.xpath("//h2[contains(text(),'List B')]/parent::div/following-sibling::div"));
		
	
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	

		WebElement location = driver.findElement(By.xpath("//span[contains(text(),'Card A')]"));
		Point coordinates = location.getLocation();
		System.out.println("Co-ordinates of the Card is : "+ "X: "+ coordinates.getX() + " Y: " + coordinates.getY());
		driver.findElement(By.xpath("//span[@title='Gokul Saminathan (gokulsaminathan)']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Log out']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Log out')]")).click();
		driver.close();
	}

}

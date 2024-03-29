package testScripts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GooglePageTestChrome {

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {

		prop = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//configfiles//config.properties";
		FileInputStream file;

		try {
			file = new FileInputStream(path);
			prop.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	@Test
	public void googlePageTesting() {

		try {

			String ExpectedTitle = "selenium tutorial in tamil - Google Search";
			String url = prop.getProperty("url");
			driver.get(url);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("APjFqb")));

			driver.findElement(By.id("APjFqb")).sendKeys("Selenium Tutorial");
			Thread.sleep(3000);

			List<WebElement> element = driver
					.findElements(By.xpath("//ul[@class='G43f7e'][1]/li//div[@class='wM6W7d']"));
			System.out.println(element.size());

			for (WebElement s : element) {
				if (s.getText().equalsIgnoreCase("selenium tutorial in tamil")) {
					s.click();
					break;
				}
			}
			
			String ActualTitle = driver.getTitle();
			System.out.println(ActualTitle);
			
			Assert.assertEquals(ActualTitle, ExpectedTitle);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void closeDriver() {
		driver.close();
	}
}

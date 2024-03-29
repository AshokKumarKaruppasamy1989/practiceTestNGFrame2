package testScripts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GooglePageTestFirefox {
	
	WebDriver driver;
	Properties prop;
	FileInputStream file;
	
	@BeforeMethod
	public void setUp() {
		
		String path = System.getProperty("user.dir") + "//src//test//resources//configfiles//config.properties";
		prop = new Properties();
		
		try {
			file = new FileInputStream(path);			
			prop.load(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String browser = prop.getProperty("firefoxbrowser");
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void googlePage() {
		String urlLink = prop.getProperty("url");
		driver.get(urlLink);
		System.out.println("Successfully launched the url");
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

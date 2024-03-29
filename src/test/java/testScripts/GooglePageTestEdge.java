package testScripts;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GooglePageTestEdge {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setUp() {
		prop = new Properties();
		String path = System.getProperty("user.dir") + "//src/test//resources//configfiles//config.properties";
		FileInputStream file;
		
		try {
			file = new FileInputStream(path);
			prop.load(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String browser = prop.getProperty("edgebrowser");
		
		if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
	}
	
	
	@Test
	public void googlePage() {
		String url = prop.getProperty("url");
		driver.get(url);
		System.out.println(driver.getTitle());
	}

	@AfterMethod
	public void closeDriver() {
		driver.close();
	}
}

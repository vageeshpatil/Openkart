package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //for logger
import org.apache.logging.log4j.Logger; //for logger
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass {
	
	public Properties p;// to read config.properties
	
	public Logger logger;// for Logging
	
	public WebDriver driver;  // make it static so that you can use same instance in Extent report manager
	

	@BeforeClass(groups = { "Master", "Sanity", "Regression" }) //Step8 groups added
	@Parameters({"os" , "browser"})   // getting browser parameter from testng.xml
	public void setup(String os , String br) throws IOException
	{
		//rb = ResourceBundle.getBundle("config");// Load config.properties
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();		
		p.load(file);
		
				
		logger = LogManager.getLogger(this.getClass());// for Logger  
		
		if (p.getProperty("executive_env").equalsIgnoreCase("remote")) {
			
			if(br.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setPlatformName(os.equalsIgnoreCase("windows")? "windows": os.equalsIgnoreCase("linux")? "linux": "mac");
				driver= new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),options);  //http://192.168.1.4:4444/
				
			}
			else if (br.equalsIgnoreCase("edge")){
				EdgeOptions options = new EdgeOptions();
				options.setPlatformName(os.equalsIgnoreCase("windows")? "windows": os.equalsIgnoreCase("linux")? "linux": "mac");
				driver= new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),options);
				
			}
			else if (br.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.setPlatformName(os.equalsIgnoreCase("windows")? "windows": os.equalsIgnoreCase("linux")? "linux": "mac");
				driver= new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),options);
			}
			
		}
		
		
		
		
		
		//for older version below
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if (p.getProperty("executive_env").equalsIgnoreCase("remote")) {
			
			//os
			switch(os.toLowerCase()) {
			case "windows" : capabilities.setPlatform(Platform.WIN11);
			break;
			case "mac" : capabilities.setPlatform(Platform.MAC);
			break;
			default : System.out.print("No matching found");
			return;
			}
			
		
		
		switch(br.toLowerCase()) {
		case "chrome": capabilities.setBrowserName("chrome");
		break;
		case "Edge" : capabilities.setBrowserName("MicrosoftEdge");
		break; 
		default: System.out.print("Invalid browser");
		return;
		
		}
		
		driver= new RemoteWebDriver(new URL( "http://localhost:4444/wd/hub"),capabilities);
		
		} */
		
		
		//launch right browser based on parameter if local
		if(p.getProperty("executive_env").equalsIgnoreCase("local")) {
	
		if (br.equals("chrome")) {
			
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			
			driver = new EdgeDriver();
		} else {
			
			driver = new ChromeDriver();
		}
		}
					
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	

		driver.get(p.getProperty("appURL")); // get url from config.properties file
		driver.manage().window().maximize();
		
			}
	
	//adding a method to login to page 
	public void doLogin() {
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
					
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));
		
		lp.setPassword(p.getProperty("password")); 
		
		lp.clickLogin();
	}


	@AfterClass(groups = { "Master", "Sanity", "Regression" }) //Step8 groups added
	public void teadDown() {
		driver.quit();
	}

	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
}

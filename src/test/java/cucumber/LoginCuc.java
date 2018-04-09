package cucumber;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginCuc {

	static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	static String URL = "http://localhost:8081/login";
	static WebDriver driver = getDriver(PathFirefox);

	@Before
	public static WebDriver getDriver(String PathFirefox) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@After
	static public void end() {
		driver.quit();
	}

	@When("^the username exists")
	public void loginUser() {
		driver.findElement(By.id("username")).sendKeys("pepe");
	}

	@And("^the password corresponds to the username")
	public void loginPass() {
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
	}

	@Then("^the operator should access the dashboard page")
	public void checkFail() {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://localhost:8081/operator/list")) {
			System.out.println("Test Login Passed");
		} else {
			System.out.println("Test Login Failed");
		}
		driver.close();
	}

}

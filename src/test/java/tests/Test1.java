package tests;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	public WebDriver driver;
	@Given("user is on login page")
	public void user_is_on_login_page() {
		WebDriverManager.chromedriver().setup();

		// Map<String, Object> prefs = new HashMap<String, Object>();
		// prefs.put("credentials_enable_service", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		// options.setExperimentalOption("prefs", prefs);
		//options.addArguments("--disable-notifications"); //not working
		//options.addArguments("disable-infobars"); //not working
		driver=new ChromeDriver(options);
		driver.get("https://facebook.com");
	}

	@When("enter {string} and {string}")
	public void enter_and(String username, String password) {
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("pass")).sendKeys(password);
	}

	@Then("Click on Login")
	public void click_on_login() {
		driver.findElement(By.name("login")).click();
	}

	@Then("Verify the error message")
	public void verify_the_error_message() {
		String Error_Message=driver.findElement(By.className("_9ay7")).getText();
		System.out.println("The error message is "+ Error_Message);
		if(Error_Message.equalsIgnoreCase("The email address or mobile number you entered isn't connected to an account. Find your account and log in."))
		{
			System.out.println("The Error message displaying correctly");
		}
		else
		{
			System.out.println("The error message not matched");
		}
	}

	@Then("Close Browser")
	public void Close_Browser() {
		driver.close();
	}
}

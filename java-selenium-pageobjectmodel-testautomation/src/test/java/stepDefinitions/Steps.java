package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.*;  // import all keywords using *
import pageObjects.LoginPage;


public class Steps {
	
	// global variables which can be accessed in all methods
	public WebDriver driver;
	public LoginPage lp;
	
	
	// Launch Chrome Browser
	@Given("User Launch Chrome Browser")
	public void user_Launch_Chrome_Browser() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		
		lp = new LoginPage(driver);
	}

	// Go to URL
	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		driver.get(url);
	}

	// Enter Email and Password
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		lp.setEmail(email);
		lp.setPassword(password);
	}

	// Click Login
	@When("Click on Login")
	public void click_on_Login() {
		lp.clickLogin();
	}	

	// Verify page title
	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) {
		
		if(driver.getPageSource().contains("Login was unsuccessful")){
			driver.close();
			Assert.assertTrue(false);
		}
		else{
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	// Click Logout
	@When("User Click on Log out link")
	public void user_Click_on_Log_out_link() throws InterruptedException {
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	
	// Close browser
	@Then("close browser")
	public void close_browser() {
		driver.quit();
	    
	}

}

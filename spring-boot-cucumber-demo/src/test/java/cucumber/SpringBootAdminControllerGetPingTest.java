package cucumber;

import org.springframework.boot.test.context.SpringBootTest;
//import org.openqa.selenium.By;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringBootAdminControllerGetPingTest {
	// WebDriver driver = null;
	private RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort
	private int port;
	
	@Given("^I am on Spring Boot Admin Endpoint URL$")
	public void setUpRestTemplate() {
		// driver = new FirefoxDriver();
		// driver.navigate().to("https://www.facebook.com/");		
		//restTemplate = new RestTemplate();
	}

	@When("^I fire RestTemplate on \"(.*)\"$")
	public void fireRestTemplate(String arg1) {
		//driver.findElement(By.id("email")).sendKeys(arg1);
		restTemplate.getForEntity("http://localhost:"+port+arg1, Void.class);
	}

	@When("^I enter password as \"(.*)\"$")
	public void enterPassword(String arg1) {
		//driver.findElement(By.id("pass")).sendKeys(arg1);
		//driver.findElement(By.id("u_0_v")).click();
	}

	@Then("^Response should be (\\d+)$")
	public void checkHttpStatusCode(int statusCode) {
		/*if (driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110")) {
			System.out.println("Test1 Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.close();*/
	}

	@Then("^Response should not have any other entity$")
	public void checkIfResponseIsWithoutAnyEntity() {
		/*if (driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110")) {
			System.out.println("Test2 Pass");
		} else {
			System.out.println("Test2 Failed");
		}
		driver.close();*/
	}
}
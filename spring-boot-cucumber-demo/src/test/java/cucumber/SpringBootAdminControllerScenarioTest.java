package cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
//import org.openqa.selenium.By;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes= {com.abhi.cucumber.demo.SpringBootCucumberApplicationDemo.class})
public class SpringBootAdminControllerScenarioTest {
	private final Logger log = LoggerFactory.getLogger(SpringBootAdminControllerScenarioTest.class);
	private RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort
	private int port;
	
	@Given("I am on Spring Boot Admin Endpoint URL")
	public void setUpRestTemplate() {
		restTemplate = new RestTemplate();
	}

	@When("I fire RestTemplate on {string}")
	public void i_fire_RestTemplate_on(String arg1) {
		log.info("------- Current Port is "+port);
		log.info("------- Args is "+ arg1);
		restTemplate.getForEntity("http://localhost:"+port+arg1, Void.class);
	}

	@Then("Response should be {int}")
	public void response_should_be(int statusCode) {
	}
	
	@Then("Response should not have any other entity")
	public void response_should_not_have_any_other_entity() {
	}
}
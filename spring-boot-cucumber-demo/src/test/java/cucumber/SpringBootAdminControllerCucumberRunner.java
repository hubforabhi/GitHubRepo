package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber; 

@RunWith(Cucumber.class) 
@CucumberOptions(
		features = "src/test/resources/", 
		plugin={"pretty", "html:target/cucumber"},
		glue= {"com.foreach.cuke"}
		) //default value cucumber.api.spring won't work, necessary to avoid NoClassDefFoundError for Spring Transaction
public class SpringBootAdminControllerCucumberRunner {
}
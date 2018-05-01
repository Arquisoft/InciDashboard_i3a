package cucumbertests;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.uniovi.Application;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@SpringBootTest(classes = { Application.class })
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features" ) //,glue = "cucumbertests.steps"
public class CucumberTest{

}
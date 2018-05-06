package io.github.asw.i3a.operatorsWebClient.cucumber;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.asw.i3a.operatorsWebClient.Application;

@SpringBootTest(classes = { Application.class })
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features" ) //,glue = "cucumbertests.steps"
public class CucumberTest{

}
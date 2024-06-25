package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./Features", 
                 glue ="stepDefinations",
                 dryRun = false,           //check all the code is correct or not(sanity testing)
                 monochrome = false, //pretty format
                 plugin = {"pretty","html:target/cucumber-reports"},///generate the report
                 tags = "@sanity or @datadriven")//run specific scenarios //latest tag syntax
					/*
					 * not @datadriven > not run
					 * */
					 
public class TestRunner 
{
	

}

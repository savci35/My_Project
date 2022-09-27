package com.step_definitions;

import com.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*
In the class we will be able to pass-& pos- conditions to each scenario and each step
 */
public class Hooks {


    // import from io.cucember.java not from junit
    @Before(order = 1)
    public void setupScenario() {
        System.out.println("====Setting up browser using cucumber @Before");
    }

    @Before(value = "@login", order = 2) // sırayı burdan ayarlayabiliriz order yaz tab tuşuna bas değeri yaz
    public void setupScenarioForLogins() {
        System.out.println("====this will only apply to scenarios with @login tag");
    }

    @Before(value = "db", order = 0)
    public void setupScenarioForDataBase() {
        System.out.println("====this will only apply to scenarios with @db");
    }

    @After
    public void teardownScenario(Scenario scenario) {

        //scenario.isFailed() -->if scenario fails this method will return TRUE boolean value
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            Driver.closeDriver();

            //  System.out.println("====Closing browser using cucumber @After");
            //  System.out.println("====Scenario ended/ Take screenshot if failed");
        }
    }
    @BeforeStep
    public void setupStep() {
        System.out.println("---------->applying setup using @BeforeStep");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("---------->applaying tearDown using @AfterStep");
    }

}

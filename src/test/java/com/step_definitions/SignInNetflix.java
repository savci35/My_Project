package com.step_definitions;

import com.pages.NetflixLogin;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInNetflix {

    NetflixLogin netflixLogin = new NetflixLogin();

    @Given("user navigate to netnetflix")
    public void user_navigate_to_netnetflix() {

        //Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().get("https://www.netflix.com/tr/");
    }

    @When("User click first sign in buutom")
    public void userClickFirstSignInBuutom() {
        netflixLogin.firstSignInBottom.click();
    }
    @Then("user enter the email")
    public void user_enter_the_email() {
        netflixLogin.inputBox.sendKeys("ksk_lee_25@hotmail.com");
    }

    @And("user enter the password")
    public void user_enter_the_password() {
        netflixLogin.passwordBox.sendKeys("tufa2205");
    }

    @And("user click sign in bottom")
    public void user_click_sign_in_bottom() throws InterruptedException {
        Thread.sleep(3000);
        netflixLogin.signButton.click();
    }


    @And("user clicks savas profile")
    public void userClicksSavasProfile() {
        netflixLogin.profileButton.click();
    }
}

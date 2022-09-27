package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetflixLogin {

    public NetflixLogin() {

        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//a[.='Oturum Aç']")
    public WebElement firstSignInBottom;

    @FindBy(xpath = "//input[@id='id_userLoginId']")
    public WebElement inputBox;

    @FindBy(xpath = "//input[@id='id_password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[.='Oturum Aç']")
    public WebElement signButton;


    @FindBy(xpath = "(//div[@class='profile-icon'])[3]")
    public WebElement profileButton;

}

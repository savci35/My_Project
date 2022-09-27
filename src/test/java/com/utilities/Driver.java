package com.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

   /*
Creating a private constructor, so we are closing access to the objecct of this class from outside the class
*/
private Driver() {}


//private static WebDriver driver; bunu normal parelel olmaksızın koşturduğumuz zamn kullanıyoruz

private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<WebDriver>();
   public static WebDriver getDriver() {

      if (driverPool.get() == null) {


         /*
         We read our browserType from configuration.properties.
                 This way, we can control which browser is opened from outside our code.
             */

         String browserType =ConfigurationReader.getProperty("browser");


         switch (browserType.toLowerCase()) {

            case "chrome":
               WebDriverManager.chromedriver().setup();
               driverPool.set(new ChromeDriver());
               driverPool.get().manage().window().maximize();
               driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

               break;
            case "firefox":
               WebDriverManager.firefoxdriver().setup();
               driverPool.set(new FirefoxDriver());
               driverPool.get().manage().window().maximize();
               driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               break;


         }
      }
      return  driverPool.get();
   }
   public static void closeDriver() {
      if (driverPool != null)
         driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
driverPool.remove(); //bu kısımda thread leri kapamak için
   }
}


/*
private Driver() {}


//private static WebDriver driver; bunu normal parelel olmaksızın koşturduğumuz zamn kullanıyoruz


   public static WebDriver getDriver() {

      if (driver == null) {


         /*
         We read our browserType from configuration.properties.
                 This way, we can control which browser is opened from outside our code.
             */
/*
   String browserType =ConfigurationReader.getProperty("browser");


         switch (browserType.toLowerCase()) {

                 case "chrome":
                 WebDriverManager.chromedriver().setup();
                 driver= new ChromeDriver();
                 driver.manage().window().maximize();
                 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                 break;
                 case "firefox":
                 WebDriverManager.firefoxdriver().setup();
                 driver= new ChromeDriver();
                 driver.manage().window().maximize();
                 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                 break;


                 }
                 }
                 return driver;
                 }
public static void closeDriver() {
        if (driver != null)
        driver.quit(); // this line will terminate the existing session. value will not even be null

        }
 */
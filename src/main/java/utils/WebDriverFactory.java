package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {

  public static WebDriver setupDriver(String driverType) {
    if (driverType == null) driverType = "CHROME";
    switch (driverType.trim()) {
      case "CHROME":
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
      case "FIREFOX":
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
      case "OPERA":
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
      default:
        throw new WebDriverException("не верный тип драйвера");
    }
  }

}

import page.MainPage;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class CourcesPageTest {

  private EventFiringWebDriver driver;
  private MainPage mainPage;

  @Before
  public static void startRemote() {
    Configuration.remote = "http://127.0.0.1/wd/hub";
    Configuration.browser = "chrome";
    Configuration.browserVersion = "101.0";
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    capabilities.setCapability("enableLog", true);
    Configuration.browserCapabilities = new ChromeOptions();
  }

  @After
  public void teamDown() {
    driver.quit();
  }


  @Test(enabled = false)
  public void clickCourse() {
    mainPage.openOtus().clickCourse(Integer.parseInt(System.getProperty("nameOpenCourse")));
  }


  @Test
  public void selectSpecialCourses() {
    mainPage.openOtus().coursesSpecial(System.getProperty("nameSpecialCourse"));
  }

  @Test
  public void selectBeforePopularCourses() {
    mainPage.openOtus().beforePopularCourse();
  }

  @Test
  public void selectAfterPopularCourse() {
    mainPage.openOtus().popularCourseAfter();
  }

  @Test
  public void selectBeforeSpecialCourse() {
    mainPage.openOtus().beforeSpecialCourse();
  }

  @Test
  public void selectAfterSpecialCourse() {
    mainPage.openOtus().afterSpecialCourse();
  }

  @Test
  public void selectMorePopularCourse() {
    mainPage.openOtus().morePopularCourse(System.getProperty("namePopularCourse"));
  }


}
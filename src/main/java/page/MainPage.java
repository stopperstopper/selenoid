package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainPage extends BasePage {
  private String baseURL = "https://otus.ru";

  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }


  public MainPage openOtus() {
    driver.get(baseURL);
    return this;
  }


  @FindBy(css = ".course-header2__title")
  private WebElement courseNameHeader;

  @FindBy(xpath = "//a//div[@class='lessons__new-item-start']")
  private List<WebElement> popularCoursesDates;

  @FindBy(xpath = "//a//div[contains(@class, 'lessons__new-item-title_bundle')]")
  private List<WebElement> specialCourses;

  @FindBy(xpath = "//div[@class='container-padding-bottom']//a//div[@class='lessons__new-item-time']")
  private List<WebElement> specialCoursesDateOne;

  @FindBy(xpath = "//a//div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse']")
  private List<WebElement> popularCourses;


  @FindBy(css = "a.lessons__new-item")
  private List<WebElement> courses;

  @FindBy(css = "a.lessons__new-item .lessons__new-item-title")
  private List<WebElement> coursesNameAll;


  public MainPage coursesSpecial(String name) {
    String courses = specialCourses.stream().filter(x -> x.getText().equals(name)).toString() == null ? "Курс не найден" : specialCourses.stream().filter(x -> x.getText().equals(name)).toString();
    System.out.println("Вы выбрали \"Специальные курсы\" курс-сы - " + courses);
    return this;
  }

  public MainPage morePopularCourse(String name) {
    String courses = popularCourses.stream().filter(x -> x.getText().equals(name)).toString() == null ? "Курс не найден" : popularCourses.stream().filter(x -> x.getText().equals(name)).toString();
    System.out.println("Вы выбрали \"Популярные курсы\" курс-сы - " + courses);
    return this;
  }

  public MainPage beforePopularCourse() {

    int numberCourse = 0;

    LocalDate startDay = LocalDate.of(2023, 1, 1);
    LocalDate nextDay;

    for (int i = 0; i != 3; i++) {

      String popular = popularCoursesDates.get(i).getText();

      String[] words = popular.split("[\\s]+");

      if (words.length == 3 || words[0].matches("[С]") || words[1].matches("[0-9]{2}") || words[2].matches("[а-я]+")) {
        int dd = Integer.parseInt(words[1]);

        nextDay = LocalDate.of(2022, dateMonthStringInInt(words[2]), dd);

        if (nextDay.isBefore(startDay)) {
          startDay = nextDay;
          numberCourse = i;
        }
      }
    }
    System.out.println("Популярный курс, который начинается раньше всех- " + popularCourses.get(numberCourse).getText() + "\nОн начинает- " + startDay);

    return this;
  }

  public MainPage popularCourseAfter() {

    int numberCourse = 0;

    LocalDate startDay = LocalDate.of(2021, 1, 1);
    LocalDate nextDay;

    for (int i = 0; i != 3; i++) {

      String popular = specialCoursesDateOne.get(i).getText();

      String[] words = popular.split("[\\s]+");

      if (words.length == 3 || words[0].matches("[С]") || words[1].matches("[0-9]{2}") || words[2].matches("[а-я]+")) {
        int dd = Integer.parseInt(words[1]);

        nextDay = LocalDate.of(2022, dateMonthStringInInt(words[2]), dd);

        if (nextDay.isAfter(startDay)) {
          startDay = nextDay;
          numberCourse = i;
        }
      }
    }
    System.out.println("Популярный курс, который начинается раньше всех- " + popularCourses.get(numberCourse).getText() + "\nОн начинает- " + startDay);

    return this;
  }

  public MainPage beforeSpecialCourse() {

    int numberCourse = 0;

    LocalDate startDay = LocalDate.of(2023, 1, 1);
    LocalDate nextDay;

    for (int i = 0; i != 10; i++) {

      String popular = specialCoursesDateOne.get(i).getText();

      String[] words = popular.split("[\\s]+");

      if (words[0].matches("[0-9]{2}") || words[1].matches("[а-я]+")) {
        int yy = 2022;
        if (Objects.equals(words[2], "2021")) {
          yy = Integer.parseInt(words[2]);
        }
        int dd = Integer.parseInt(words[0]);
        nextDay = LocalDate.of(yy, dateMonthStringInInt(words[1]), dd);

        if (nextDay.isBefore(startDay)) {
          startDay = nextDay;
          numberCourse = i;
        }
      }
    }
    System.out.println("Популярный курс, который начинается раньше всех- " + specialCourses.get(numberCourse).getText() + "\nОн начинает- " + startDay);

    return this;
  }

  public MainPage afterSpecialCourse() {

    int numberCourse = 0;

    LocalDate startDay = LocalDate.of(2021, 1, 1);
    LocalDate nextDay;

    for (int i = 0; i != 10; i++) {

      String popular = specialCoursesDateOne.get(i).getText();

      String[] words = popular.split("[\\s]+");

      if (words[0].matches("[0-9]{2}") || words[1].matches("[а-я]+")) {
        int yy = 2022;
        if (Objects.equals(words[2], "2021")) {
          yy = Integer.parseInt(words[2]);
        }
        int dd = Integer.parseInt(words[0]);
        nextDay = LocalDate.of(yy, dateMonthStringInInt(words[1]), dd);

        if (nextDay.isAfter(startDay)) {
          startDay = nextDay;
          numberCourse = i;
        }
      }
    }
    System.out.println("Популярный курс, который начинается раньше всех- " + specialCourses.get(numberCourse).getText() + "\nОн начинает- " + startDay);

    return this;
  }

  public int dateMonthStringInInt(String month) {
    Locale locales = Locale.getDefault();
    return Integer.parseInt(new SimpleDateFormat("MM", locales).format(month));
  }

  public MainPage clickCourse(int i) {
    Actions actions = new Actions(driver);
    i--;
    String courseName = coursesNameAll.get(i).getText();

    actions
            .moveToElement(courses.get(i))
            .pause(1000)
            .click()
            .build()
            .perform();

    Assert.assertEquals(courseName, courseNameHeader.getText());

    return this;
  }
}

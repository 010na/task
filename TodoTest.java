package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.TodoPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.testng.Assert.*;

public class TodoTest {
    WebDriver driver;
    TodoPage todoPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://to-do-simple.netlify.app/");
        todoPage = new TodoPage(driver);
    }

    @Test(priority = 1)
    public void testAddTask() {
        todoPage.addTask("Learn Selenium");
        assertTrue(todoPage.isTaskPresent("Learn Selenium"), "Task not found after adding.");
    }

    @Test(priority = 2)
    public void testCompleteTask() {
        todoPage.completeTask("Learn Selenium");
        // You can also assert checkbox is selected if needed
    }

    @Test(priority = 3)
    public void testDeleteTask() {
        todoPage.deleteTask("Learn Selenium");
        assertFalse(todoPage.isTaskPresent("Learn Selenium"), "Task still present after deletion.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class TodoPage {
    WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    By inputField = By.cssSelector("input.new-todo");
    By todoListItems = By.cssSelector("ul.todo-list li");
    By deleteButton = By.cssSelector("button.destroy");

    public void addTask(String taskText) {
        driver.findElement(inputField).sendKeys(taskText + Keys.ENTER);
    }

    public WebElement getTaskByText(String text) {
        for (WebElement task : driver.findElements(todoListItems)) {
            String taskName = task.findElement(By.cssSelector("label")).getText();
            if (taskName.equalsIgnoreCase(text)) {
                return task;
            }
        }
        return null;
    }

    public void completeTask(String taskText) {
        WebElement task = getTaskByText(taskText);
        if (task != null) {
            task.findElement(By.cssSelector("input.toggle")).click();
        }
    }

    public void deleteTask(String taskText) {
        WebElement task = getTaskByText(taskText);
        if (task != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(task).perform();
            task.findElement(deleteButton).click();
        }
    }

    public boolean isTaskPresent(String taskText) {
        return getTaskByText(taskText) != null;
    }
}

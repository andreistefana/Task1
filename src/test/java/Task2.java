import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import java.time.Duration;

public class Task2 {
    WebDriver driver = new EdgeDriver();


    @AfterTest
    public void quitBrowser() {
        driver.quit();
    }

    @Test
    public void Task2() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        driver.get("https://pastebin.com/");
        driver.manage().window().maximize();

        // accepting privacy terms
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//span[text()='AGREE']"))).click();

        //unique identifier when using cssSelector
        WebElement code = driver.findElement(By.cssSelector("#postform-text"));

        //adding requested text into code field
        String codeToSend = "git config --global user.name  \"New Sheriff in Town \n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\") \n" +
                "git push origin master --force";
        code.sendKeys(codeToSend);

        //scrolling down the  page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        //opening the Syntax Highlight dropdown and selecting Bash
        WebElement syntaxHighlight = driver.findElement(By.id("select2-postform-format-container"));
        syntaxHighlight.click();
        driver.findElement(By.xpath("//li[text()='Bash']")).click();

        //opening the paste expiration dropdown and selecting requested 10 minutes time
        WebElement pasteExpiration = driver.findElement(By.id("select2-postform-expiration-container"));
        pasteExpiration.click();
        driver.findElement(By.xpath("//li[text()='10 Minutes']")).click();

        //adding the requested title
        String addTitle = "how to gain dominance among developers";
        WebElement title = driver.findElement(By.id("postform-name"));
        title.sendKeys(addTitle);

        //submitting by clicking the Create New Paste button
        WebElement createPasteInput = driver.findElement(By.
                xpath("//button[@type = 'submit' and contains(text(),'Paste')]"));
        createPasteInput.click();
        System.out.println("Test:");

        //add assertion for title
        WebElement resultTitle = driver.findElement(By.cssSelector(".info-top"));
        Assert.assertEquals(resultTitle.getText(), addTitle, "Title is not " + addTitle);

        //add assertion for syntax highlight
        WebElement resultSyntax = driver.findElement(By
                .xpath("//a[contains(@href,'/archive/bash') and text() ='Bash']"));
        Assert.assertEquals(resultSyntax.getText(), "Bash", "Syntax is not Bash");

        //add assertion for code
        WebElement resultCode = driver.findElement(By
                .xpath("//div[contains(@class, 'source bash')]"));
        Assert.assertEquals(resultCode.getText(), codeToSend, "Code is not" + codeToSend);

    }
}

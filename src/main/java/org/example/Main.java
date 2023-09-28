package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://pastebin.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[2]")));
        driver.findElement(By.xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[2]")).click();


        WebElement code = driver.findElement(By.id("postform-text"));
        code.sendKeys("Hello from WebDriver");

        List<WebElement> options = driver.findElements(By
                .xpath("//*[@id=\"select2-postform-expiration-container\"]"));
        for (WebElement option : options) {
            if (option.getText().contains("10 Minutes")) {
                option.click();
                break;
            }


            WebElement title = driver.findElement(By.id("postform-name"));
            title.sendKeys("helloweb");

            WebElement createPasteInput = driver.findElement(By
                    .xpath("//*[@id=\"w0\"]/div[5]/div[1]/div[10]/button"));
            createPasteInput.click();

            driver.quit();


        }
    }
}
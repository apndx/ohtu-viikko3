package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {

// // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana        
//        WebDriver driver = new HtmlUnitDriver();
//        driver.get("http://localhost:4567");
//        sleep(2);
//        // tulostetaan sivu konsoliin
//        System.out.println(driver.getPageSource());
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//        sleep(2);  
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akke");
//        element.submit();
//        sleep(2);
//        System.out.println(driver.getPageSource());
//        driver.quit();
//        //element.sendKeys("akkep"); //oikea salasana
//        // tulostetaan sivu konsoliin
//        System.out.println(driver.getPageSource());
//        // ...
//        driver.quit();
///epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
//        WebDriver driver = new HtmlUnitDriver();
//        driver.get("http://localhost:4567");
//        sleep(2);
//        // tulostetaan sivu konsoliin
//        System.out.println(driver.getPageSource());
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//        sleep(2);  
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekk");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akke");
//        element.submit();
//        sleep(2);
//        System.out.println(driver.getPageSource());
//        driver.quit();
//        //element.sendKeys("akkep"); //oikea salasana
//uuden käyttäjätunnuksen luominen
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        sleep(2);
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("jamppa" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("passelipassu");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("passelipassu");
        element.submit();
        System.out.println(driver.getPageSource());
        sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource());
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());
        
        
        driver.quit();
        //element.sendKeys("akkep"); //oikea salasana

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}

package Login;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ApprovalTahsildar 
{	WebDriver driver;
@BeforeClass
public void setup() {
	ChromeOptions options = new ChromeOptions();
    options.addArguments("--allow-insecure-localhost");
    options.addArguments("--disable-web-security");
      driver = new ChromeDriver(options);
    // Manage browser settings
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://mahamahsul-pune.mahamining.com/login");
}
@Test(priority=1)
public void testLogin() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
	usernameField.sendKeys("9696969696");
	WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	passwordField.sendKeys("Aa@158310");
	WebElement captchaField = driver.findElement(By.xpath("//input[@placeholder='Captcha']"));
	captchaField.click();
	Thread.sleep(6000);
	// Click Login Button
	WebElement loginButton = driver.findElement(By.xpath("//span[normalize-space()='LOGIN']"));
	loginButton.click();
	Thread.sleep(3000);
	// Verify login success (Assumption: Dashboard appears after login)
	String expectedUrl = "https://mahamahsul-pune.mahamining.com/dashboard"; 
	Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login Failed!");

}   
@Test(priority=2)
public void Applicationlist() throws InterruptedException
{	// click on Application list 
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[normalize-space()='Application List']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id=\"content\"]/div/app-application/mat-card/mat-card-content/ul/li[1]/div[1]/div[4]/div/button[2]/span[3]")).click();
	Thread.sleep(2000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-form-field-label-14\"]/mat-label")));

	// Use JavascriptExecutor to click the element

	js.executeScript("arguments[0].click();", element);
	Thread.sleep(1000);
	WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-option-18\"]/span")));
	js.executeScript("arguments[0].click();", option);
	Thread.sleep(1000);
	WebElement elementd = driver.findElement(By.xpath("//input[@formcontrolname='remark']"));
	js.executeScript("arguments[0].click();", elementd);
	elementd.sendKeys("All Applicant document details filled");
	Thread.sleep(1000); 
	// Store the current window handle
	String originalWindow = driver.getWindowHandle();

	// Perform actions that open a new window
	WebElement de = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/div/div[8]/div/form/div/div[4]/button/span[2]")));
	js.executeScript("arguments[0].click();", de);
	Thread.sleep(1000);
	WebElement ds = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")));
	js.executeScript("arguments[0].click();", ds);
	Thread.sleep(1000);

	// Switch to the new window
	for (String windowHandle : driver.getWindowHandles()) {
		if (!originalWindow.contentEquals(windowHandle)) {
			driver.switchTo().window(windowHandle);
			break;
		}
	}

	// Close the new window
	driver.close();

	// Switch back to the original window
	driver.switchTo().window(originalWindow);
	WebElement button  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-tab-content-1-0\"]/div/div/div[8]/div/form/div/div[4]/button/span[2]")));
	js.executeScript("arguments[0].click();", button);
	Thread.sleep(2000);
	WebElement dn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-dialog-1\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")));
	js.executeScript("arguments[0].click();", dn);
	Thread.sleep(4000);
	System.out.println("Approval done by Approval Tahsildar 3");
}
@AfterClass            
public void teardown() {
	driver.quit();
}
}

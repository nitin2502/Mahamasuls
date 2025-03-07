package Login;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ApprovalJRClerk14
{	WebDriver driver;
@BeforeClass
public void setup() {

	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
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
	usernameField.sendKeys("9898989898");
	WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	passwordField.sendKeys("Aa@189649");
	WebElement captchaField = driver.findElement(By.xpath("//input[@placeholder='Captcha']"));
	captchaField.click();
	Thread.sleep(5000);
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
	Thread.sleep(4000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application/mat-card/mat-card-content/ul/li[1]/div[1]/div[3]/div/button[2]/span[3]")).click();
	Thread.sleep(2000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//Submit order
	WebElement options = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/div/div[10]/div/div/button/span[2]")));
	js.executeScript("arguments[0].click();", options);
	Thread.sleep(2000);
	WebElement buttono  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/app-order/mat-card/mat-card-content/div/div/button/span[2]")));
	js.executeScript("arguments[0].click();", buttono);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	Thread.sleep(3000);
	
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	

	WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-form-field-label-18\"]/mat-label")));
	// Use JavascriptExecutor to click the element
	js1.executeScript("arguments[0].click();", element);
	Thread.sleep(1000);
	WebElement option = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-option-19\"]/span")));
	js1.executeScript("arguments[0].click();", option);
	Thread.sleep(1000);
	
	WebElement elementd = driver.findElement(By.xpath("//input[@formcontrolname='remark']"));
	js.executeScript("arguments[0].click();", elementd);
	elementd.sendKeys("All Applicant document details filled");
	Thread.sleep(1000); 
	
	// click to submit
	WebElement button  = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-tab-content-1-0\"]/div/div/div[11]/div/form/div/div[4]/button/span[2]")));
	js1.executeScript("arguments[0].click();", button);
	Thread.sleep(2000);
	WebElement dn = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")));
	js1.executeScript("arguments[0].click();", dn);
	Thread.sleep(3000);
}
//@AfterClass            
public void teardown() {
	driver.quit();
}
}

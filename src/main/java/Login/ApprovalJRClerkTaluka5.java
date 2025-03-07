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
public class ApprovalJRClerkTaluka5 
{	WebDriver driver;
@BeforeClass
public void setup() {
	//Need to creat a new application
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
	usernameField.sendKeys("6565644444");
	WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	passwordField.sendKeys("Raj@1122");
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
	Thread.sleep(4000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application/mat-card/mat-card-content/ul/li[1]/div[1]/div[3]/div/button[2]/span[3]")).click();
	Thread.sleep(1000);

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[1]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[2]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[3]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[4]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[5]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[6]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[7]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[8]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[9]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[10]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[11]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[12]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[13]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[14]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[15]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[16]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[17]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[18]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[19]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[20]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[21]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[22]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[23]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");;
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[24]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-application-preview/mat-card/mat-card-content/div/div/mat-tab-group/div/mat-tab-body[1]/div/div/div[8]/div/table/tbody/tr[25]/td[3]/div/input")).sendKeys("C:\\Users\\niting\\Downloads\\images.jpg");
	Thread.sleep(1000);

	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-form-field-label-14\"]/mat-label")));
	js.executeScript("arguments[0].click();", element);
	Thread.sleep(1000);
	WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-option-18\"]/span")));
	js.executeScript("arguments[0].click();", option);
	Thread.sleep(1000);
	// select officer
	WebElement elements = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-form-field-label-18\"]/mat-label")));
	js.executeScript("arguments[0].click();", elements);
	Thread.sleep(1000);
	WebElement options = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-option-20\"]/span")));
	js.executeScript("arguments[0].click();", options);
	Thread.sleep(1000);
	WebElement elementg = driver.findElement(By.xpath("//input[@formcontrolname='remark']"));
	js.executeScript("arguments[0].click();", elementg);
	elementg.sendKeys("All Applicant document details filled");
	Thread.sleep(1000); 
	
	WebElement buttons  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/div/div[9]/div/form/div/div[5]/button/span[2]")));
	js.executeScript("arguments[0].click();", buttons);
	Thread.sleep(2000);
	WebElement dn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")));
	js.executeScript("arguments[0].click();", dn);
	Thread.sleep(4000);
}
@AfterClass            
public void teardown() {
	driver.quit();
}
}

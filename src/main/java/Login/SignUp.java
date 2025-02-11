package Login;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
public class SignUp {
WebDriver driver;
Faker faker = new Faker();
Random random = new Random();

String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
String randomUsername = faker.name().username();
String randomPassword = faker.internet().password(8, 13, true, true, true) 
                        + (char) (random.nextInt(26) + 'A') 
                        + random.nextInt(10) 
                        + specialCharacters.charAt(random.nextInt(specialCharacters.length()));

	@BeforeTest
	public void setUp() throws InterruptedException {
		// Use WebDriverManager to set up ChromeDriver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Navigate to the login page
		driver.get("https://mahamahsul-pune.mahamining.com/login"); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Sign up']")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@maxlength='10']")).sendKeys("9325843635");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-otp/div[3]/button/span[2]")).click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://mahamahasul-api.mahamining.com/MahaMahasul/api/LoginOtp/Get-OTP-By-Mobile?MobileNo=9325843635');");
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-otp/div[3]/button/span[2]")).click();
        Thread.sleep(1000);
	  driver.navigate().refresh();
	}
	
@Test(priority=1)
public void testSignupFormIndividual() throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    
     {    	//Individual user 
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
    	driver.findElement(By.id("mat-radio-2-input")).click();
        Thread.sleep(1000);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-0\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-0\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
         driver.findElement(By.xpath("//input[@formcontrolname='fullName']")).sendKeys("santosh kadam");
        driver.findElement(By.xpath("//input[@formcontrolname='fullName_M']")).sendKeys("संतोष कदम");
        
        driver.findElement(By.xpath("//*[@id=\"mat-select-value-3\"]/span")).click();
  
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
            
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-8\"]/mat-label")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[1]/td[2]/button/span[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("sandesgh0525@gmail.com");
      

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-16\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-13\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-18\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[2]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-20\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-94\"]/span")).click();
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@formcontrolname='roadName']")));
        driver.findElement(By.xpath("//input[@formcontrolname='roadName']")).sendKeys("Atalbihari vajpeyee road pune ");
       

        driver.findElement(By.xpath("//input[@formcontrolname='buildingName']")).sendKeys("Atalbihari vajpeyee road pune ");
        

        driver.findElement(By.xpath("//input[@formcontrolname='flatno']")).sendKeys("25/A ");
      

        driver.findElement(By.xpath("//input[@formcontrolname='pinCode']")).sendKeys("413209");


        driver.findElement(By.xpath("//input[@formcontrolname='occupation']")).sendKeys("financial covrage");
  

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-32\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-32\"]/mat-label")).click();
       

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
       

        driver.findElement(By.xpath("//input[@formcontrolname='documentNo']")).sendKeys("cjpgr1234Q");
        
        driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys(randomUsername);
        

        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(randomPassword);
  

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@formcontrolname='confirmedPassword']")));
        driver.findElement(By.xpath("//input[@formcontrolname='confirmedPassword']")).sendKeys(randomPassword);
      

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-42\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-42\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[2]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='answer']")).sendKeys("USEAI");
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"main-box\"]/div/div/div[2]/form/div[4]/button[2]")));
        driver.findElement(By.xpath("//*[@id=\"main-box\"]/div/div/div[2]/form/div[4]/button[2]")).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        
            }
}
    @Test(priority=2)
    public void testSignupFormOrganization() throws Exception {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	
        // Organization user
        driver.findElement(By.id("mat-radio-3-input")).click();
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-0\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-0\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='fullName']")).sendKeys("santosh kadam");
        driver.findElement(By.xpath("//input[@formcontrolname='fullName_M']")).sendKeys("संतोष कदम");
       

        driver.findElement(By.xpath("//*[@id=\"mat-select-value-3\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-8\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-8\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[1]/td[2]/button/span[1]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("sandesgh025@gmail.com");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-16\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-13\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-18\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-87\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-20\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-94\"]/span")).click();
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@formcontrolname='roadName']")));
        driver.findElement(By.xpath("//input[@formcontrolname='roadName']")).sendKeys("Atalbihari vajpeyee road pune ");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='buildingName']")).sendKeys("Atalbihari vajpeyee road pune ");
        driver.findElement(By.xpath("//input[@formcontrolname='flatno']")).sendKeys("25/A ");
        driver.findElement(By.xpath("//input[@formcontrolname='pinCode']")).sendKeys("413209");
        driver.findElement(By.xpath("//input[@formcontrolname='occupation']")).sendKeys("financial covrage");
        

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-32\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-32\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();
        

        driver.findElement(By.xpath("//input[@formcontrolname='documentNo']")).sendKeys("cjcpg1234Q");
        
        driver.findElement(By.xpath("//input[@formcontrolname='org_Name']")).sendKeys("SteelplantLTD");
      

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-48\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-48\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"mat-option-85\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='org_RoadName']")).sendKeys("Atalbihari vajpeyee road pune ");
       

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-50\"]/mat-label")).click();
    

        driver.findElement(By.xpath("//*[@id=\"mat-option-49\"]/span")).click();
       

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-52\"]/mat-label")).click();
       

        driver.findElement(By.xpath("//*[@id=\"mat-option-276\"]/span")).click();
      

        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-54\"]/mat-label")).click();
      

        driver.findElement(By.xpath("//input[@formcontrolname='org_ContactNo']")).sendKeys("9898989898");
        

        driver.findElement(By.xpath("//*[@id=\"mat-option-291\"]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='org_BuildingName']")).sendKeys("Atalbihari vajpeyee road pune ");
        

        driver.findElement(By.xpath("//input[@formcontrolname='org_Flatno']")).sendKeys("25/A ");
       

        driver.findElement(By.xpath("//input[@formcontrolname='org_PinCode']")).sendKeys("413209");
       

        driver.findElement(By.xpath("//input[@formcontrolname='org_Email']")).sendKeys("syndicate045@govern.com");
       

        driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys(randomUsername);
       

        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(randomPassword);
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@formcontrolname='confirmedPassword']")));
        driver.findElement(By.xpath("//input[@formcontrolname='confirmedPassword']")).sendKeys(randomPassword);
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-42\"]/mat-label")));
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-42\"]/mat-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[2]/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@formcontrolname='answer']")).sendKeys("USEAI");
       

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"main-box\"]/div/div/div[2]/form/div[4]/button[2]")));
        driver.findElement(By.xpath("//*[@id=\"main-box\"]/div/div/div[2]/form/div[4]/button[2]")).click();
        Thread.sleep(4000);
    
    } 


	  @AfterClass
	public void tearDown() throws InterruptedException {
		// Close the browser
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}

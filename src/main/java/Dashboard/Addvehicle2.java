package Dashboard;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Addvehicle2 {
	WebDriver driver;
	@BeforeClass
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-insecure-localhost", "--disable-web-security");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://jaldoot.ahmednagar.gov.in/add-vehicle");
		//driver.get("https://jaldoot.ahmednagar.gov.in/add-vehicle");
	}

	@Test(priority = 0)
	public void testLogin() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("9876543210");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Stpl@1234");
		driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-4\"]/mat-label")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button/span[2][text()='Login']")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void addVehicleFromExcel() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\niting\\Downloads\\Addvehicle.xlsx\\"));
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet Sheet1 = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter(); 
		int vehicleCount = 0;
		for (int i = 1; i <= Sheet1.getLastRowNum(); i++) {
			Row row = Sheet1.getRow(i);		

			String name = formatter.formatCellValue(row.getCell(0));
			String mobile = formatter.formatCellValue(row.getCell(1));
			String vehicleNo = formatter.formatCellValue(row.getCell(2));
			String engineNo = formatter.formatCellValue(row.getCell(3));
			String chassisNo = formatter.formatCellValue(row.getCell(4));
			String imei = formatter.formatCellValue(row.getCell(5));
			String sim1 = formatter.formatCellValue(row.getCell(6));
			String sim2 = formatter.formatCellValue(row.getCell(7));

			driver.get("https://jaldoot.ahmednagar.gov.in/add-vehicle");
			//driver.get("https://jaldoot.ahmednagar.gov.in/add-vehicle");

			driver.findElement(By.xpath("//input[@formcontrolname='ownerName']")).sendKeys(name);
			driver.findElement(By.xpath("//input[@formcontrolname='ownerMobileNo']")).sendKeys(mobile);
			driver.findElement(By.xpath("//input[@formcontrolname='vehicleRegistrationNo']")).sendKeys(vehicleNo);
			Thread.sleep(1000);

			WebElement vehicleDropdownLabel = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='mat-mdc-form-field-label-8']/mat-label")));
			vehicleDropdownLabel.click();
			// Wait and click the dropdown option
			WebElement vehicleOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div/div/mat-option")));
			vehicleOption.click();
			

			WebElement vehicleTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='mat-mdc-form-field-label-10']/mat-label")));
			vehicleTypeDropdown.click();
			
			// Wait for dropdown options to be visible and click desired one
			WebElement vehicleTypeOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]"))); 
			
			vehicleTypeOption.click();
			driver.findElement(By.xpath("//input[@formcontrolname='engineNo']")).sendKeys(engineNo);
			
			driver.findElement(By.xpath("//input[@formcontrolname='chassiesno']")).sendKeys(chassisNo);
			
			WebElement districtCodeLabel = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='mat-mdc-form-field-label-32']/mat-label")));
			districtCodeLabel.click();
		
			WebElement districtCodeOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[4]/span")));
			districtCodeOption.click();
				driver.findElement(By.xpath("//input[@formcontrolname='deviceId']")).sendKeys(imei);
			driver.findElement(By.xpath("//input[@formcontrolname='deviceSIMNo']")).sendKeys(sim1);
			
			WebElement simLabel = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='mat-mdc-form-field-label-38']/mat-label")));
			js.executeScript("arguments[0].click();", simLabel);
			// Wait for SIM option to be clickable and select it
			WebElement simOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]")));
			js.executeScript("arguments[0].click();", simOption);
			driver.findElement(By.xpath("//input[@formcontrolname='secondarySIMNo']")).sendKeys(sim2);
			
			WebElement secondarySimLabel = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='mat-mdc-form-field-label-42']/mat-label")));
			js.executeScript("arguments[0].click();", secondarySimLabel);
			// Wait for the dropdown option to appear and be clickable, then select it
			WebElement secondarySimOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[4]")));
			js.executeScript("arguments[0].click();", secondarySimOption);
			Thread.sleep(1000); 
			WebElement Add = driver.findElement(By.xpath("//*[@id=\"content\"]/div/app-add-vehicle/mat-card/div[2]/form/mat-card-actions/div/button[2]/span[2]"));
			js.executeScript("arguments[0].click();", Add);
			vehicleCount++;
			System.out.println("[" + vehicleCount + "] Adding vehicle: " + vehicleNo + " for owner: " + name);
			Thread.sleep(2000); 
		}

		workbook.close();
		fis.close();

	}
}

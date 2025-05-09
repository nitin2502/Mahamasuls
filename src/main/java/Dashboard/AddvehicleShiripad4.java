package Dashboard;

import java.io.File;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddvehicleShiripad4 {

	WebDriver driver;
	String userNameKey;
	String userNameValue;
	String passwordKey;
	String passwordValue;

	@BeforeClass
	public void setup() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-insecure-localhost", "--disable-web-security");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Read credentials before opening the site
		readCredentialsFromExcel();

		driver.get("http://jaldoot-uat.erpguru.in/login");
	}

	// Method to read username and password from Excel
	public void readCredentialsFromExcel() throws Exception {
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\niting\\Downloads\\Addvehicle5.xlsx"));
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0); 

		DataFormatter formatter = new DataFormatter();
		
		Row row1 = sheet.getRow(0); // Credentials on first row
		userNameKey = formatter.formatCellValue(row1.getCell(0));
		passwordKey = formatter.formatCellValue(row1.getCell(1));
		
		Row row2 = sheet.getRow(1); // Credentials on first row
		userNameValue = formatter.formatCellValue(row2.getCell(0));
		passwordValue = formatter.formatCellValue(row2.getCell(1));

		workbook.close();
		fis.close();
	}

	@Test(priority = 0)
	public void testLogin() throws InterruptedException {
		driver.findElement(By.xpath("//input[@formcontrolname='" + userNameKey + "']")).sendKeys(userNameValue);
		driver.findElement(By.xpath("//input[@formcontrolname='" + passwordKey + "']")).sendKeys(passwordValue);
		driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-4\"]/mat-label")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button/span[2][text()='Login']")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void addVehicleFromExcel() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		FileInputStream fis = new FileInputStream(new File("C:\\Users\\niting\\Downloads\\Addvehicle5.xlsx"));
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();

		int vehicleCount = 0;
		Row headerRow = sheet.getRow(5); // header in first row
		
		for (int i = 6; i <= sheet.getLastRowNum(); i++) {
		    Row row = sheet.getRow(i);
		    if (row == null) continue; // skip empty rows

		    try {          // Step 1: Load all form field values into a Map

		        Map<String, String> formData = new LinkedHashMap<>();

		        userNameKey = formatter.formatCellValue(headerRow.getCell(0));
		        System.out.println("Header row: " + userNameKey);
		        
		        for (int col = 0; col < headerRow.getLastCellNum(); col++) {  // Build key-value pairs from Excel header and row data
		            String key = formatter.formatCellValue(headerRow.getCell(col)).trim();
		            String value = formatter.formatCellValue(row.getCell(col)).trim();
		            if (!key.isEmpty() && !value.isEmpty()) {
		                formData.put(key, value);
		            }
		        }
		        
		        // Step 2: Open the form page
		        driver.get("http://jaldoot-uat.erpguru.in/add-vehicle");

		        // Step 3: Fill all text inputs dynamically
		        for (Map.Entry<String, String> entry : formData.entrySet()) {
		            String controlName = entry.getKey();
		            String value = entry.getValue();
		            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//input[@formcontrolname='" + controlName + "']")));
		            field.clear();
		            field.sendKeys(value);
 
		        }
		        

		        // Step 4: Select dropdown options dynamically
		        Row dropdownKey = sheet.getRow(3);
		        int cellCount = dropdownKey.getLastCellNum();
		        for (int j = 0; j < cellCount; j++) {
		            selectFirstMatSelectOption(driver, wait, dropdownKey.getCell(j).toString());
		        }
		        
		        
		     // Step 5: Select date picker options dynamically
		        Row datePikerKey = sheet.getRow(4);
		        int dateCellCount = datePikerKey.getLastCellNum();
		        for (int j = 0; j < dateCellCount; j++) {
		        	System.out.println(datePikerKey.getCell(j).toString());
		        	selectCurrentMatDatepickerOption(driver, wait, datePikerKey.getCell(j).toString());
		        }

		        
		        // Step 6: Click Add button
		        Thread.sleep(1000);
		        WebElement addBtn = driver.findElement(By.xpath(
		            "//*[@id='content']/div/app-add-vehicle/mat-card/div[2]/form/mat-card-actions/div/button[2]/span[2]"
		        ));
		        js.executeScript("arguments[0].click();", addBtn);

		        vehicleCount++;
		        System.out.println("[" + vehicleCount + "] ✅ Added vehicle: " + formData.get("vehicleRegistrationNo") + " for owner: " + formData.get("ownerName"));

		        Thread.sleep(2000);

		    } catch (Exception e) {
		        System.err.println("❌ Failed to add vehicle on row " + (i + 1) + ": " + e.getMessage());
		        continue; // Continue to next row even if current one fails
		    }
		}

		workbook.close();
		fis.close();
	}
	
	
	public void selectCurrentMatDatepickerOption(WebDriver driver, WebDriverWait wait, String formControlName) {
	    // Step 1: Locate the input field for mat-datepicker
	    WebElement matDatepickerInput = driver.findElement(By.xpath("//input[@formcontrolname='" + formControlName + "']"));

	    // Step 2: Use JavaScript to simulate the click event on the input field to open the date picker
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", matDatepickerInput);

	    // Step 3: Wait for the mat-datepicker calendar to be visible
	    By datePickerVisibleLocator = By.xpath("//mat-datepicker");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerVisibleLocator));

	    // Step 4: Get the current date (day) as a string
	    LocalDate today = LocalDate.now();
	    String currentDate = today.format(DateTimeFormatter.ofPattern("d")); // Get the current day, e.g., "7" (no leading zero)
	    
	    // Step 5: Wait for the calendar to load and check for today's date
	    By currentDateLocator = By.xpath("//mat-datepicker//button[normalize-space(text())='" + currentDate + "']");
	    
	    // Step 6: Click the current date
	    WebElement todayDateButton = driver.findElement(currentDateLocator);
	    
	    // Step 7: Click on today's date to select it
	    todayDateButton.click();
	}






	
	public void selectFirstMatSelectOption(WebDriver driver, WebDriverWait wait, String formControlName) {
	    WebElement matSelect = driver.findElement(By.xpath("//mat-select[@formcontrolname='" + formControlName + "']")); 	    // Step 1: Click the mat-select to open the dropdown
	    matSelect.click();
	    By firstOptionLocator = By.xpath("//mat-option[1]"); 	    // Step 2: Wait for options to appear
	    wait.until(ExpectedConditions.visibilityOfElementLocated(firstOptionLocator));
	    WebElement firstOption = driver.findElement(firstOptionLocator);    // Step 3: Click the first option
	    firstOption.click();
	}

	
}

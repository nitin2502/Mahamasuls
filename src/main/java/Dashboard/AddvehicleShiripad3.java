package Dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AddvehicleShiripad3 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void reportSetup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-insecure-localhost", "--disable-web-security");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://jaldoot-uat.erpguru.in/add-vehicle");
    }

    @Test(priority = 0)
    public void testLogin() throws InterruptedException {
        test = extent.createTest("Login Test");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("9876543210");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Stpl@1234");
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-4\"]/mat-label")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button/span[2][text()='Login']")).click();
        Thread.sleep(2000);
        test.pass("Login successful");
    }

    @Test(priority = 1)
    public void addVehicleFromExcel() throws Exception {
        test = extent.createTest("Add Vehicles from Excel");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        FileInputStream fis = new FileInputStream(new File("C:\\Users\\niting\\Downloads\\Addvehicleshripad.xlsx\\"));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            String name = formatter.formatCellValue(row.getCell(0));
            String mobile = formatter.formatCellValue(row.getCell(1));
            String vehicleNo = formatter.formatCellValue(row.getCell(2));
            String engineNo = formatter.formatCellValue(row.getCell(3));
            String chassisNo = formatter.formatCellValue(row.getCell(4));
            String imei = formatter.formatCellValue(row.getCell(5));
            String sim1 = formatter.formatCellValue(row.getCell(6));
            String sim2 = formatter.formatCellValue(row.getCell(7));

            driver.get("http://jaldoot-uat.erpguru.in/add-vehicle");

            driver.findElement(By.xpath("//input[@formcontrolname='ownerName']")).sendKeys(name);
            driver.findElement(By.xpath("//input[@formcontrolname='ownerMobileNo']")).sendKeys(mobile);
            driver.findElement(By.xpath("//input[@formcontrolname='vehicleRegistrationNo']")).sendKeys(vehicleNo);
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mat-mdc-form-field-label-8']/mat-label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mat-mdc-form-field-label-10']/mat-label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]"))).click();

            driver.findElement(By.xpath("//input[@formcontrolname='engineNo']")).sendKeys(engineNo);
            driver.findElement(By.xpath("//input[@formcontrolname='chassiesno']")).sendKeys(chassisNo);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mat-mdc-form-field-label-32']/mat-label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[4]/span"))).click();

            driver.findElement(By.xpath("//input[@formcontrolname='deviceId']")).sendKeys(imei);
            driver.findElement(By.xpath("//input[@formcontrolname='deviceSIMNo']")).sendKeys(sim1);

            Thread.sleep(1000);
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/app-add-vehicle/mat-card/div[2]/form/mat-card-actions/div/button[2]/span[2]"));
            js.executeScript("arguments[0].click();", addButton);

            test.pass("Vehicle added: " + vehicleNo + " - Owner: " + name);
            Thread.sleep(2000);
        }

        workbook.close();
        fis.close();
    }

    @AfterMethod
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SUCCESS) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                String path = "test-output/screenshots/" + result.getName() + "_" + new Date().getTime() + ".png";
                FileUtils.copyFile(src, new File(path));
                test.addScreenCaptureFromPath(path);
                if (result.getStatus() == ITestResult.FAILURE)
                    test.fail(result.getThrowable());
            } catch (IOException e) {
                test.fail("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @AfterSuite
    public void tearDown() throws Exception {
        extent.flush();
        driver.quit();
        // Auto open report in browser
        java.awt.Desktop.getDesktop().browse(new File("test-output/ExtentReport.html").toURI());
    }
}

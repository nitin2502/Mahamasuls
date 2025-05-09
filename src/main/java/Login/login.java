package Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class login {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-web-security");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mahamahsul-pune.mahamining.com/login");
    }

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        usernameField.sendKeys("9876543210");

        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys("Admin@007");

        WebElement captchaField = driver.findElement(By.xpath("//input[@placeholder='Captcha']"));
        captchaField.click();

        Thread.sleep(5000); // Wait for manual captcha entry if needed

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-wrapper\"]/div/div/div/div/div/div[2]/div/form/div[5]/div[2]/button/span[2]"));
        loginButton.click();

        Thread.sleep(3000);
        String expectedUrl = "https://mahamahsul-pune.mahamining.com/dashboard";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login Failed!");
    }

    @Test(priority = 2)
    public void testLoginAPI() {
        RestAssured.baseURI = "https://mahamahsul-pune.mahamining.com";

        Response res = given()
            .relaxedHTTPSValidation() // in case of certificate issues
            .header("Content-Type", "application/json")
            .body("{\"username\":\"9876543210\",\"password\":\"Admin@007\"}")
            .post("https://mahamahasul-api.mahamining.com/MahaMahasul/api/Login/CheckLogin?LoginFlag=web&lan=en") // Check if this path is correct
        .then()
            .log().all() // Logs request and response
            .extract().response();

        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("Response Body: " + res.asString());

        Assert.assertEquals(res.statusCode(), 200, "Expected HTTP 200");
        Assert.assertNotNull(res.path("token"), "Token should not be null");
    }


//   @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

package Dashboard;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;
import org.openqa.selenium.devtools.v135.network.model.Request;
import org.openqa.selenium.devtools.v135.network.model.Response;
import org.openqa.selenium.devtools.v135.network.model.ResourceType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class APITest {
    WebDriver driver;
    DevTools devTools;

    @BeforeClass
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(priority = 0)
    public void enableDevTools() {
        System.out.println("Initializing DevTools");

        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Log API Requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            if (request.getType().equals(Optional.of(ResourceType.XHR)) || request.getType().equals(Optional.of(ResourceType.FETCH))) {
                Request req = request.getRequest();
                System.out.println("\n--- API REQUEST ---");
                System.out.println("URL: " + req.getUrl());
                System.out.println("Method: " + req.getMethod());
            }
        });

        // Log API Responses with Pretty JSON and Debugging
        devTools.addListener(Network.responseReceived(), response -> {
            if (response.getType().equals(Optional.of(ResourceType.XHR)) || response.getType().equals(Optional.of(ResourceType.FETCH))) {
                Response res = response.getResponse();
                System.out.println("\n--- API RESPONSE ---");
                System.out.println("URL: " + res.getUrl());
                System.out.println("Status: " + res.getStatus());
                System.out.println("Mime Type: " + res.getMimeType());

                try {
                    Network.GetResponseBodyResponse body = devTools.send(Network.getResponseBody(response.getRequestId()));
                    String responseBody = body.getBody();
                    System.out.println("Raw Response Body:\n" + responseBody);

                    if (res.getMimeType().contains("application/json")) {
                        System.out.println("JSON Response Body (Pretty):");
                        try {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            JsonElement jsonElement = JsonParser.parseString(responseBody);
                            String prettyJson = gson.toJson(jsonElement);
                            System.out.println(prettyJson);
                        } catch (Exception ex) {
                            System.out.println("Failed to pretty print JSON: " + ex.getMessage());
                        }
                    } else {
                        System.out.println("Not a JSON response.");
                    }
                } catch (Exception e) {
                    System.out.println("Could not retrieve response body: " + e.getMessage());
                }
            }
        });
    }

    @Test(priority = 1)
    public void openAppAndLogin() throws InterruptedException {
        driver.get("http://jaldoot-uat.erpguru.in/application-list");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("9876543210");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Stpl@1234");
        driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-4\"]/mat-label")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button/span[2][text()='Login']")).click();
        Thread.sleep(5000);
        driver.close();
    }
}

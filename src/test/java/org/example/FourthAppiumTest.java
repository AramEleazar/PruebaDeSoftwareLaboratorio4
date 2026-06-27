package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class FourthAppiumTest {
    AndroidDriver driver;
    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "HONOR X5b Plus");
        capabilities.setCapability("appium:platformVersion", "14");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability(
                "appium:app",
                "D:/software/proyecto2/src/test/java/app/Android.SauceLabs.Mobile.Sample.app.2.7.0.apk"
        );
        capabilities.setCapability("appium:noReset", true); //true evita reinstalar
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @Test(priority = 1)
    public void  loginSuccess() throws InterruptedException {

        sleep(1500);
        WebElement usernameField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Usuario\"]"));
        usernameField.sendKeys("standard_user");

        WebElement passField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Contraseña\"]"));
        passField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]"));
        loginButton.click();

        sleep(1500);
        WebElement textLabel = driver.findElement(By.xpath("//android.widget.TextView[@text=\"PRODUCTOS\"]"));
        String texto = textLabel.getText();
        System.out.println("Texto obtenido ingresar al app : "+texto);
        //Asserting the text as expected
        Assert.assertEquals(texto, "PRODUCTOS");
    }

    @Test(priority = 2)
    public void AddProductToCartSuccess() throws InterruptedException {

        sleep(1500);
        WebElement AddCarrito = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"AÑADIR A CARRITO\"])[1]"));
        AddCarrito.click();
        sleep(500);
        WebElement textLabel = driver.findElement(By.xpath("//android.widget.TextView[@text=\"REMOVER\"]"));
        String texto = textLabel.getText();
        System.out.println("Texto obtenido del botón en el app : "+texto);
        //Asserting the text as expected
        Assert.assertEquals(texto, "REMOVER");
    }

    @Test(priority = 3)
    public void TuCarritoScreenSuccess() throws InterruptedException {

        sleep(500);
        WebElement IconCarrito = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Carrito\"]/android.view.ViewGroup/android.widget.ImageView"));
        IconCarrito.click();
        sleep(500);
        WebElement textLabel = driver.findElement(By.xpath("//android.widget.TextView[@text=\"TU CARRITO\"]"));
        String texto = textLabel.getText();
        System.out.println("Texto obtenido del app : "+texto);
        //Asserting the text as expected
        Assert.assertEquals(texto, "TU CARRITO");

        WebElement textLabel2 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]"));
        String texto2 = textLabel2.getText();
        System.out.println("Texto obtenido del app : "+texto2);
        //Asserting the text as expected
        Assert.assertEquals(texto2, "CHECKOUT");
    }

    @Test(priority = 4)
    public void RemoverProductosNoSuccess() throws InterruptedException {

        sleep(500);
        WebElement btnRemover = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-REMOVER\"]"));
        btnRemover.click();

        sleep(500);
        WebElement btnSeguirComprando = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-CONTINUAR COMPRANDO\"]"));
        btnSeguirComprando.click();

        sleep(500);
        WebElement textLabel = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"AÑADIR A CARRITO\"])[1]"));
        String texto = textLabel.getText();
        System.out.println("Texto obtenido del botón en el app : "+texto);
        //Asserting the text as expected
        Assert.assertEquals(texto, "AÑADIR A CARRITO");

    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


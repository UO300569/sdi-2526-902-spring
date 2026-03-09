package com.uniovi.sdi.sdi2526902spring;

import com.uniovi.sdi.sdi2526902spring.pageobjects.*;
import com.uniovi.sdi.sdi2526902spring.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotaneitorComplementariosTests {
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.36.0-win64.exe";
    static String Geckodriver = "C:\\Users\\david\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver.exe";

    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
    // Para la versión de Firefox 121 en adelante la ruta de firefo en MAC es
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox";

    //Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @Test
    @Order(1)
    public void PR01() {
        PO_HomeView.clickOption(driver, "identificarse", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");

        PO_PrivateView.clickDepartmentsMenu(driver);
        PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'department/add')]").get(0).click();


        PO_PrivateView.fillFormAddDepartment(driver, "INFO-120A", "Departamento de Informática", "EII", "985123456", 0);
        String checkText = "INFO-120A";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.getFirst().getText());

        PO_PrivateView.deleteDepartmentByCode(driver, "INFO-120A");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "identificarse", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");

        PO_PrivateView.clickDepartmentsMenu(driver);
        PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'department/add')]").getFirst().click();

        PO_PrivateView.fillFormAddDepartment(driver, "INVALID", "", "EII", "985123456", 0);

        String checkText = "La longitud del código tiene que ser de 9 caracteres.";
        List<WebElement> errores = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertFalse(errores.isEmpty(), "El mensaje de error no apareció en la pantalla");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(3)
    public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");

        PO_PrivateView.clickDepartmentsMenu(driver);

        List<WebElement> addLinks = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'department/add')]");
        Assertions.assertEquals(0, addLinks.size());

        driver.navigate().to(URL + "/department/add");


        String currentUrl = driver.getCurrentUrl();
        Assertions.assertFalse(currentUrl.contains("department/add"));

        PO_LoginView.logOut(driver);
    }



    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }



}
package com.uniovi.sdi.sdi2526902spring;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GradeManagerApplicationTests {
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
    void PR01() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(2)
    void PR02() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(3)
    void PR03() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(4)
    void PR04() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(5)
    void PR05() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(6)
    void PR06() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(7)
    void PR07() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(8)
    void PR08() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(9)
    void PR09() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(3)
    void PR10() {
        Assertions.assertTrue(true);
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }
}
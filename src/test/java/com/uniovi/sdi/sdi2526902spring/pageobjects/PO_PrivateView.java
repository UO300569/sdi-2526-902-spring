package com.uniovi.sdi.sdi2526902spring.pageobjects;

import com.uniovi.sdi.sdi2526902spring.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumno userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void fillFormAddDepartment(WebDriver driver, String code, String name, String faculty,
                                             String telephone, int numberProfessors)
    {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);

        // Código
        WebElement codeW = driver.findElement(By.name("code"));
        codeW.clear();
        codeW.sendKeys(code);

        // Nombre
        WebElement nameW = driver.findElement(By.name("name"));
        nameW.clear();
        nameW.sendKeys(name);

        // Facultad
        WebElement facultyW = driver.findElement(By.name("faculty"));
        facultyW.clear();
        facultyW.sendKeys(faculty);

        // Teléfono
        WebElement telephoneW = driver.findElement(By.name("telephone"));
        telephoneW.clear();
        telephoneW.sendKeys(telephone);

        // Nombre
        WebElement professorsW = driver.findElement(By.name("professors"));
        professorsW.clear();
        professorsW.sendKeys(String.valueOf(numberProfessors));

        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void clickMarksMenu(WebDriver driver) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='myNavbar']/ul[1]/li[2]");
        elements.get(0).click();
    }

    static public void clickDepartmentsMenu(WebDriver driver) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[@id='navbarDropdown2']");
        if (!elements.isEmpty()) {
            elements.get(0).click();
        } else {
            throw new RuntimeException("No se encontró el menú de departamentos");
        }
    }

    static public void goToLastPage(WebDriver driver) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.get(elements.size() - 1).click();
    }

    static public void clickDeleteMarkByText(WebDriver driver, String text) {
        String xpath = "//td[contains(text(), '" + text + "')]/following-sibling::*/a[contains(@href, 'mark/delete')]";
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", xpath);
        elements.get(0).click();
    }

    static public void deleteDepartmentByCode(WebDriver driver, String code) {
        String xpath = "//tr[contains(., '" + code + "')]//a[contains(@href, 'department/remove')]";
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", xpath);

        if (!elements.isEmpty()) {
            elements.get(0).click();
        } else {
            System.out.println("No se encontró el botón de eliminar para el código: " + code);
        }
    }
}

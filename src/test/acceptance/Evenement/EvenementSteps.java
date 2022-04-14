package test.acceptance.Evenement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvenementSteps {

    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver", "/Library/Java/JUNIT/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Given("^Je suis sur la page d'accueil \"([^\"]*)\" en passant par le burger menu je peut cliquer sur le bouton \"([^\"]*)\" qui m'amène sur cette page \"([^\"]*)\"$")
    public void je_suis_sur_la_page_d_accueil_en_passant_par_le_burger_menu_je_peut_cliquer_sur_le_bouton_qui_m_amène_sur_cette_page(String arg1, String arg2, String arg3) throws Throwable {
        driver.get(arg1);
        WebElement menu = driver.findElement(By.cssSelector("button[title*='Menu']"));
        menu.click();
        Thread.sleep(1000);
        WebElement lien = driver.findElement(By.cssSelector("ol[class*=nav-items--]"));
        WebElement lienClick = lien.findElement(By.cssSelector("li a[title*=" + arg2 + "]"));
        assertEquals(arg3, lienClick.getAttribute("href"));
        Thread.sleep(1000);
        lienClick.click();
    }

    @Then("^La page contient \"([^\"]*)\" Evenements différents$")
    public void la_page_contient_Evenements_différents(String arg1) throws Throwable {
        List<WebElement> evenements = driver.findElements(By.cssSelector("div[class*=views-row]"));
        assertEquals(evenements.size(), Integer.parseInt(arg1));

    }

    @Then("^Lorsque je cherche un événement à \"([^\"]*)\" le premier résultat de recherche indique un événement localisé à \"([^\"]*)\"$")
    public void lorsque_je_cherche_un_événement_à_le_premier_résultat_de_recherche_indique_un_événement_localisé_à(String arg1, String arg2) throws Throwable {
        WebElement input = driver.findElement(By.cssSelector("input[name='geocode']"));
        input.clear();
        Thread.sleep(1000);
        input.sendKeys(arg1);
        WebElement loupe = driver.findElement(By.cssSelector("input[name='loupe']"));
        Thread.sleep(1000);
        input.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        loupe.click();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        List<WebElement> localisations = driver.findElements(By.cssSelector("div[class*=group-left] div[class*=location-teaser]"));
        boolean locationValide = false;
        for (int i = 0; i < localisations.size(); i++) {
            if (localisations.get(i).getAttribute("innerHTML").contains(arg2)){
                locationValide = true;
            }
        }
        assertTrue(locationValide);
    }

    @Then("^Je souhaite m'inscrire à un événement qui aura lieu en France,je fais une recherche pour la \"([^\"]*)\" puis je clic sur le lien Informations d'un événement\\. Je suis alors redirigé vers la page \"([^\"]*)\" de l'événement \"([^\"]*)\"$")
    public void je_souhaite_m_inscrire_à_un_événement_qui_aura_lieu_en_France_je_fais_une_recherche_pour_la_puis_je_clic_sur_le_lien_Informations_d_un_événement_Je_suis_alors_redirigé_vers_la_page_de_l_événement(String arg1, String arg2, String arg3) throws Throwable {

        WebElement input = driver.findElement(By.cssSelector("input[name='geocode']"));
        input.clear();
        Thread.sleep(1000);
        input.sendKeys(arg1);
        WebElement loupe = driver.findElement(By.cssSelector("input[name='loupe']"));
        Thread.sleep(1000);
        input.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        loupe.click();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        List<WebElement> evenements = driver.findElements(By.cssSelector("div.content h2 a"));
        for (int i = 0; i < evenements.size(); i++) {
            if (evenements.get(i).getText().equals(arg2)){
                assertEquals(arg3, evenements.get(i).getAttribute("href"));
                evenements.get(i).click();
            }
        }
    }



    @After
    public void afterScenario() {
        driver.quit();
    }

}

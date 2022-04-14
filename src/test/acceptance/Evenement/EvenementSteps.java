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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.crypto.spec.PSource;
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
        input.sendKeys("");
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
        input.sendKeys("");
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



    @Then("^Un formulaire d'inscription est disponible avec les champs \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" et un bouton d'envoi \"([^\"]*)\" Lorsque je remplis le formulaire sans l'email et que je le valide un message rouge apparait sous le champ de l'email indiquant \"([^\"]*)\"$")
    public void un_formulaire_d_inscription_est_disponible_avec_les_champs_et_un_bouton_d_envoi_Lorsque_je_remplis_le_formulaire_sans_l_email_et_que_je_le_valide_un_message_rouge_apparait_sous_le_champ_de_l_email_indiquant(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) throws Throwable {
        List<WebElement> allLabel = driver.findElements(By.cssSelector("div[class*=form-item] label"));
        String[] allArg = {arg1, arg2, arg3, arg4, arg5, arg6, arg7};
        int nbChamps = 0;
        for (int i = 0; i < allLabel.size(); i++) {
            Actions actions = new Actions(driver);
            actions.moveToElement(allLabel.get(i));
            actions.perform();
            switch (allLabel.get(i).getText()) {
                case "Prénom *" -> {
                    WebElement input = driver.findElement(By.cssSelector("div[class*=form-item] div input[name*='firstname']"));
                    input.sendKeys(" ");
                    input.clear();
                    input.sendKeys("André");
                    Thread.sleep(500);
                }
                case "Nom *" -> {
                    WebElement input = driver.findElement(By.cssSelector("div[class*=form-item] div input[name*='lastname']"));
                    input.sendKeys(" ");
                    input.clear();
                    input.sendKeys("Desousa");
                    Thread.sleep(500);
                }
                case "Téléphone" -> {
                    WebElement input = driver.findElement(By.cssSelector("div[class*=form-item] div input[name*='phonenumber']"));
                    input.sendKeys(" ");
                    input.clear();
                    input.sendKeys("+33 06 73 82 91 01");
                    Thread.sleep(500);
                }
                case "Code postal" -> {
                    WebElement input = driver.findElement(By.cssSelector("div[class*=form-item] div input[name*='zipcode']"));
                    input.sendKeys(" ");
                    input.clear();
                    input.sendKeys("75017");
                    Thread.sleep(500);
                }
                case "Recevoir les News Tesla" -> {
                    allLabel.get(i).click();
                    Thread.sleep(1000);
                }
                default -> {
                }
            }
            for (int j = 0; j < allArg.length; j++) {
                if (allLabel.get(i).getText().contains(allArg[j])){
                    nbChamps += 1;
                }
            }
        }
        WebElement button = driver.findElement(By.cssSelector("div[class*=action-btn] input[name*=ajax]"));
        assertEquals(arg8, button.getAttribute("value"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button);
        actions.perform();
        button.click();
        assertEquals(7, nbChamps);

        WebElement error = driver.findElement(By.cssSelector("ul[id*=parsley-id-89] li"));
        assertEquals(arg9 ,error.getText());
        assertEquals(error.getCssValue("color"), "rgba(183, 65, 52, 1)");


    }

    /*@Then("^Lorsque je remplis le formulaire sans l'email et que je le valide un message rouge apparait sous le champ de l'email indiquant \"([^\"]*)\"$")
    public void lorsque_je_remplis_le_formulaire_sans_l_email_et_que_je_le_valide_un_message_rouge_apparait_sous_le_champ_de_l_email_indiquant(String arg1) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)", "");
        Thread.sleep(2000);
        List<WebElement> allLabel = driver.findElements(By.cssSelector("div[class*=form-item] label"));
        List<WebElement> allInput = driver.findElements(By.cssSelector("div[class*=form-item] div input"));
        for (int i = 0; i < allLabel.size(); i++) {
            Actions actions = new Actions(driver);
            actions.moveToElement(allLabel.get(i));
            actions.perform();
            System.out.println(i + " - label -" + allLabel.get(i).getText());
        }

        for (int i = 0; i < allLabel.size(); i++) {
            switch (allLabel.get(i).getText()) {
                case "Prénom * " -> {
                    allInput.get(i).sendKeys(" ");
                    allInput.get(i).clear();
                    allInput.get(i).sendKeys("André");
                }
                case "Nom * " -> {
                    allInput.get(i).sendKeys(" ");
                    allInput.get(i).clear();
                    allInput.get(i).sendKeys("Desousa");
                }
                case "Téléphone " -> {
                    allInput.get(i).sendKeys(" ");
                    allInput.get(i).clear();
                    allInput.get(i).sendKeys("+33 06 73 82 91 01");
                }
                case "Code postal " -> {
                    allInput.get(i).sendKeys(" ");
                    allInput.get(i).clear();
                    allInput.get(i).sendKeys("75017");
                }
                case "Recevoir les News Tesla " -> allLabel.get(i).click();
                default -> {
                }
            }
        }
        WebElement button = driver.findElement(By.cssSelector("div[class*=action-btn] input"));
        button.click();
        Thread.sleep(10000);
    }*/




    @After
    public void afterScenario() {
        driver.quit();
    }

}

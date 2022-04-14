package test.acceptance.Configurateur;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;



import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ConfigurateurSteps {

    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
        driver = new ChromeDriver();
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Given("^Je suis sur la page \"([^\"]*)\"$")
    public void je_suis_sur_la_page(String arg1) throws Throwable {
        driver.get(arg1);
    }


    @Then("^Quand je clique sur le bouton commander je doit arriver sur la page \"([^\"]*)\"$")
    public void quand_je_clique_sur_le_bouton_commander_je_doit_arriver_sur_la_page(String arg1) throws Throwable {
        WebElement bouton = driver.findElement(By.cssSelector("a[title*=Commander]"));
        assertEquals(arg1, bouton.getAttribute("href"));
    }


    @Then("^Pour connaitre la liste des localisations de vente je vais en bas de la page puis je clique clique sur le bouton Locations qui doit m'amener sur la page \"([^\"]*)\"$")
    public void pour_connaitre_la_liste_des_localisations_de_vente_je_vais_en_bas_de_la_page_puis_je_clique_clique_sur_le_bouton_Locations_qui_doit_m_amener_sur_la_page(String arg1) throws Throwable {
        List<WebElement> localisations = driver.findElements(By.cssSelector("li[class*='hideon-phone'] a[class*=ds-link]"));
        for (int i = 0; i < localisations.size(); i++) {
            if (localisations.get(i).getText().equals("Locations")){
                assertEquals(arg1, localisations.get(i).getAttribute("href"));
                i = localisations.size();
            }
        }
    }


    @Then("^Par défaut le prix affiché est un prix \"([^\"]*)\" à \"([^\"]*)\"$")
    public void par_défaut_le_prix_affiché_est_un_prix_à(String arg1, String arg2) throws Throwable {
        WebElement prixType = driver.findElement(By.cssSelector("span[class*='pricing-label']"));
        WebElement prix = driver.findElement(By.cssSelector("span[class*='finance-type']"));
        assertTrue(prixType.getText().contains(arg1));
        assertTrue(prix.getText().contains(arg2));
    }


    @Then("^Quand je sélectionne l'option Capacité de conduite entièrement autonome, le prix augmente de \"([^\"]*)\"$")
    public void quand_je_sélectionne_l_option_Capacité_de_conduite_entièrement_autonome_le_prix_augmente_de(String arg1) throws Throwable {
        List<WebElement> allAjouter = driver.findElements(By.cssSelector("button[aria-label*='Ajouter cette option']"));
        WebElement ajouter = driver.findElement(By.cssSelector("button[aria-label*='Ajouter cette option']"));

        for (int i = 0; i < allAjouter.size(); i++) {
            if (allAjouter.get(i).getAttribute("aria-label").contains("Capacité de conduite entièrement autonome")){
                ajouter = allAjouter.get(i);
            }
        }
        String prix = driver.findElement(By.cssSelector("span[class*='finance-type']")).getText();
        ajouter.click();

        prix = prix.replaceAll("[^\\d]", " ");
        prix = prix.trim();
        Float ancienPrix = Float.valueOf(prix.replaceAll(" ", "."));
        prix = driver.findElement(By.cssSelector("span[class*='finance-type']")).getText();
        prix = prix.replaceAll("[^\\d]", " ");
        prix = prix.trim();
        Float nouveauPrix = Float.valueOf(prix.replaceAll(" ", "."));

        float soustraction = nouveauPrix - ancienPrix;
        soustraction = soustraction * 100;
        soustraction = Math.round(soustraction);
        assertEquals(Integer.valueOf((int) (Float.valueOf(arg1) * 100)), Integer.valueOf((int) soustraction));
    }







    @After
    public void afterScenario() {
        driver.quit();
    }

}

package test.acceptance.Homepage;

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


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class HomepageSteps {

	public static WebDriver driver;

	@Before
	public void beforeScenario() {
		System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
		driver = new ChromeDriver();
		// Seems no more working in last Chrome versions
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}


	@Given("^je suis sur la homepage \"([^\"]*)\"$")
	public void je_suis_sur_la_homepage(String arg1) throws Throwable {
		driver.get(arg1);
	}


	@Then("^le titre doit être \"([^\"]*)\"$")
	public void le_titre_doit_être(String arg1) throws Throwable {
		String titre = driver.findElement(By.cssSelector("meta[property*='title']")).getAttribute("content");
		assertEquals(arg1, titre);
	}


	@Then("^la description doit être \"([^\"]*)\"$")
	public void la_description_doit_être(String arg1) throws Throwable {
		String description = driver.findElement(By.cssSelector("meta[property*='description']")).getAttribute("content");
		assertEquals(arg1, description);
	}



	@Then("^il existe cinq titres importants qui doivent être \"([^\"]*)\"$")
	public void il_existe_cinq_titres_importants_qui_doivent_être(String arg1) throws Throwable {
		String[] args = arg1.split("-");
		List<WebElement> titres = driver.findElements(By.cssSelector("div[class*=homepage] h1[class*=homepage]"));
		int nbTitre = 0;

		for (int i = 1; i < args.length; i++) {
			if(args[i].equals(titres.get(i).getAttribute("innerHTML").strip())){
				nbTitre += 1;
			}
		}
		assertEquals(5, nbTitre);
	}


	@Then("^les liens du menu mènent vers ces liens \"([^\"]*)\"$")
	public void les_liens_du_menu_mènent_vers_ces_liens(String arg1) throws Throwable {
		String[] args = arg1.split("-");
		List<WebElement> liens = driver.findElements(By.cssSelector("li a[class*=nav-item]"));
		for (int i = 0; i < args.length; i++) {
			assertEquals((args[i]), (liens.get(i).getAttribute("href")));
		}
	}

	@Then("^dans le burger menu de droite ces éléments sont des liens \"([^\"]*)\"$")
	public void dans_le_burger_menu_de_droite_ces_éléments_sont_des_liens(String arg1) throws Throwable {
		String[] args = arg1.split("-");
		WebElement menu = driver.findElement(By.cssSelector("button[title*='Menu']"));
		menu.click();
		WebElement lien = driver.findElement(By.cssSelector("ol[class*=nav-items--]"));
		List<WebElement> liens = lien.findElements(By.cssSelector("li a"));
		for (int i = 0; i < args.length; i++) {
			assertEquals(args[i], liens.get(i).getAttribute("title"));
		}

	}



	@After
	public void afterScenario() {
		driver.quit();
	}

}

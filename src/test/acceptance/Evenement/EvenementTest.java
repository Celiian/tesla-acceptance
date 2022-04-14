package test.acceptance.Evenement;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"../src/test/acceptance/Configurateur"}, // ou se situe votre fichier .feature
        plugin = {"pretty"}
)
public class EvenementTest {

}
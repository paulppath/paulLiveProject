package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.BrowserUtils;

public class HomeSteps
{
    HomePage page;
    public HomeSteps()
    {
        page = new HomePage();
    }

//    @Then("verify {string} text is displayed")
//    public void verifyTextIsDisplayed(String arg0)
//    {
//        System.out.println("test");
//    }
    @Then("verify {string} text is displayed")
    public void verify_text_is_displayed(String text)
    {
        switch(text.toLowerCase())
        {
            case "10090 main street":
                BrowserUtils.isDisplayed(page.address.get(0));
                BrowserUtils.assertEquals(page.address.get(0).getText(), text);
                break;
            case "fairfax, va, usa":
                BrowserUtils.isDisplayed(page.address.get(1));
                BrowserUtils.assertEquals(page.address.get(1).getText(), text);
                break;
            case "703-831-3217":
                BrowserUtils.isDisplayed(page.phoneNumber);
                BrowserUtils.assertEquals(page.phoneNumber.getText(), text);
                break;
            default:
                Assert.fail("Invalid Text!");
        }

    }

    @And("verify that text is contain {string}")
    public void verifyThatTextIsContain(String text)
    {
        BrowserUtils.isDisplayed(page.phoneNumber);
        BrowserUtils.assertEquals(page.phoneNumber.getText(), "+1 " + text);
    }

    @Then("Verify the title is {string}")
    public void verifyTheTitleIs(String title)
    {
        String actualTitle = BrowserUtils.getDriver().getTitle();
        BrowserUtils.assertEquals(actualTitle, title);
    }
}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePage
{
    public HomePage() { PageFactory.initElements(BrowserUtils.getDriver(), this);}

    @FindBy(xpath = "//div[@class='info-box-one']/ul/li")
    public List<WebElement> address = new ArrayList<>();

    @FindBy(xpath = "//*[contains(text(),'Quick Contact:')]/following::li")
    public WebElement phoneNumber;


}

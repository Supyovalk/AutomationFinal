package tutorialNinjaPOM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends HeaderWrapperPage {
	@FindBy(css="#content>h1")
	protected WebElement mainError;
	@FindBy(css="div.pull-right>a")
	protected WebElement buttonContinue;
	public ErrorPage(WebDriver driver) {
		super(driver);
	}
	public String getErrorMain() { 
		return mainError.getText();
	}
	public void pressContinue() { 
		click(buttonContinue);
	}
}

package tutorialNinjaPOM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HeaderWrapperPage {
	@FindBy(css=".btn-primary")
	protected WebElement registerButton;
	@FindBy(css="input[value='Login']")
	protected WebElement loginButton;
	@FindBy(css="#input-email")
	protected WebElement loginEmailBox;
	@FindBy(css="#input-password")
	protected WebElement loginPasswordBox;
	@FindBy(css=".alert-danger")
	protected WebElement errorMessage;
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public void pressRegisterButton() {
		click(registerButton);
	}
	public void logOn(String email,String password) {
		fillElement(loginEmailBox,email);
		fillElement(loginPasswordBox,password);
		click(loginButton);
	}
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	public void urlNavigate() {
		super.urlNavigate("https://tutorialsninja.com/demo/index.php?route=account/login");
	}
}

package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

public class RegisterPage extends HeaderWrapperPage {
	@FindBy(css="#input-firstname")
	protected WebElement firstNameField;
	@FindBy(css="#input-lastname")
	protected WebElement lastNameField;
	@FindBy(css="#input-email")
	protected WebElement emailField;
	@FindBy(css="#input-password")
	protected WebElement passwordField;
	@FindBy(css="#input-confirm")
	protected WebElement passwordConfirmField;
	@FindBy(css="#input-telephone")
	protected WebElement telephoneField;
	@FindBy(css=".radio-inline>input")
	protected List<WebElement> newsletterRadios;
	@FindBy(css="[name='agree']")
	protected WebElement policyButton;
	@FindBy(css="input.btn-primary")
	protected WebElement registerButton;
	@FindAll({@FindBy(css="div>.text-danger"),@FindBy(css=".alert-danger")})
	protected List<WebElement> fieldErrorMessages;
	@FindBy(css="#content>h1")
	protected WebElement sucessfulRegisterContext;
	@FindBy(css="div.pull-right>a.btn")
	protected WebElement sucessfulRegisterContiune;
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	public void urlNavigate() {
		super.urlNavigate("https://tutorialsninja.com/demo/index.php?route=account/register");
	}
	public void enterRegisterValues(String firstName,String lastName,String email,String telephone,String password,String confirmPassword,boolean newsLetter) {
		fillElement(firstNameField,firstName);
		fillElement(lastNameField,lastName);
		fillElement(emailField,email);
		fillElement(telephoneField,telephone);
		fillElement(passwordField,password);
		fillElement(passwordConfirmField,confirmPassword);
		for(WebElement radio: newsletterRadios) {
			if((radio.getAttribute("value")=="1")==newsLetter) {
				click(radio);
			}
		}
	}
	public boolean isPolicySelected() {
		return policyButton.isSelected();
	}
	public void clickRegister() {
		clickRegister(true);
	}
	public void clickRegister(boolean selectConfirm) {
		if(isPolicySelected()!=selectConfirm) click(policyButton);
		click(registerButton);
	}
	public boolean hasFieldError(String text) {
		for(WebElement el:fieldErrorMessages) {
			String messageFull=el.getText().strip();
			String message;
			if(messageFull.indexOf("\n")==-1) message=messageFull;
			else {
				message=messageFull.substring(0,messageFull.indexOf("\n"));
			}
			if(message.equals(text)) {
				return true;
			}
		}
		return false;
	}
	public void doFullRegister(String firstName,String lastName,String email,String telephone,String password,String confirmPassword,boolean newsLetter) {
		doFullRegister(firstName, lastName,email,telephone,password,confirmPassword,newsLetter,true);
	}
	public void doFullRegister(String firstName,String lastName,String email,String telephone,String password,String confirmPassword,boolean newsLetter,boolean selectConfirm) {
		enterRegisterValues(firstName, lastName,email,telephone,password,confirmPassword,newsLetter);
		clickRegister(selectConfirm);
	}
	public boolean hasSuccessfulRegistered() {
		return sucessfulRegisterContext!=null;
	} 
	public void finishsucessfulRegister() {
		click(sucessfulRegisterContiune);
	}
}

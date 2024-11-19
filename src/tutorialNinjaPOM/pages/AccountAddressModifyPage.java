package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountAddressModifyPage extends HeaderWrapperPage {
	@FindBy(css="#input-firstname")
	protected WebElement firstNameField;
	@FindBy(css="#input-lastname")
	protected WebElement lastNameField;
	@FindBy(css="#input-company")
	protected WebElement companyField;
	@FindBy(css="#input-address-1")
	protected WebElement firstAddressField;
	@FindBy(css="#input-address-2")
	protected WebElement secondAddressField;
	@FindBy(css="#input-city")
	protected WebElement cityField;
	@FindBy(css="#input-postcode")
	protected WebElement postcodeField;
	@FindBy(css="#input-country")
	protected WebElement countryField;//Select
	@FindBy(css="#input-zone")
	protected WebElement regionField; //Select
	@FindBy(css=".col-sm-10>label")
	protected List<WebElement> defaultAddressButtons;
	@FindBy(css="input[type='submit']")
	protected WebElement ContiuneButton;
	public AccountAddressModifyPage(WebDriver driver) {
		super(driver);
	}
	public void pressContinue(){
		click(ContiuneButton);
	}
	public void doFullmodifyTask() {
		doFullmodifyTask("D","FAULT","COMPANY","ADDRESS1","ADDRESS2","NETANYA","1234567","Israel","Haifa",true);
	}
	public void doFullmodifyTask(String firstName,String lastName,String company,String firstAddress,String secondAddress,String city,String postcode,String country,String region,boolean is_default) {
		fillElement(firstNameField,firstName);
		fillElement(lastNameField,lastName);
		fillElement(companyField,company);
		fillElement(firstAddressField,firstAddress);
		fillElement(secondAddressField,secondAddress);
		fillElement(cityField,city);
		fillElement(postcodeField,postcode);
		selectChoiceByText(countryField,country);
		waitSeconds(2);
		selectChoiceByText(regionField,region);
		for(WebElement radio: defaultAddressButtons) {
			if((radio.getAttribute("value")=="1")==is_default) {
				click(radio);
			}
		}
		pressContinue();	
	}
}

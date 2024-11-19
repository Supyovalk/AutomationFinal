package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends HeaderWrapperPage {
	public enum AccountOption{
		EDIT_ACCOUNTINFO,
		CHANGE_PASSWORD,
		CHANGE_ADDRESSBOOK,
		CHANGE_WISHLIST,
		VIEW_ORDERHISTORY,
		VIEW_DOWNLOADS,
		VIEW_REWARDPOINTS,
		VIEW_RETURNREQUESTS,
		VIEW_TRANSACTIONS,
		VIEW_RECCURINGPAYMENTS,
		REGISTER_AFFILIATE,
		SUBSCRIBE_NEWSLETTER;
	}
	@FindBy(css="#content>ul>li>a")
	protected List<WebElement> accountOptions;
	public AccountPage(WebDriver driver) {
		super(driver);
	}
	public void pressAccountOption(String text) {
		WebElement matchedOption= elementListSearchText(accountOptions,text);
		click(matchedOption);
	}
	public void pressAccountOption(AccountOption option) {
		switch(option) {
		case CHANGE_WISHLIST:
			pressAccountOption("Modify your wish list");
		case CHANGE_ADDRESSBOOK:
			pressAccountOption("Modify your address book entries");
		default:
			break;
		}
	}
}

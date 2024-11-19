package tutorialNinjaPOM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import tutorialNinjaPOM.pages.AccountAddressEntryPage;
import tutorialNinjaPOM.pages.AccountAddressModifyPage;
import tutorialNinjaPOM.pages.AccountPage;
import tutorialNinjaPOM.pages.AccountPage.AccountOption;
import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;
public class AccountTests extends BaseDriverTest {
	@Override
	@BeforeClass
	public void setUp(){
		super.setUp();
	    homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		accountPage=new AccountPage(driver);
		addressEntryPage=new AccountAddressEntryPage(driver);
		addressModifyPage=new AccountAddressModifyPage(driver);
		
	    homePage.urlSessionRestart();
	    homePage.toggleAccountDropdown();
	    homePage.pressAccountDropdownLogin();
	    loginPage.logOn("SupyovalkTest1@gmail.com", "1111");
	}
	@Test()
	public void tc01_CheckwishList(){
		homePage.pressFooterMyAccount();
		accountPage.pressAccountOption(AccountOption.CHANGE_ADDRESSBOOK);
		addressEntryPage.pressAddAddress();
		addressModifyPage.doFullmodifyTask();
		Assert.assertNotNull(addressEntryPage.getAddressInfo("D FAULT"));
	}
}

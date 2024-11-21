package tutorialNinjaPOM.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;
import tutorialNinjaPOM.pages.RegisterPage;

public class RegisterLoginTests extends BaseDriverTest {
	//Test's Start Position
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected RegisterPage registerPage;
	protected static final String REGISTERED_EMAIL = "SupyovalkTest1@gmail.com";
	@Override
	@BeforeClass
	public void setUp(){
		super.setUp();
		
	    homePage.urlSessionRestart();	    
	}
	public void setToRegisterPage(){
		homePage.toggleAccountDropdown();
		homePage.pressAccountDropdownRegister();
	}
	public void setToLoginPage(){
		System.out.println("Attempt");
		homePage.toggleAccountDropdown();
		homePage.pressAccountDropdownLogin();
	}
	@Test(dataProvider="getUnloggedInassablePages")
	public void tc01_UnloggedInasseablePages(String url,String redirectURL) {
		homePage.urlNavigate(url);
		Assert.assertEquals(driver.getCurrentUrl(), redirectURL);
	}
	@Test(dataProvider="getInvaildLogins")
	public void tc02_InvaildLogin(String email,String password) {
		loginPage.logOn(email, password);
		Assert.assertEquals(loginPage.getErrorMessage(), "Warning: No match for E-Mail Address and/or Password.");
	}
	@Test(dataProvider="getInvalidRegisters")
	public void tc03_InvaildRegister(String firstName,String lastName,String email,String telephone,String password,String confirmPassword,boolean newsLetter,boolean privacy,String errorMessage) {
		registerPage.doFullRegister(firstName, lastName, email, telephone, password, confirmPassword, newsLetter,privacy);
		Assert.assertTrue(registerPage.hasFieldError(errorMessage), "Unfound Error Message");
	}
	@Test(dataProvider="getVaildLogins")
	public void tc04_VaildLogin(String email,String password) {
		loginPage.logOn(email, password);
		Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/account");
	}
	@Test
	public void tc05_LoginURLRedirectedWhenLoggedState() {
		loginPage.urlNavigate();
		Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/account");
	}
	@Test
	public void tc06_AddItemUnlogged() {
		String item="MacBook";
		homePage.toggleAccountDropdown();
		homePage.pressAccountDropdownLogout();
		homePage.urlNavigate();
		homePage.cartAddThumb(item);
		homePage.toggleCartBurger();
		Assert.assertTrue(homePage.BurgerItemContains(item));
		homePage.deleteItem(item);
	}
	@Test
	public void tc07_VaildRegister() {
		setToRegisterPage();
		Random ran = new Random();
		int x = ran.nextInt(Integer.MAX_VALUE);
		registerPage.doFullRegister("Ran", "dom", "SupyovalkRandom"+x+"@gmail.com", "0001111", "1111", "1111", false,true);
		Assert.assertTrue(registerPage.hasSuccessfulRegistered());
		registerPage.finishsucessfulRegister();
		Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/account");
	} 
	
	@DataProvider
	public Object[] getUnloggedInassablePages() {
		Object[][] data = {
				{"https://tutorialsninja.com/demo/index.php?route=account/wishlist","https://tutorialsninja.com/demo/index.php?route=account/login"},
		};
		return data;
	}
	@DataProvider
	public Object[] getVaildLogins() {
		setToLoginPage();
		Object[][] data = {
				{REGISTERED_EMAIL,"1111"},
		};
		return data;
	} 
	@DataProvider
	public Object[] getInvalidRegisters() {
		setToRegisterPage();
		String email="Supyovalktestb@gmail.com";
		Object[][] data = {
				{"Test","ShortPassword",email,"0555555555","a","a",true,true,"Password must be between 4 and 20 characters!"},
				//{"Test","OverlongPassword",email,"0555555555","12345678901234567890a","12345678901234567890a",true,true,"Password must be between 4 and 20 characters!"},  //BUG, Fails
				{"Test","UnmatchingConfirmpasswordLength",email,"0555555555","123456","12345",true,true,"Password confirmation does not match password!"},  
				{"Test","UnmatchingConfirmpasswordContent",email,"0555555555","12345","12346",true,true,"Password confirmation does not match password!"}, 
				{"Test","NoPoliacy",email,"0555555555","123456","12346",true,false,"Warning: You must agree to the Privacy Policy!"}, 
				{"Test","EmailPreregister",REGISTERED_EMAIL,"0555555555","123456","12346",true,true,"Warning: E-Mail Address is already registered!"}
		};
		return data;
	}
	@DataProvider
	public Object[] getInvaildLogins() {
		setToLoginPage(); //Run only once for all 4 sub-tests
		Object[][] data = {
				{"",""},
				{"Supyovalk@gmail.com","11111"},
				{"NonMatchingEmail@gmai.com","1111"},
				{" SupyovalkTest1@gmail.com ","1111"},
		};
		return data;
	}
}

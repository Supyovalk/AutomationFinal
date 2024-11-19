package tutorialNinjaPOM.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;
import tutorialNinjaPOM.pages.SearchPage;

public class CartBurgerTests extends BaseDriverTest {
	static final String goldilock_product="iPod Touch"; //The only Product in the website in stock that can be easily added to cart (no direct to product page)
	@Override
	@BeforeClass
	public void setUp(){
		super.setUp();
	    homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		searchPage=new SearchPage(driver);
		
	    homePage.urlSessionRestart();
	    homePage.toggleAccountDropdown();
	    homePage.pressAccountDropdownLogin();
	    loginPage.logOn("SupyovalkTest1@gmail.com", "1111");
	    homePage.deleteAllItems();
	    homePage.search(goldilock_product);
	    searchPage.cartAddThumb(goldilock_product);
	}
	@Test
	public void tc01_correctLinkCart() {
		homePage.toggleCartBurger();
		Assert.assertTrue(homePage.BurgerItemContains(goldilock_product));
		homePage.pressCartBurgerCartLink();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=checkout/cart");
	}
	@Test
	public void tc02_correctLinkCheckout() {
		homePage.toggleCartBurger();
		Assert.assertTrue(homePage.BurgerItemContains(goldilock_product));
		homePage.pressCartBurgerCheckoutLink();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=checkout/checkout");
	}
	@Test
	public void tc03_removeLastItem() {
		homePage.toggleCartBurger();
		homePage.deleteItem(goldilock_product);
		Assert.assertFalse(homePage.BurgerItemContains(goldilock_product));
	}
	@Test
	public void tc04_BurgerAddSimpleEchoCheck() {
		final String[] simpleItems= {"iMac","MacBook","Samsung Galaxy Tab 10.1"};
		homePage.urlNavigate();
		homePage.search("a");
		for(int i=0;i<simpleItems.length;i++) {
			searchPage.cartAddThumb(2,simpleItems[i]);
		}
		homePage.toggleCartBurger();
		for(int i=0;i<simpleItems.length;i++) {
			Assert.assertTrue(searchPage.BurgerItemContains(simpleItems[i]),"Item "+simpleItems[i]+" wasn't echoed.");
		}
	}
	@AfterClass
	public void tearDown(){
		homePage.deleteAllItems();
		super.tearDown();
	}
}

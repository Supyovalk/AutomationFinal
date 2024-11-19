package tutorialNinjaPOM.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import tutorialNinjaPOM.pages.CartPage;
import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;

public class CartPageTests extends BaseDriverTest {
	String[] HomesetUpItems= {"MacBook","iPhone","iPod Shuffle","Nikon D300","MacBook Air"};
	@Override
	@BeforeClass
	public void setUp(){
		super.setUp();
	    homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		cartPage=new CartPage(driver);
		
	    homePage.urlSessionRestart();
	    homePage.toggleAccountDropdown();
	    homePage.pressAccountDropdownLogin();
	    loginPage.logOn("SupyovalkTest1@gmail.com", "1111");
	    homePage.urlNavigate();
	    homePage.search("o");
	    homePage.cartAddThumb(1.5,HomesetUpItems);
		homePage.toggleCartBurger();
		homePage.pressCartBurgerCartLink();
	}
	@Test
	public void tc01_ItemsEcho() {
		Assert.assertTrue(cartPage.CartPageContainsAllItems(HomesetUpItems));
	}
	@Test
	public void tc02_ItemsTotalPrice() {
		Assert.assertTrue(cartPage.CartPageCheckTotalCosts(HomesetUpItems));
	}
	@Test
	public void tc03_ItemsModifyCount() {
		cartPage.setCartPageItemCount(HomesetUpItems[0],"5");
		Assert.assertEquals(5, cartPage.getCartPageItemUnitCount(HomesetUpItems[0]));
		cartPage.setCartPageItemCount(HomesetUpItems[1],"6.5");
		Assert.assertEquals(6, cartPage.getCartPageItemUnitCount(HomesetUpItems[1]));
		Assert.assertTrue(cartPage.CartPageCheckTotalCosts(HomesetUpItems));
	}
	@Test
	public void tc04_ItemsCheckTotalSumShipless() {
		Assert.assertEquals(cartPage.getCartPageTotalPrice(),cartPage.getCartPagePriceSummation());
	}
	@Test
	public void tc05_ItemsModifyCount() {
		cartPage.setCartPageItemCount(HomesetUpItems[0],"-5.6");
		cartPage.setCartPageItemCount(HomesetUpItems[1],"false");
		cartPage.setCartPageItemCount(HomesetUpItems[2],"0");
		Assert.assertFalse(cartPage.CartPageContainsAllItems(HomesetUpItems[0]));
		Assert.assertFalse(cartPage.CartPageContainsAllItems(HomesetUpItems[1]));
		Assert.assertFalse(cartPage.CartPageContainsAllItems(HomesetUpItems[2]));
		Assert.assertEquals(cartPage.getCartPageTotalPrice(),cartPage.getCartPagePriceSummation());
	}
	@Test
	public void tc06_VoucherInvaild() {
		cartPage.fillGiftVoucher("aaa", true);
		cartPage.waitSeconds(1);
		Assert.assertTrue(cartPage.hasFieldError("Warning: Gift Certificate is either invalid or the balance has been used up!"));
	}
	@Test
	public void tc07_CouponInvaild() {
		cartPage.fillCouponVoucher("aaa", true);

		Assert.assertTrue(cartPage.hasFieldError("Warning: Coupon is either invalid, expired or reached its usage limit!"));
	}
	@Test
	public void tc08_ShippingMethodUnchecked() {
		cartPage.fillShippingValues("Israel","Haifa", "");
		Assert.assertFalse(cartPage.isShippingButtonEnabled());
		cartPage.cancelShippingMethod();
	}
	@Test
	public void tc09_ShippingCheck() {
		cartPage.fillShippingValues("Israel","Sharon", "",false);
		cartPage.selectShippingMethod(true);
		Assert.assertTrue(cartPage.hasFieldSuccess("Success: Your shipping estimate has been applied!"));
	}
	@Test
	public void tc99_ContiuneShopping() {
		cartPage.pressContiuneShoppingButton();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=common/home");
	}
	@AfterClass
	public void tearDown(){
		homePage.deleteAllItems();
		super.tearDown();
	}
}

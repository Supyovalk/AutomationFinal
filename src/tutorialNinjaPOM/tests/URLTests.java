package tutorialNinjaPOM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class URLTests extends BaseDriverTest {

	@BeforeClass
	public void setUp(){
		super.setUp();
		homePage.urlSessionRestart();
	    homePage.toggleAccountDropdown();
	    homePage.pressAccountDropdownLogin();
	   
	    loginPage.logOn("SupyovalkTest1@gmail.com", "1111");    
	}
	@Test
	public void tc01_defaultRoutes() {
		homePage.urlNavigate("https://tutorialsninja.com/demo/index.php");
		Assert.assertNotNull(homePage.getMatchingContainer(), "Can't find the Homepage's matching container");
	}
	@Test(dataProvider="getInvaildRoutes")
	public void tc02_invaildRoutes(String invaildUrl,String expectedError) {
		homePage.urlNavigate(invaildUrl);
		String errorMessage=errorPage.getErrorMain();
		Assert.assertEquals(expectedError, errorMessage,"Unmatching Error");
		errorPage.pressContinue();
		Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=common/home");
	}
	@Test
	public void tc03_SearchEmpty() {
		homePage.urlNavigate("https://tutorialsninja.com/demo/index.php?route=product/search");
		Assert.assertTrue(searchPage.isSearchEmpty());
	}
	@DataProvider
	public Object[] getInvaildRoutes() {
		String errorMessage="The page you requested cannot be found!";
		Object[][] data = {
				{"https://tutorialsninja.com/demo/index.php?route=account",errorMessage},
				{"https://tutorialsninja.com/demo/index.php?route=product",errorMessage},
				{"https://tutorialsninja.com/demo/index.php?route=account/invaild",errorMessage},
				{"https://tutorialsninja.com/demo/index.php?route=product/invaild",errorMessage}
		};
		return data;
	}
}

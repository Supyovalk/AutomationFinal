package tutorialNinjaPOM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tutorialNinjaPOM.pages.AccountPage;
import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;
import tutorialNinjaPOM.pages.SearchPage;
import tutorialNinjaPOM.pages.SearchPage.SearchSubcategory;

public class SearchTests extends BaseDriverTest {
	@Override
	@BeforeClass
	public void setUp(){
		super.setUp();
	    homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		accountPage=new AccountPage(driver);
		searchPage=new SearchPage(driver);
		
	    homePage.urlSessionRestart();
	    homePage.toggleAccountDropdown();
	    homePage.pressAccountDropdownLogin();
	    loginPage.logOn("SupyovalkTest1@gmail.com", "1111");
	}
	@Test
	public void tc01_emptySearch() {
		accountPage.search("");
	    Assert.assertTrue(searchPage.isSearchEmpty());
	}
	@Test
	public void tc02_subCategoryButtonDisableEnable() {
		searchPage.doPageSearch("");
		searchPage.selectSubCategory(SearchSubcategory.DESKTOPS);
		Assert.assertTrue(searchPage.isSubcategoryButtonEnabled());
		searchPage.selectSubCategory(SearchSubcategory.ALL_CATEGORIES);
		Assert.assertFalse(searchPage.isSubcategoryButtonEnabled());
	}
	@Test(dataProvider="getSearchFilterNonDesc")
	public void tc03_SearchFilterNonDesc(String searchText) {
		searchPage.doPageSearch(searchText);
		Assert.assertTrue(searchPage.checkThumbLabelsMatchingSearch(searchText));
	}
	@DataProvider
	public Object[] getSearchFilterNonDesc(){
		Object[][] data= {
				{"a"},
				{"A"},
				{"Mac "},
				{" MaC"},
		};
		return data;
	}
}

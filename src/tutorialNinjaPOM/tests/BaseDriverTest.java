package tutorialNinjaPOM.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import tutorialNinjaPOM.pages.AccountAddressEntryPage;
import tutorialNinjaPOM.pages.AccountAddressModifyPage;
import tutorialNinjaPOM.pages.AccountPage;
import tutorialNinjaPOM.pages.CartPage;
import tutorialNinjaPOM.pages.ErrorPage;
import tutorialNinjaPOM.pages.HomePage;
import tutorialNinjaPOM.pages.LoginPage;
import tutorialNinjaPOM.pages.RegisterPage;
import tutorialNinjaPOM.pages.SearchPage;

public class BaseDriverTest {
	protected WebDriver driver;
	
	protected AccountPage accountPage;
	protected AccountAddressEntryPage addressEntryPage;
	protected AccountAddressModifyPage addressModifyPage;
	protected CartPage cartPage;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected SearchPage searchPage;
	protected RegisterPage registerPage;
	protected ErrorPage errorPage;
	
	protected static final String REGISTERED_EMAIL = "SupyovalkTest1@gmail.com";
	
	@BeforeClass
	public void setUp(){
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
}

package tutorialNinjaPOM.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends HeaderAndProductThumbPage {
	protected static final String HOME_URL="https://tutorialsninja.com/demo/index.php";
	@FindBy(css="#common-home")
	protected WebElement homeContainer;
	public HomePage(WebDriver driver) {
		super(driver);
	}
	public final void urlNavigate() {
		urlNavigate(HOME_URL,0);
	}
	public final void urlNavigate(double cooldown) {
		urlNavigate(HOME_URL,cooldown);
	}
	public final void urlSessionRestart() {
		urlSessionRestart(HOME_URL);
	}
	public final WebElement getMatchingContainer(){
		return homeContainer;
	}
}

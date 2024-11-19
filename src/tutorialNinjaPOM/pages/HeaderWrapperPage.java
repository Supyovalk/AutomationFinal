package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import tutorialNinjaPOM.utils.StringParsing;

public class HeaderWrapperPage extends BasePage {
	@FindBy(css=".col-sm-3 > ul >li > a")
	protected List<WebElement> footerAccountLinks;
	@FindBy(css=".dropdown-toggle[title='My Account']")
	protected WebElement headerAccountButton;
	@FindBy(css=".dropdown-menu-right>li>a")
	protected List<WebElement> accountDropoutOptions;
	@FindBy(css="#cart")
	protected WebElement cartContainer;
	@FindBy(css="#cart>button")
	protected WebElement cartButton;
	@FindBys({@FindBy(css="#cart"),@FindBy(css=".table-striped"),@FindBy(css="tbody>tr")})
	protected List<WebElement> cartBurgerItems;
	@FindBys({@FindBy(css="#cart"),@FindBy(css=".text-right>a")})
	protected List<WebElement> cartBurgerLinks;
	@FindBy(css="#search>input")
	protected WebElement searchInput;
	@FindBy(css="#search>span>button")
	protected WebElement searchButton;
	@FindBys({@FindBy(css="#top-links"),@FindBy(css="a>i.fa-shopping-cart")})
	protected WebElement headerCartLink;
	@FindBy(css=".navbar-nav>li")
	protected List<WebElement> navOptions;
	
	public HeaderWrapperPage(WebDriver driver) {
		super(driver);
	}
	public void pressHeaderCart(String buttonText) {
		click(headerCartLink);
	}
	public void pressFooterLink(String buttonText) {
		WebElement el=elementListSearchText(footerAccountLinks, buttonText);
		click(el);
	}
	public void pressAccountDropdown(String buttonText) {
		WebElement el=elementListSearchText(accountDropoutOptions, buttonText);
		click(el);
	}
	public void pressAccountDropdownLogin() {
		pressAccountDropdown("Login");
	}
	public void pressAccountDropdownRegister() {
		pressAccountDropdown("Register");
	}
	public void pressAccountDropdownLogout() {
		pressAccountDropdown("Logout");
	}
	public void search(String buttonText) {
		fillElement(searchInput,buttonText);
		click(searchButton);
	}
	public void toggleAccountDropdown() {
		click(headerAccountButton);
	}
	public boolean isCartBurgerOpen() {
		return cartContainer.getAttribute("class").contains("open");
	}
	public void toggleCartBurger() {
		toggleCartBurger(true);
	}
	public void toggleCartBurger(boolean open) {
		if(isCartBurgerOpen()!=open) click(cartButton);
	}
	public void pressCartBurgerCartLink() {
		click(cartBurgerLinks.get(0));
	}
	public void pressCartBurgerCheckoutLink() {
		click(cartBurgerLinks.get(1));
	}
	public void pressFooterMyAccount() {
		pressFooterLink("My Account");
	}
	public String getItemName(WebElement itemTr) {
		return itemTr.findElement(By.cssSelector(".text-left>a")).getText();
	}
	public double getItemTotalCost(WebElement itemTr) {
		String dollarString= itemTr.findElements(By.cssSelector(".text-right>a")).get(1).getText();
		return Double.parseDouble(StringParsing.stripDollar(dollarString));
	}
	public double getItemTotalCost(String name) {
		return  getItemTotalCost(findBurgerItemByName(name));
	}
	public void deleteItem(WebElement itemTr) {
		WebElement button= itemTr.findElement(By.cssSelector(".text-center>button"));
		click(button);
	}
	public void deleteItem(String name) {
		deleteItem(findBurgerItemByName(name));
	}	
	public void deleteAllItems() {
		while(cartBurgerItems.size()!=0) {
			toggleCartBurger();
			deleteItem(cartBurgerItems.get(0));
			waitSeconds(2);
		}
	}
	public int getItemCount(WebElement itemTr) {
		String countString= itemTr.findElements(By.cssSelector(".text-right>a")).get(0).getText();
		return Integer.parseInt(countString.substring(1));
	}
	public int getItemCount(String name) {
		return getItemCount(findBurgerItemByName(name));
	}

	public WebElement findBurgerItemByName(String name) {
		for(WebElement el:cartBurgerItems) {
			if(name.equals(getItemName(el))) return el;
		}
		return null;
	}
	public boolean BurgerItemContains(String name) {
		return findBurgerItemByName(name)!=null;
	}
	public String getNavMenuName(WebElement li){ 
		return li.findElement(By.cssSelector("a.dropdown-toggle")).getText(); 
	}
	public WebElement findNavMenuByName(String name) {
		for(WebElement el:navOptions) {
			if(getNavMenuName(el).equals(name)) return el;
		}
		return null;
	}
}

package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderAndProductThumbPage extends HeaderWrapperPage {
	@FindBy(css=".product-thumb")
	protected List<WebElement> productThumbs;
	public HeaderAndProductThumbPage(WebDriver driver) {
		super(driver);
	}
	public String getThumbName(WebElement productThumb) {
		WebElement caption=productThumb.findElement(By.cssSelector("div.caption>h4>a"));
		if(caption==null) return null;
		return caption.getText();
	}	
	public void cartAddThumb(String... names) {
		for(String item:names) {
			cartAddThumb(findThumbByName(item),0);
		}
	}
	public void cartAddThumb(double coolDown,String... names) {
		for(String item:names) {
			cartAddThumb(findThumbByName(item),coolDown);
		}
	}
	public void cartAddThumb(WebElement productThumb,double coolDown) {
		if(productThumb==null) return;
		WebElement Cartsymbol=productThumb.findElement(By.cssSelector(".fa-shopping-cart"));
		click(Cartsymbol);
		if(coolDown>0) waitSeconds(coolDown);
	}
	public WebElement findThumbByName(String name){
		for(WebElement thumb:productThumbs) {
			if(getThumbName(thumb).equals(name)) return thumb;
		}
		return null;
	}
	public boolean checkThumbLabelsMatchingSearch(String searchText) {
		String lowersearch=searchText.strip().toLowerCase();
		for(WebElement thumb:productThumbs) {
			if(!getThumbName(thumb).toLowerCase().contains(lowersearch)) {
				return false;
			}
		}
		return true;
	}
}

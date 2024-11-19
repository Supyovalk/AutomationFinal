package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountAddressEntryPage extends HeaderWrapperPage {
	@FindBy(css="div.pull-right>a.btn")
	protected WebElement buttonAddAddress; 
	@FindBy(css="tbody>tr")
	protected List<WebElement> addressEntryBlocks; 
	public AccountAddressEntryPage(WebDriver driver) {
		super(driver);
	}
	public void pressAddAddress() {
		click(buttonAddAddress);
	}
	public String[] getAddressInfo(String combinedName){
		for(WebElement entry:addressEntryBlocks) {
			WebElement infoBlock=entry.findElement(By.cssSelector("td.text-left"));
			String innerText=infoBlock.getAttribute("innerText");
			String[] info=innerText.split("\n");
			if(info[0].equals(combinedName)) {
				return info;
			}
		}
		return null;
	}
}

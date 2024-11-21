package tutorialNinjaPOM.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BasePage {
	protected WebDriver driver;
	public static final int WAIT_DURATION=5;
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public final int getWindowCount(int index) {
		return driver.getWindowHandles().size();
	}
	public final String getWindowString(int index) {
		Object[] windowHandles=driver.getWindowHandles().toArray();
		if(index<0||index>=windowHandles.length) return null;
		return (String)windowHandles[index];
	}
	public final WebElement elementListSearchText(List<WebElement> elements,String text) {
		for(WebElement el: elements) {
			if(el.getText().equals(text)) {
				return el;
			}
		}
		return null;
	}
	public final void urlNavigate(String url) {
		urlNavigate(url,0);
	}
	public final void urlNavigate(String url,double cooldown) {
		driver.navigate().to(url);
		if(cooldown>0) waitSeconds(cooldown);
	}
	public final void urlSessionRestart(String url) {
		driver.get(url);
	}
	
	public final void openWindow(String url) {
		driver.switchTo().newWindow(WindowType.TAB);
		urlNavigate(url);
	}
	
	public final void fillElement(WebElement el,String sentKeys) {
		el.clear();
		highlightElement(el,"blue");
		el.sendKeys(sentKeys);
	}
	public final void selectChoiceByText(WebElement el,String value){ 
		Select sl=new Select(el);
		sl.selectByVisibleText(value);
	}
	public final void selectChoiceByValue(WebElement el,String value){ 
		Select sl=new Select(el);
		highlightElement(el,"red");
		sl.selectByValue(value);
	}
	public final void click(WebElement el) {
		click(el,WAIT_DURATION);
	}
	public final void click(WebElement el,int duration) {
		if(el==null) return;
		if(duration > 0) waitUntilElementClickable(el,duration);
		highlightElement(el,"yellow");
		el.click();
	}
	
	public final boolean cssLimitCheck(String cssSelector,int min) {
		return driver.findElements(By.cssSelector(cssSelector)).size() >= min;
	}
	
	public final void waitSeconds(double seconds) {
		try {
			Thread.sleep((int)(1000*seconds));
			}
		catch(InterruptedException e) {
				System.out.println("Couldnt Sleep");
			}
	}
	public final void waitUntilElementClickable(WebElement Element) {
		waitUntilElementClickable(Element,WAIT_DURATION);
	}
	public final void waitUntilElementClickable(WebElement element,int duration) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public final void waitUntilElementAttibuteEquals(WebElement element,String attribute,String value) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION));
		wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	}
	public final void waitUntilURL(String urlMatch,int duration) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.urlToBe(urlMatch));
	}
	public final void windowSwitchIndex(int index) {
		String windowString=getWindowString(index);
		if(windowString!=null) driver.switchTo().window(getWindowString(index));
	}
	public final void windowSwitchString(String name) {
		driver.switchTo().window(name);
	}
	protected void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:"+color+"; border: 1px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}
}

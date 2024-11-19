package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends HeaderAndProductThumbPage {
	public enum SearchSubcategory{
		ALL_CATEGORIES,
		DESKTOPS
	}
	@FindBy(css="#content>div.row")
	protected List<WebElement> searchContentRows;
	@FindBy(css="[name='sub_category']")
	protected WebElement subCategoryRadio;
	@FindBy(css="[name='category_id']")
	protected WebElement subCategorySwitch;
	@FindBy(css="#input-search")
	protected WebElement pageSearchInput;
	@FindBy(css="#button-search")
	protected WebElement pageSearchButton;
	@FindBy(css="#content>p>label.checkbox-inline>input")
	protected WebElement productDescSearchRadio;
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	public boolean isSearchEmpty() {
		return searchContentRows.size()==1;
	}
	public boolean isSubcategoryButtonEnabled() {
		return subCategoryRadio.isEnabled();
	}
	public void selectSubCategory(SearchSubcategory subCategory) {
		switch(subCategory) { 
		case ALL_CATEGORIES:
			selectChoiceByValue(subCategorySwitch,"0");
			break;
		case DESKTOPS:
			selectChoiceByValue(subCategorySwitch,"20");
			break;
		default:
			break;
		}
	}
	public void doPageSearch(String searchText) {
		fillElement(pageSearchInput,searchText);
		click(pageSearchButton);
	}
	public void toggleDescSearchRadio() {
		click(productDescSearchRadio);
	}
}

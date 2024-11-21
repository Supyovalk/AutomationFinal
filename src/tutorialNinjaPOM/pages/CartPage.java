package tutorialNinjaPOM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import tutorialNinjaPOM.utils.StringParsing;

public class CartPage extends HeaderWrapperPage {
	@FindBys({ @FindBy(css = "#content>form"), @FindBy(css = "tbody>tr") })
	protected List<WebElement> cartItemTrs;
	@FindBys({ @FindBy(css = "#content>div.row"), @FindBy(css = "tbody>tr") })
	protected List<WebElement> priceTotalTrs;
	@FindBys({ @FindBy(css = ".buttons.clearfix"), @FindBy(css = ".btn-default") })
	protected WebElement continueButton;
	@FindBys({ @FindBy(css = "#accordion"), @FindBy(css = "h4.panel-title>a") })
	protected List<WebElement> collapseHeadings;
	@FindAll({ @FindBy(css = "div>.text-danger"), @FindBy(css = ".alert-danger") })
	protected List<WebElement> fieldErrorMessages;
	@FindAll({ @FindBy(css = ".alert-success") })
	protected List<WebElement> fieldErrorSuccesses;
	@FindBy(css = "#input-voucher")
	protected WebElement voucherField;
	@FindBy(css = "#button-voucher")
	protected WebElement voucherButton;
	@FindBy(css = "#input-coupon")
	protected WebElement couponField;
	@FindBy(css = "#button-coupon")
	protected WebElement couponButton;
	@FindBy(css = "#input-country")
	protected WebElement shippingCountry;
	@FindBy(css = "#input-zone")
	protected WebElement shippingRegion;
	@FindBy(css = "#input-postcode")
	protected WebElement shippingPost;
	@FindBy(css = "#button-quote")
	protected WebElement shippingButton;
	@FindBys({ @FindBy(css = "#modal-shipping"), @FindBy(css = "label>input[type='radio']") })
	protected WebElement FirstShippingOption;
	@FindBy(css = "#button-shipping")
	protected WebElement shippingMethodButton;
	@FindBy(css = ".modal-footer>button.btn-default")
	protected WebElement shippingCancelButton;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public boolean hasFieldError(String text) {
		for (WebElement el : fieldErrorMessages) {
			String messageFull = el.getText().strip();
			String message;
			if (messageFull.indexOf("\n") == -1)
				message = messageFull;
			else {
				message = messageFull.substring(0, messageFull.indexOf("\n"));
			}
			if (message.equals(text)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasFieldSuccess(String text) {
		for (WebElement el : fieldErrorSuccesses) {
			String messageFull = el.getText().strip();
			String message;
			if (messageFull.indexOf("\n") == -1)
				message = messageFull;
			else {
				message = messageFull.substring(0, messageFull.indexOf("\n"));
			}
			if (message.equals(text)) {
				return true;
			}
		}
		return false;
	}

	// Items
	public WebElement getCartPageItemInputBox(String name) {
		return getCartPageItemInputBox(findCartPageTrByName(name));
	}

	protected WebElement getCartPageItemInputBox(WebElement tr) {
		if (tr == null)
			return null;
		return tr.findElement(By.cssSelector("td.text-left>div>input"));
	}

	public WebElement getCartPageItemUpdate(String name) {
		return getCartPageItemUpdate(findCartPageTrByName(name));
	}

	protected WebElement getCartPageItemUpdate(WebElement tr) {
		if (tr == null)
			return null;
		return tr.findElement(By.cssSelector(".input-group-btn>button[data-original-title='Update']"));
	}

	public WebElement getCartPageItemRemove(String name) {
		return getCartPageItemRemove(findCartPageTrByName(name));
	}

	protected WebElement getCartPageItemRemove(WebElement tr) {
		if (tr == null)
			return null;
		return tr.findElement(By.cssSelector(".input-group-btn>button[data-original-title='Remove']"));
	}

	public String getCartPageItemName(String name) {
		return getCartPageItemName(findCartPageTrByName(name));
	}

	protected String getCartPageItemName(WebElement tr) {
		if (tr == null)
			return null;
		return tr.findElement(By.cssSelector("td.text-left>a")).getText();
	}

	public double getCartPageItemUnitPrice(String name) {
		return getCartPageItemUnitPrice(findCartPageTrByName(name));
	}

	protected double getCartPageItemUnitPrice(WebElement tr) {
		if (tr == null)
			return 0;
		String unitPrice = tr.findElements(By.cssSelector("td.text-right")).get(0).getText();
		return Double.parseDouble(StringParsing.stripDollar(unitPrice));
	}

	public double getCartPageItemTotalPrice(String name) {
		return getCartPageItemTotalPrice(findCartPageTrByName(name));
	}

	protected double getCartPageItemTotalPrice(WebElement tr) {
		if (tr == null)
			return 0;
		String totalPrice = tr.findElements(By.cssSelector("td.text-right")).get(1).getText();
		return Double.parseDouble(StringParsing.stripDollar(totalPrice));
	}

	public void setCartPageItemCount(String name, String count) {
		setCartPageItemCount(findCartPageTrByName(name), count, 0);
	}

	public void setCartPageItemCount(String name, String count, double cooldown) {
		setCartPageItemCount(findCartPageTrByName(name), count, cooldown);
	}

	protected void setCartPageItemCount(WebElement tr, String count, double cooldown) {
		WebElement box = getCartPageItemInputBox(tr);
		fillElement(box, count);
		click(getCartPageItemUpdate(tr));
		if (cooldown > 0)
			waitSeconds(cooldown);
	}

	public void removeCartPageItem(String name) {
		removeCartPageItem(findCartPageTrByName(name));
	}

	protected void removeCartPageItem(WebElement tr) {
		click(getCartPageItemUpdate(tr));
	}

	public int getCartPageItemUnitCount(String name) {
		return getCartPageItemUnitCount(findCartPageTrByName(name));
	}

	protected int getCartPageItemUnitCount(WebElement tr) {
		if (tr == null)
			return 0;
		String unitCount = getCartPageItemInputBox(tr).getAttribute("value");
		return Integer.parseInt(unitCount);
	}

	public WebElement findCartPageTrByName(String name) {
		for (WebElement tr : cartItemTrs) {
			if (getCartPageItemName(tr).equals(name))
				return tr;
		}
		return null;
	}

	public boolean CartPageContainsAllItems(String... names) {
		for (String name : names) {
			if (findCartPageTrByName(name) == null)
				return false;
		}
		return true;
	}

	public boolean CartPageCheckTotalCosts(String... names) {
		for (String name : names) {
			if (getCartPageItemUnitPrice(name) * getCartPageItemUnitCount(name) != getCartPageItemTotalPrice(name))
				return false;
		}
		return true;
	}

	public double getCartPagePriceSummation() {
		double totalPriceSum = 0;
		for (WebElement item : cartItemTrs) {
			totalPriceSum += getCartPageItemTotalPrice(item);
		}
		System.out.println("TotalItemSum:" + totalPriceSum);
		return totalPriceSum;
	}

	// PriceTotalSection
	public double getCartPageTotalPrice() {
		String dollarString = priceTotalTrs.get(3).findElements(By.cssSelector(".text-right")).get(1).getText();
		return Double.parseDouble(StringParsing.stripDollar(dollarString));
	}

	// Collapse Headings
	public void pressCollapseCoupon() {
		pressCollapseHeadings(0);
	}

	public void pressCollapseShipping() {
		pressCollapseHeadings(1);
	}

	public void pressCollapseGift() {
		pressCollapseHeadings(2);
	}

	protected void pressCollapseHeadings(int index) {
		click(collapseHeadings.get(index));
		waitSeconds(1);
	}

	// Gift
	public void fillGiftVoucher(String code) {
		fillGiftVoucher(code, true);
	}

	public void fillGiftVoucher(String code, boolean pressCollapse) {
		if (pressCollapse)
			pressCollapseGift();
		fillElement(voucherField, code);
		click(voucherButton);
		waitSeconds(1);
	}

	// Coupon
	public void fillCouponVoucher(String code) {
		fillCouponVoucher(code, true);
	}

	public void fillCouponVoucher(String code, boolean pressCollapse) {
		if (pressCollapse)
			pressCollapseCoupon();
		fillElement(couponField, code);
		click(couponButton);
		waitSeconds(1);
	}

	// Shipping
	public void fillShippingValues(String country, String region, String code) {
		fillShippingValues(country, region, code, true);
	}

	public void fillShippingValues(String country, String region, String code, boolean pressCollapse) {
		if (pressCollapse)
			pressCollapseShipping();
		selectChoiceByText(shippingCountry, country);
		waitSeconds(2);
		selectChoiceByText(shippingRegion, region);
		fillElement(shippingPost, code);
		click(shippingButton);
		waitSeconds(2);
	}

	public void cancelShippingMethod() {
		click(shippingCancelButton);
	}

	public void selectShippingMethod(boolean selectMethod) {
		if (selectMethod && !FirstShippingOption.isSelected())
			click(FirstShippingOption);
		click(shippingMethodButton);
		waitSeconds(4);
	}

	public boolean isShippingButtonEnabled() {
		return shippingMethodButton.isEnabled();
	}

	// Contiune
	public void pressContiuneShoppingButton() {
		click(continueButton);
	}
}

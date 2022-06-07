package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonFunctions.DriverSetup;
import utility.BaseUtil;

public class HomePage extends DriverSetup {

	String item="Samsung";
    String categoryText="Computers/Tablets & Networking";
    int pageNo=3;
	By acceptCookie=By.id("gdpr-banner-accept");
	//search
	String results="//a[contains(@href,'www.ebay.co.uk')]/h3";
	By searchText=By.id("gh-ac");
	By searchBtn=By.id("gh-btn");
	By serachResult=By.xpath(results);
	By category=By.id("gh-cat");
	//cards content
	boolean checkContent=false;
	By postagePrice=By.xpath(results+"//following::span[@class='s-item__shipping s-item__logisticsCost']");
	By price=By.xpath(results+"//following::span[@class='s-item__price'][1]");
	By bid=By.xpath(results+"//following::span[@class='s-item__bids s-item__bidCount']");
	By buyItNow=By.xpath(results+"//following::span[@class='s-item__purchase-options-with-icon']");
	
	//Sort
	By sort=By.xpath("(//span[@class='expand-btn__cell'])[1]");	
	By filter=By.xpath("(//span[@class='filter-menu-button__button-text'])[1]");
	By categotyResult=By.xpath("(//li[@data-state='selected']//span)[1]");
	
	By resultCount=By.xpath("//*[@class='srp-controls__count-heading']/span[1]");
	String page="//ol[@class='pagination__items']/li";
	By paging=By.xpath(page);
	
	
	public void openWebsite() {
		driver.navigate().to(configFileReader.getURL());
	}
	
	public void acceptCookie() {
		try {Thread.sleep(9000);} catch (InterruptedException e) {e.printStackTrace();}
		BaseUtil.clickByJS(acceptCookie, "accept cookie");
	}
	public void enterInSearchText() {
		BaseUtil.writeText(searchText, "text is entered", item);
	}
	
	public void selectCategory() {
		Select select = new Select(driver.findElement(category));
		select.selectByVisibleText(categoryText);
		
	}
	public void clickSearch() {
		BaseUtil.click(searchBtn, "search ");
	}
	public void getSearchResult() {
		String[] text=BaseUtil.getTextArrayOfElements(serachResult);
		for (int i=0;i<text.length;i++) {
			Assert.assertEquals(text[i].toLowerCase().contains(item.toLowerCase()), true,"Element number "+i+" wrong result for search");
		}
	}
	
	
		
}

package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import commonFunctions.DriverSetup;
import utility.BaseUtil;

public class ResultPage extends DriverSetup {

	String item="Samsung";
    String categoryText="Computers/Tablets & Networking";
    int pageNo=3;
	String results="//a[contains(@href,'www.ebay.co.uk')]/h3";
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
	
	
	public void checkCardsContent() {
		Assert.assertEquals(BaseUtil.getSizeArrayOfElements(postagePrice)>0,true);
		Assert.assertEquals(BaseUtil.getSizeArrayOfElements(price)>0,true);
		Assert.assertEquals(BaseUtil.getSizeArrayOfElements(bid)>0,true);
		Assert.assertEquals(BaseUtil.getSizeArrayOfElements(buyItNow)>0,true);
		
	}
	public void sortBy(String text) {
		driver.findElement(sort).click();
		By price=By.xpath("//span[text()='"+text+" price']");
		 switch(text){    
	    	case "Highest":    
	    	 BaseUtil.click(price, text);
	    	 break;  
	    	case "Lowest":    
	    		 BaseUtil.click(price, text);
	    	 break;  
	    	default:     
	    	  System.out.println("sort by  "+text);  
	    	} 
	}
	
	 public void correctOrder(String text) {
		  
		 
		 boolean correct=false;
          switch(text){    
	    	case "Highest":    
	    		Float[] priceFl= BaseUtil.getDigitsArrayOfElements(price,text);
	    	 correct=BaseUtil.isSortedDecending(priceFl);
	    	 break;  
	    	case "Lowest":  
	    		Float[] priceFl2= BaseUtil.getDigitsArrayOfElements(price,text);
	    	 correct=BaseUtil.isSortedAscending(priceFl2);
	    	 break;  
	    	default:     
	    	  System.out.println("no sorted ");  
	    	} 
          Assert.assertTrue(correct);
	 }
	 public void filerBy(String text) {
		
		 By filterBy=By.xpath("//span[@class='srp-format-tabs-h2' and (text()='Buy It Now' or text()='Buy it now')]");
			//driver.findElement(filter).click();
			driver.findElement(filterBy).click();
		}
	 public void filerResult(String filterText) {
		 String[] text=BaseUtil.getTextArrayOfElements(buyItNow);
			for (int i=0;i<text.length;i++) {
				Assert.assertEquals(text[i].toLowerCase().contains(filterText.toLowerCase()) || text[i].toLowerCase().contains("best offer"), true,"Element number "+i+" wrong result for filter");
			}
			
		}
	 public void categoryResult() {
		 
	  Assert.assertEquals( BaseUtil.getText(categotyResult, "category result").contains(categoryText), true);
			
			
		}
	 public void checkPaging() {
		 String text=BaseUtil.getText(resultCount, "result count").replaceAll(",", "");		
		if (Integer.parseInt(text) >60)
		{
			  Assert.assertEquals(BaseUtil.getSizeArrayOfElements(paging)>0,true);
		}
			
			}
	 public void openPage() {
		 String text=BaseUtil.getText(resultCount, "result count").replaceAll(",", "");
		 try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		 if (Integer.parseInt(text) >60)
			{
			 By pageNumber=By.xpath("//ol/li["+pageNo+"]/a");
				BaseUtil.click(pageNumber, "Open page number");
					Assert.assertEquals(driver.getCurrentUrl().contains("_pgn="+pageNo), true);
			}			
		 }
		
}

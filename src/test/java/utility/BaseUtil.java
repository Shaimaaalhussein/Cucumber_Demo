package utility;


import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonFunctions.DriverSetup;

public class BaseUtil extends DriverSetup {

	
		private static WebDriverWait wait;
		 
	  //Wait Wrapper Method for visibility
		@SuppressWarnings("deprecation")
		public static void waitVisibility(By elementBy)  {
			wait=new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
			
		}		
		//Click Method
		public static void click (By elementBy, String logText) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.click();
				System.out.println(logText);
		}
		//ClickBy javascript Method
		public static void clickByJS (By elementBy, String logText) {
			
				//waitVisibility(elementBy);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,250)", "");
				WebElement element = driver.findElement(elementBy);
				
				js.executeScript("arguments[0].click();", element);
				System.out.println(logText);
			
		}
		
		
		//CheckElement is displayed
		public static boolean elementDisplayed(By elementBy, String logText) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				
				System.out.println(logText);
				
			    return element.isDisplayed();
		}
		//writeText Method
		public static void writeText(By elementBy, String logText,String Text) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.sendKeys(Text);
				System.out.println(logText);
				
				
		}
		//writeText Method
		public static void writeSearchText(By elementBy, String logText,String Text) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.sendKeys(Text);
				element.sendKeys(Keys.ENTER);
				System.out.println(logText);
				
				
		}
		//submit Method
		public static void submit(By elementBy, String logText) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.submit();
				System.out.println(logText);
				
				 
		}
		//get size array of elements
		public static int getSizeArrayOfElements(By elementBy) {
			      waitVisibility(elementBy);
				  List<WebElement> element = driver.findElements(elementBy);
				return element.size() ;
			
		}
		//get text Method
		public static String getText(By elementBy, String logText) {
			
				waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				System.out.println(logText);
				return element.getText();
			
		}
		
		
		//get digits  of array of elements
				public static String[] getTextArrayOfElements(By elementBy) {
					      waitVisibility(elementBy);
						  List<WebElement> element = driver.findElements(elementBy);
						
						 String[] text=new String[element.size()];
						  for (int i=0;i<element.size();i++)
						  {
							  
							text[i]=element.get(i).getText();
						  }
						return text;
					
				}
				
				//get array of elements as digits
				public static Float[] getDigitsArrayOfElements(By elementBy,String textPrice) {
					      waitVisibility(elementBy);
						  List<WebElement> element = driver.findElements(elementBy);
						 String[] text=new String[element.size()];
						 Float[] f=new Float[element.size()];
		
						  for (int i=0;i<element.size();i++)
						  {
							text[i]=element.get(i).getText();
							
							if (text[i].toLowerCase().contains("to"))
							{
								String[] splitSt=text[i].split("to");
								  switch(textPrice){    
							    	case "Highest":    
							    		text[i]=splitSt[1];
							    	 break;  
							    	case "Lowest":    
							    		text[i]=splitSt[0];
							    	 break;  
							    	default:     
							    	  System.out.println("no sorted ");  
							    	} 
								
							}
							
							if (text[i].contains(","))
							{
				              text[i]=text[i].replaceAll(",","");
								
							}
							
							f[i]= Float.parseFloat(text[i].replaceAll("£",""));
						  }
						return f;
					
				}
		
		//random number
		public static int getRandomNo(int Range) {
			 Random rand = new Random(); //instance of random class
	         int int_random = rand.nextInt(Range)+1; 
	         return int_random;
		}
		
		public static void scroll() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0,500);");
			
		}
		public static boolean isSortedAscending(Float[] a) 
		{
			
		    int i;
		    for ( i = 0; i < a.length - 1; i++)
	        {
	            if (a[i] > a[i + 1]) {
	            	 System.out.println(" if Ascending "+i +" " +a[i]+" " +a[i+1]);
	                return false;
	            }
	        }
		    return true;
		    
		}
		public static boolean isSortedDecending(Float[] a) 
		{
		    int i;
		   
		    for ( i = 0; i < a.length - 1; i++)
	        {
	            if (a[i] < a[i + 1]) {
	            	 System.out.println(" if descending "+i +" " +a[i]+" " +a[i+1]);
	                return false;
	            }
	        }
		    return true;
		}
		    
		   
		
		
}
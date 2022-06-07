package stepDefinition;




import commonFunctions.DriverSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import pages.*;


public class ResultPageSteps extends DriverSetup{
	
	ResultPage resultPage=new ResultPage();
    String orderBy;

  
    @And("the resulting items cards show: {},{},{} or {}")
    public void the_resulting_items_cards_show(String postagePrice,String bids,String price ,String buyNow) {
    	
    	resultPage.checkCardsContent();
    }
    @Then("I can sort the results by {} Price")
    public void i_can_sort_the_results_by(String order) {
    	orderBy=order;
    	resultPage.sortBy(order);
       }
    @And("the results are listed in the page in the correct order")
    public void the_results_are_listed_in_the_page_in_the_correct_order(){
    	
    	resultPage.correctOrder(orderBy);
	}
    @Then("I can filter the results by {string}")
    public void i_can_filter_the_results_by(String string) {
    	resultPage.filerBy(string);
        
    }
 
    
    
    @And("all the results shown in the page have the {string} tag")
    public void all_the_results_shown_in_the_page_have(String string){
  resultPage.filerResult(string);
    	
	}
   
   
    @And("I can verify that the results shown as per the selected category")
    public void i_can_verify_that_the_results_shown_as_per_the_selected_category(){
    	
    	 resultPage.categoryResult();
    
	}
    @And("the results show more than one page")
    public void the_results_show_more_than_one_page(){
    	
    	 resultPage.checkPaging();
    
	}
    @Then("the user can navigate through the pages to continue looking at the items")
    public void the_user_can_navigate_through_the_pages_to_continue_looking_at_the_items(){
    	
    	 resultPage.openPage();
    
	}
    
   
  
}

package stepDefinition;




import commonFunctions.DriverSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;


public class HomePageSteps extends DriverSetup{
	
	HomePage homePage=new HomePage();


    @Given("I am a non-registered customer")
    public void i_am_a_non_registered_customer() {
    	//username
	  
       }
    
    @And("I navigate to www.ebay.co.uk")
    public void i_navigate_to_url() {
    	homePage.openWebsite();
    	 
    }
    
    
    @When("I search for an item")
    public void i_search_for_item() {
    	homePage.acceptCookie();
    	homePage.enterInSearchText();
    	homePage.clickSearch();
	   
    }
    
  
    @Then("I get a list of matching results")
    public void i_get_alist_of_matching_results() {
    		   
    	homePage.getSearchResult();
    }
 
    
    @When("I enter a search term and select a specific Category")
    public void I_enter_a_search_term_and_select_a_specific_Category(){
    	homePage.acceptCookie();
    	homePage.enterInSearchText();
    	homePage.selectCategory();
    	homePage.clickSearch();
	    
    
	}
    
  
    
   
  
}

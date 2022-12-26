package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	private String search = "input[name='search']" ;
	private String searchicon = "div#search button" ;
	private String searchPageHeader = "div#content h1" ;
	private String loginlink = "a:text('Login')" ;
	private String myAccountlink = "a[title='My Account']" ;
	
	public HomePage(Page page) {
	   this.page = page;		
	}
	
	public String getHomePageTitle()
	{
		return page.title() ;
	}
	
   public String 	getHomePageUrl()
   {
	   return page.url() ;
   }
   
   public String doSearch(String productName)
   {
	   page.fill(search, productName);
	   page.click(searchicon);
	   return page.textContent(searchPageHeader) ;
   }
   
   public LoginPage navigateToLoginPage()
   {
	   page.click(myAccountlink);
	   page.click(loginlink);
	   return new LoginPage(page) ;
   }
  
}

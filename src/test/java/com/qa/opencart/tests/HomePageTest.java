package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest{
	
	

	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {
			
			{"Macbook"} ,
			{"iMac"} ,
			{"Samsung"}
			
		} ;
	}
	
	@Test()
	public void homePageTitleTest()
	{
		String actualTitle = homePage.getHomePageTitle() ;
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
		
	}
	
	@Test
	public void homePageUrlTest()
	{
		String actualUrl = homePage.getHomePageUrl() ;
		Assert.assertEquals(actualUrl, prop.getProperty("URL"));

	}
	
	@Test(dataProvider = "getData")
	public void searchTest(String productName)
	{
		String actualHeader = homePage.doSearch(productName) ;
		Assert.assertEquals(actualHeader, "Search - " + productName);
		
	}

}

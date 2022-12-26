package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory pf;
	Page page;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected Properties prop ;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setuP(String browserName)
	{
		pf = new PlaywrightFactory() ;
		prop = pf.initProp() ;
		     if(browserName != null)
		     {
		    	 prop.setProperty("BROWSER", browserName) ;
		     }
		   
		
		page = pf.initBrowser(prop) ;
		homePage = new HomePage(page) ;	
	}
	

	@AfterTest
	public void tearDown()
	{
	     page.context().browser().close(); 
	}
	

}

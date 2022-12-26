package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest{
	
	
	@Test(priority = 1)
	public void loginpageNavigationTest()
	{
		loginPage =  homePage.navigateToLoginPage() ;
		String actualLoginPagetitle = loginPage.getLoginpageTitle() ;
		Assert.assertEquals(actualLoginPagetitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test(priority = 2)
	public void forgotPwdLinkExistsTest()
	{
		boolean flag = loginPage.isForgotPwdLinkExist() ;
		Assert.assertTrue(flag);		
	}
	
	
	@Test(priority = 3)
	public void appLoginTest()
	{
		Assert.assertTrue(loginPage.login(prop.getProperty("USERNAME").trim(), prop.getProperty("PASSWORD").trim())) ;
	}

}

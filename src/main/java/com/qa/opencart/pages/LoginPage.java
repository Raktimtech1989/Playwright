package com.qa.opencart.pages;

import javax.xml.bind.annotation.XmlElementWrapper;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	private String emailId = "//input[@id='input-email']" ;
	private String password = "//input[@id='input-password']" ;
	private String loginBtn = "//input[@value='Login']" ;
	private String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";
	
	public LoginPage(Page page)
	{
		this.page = page;
	}
	
	public String getLoginpageTitle()
	{
		return page.title() ;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return page.isVisible(forgotPwdLink) ;
	}
	
	public boolean login(String appUserName , String appPassword)
	{
		System.out.println("app credentiald are   :"  + appUserName + " " + appPassword);
		page.fill(emailId, appUserName);
		page.fill(password, appPassword);
		page.click(loginBtn);
		if(page.isVisible(logoutLink))
		{
			System.out.println("user is logged In successfully ......");
			return true;
		}
		
		return true;
		
	}
	
	

}

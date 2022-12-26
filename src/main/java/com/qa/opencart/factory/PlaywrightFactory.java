package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Playwright playwright ;
	Browser browser ;
	BrowserContext browserContext ;
	Page page;
	Properties prop;
	
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>() ;
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>() ;
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<BrowserContext>() ;
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>() ;
	
	
	public static Playwright getPlaywright()
	{
		return tlPlaywright.get() ;
	}
	
	public static Browser getBrowser()
	{
		return tlBrowser.get() ;
	}
	
	public static BrowserContext getBrowserContext()
	{
		return tlBrowserContext.get() ;
	}
	
	public static Page getPage()
	{
		return tlPage.get() ;
	}
	
		
	public Page initBrowser(Properties prop) {
		
		   String browserName = prop.getProperty("BROWSER").trim() ;
		   System.out.println("browser name is  "  + browserName);
		   tlPlaywright.set(Playwright.create());
		   
		   switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));			
			break;

		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));		
			break;
			
		case "safari":
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false))) ;			
			break;
			
		case "chrome":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)) ;
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));		
			break;		
		case "edge":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)) ;
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));		
			break;	
		default:
			System.out.println("Please ss the right browser name ........");
			break;
		}
		   
		 tlBrowserContext.set(getBrowser().newContext());
		 tlPage.set(getBrowserContext().newPage());
		 getPage().navigate(prop.getProperty("URL").trim()) ;
		  
		
		 /*  browserContext = browser.newContext() ;
		  page = browserContext.newPage() ;
		  page.navigate(prop.getProperty("URL").trim()) ;  */
		  
		  return getPage();		
	}
	
	/**
	 * This method is used to initialize the properties from config file
	 * 
	 * 26-Dec-2022
	 * @author Raktim Sarkar
	 * @version 1.0
	 * @return 
	 * @since 1.0
	 */
	public Properties initProp()
	{
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties") ;
			prop = new Properties() ;
			prop.load(ip) ;
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;

	}
	/**
	 * This service is to ttake screenshot and feed data in listener interface
	 * 
	 * 26-Dec-2022
	 * @author Raktim Sarkar
	 * @version 1.0
	 * @since 1.0
	 * @return
	 */
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);
		
		return base64Path;
	}

}

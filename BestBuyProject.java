package aTE_Commerce;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BestBuyProject {
	
	public static WebDriver driver;
	@BeforeMethod
	public void startUp() throws Exception{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.bestbuy.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		this.takeSnapShot(driver, "C:\\Users\\Hp\\eclipse-workspace\\BestBuy\\src\\test\\java\\aTE_Commerce") ;
	}
	@Test
	public void validateURL() {
		String url ="";
		String homePage= "https://www.bestbuy.com/";
		HttpURLConnection huc = null;
		int respCode = 200;
		driver= new ChromeDriver();
		 driver.get(homePage);
		String E ="Best Buy International: Select your Country - Best Buy";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), E);
		
		
		// validating broken links if any
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		// Iterating through all links
		
		Iterator<WebElement> it = links.iterator();
		
		while(it.hasNext()) {
			url = it.next().getAttribute("href");
			
			System.out.println(url);
			
			if(url == null || url.isEmpty()) {
				System.out.println("URL is not configured");
				continue;
			}
			
			if(!url.startsWith(homePage)) {
				System.out.println("url belongs to other domain");
				continue;
				
			}
			
			try {
				huc =(HttpURLConnection)(new URL(url).openConnection());
			    huc.setRequestMethod("HEAD");
			    huc.connect();
			    
			    respCode = huc.getResponseCode();
			    
			    if(respCode >= 400) {
			    	System.out.println(url+"is broken link");
			    }
			    else {
			    	System.out.println(url+"is valid link");
			    }
			
			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void login() {
	driver.get(" 	/?intl=nosplash");
	//WebElement country = driver.findElement(By.xpath("(//h4[text()='Mexico'])[1]"));
	//country.click();
	//sign up
	}
	@Test
	public void signUp() {
		
		driver.get("https://www.bestbuy.com/?intl=nosplash");
	WebDriverWait wait=new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
	WebElement acc = driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']"));
	acc.click();
	
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='c-button c-button-outline c-button-sm create-account-btn']")));
	WebElement createacc = driver.findElement(By.xpath("//a[@class='c-button c-button-outline c-button-sm create-account-btn']"));
	createacc.click();
	
	WebElement firstname = driver.findElement(By.xpath("//input[@name='firstName']"));
	firstname.sendKeys("Rinni");
	
	WebElement secondName = driver.findElement(By.xpath("//input[@name='lastName']"));
	secondName.sendKeys("Kh");
	
	WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
	email.sendKeys("khare.niketa+92901@gmail.com");
	
	WebElement password = driver.findElement(By.xpath("//input[@id='fld-p1']"));
	password.sendKeys("Niketa@90983");
	
	WebElement confirmPW= driver.findElement(By.xpath("//input[@id='reenterPassword']"));
	confirmPW.sendKeys("Niketa@90983");
	
	WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
	phone.sendKeys("9639822471");
	
	WebElement checkBox = driver.findElement(By.xpath("//input[@id='is-recovery-phone']"));
	checkBox.click();
	
	WebElement createacc1 = driver.findElement(By.xpath("//button[text()='Create an Account']"));
	createacc1.click();	
	
	WebElement returnPage = driver.findElement(By.xpath("//a[text()='Return to previous page']"));
	returnPage.click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
	WebElement acc1 = driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']"));
	acc1.click();
//	
	//WebElement signIn = driver.findElement(By.xpath("//a[text()='Sign In']"));
	//signIn.click();
		
		
	}
	@Test
	public void signIn() throws Exception {
		driver.get("https://www.bestbuy.com/?intl=nosplash");
		WebDriverWait wait=new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
		WebElement acc = driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']"));
		acc.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='c-button c-button-secondary c-button-sm sign-in-btn']")));
		WebElement signIn = driver.findElement(By.xpath("//a[@class='c-button c-button-secondary c-button-sm sign-in-btn']"));
		signIn.click();
		
		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
		email.click();
		email.sendKeys("khare.niketa+9290@gmail.com");
		
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.click();
		password.sendKeys("Niketa@90983");
		
		WebElement signInButton = driver.findElement(By.xpath("//*[contains(@class,'c-button c-button-secondary c-button-lg ')]"));
	   signInButton.click();
		
	   WebElement returnTo = driver.findElement(By.xpath("//a[text()='Return to previous page']"));
	   returnTo.click();
		
		
//		WebElement signwithGoogle = driver.findElement(By.xpath("//button[@class='c-button c-button-outline c-button-lg c-button-block c-button-icon c-button-icon-leading svg-button social-button undefined']"));
//		
//		Actions actions = new Actions(driver);
//		actions.moveToElement(signwithGoogle);
//		actions.perform();
//		//WebElement signwithGoogle = driver.findElement(By.xpath("//button[@class='c-button c-button-outline c-button-lg c-button-block c-button-icon c-button-icon-leading svg-button social-button undefined']"));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='c-button c-button-outline c-button-lg c-button-block c-button-icon c-button-icon-leading svg-button social-button undefined']")));
//		signwithGoogle.click();
//		String currentWindow= driver.getWindowHandle();
//		driver.getWindowHandle()
//		Thread.sleep(3000);
//		WebElement googEmail = driver.findElement(By.xpath("//input[@id='identifierId']"));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='identifierId']")));
//		googEmail.click();
//		googEmail.sendKeys("Khare.niketa@gmail.com");
//		
		
		
	}
	@Test
	public void navigationAndTitleCheck() {
		driver.get("https://www.bestbuy.com/?intl=nosplash");
//		WebDriverWait wait=new WebDriverWait(driver, 20);
//		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
//			WebElement acc = driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']"));
//			acc.click();
//		WebElement bestbuy = driver.findElement(By.xpath("//a[@title='BestBuy.com']"));
//		System.out.println(bestbuy);
//		WebElement element = driver.findElement(By.name("q"));
//		element.sendKeys("BestBuy\n");
	   Assert.assertEquals(driver.getTitle(), "Best Buy | Official Online Store | Shop Now & Save");
	   WebElement bestbuy = driver.findElement(By.xpath("//a[@title='BestBuy.com']"));
	   Assert.assertTrue(bestbuy.isDisplayed());
	   WebElement topDeals = driver.findElement(By.linkText("Top Deals"));
	   Assert.assertTrue(topDeals.isDisplayed());
	   Assert.assertTrue(topDeals.isEnabled());
	   
	   WebElement DealoftheDay = driver.findElement(By.partialLinkText("Deal of the"));
	   Assert.assertTrue(DealoftheDay.isDisplayed());
	   DealoftheDay.click();
	   Assert.assertEquals(driver.getTitle(), "Deal of the Day: Electronics Deals - Best Buy");
	   
	   
	   		
		WebElement Totaltech = driver.findElement(By.linkText("Totaltech Membership"));
		Assert.assertTrue(Totaltech.isDisplayed());
		Totaltech.click();
		Assert.assertEquals(driver.getTitle(), "Best Buy Totaltech™ – Best Buy");
		
		WebElement Menu = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
		Assert.assertTrue(Menu.isDisplayed());
		//Menu.click();
		
		WebElement More = driver.findElement(By.xpath("//span[text()='More']"));
		More.click();
		
		WebElement creditCards = driver.findElement(By.linkText("Credit Cards"));
		Assert.assertTrue(creditCards.isDisplayed());
		creditCards.click();
		Assert.assertEquals(driver.getTitle(), "Best Buy Credit Card: Rewards & Financing");
		
		WebElement giftCards = driver.findElement(By.linkText("Gift Cards"));
		Assert.assertTrue(giftCards.isDisplayed());
		giftCards.click();
		Assert.assertEquals(driver.getTitle(), "Gifts Cards and E-Gift Cards - Best Buy");
		
		
		WebElement More1 = driver.findElement(By.xpath("//span[text()='More']"));
		More1.click();
		
		WebElement giftIdeas = driver.findElement(By.linkText("Gift Ideas"));
		Assert.assertTrue(giftIdeas.isDisplayed());
		giftIdeas.click();
		Assert.assertEquals(driver.getTitle(), "Gift Ideas 2022: Best Gifts to Give This Year - Best Buy");
		
		WebElement More2 = driver.findElement(By.xpath("//span[text()='More']"));
		More2.click();
		WebElement HealthNwellness = driver.findElement(By.xpath("(//a[text()='Health & Wellness'])[2]"));
		Assert.assertTrue(HealthNwellness.isDisplayed());
		HealthNwellness.click();
		Assert.assertEquals(driver.getTitle(), "Health & Wellness Solutions & Technology - Best Buy");
		
		WebElement More3 = driver.findElement(By.xpath("//span[text()='More']"));
		More3.click();
		WebElement bestBuyoutlet = driver.findElement(By.xpath("(//a[text()='Best Buy Outlet'])[2]"));
		Assert.assertTrue(bestBuyoutlet.isDisplayed());
		bestBuyoutlet.click();
		Assert.assertEquals(driver.getTitle(), "Best Buy Outlet: Clearance Electronics Outlet Store – Best Buy");
		
		
		WebElement More4 = driver.findElement(By.xpath("//span[text()='More']"));
		More4.click();
		WebElement bestBuyBusiness = driver.findElement(By.xpath("(//a[text()='Best Buy Business'])[2]"));
		Assert.assertTrue(bestBuyBusiness.isDisplayed());
		bestBuyBusiness.click();
		Assert.assertEquals(driver.getTitle(), "Best Buy for Business - Best Buy");
	
		   
		WebElement More5 = driver.findElement(By.xpath("//span[text()='More']"));
		More5.click();
		WebElement close = driver.findElement(By.className("accountMenuCloseText"));
		Assert.assertTrue(close.isDisplayed());
		close.click();
	}
	
	    @Test
		public void testing() {
			
	    driver.get("https://www.bestbuy.com/?intl=nosplash");
		WebElement recentlyViewed = driver.findElement(By.xpath("//span[text()='Recently Viewed']"));
		Assert.assertTrue(recentlyViewed.isDisplayed());
		recentlyViewed.click();
		WebElement message = driver.findElement(By.xpath("//div//h2[@class='recently-veiwed-title']"));
		System.out.println(message);
	    Assert.assertTrue(message.isDisplayed());
	    
	    WebElement orderStatus = driver.findElement(By.xpath("//span[text()='Order Status']"));
	    orderStatus.click();
	    WebElement orderMessage = driver.findElement(By.xpath("//h2[text()='Order Status']"));
	    Assert.assertTrue(orderMessage.isDisplayed());
		
		
	    WebElement savedItems = driver.findElement(By.xpath("//span[text()='Saved Items']"));
	    savedItems.click();
	    WebElement recentlyViewed1 = driver.findElement(By.xpath("//h2[@class='recently-viewed-title']"));
	    Assert.assertTrue(recentlyViewed1.isDisplayed());
		
	}
	    @Test
	    public void footerLinksTesting() {
	    	driver.get("https://www.bestbuy.com/?intl=nosplash");
	    	WebElement accesiblity = driver.findElement(By.xpath("//a[text()='Accessibility']"));
	    	accesiblity.click();
	    	Assert.assertEquals(driver.getTitle(), "Accessibility - Best Buy");
	    	
	    	WebElement termsAndcond = driver.findElement(By.linkText("Terms & Conditions"));
	    	termsAndcond.click();
	    	Assert.assertEquals(driver.getTitle(), "BestBuy.com Terms and Conditions");
	    	

	    	WebElement privacy = driver.findElement(By.xpath("//a[text()='Privacy']"));
	    	privacy.click();
	    	Assert.assertEquals(driver.getTitle(), "Privacy Policy Hub - Best Buy");
	    	
	    	
	    	
	    }
	    @Test
	    public void searchAndAdd(){
	    	driver.get("https://www.bestbuy.com/?intl=nosplash");
	    	WebElement searchbox = driver.findElement(By.className("search-input"));
	    	searchbox.click();
	    	searchbox.sendKeys("bag");
	    	WebElement classicBag= driver.findElement(By.partialLinkText("Samsonite - Classic Leather Backpack for 15.6"));
	    	classicBag.click();
	    	WebElement addToCart= driver.findElement(By.xpath("//button[@data-button-state='ADD_TO_CART']"));
	    	addToCart.click();
	    	
	    	
	    	
	    	
	    }
	    @Test
	    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
	    	
	    	//Convert web driver object to TakeScreenshot
	    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
	    	//Call getScreenshotAs method to create image file
	    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	    	//Move image file to new destination
	    	File DestFile=new File(fileWithPath);
	    	//Copy file at destination
	    	FileUtils.copyFile(SrcFile, DestFile);
	    	}
	    	

	    @Test
	    public void shopbyDepartment() throws Exception {
	    	WebDriverWait wait = new WebDriverWait(driver,30);
	    	driver.get("https://www.bestbuy.com/?intl=nosplash");
	    	WebElement Menu = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
	    	Menu.click();
	    	Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Shop by Department']")).isDisplayed());
	    	WebElement tvNHome = driver.findElement(By.xpath("//button[text()='TV & Home Theater']"));
	    	tvNHome.click();
	    	WebElement TvsBysize = driver.findElement(By.xpath("//button[text()='TVs by Size']"));
	    	TvsBysize.click();
	    	WebElement sizeLarge = driver.findElement(By.xpath("//a[@href='/site/tvs/85-inch-tvs/pcmcat1571250794220.c?id=pcmcat1571250794220']"));
	    	sizeLarge.click();
	    	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='85-Inch or Larger TVs']")).isDisplayed());
	    	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fulfillment-add-to-cart-button-99480237']//button[text()='Add to Cart'] ")));
	    	
	    	Thread.sleep(3000);
	    	WebElement addtoCart = driver.findElement(By.xpath("(//button[text()='Add to Cart'])[1] "));
	    	addtoCart.click();
	    	WebElement gotoCart = driver.findElement(By.xpath("//a[text()='Go to Cart']"));
	    	gotoCart.click();
	    	
	    	WebElement close = driver.findElement(By.xpath("//div[@class='c-modal-grid col-xs-10']//button[@aria-label='Close']"));
	    	close.click();
	    	
	    	
	    	WebElement checkOut = driver.findElement(By.xpath("//button[text()='Checkout']"));
	    	checkOut.click();
	    	

	    	WebElement contAsGuest = driver.findElement(By.xpath("//button[text()='Continue as Guest']"));
	    	contAsGuest.click();
	    	
          
	    	WebElement email = driver.findElement(By.xpath("//input[@id='user.emailAddress']"));
	    	email.click();
	    	email.sendKeys("khare.niketa@gmail.com");
	    	
	    	WebElement phoneNo = driver.findElement(By.xpath("//input[@id='user.phone']"));
	    	phoneNo.click();
	    	phoneNo.sendKeys("147 078 9108");
	    	
//	    	WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
//	    	checkBox.click();
	    	
	    	WebElement contToPayment = driver.findElement(By.xpath("//span[text()='Continue to Payment Information']"));
	    	 contToPayment.click(); 
	    	 
	    //	 Assert.assertTrue(driver.findElement(By.xpath(//span[text()='Getting your order']).
	    			 Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Getting your order']")).isDisplayed());
	    }
	    
	     @AfterMethod  
	    public void close() {
	    	 driver.close();
		
	}
	

}

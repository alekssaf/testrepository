package com.mycompany.mobilebg.Pages;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DetailSearchPageTest extends TestCase {

	private static WebDriver driver = null;

	@Override
	protected void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(input);
		// try {
		// prop.setProperty("site", "http://rbb.bg");
		// prop.store(new FileOutputStream("config.properties"), null);
		// }
		// catch (Exception e){
		// }
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.get(prop.getProperty("site"));
	}

	@Override
	protected void tearDown() throws Exception {
		driver.quit();
	}

	public void testIsAnySearchResultExist() {
		SearchPage searchPage = new SearchPage(driver);
		DetailSearchPage detailSearchPage = searchPage.doDetailSearch();
		detailSearchPage.setupDetailSearch(true, true, true, "10000", "20000", "2012", "2014", "3");
		ResultPage resultPage = detailSearchPage.doDetailSearch();
		Assert.assertFalse(driver.getPageSource().contains("Няма намерени обяви - Автомобили"));

	}

	public void testIsFirstSortFilterChosen() {
		SearchPage searchPage = new SearchPage(driver);
		DetailSearchPage detailSearchPage = searchPage.doDetailSearch();
		detailSearchPage.setupDetailSearch(true, true, true, "10000", "20000", "2012", "2014", "1");
		ResultPage resultPage = detailSearchPage.doDetailSearch();
		Assert.assertTrue(driver.getPageSource().contains("VIP/Марка/Модел/Цена"));
	}

	public void testIsLowPriceFilterWorking() {
		SearchPage searchPage = new SearchPage(driver);
		DetailSearchPage detailSearchPage = searchPage.doDetailSearch();
		detailSearchPage.setupDetailSearch(true, true, true, "10000", "20000", "2012", "2014", "3");
		ResultPage resultPage = detailSearchPage.doDetailSearch();
		Assert.assertTrue(resultPage.getFirstCarPrice() >= 10000);

	}

	public void testIsFilterCorrectionButtonWorking() {
		SearchPage searchPage = new SearchPage(driver);
		DetailSearchPage detailSearchPage = searchPage.doDetailSearch();
		detailSearchPage.setupDetailSearch(true, true, true, "10000", "20000", "2012", "2014", "3");
		ResultPage resultPage = detailSearchPage.doDetailSearch();
		detailSearchPage = resultPage.doFilterCorrection();
		Assert.assertEquals("Incorrect min price", "10000", detailSearchPage.getMinPrice());
		Assert.assertEquals("Incorrect max price", "20000", detailSearchPage.getMaxPrice());
	}

	public void testIsNewSearchButtonWorking() {
		SearchPage searchPage = new SearchPage(driver);
		DetailSearchPage detailSearchPage = searchPage.doDetailSearch();
		detailSearchPage.setupDetailSearch(true, true, true, "10000", "20000", "2012", "2014", "3");
		ResultPage resultPage = detailSearchPage.doDetailSearch();
		detailSearchPage = resultPage.doNewSearch();
		Assert.assertEquals("Min price is not empty", "", detailSearchPage.getMinPrice());
		Assert.assertEquals("Max price is not empty", "", detailSearchPage.getMaxPrice());
	}
}

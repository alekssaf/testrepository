package com.mycompany.mobilebg.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;
    private static String TITLE_MATCH = "Mobile.bg – Българският автомобилен пазар: нови и втора употреба автомобили, джипове, камиони, мотоциклети...";

    @FindBy(xpath = "//*[@ class='linkSearch']")
    private WebElement detailSearchButton;
    @FindBy(xpath = "//*[@ value ='Т Ъ Р С И']")
    private WebElement searchButton;

    public SearchPage(WebDriver driver) {

        if (!driver.getTitle().contains(TITLE_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public DetailSearchPage doDetailSearch() {
        detailSearchButton.click();
        return new DetailSearchPage(driver);
    }

    public ResultPage doSearch() {
        searchButton.click();
        return new ResultPage(driver);
    }

}

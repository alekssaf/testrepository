package com.mycompany.mobilebg.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DetailSearchPage2 {

    private WebDriver driver;
    private static String TITLE_MATCH = "Намери обяви за продажба на Автомобили в Mobile.bg";

    @FindBy(id = "eimg95")
    private WebElement has4DoorsCheckBox;
    @FindBy(name = "f22")
    private WebElement hasPictureCheckBox;
    @FindBy(xpath = "//*[@ name='f24'][@ value ='1']")
    private WebElement privateUserCheckBox;
    @FindBy(name = "f7")
    private WebElement fromPrice;
    @FindBy(name = "f8")
    private WebElement toPrice;
    @FindBy(name = "f10")
    private WebElement fromYear;
    @FindBy(name = "f11")
    private WebElement toYear;
    @FindBy(name = "f20")
    private WebElement sortType;
    @FindBy(xpath = "//*[@ value ='Т Ъ Р С И']")
    private WebElement searchButton;

    public DetailSearchPage2(WebDriver driver) {

        if (!driver.getTitle().contains(TITLE_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private void hasPictures(boolean status) {
        if (status) {
            hasPictureCheckBox.click();
        }
    }

    private void has4Doors(boolean status) {
        if (status) {
            has4DoorsCheckBox.click();
        }
    }

    private void setPrivateUserOnly(boolean status) {
        if (status) {
            privateUserCheckBox.click();
        }
    }

    private void setMinPrice(String minPrice) {
        fromPrice.sendKeys(minPrice);

    }

    private void setMaxPrice(String maxPrice) {
        toPrice.sendKeys(maxPrice);
    }

    private void setYear(String minYear, String maxYear) {
        Select fromPriceSelect = new Select(fromYear);
        Select toPriceSelect = new Select(toYear);
        fromPriceSelect.selectByValue(minYear);
        toPriceSelect.selectByValue(maxYear);
    }

    private void setSort(String value) {
        Select sortTypeSelect = new Select(sortType);
        sortTypeSelect.selectByValue(value);
    }

    
    public String getMinPrice() {
        return fromPrice.getAttribute("value");

    }

    public String getMaxPrice() {
        return toPrice.getAttribute("value");
    }
    
    public void setupDetailSearch(boolean doorsStatus, boolean pictureStatus, boolean privateUserStatus,
            String lowPrice, String highPrice, String fromYear, String toYear, String sortType) {

        has4Doors(doorsStatus);
        hasPictures(pictureStatus);
        setPrivateUserOnly(privateUserStatus);
        setMinPrice(lowPrice);
        setMaxPrice(highPrice);
        setYear(fromYear, toYear);
        setSort(sortType);

    }

    public ResultPage doDetailSearch() {
        searchButton.click();
        return new ResultPage(driver);
    }
    

}

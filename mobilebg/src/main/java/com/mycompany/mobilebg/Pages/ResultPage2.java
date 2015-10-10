package com.mycompany.mobilebg.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage2 {

    private WebDriver driver;
    private static String TITLE_MATCH = "Авто обяви (за Автомобили) марка :: Mobile.bg";

    @FindBy(xpath = "//a[text()='КОРЕКЦИЯ НА ТЪРСЕНЕТО']")
    private WebElement filterCorrectionButton;
    @FindBy(xpath = "//a[text()='НОВО ТЪРСЕНЕ']")
    private WebElement newSearchButton;
    @FindBy(xpath = "//table[4]//table[4]//span[@class='price']")
    private WebElement firstCarPrice;

    public ResultPage2(WebDriver driver) {

        if (!driver.getTitle().contains(TITLE_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public int getFirstCarPrice() {
        return Integer.valueOf(firstCarPrice.getText().substring(0, (firstCarPrice.getText().length() - 3)).replace(" ", ""));

    }

    public DetailSearchPage doFilterCorrection() {
        filterCorrectionButton.click();
        return new DetailSearchPage(driver);
    }

    public DetailSearchPage doNewSearch() {
        newSearchButton.click();
        return new DetailSearchPage(driver);
    }

}

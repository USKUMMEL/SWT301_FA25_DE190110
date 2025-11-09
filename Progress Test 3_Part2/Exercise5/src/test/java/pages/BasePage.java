package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        WebElement element = waitForClickable(locator);

        // Scroll element vào giữa màn hình cho chắc
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Nếu bị che bởi iframe / quảng cáo -> remove nhanh rồi JS click
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll('iframe, [id*=\"google_ads\"], [id*=\"adplus-anchor\"]').forEach(e => e.remove());"
                );
            } catch (JavascriptException ignore) {
                // nếu fail cũng kệ, chuyển sang JS click trực tiếp
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
        // Option: dọn rác ads ngay khi load trang demoqa
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('iframe, [id*=\"google_ads\"], [id*=\"adplus-anchor\"]').forEach(e => e.remove());"
            );
        } catch (JavascriptException ignore) {}
    }

    protected void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
}

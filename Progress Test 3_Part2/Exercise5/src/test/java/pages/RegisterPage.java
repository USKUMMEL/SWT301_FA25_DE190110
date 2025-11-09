package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Locators chính
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By mobile = By.id("userNumber");
    private final By address = By.id("currentAddress");
    private final By submitBtn = By.id("submit");
    private final By modalTitle = By.id("example-modal-sizes-title-lg");

    // Helper locators dynamic
    private By genderOption(String genderText) {
        // genderText: "Male", "Female", "Other"
        return By.xpath("//label[text()='" + genderText + "']");
    }

    private By hobbyOption(String hobbyText) {
        // hobbyText: "Sports", "Reading", "Music"
        return By.xpath("//label[text()='" + hobbyText + "']");
    }

    // Actions
    public void open() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillBasicInfo(String fName,
                              String lName,
                              String mail,
                              String gender,
                              String phone,
                              String hobby,
                              String addr) {

        type(firstName, fName);
        type(lastName, lName);
        type(email, mail);

        if (gender != null && !gender.isBlank()) {
            click(genderOption(gender));
        }

        type(mobile, phone);

        if (hobby != null && !hobby.isBlank()) {
            click(hobbyOption(hobby));
        }

        type(address, addr);
    }

    public void submitForm() {
        // đảm bảo nút submit nhìn thấy được
        scrollBy(0, 600);
        click(submitBtn);
    }

    public boolean isSubmissionSuccess() {
        return isVisible(modalTitle)
                && getText(modalTitle).contains("Thanks for submitting the form");
    }
}

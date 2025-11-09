package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("DemoQA Automation Practice Form - CSV Driven Tests")
public class RegisterTest extends BaseTest {

    static RegisterPage registerPage;

    @BeforeAll
    static void initPage() {
        registerPage = new RegisterPage(driver);
    }

    @ParameterizedTest(name = "Register with: {0} {1}, {2}, {3}, {4}, {5}, expect: {7}")
    @CsvFileSource(resources = "/register-data.csv", numLinesToSkip = 1)
    @Order(1)
    @DisplayName("Submit form with data from CSV")
    void testFormWithCsvData(String firstName,
                             String lastName,
                             String email,
                             String gender,
                             String mobile,
                             String hobby,
                             String address,
                             String expectedResult) {

        // Chuẩn hóa dữ liệu (tránh lỗi khoảng trắng trong file CSV)
        firstName = trimOrEmpty(firstName);
        lastName = trimOrEmpty(lastName);
        email = trimOrEmpty(email);
        gender = trimOrEmpty(gender);
        mobile = trimOrEmpty(mobile);
        hobby = trimOrEmpty(hobby);
        address = trimOrEmpty(address);
        expectedResult = trimOrEmpty(expectedResult).toLowerCase();

        registerPage.open();
        registerPage.fillBasicInfo(firstName, lastName, email, gender, mobile, hobby, address);
        registerPage.submitForm();

        boolean success = registerPage.isSubmissionSuccess();

        if ("success".equals(expectedResult)) {
            assertTrue(success,
                    "Expected SUCCESS but submission popup not shown with valid data set.");
        } else {
            assertFalse(success,
                    "Expected ERROR but form was submitted successfully with invalid data set.");
        }
    }

    private String trimOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }
}

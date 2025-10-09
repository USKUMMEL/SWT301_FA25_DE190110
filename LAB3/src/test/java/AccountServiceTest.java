
import HuynhQuangHuy.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService service;
    private static final String RESULT_FILE = "UnitTestResult.txt";

    @BeforeEach
    void setUp() {
        // Khởi tạo đối tượng AccountService trước mỗi test case
        service = new AccountService();
    }

    // ---------------------------------------------------------------
    // 1️ Test cơ bản cho hàm isValidEmail()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("isValidEmail returns true for valid email")
    void testValidEmail() {
        boolean result = service.isValidEmail("test@example.com");
        logResult("isValidEmail", "test@example.com", "N/A", "N/A", true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("isValidEmail returns false for invalid email (missing @)")
    void testInvalidEmailNoAtSymbol() {
        boolean result = service.isValidEmail("testexample.com");
        logResult("isValidEmail", "testexample.com", "N/A", "N/A", false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("isValidEmail returns false for invalid email (missing dot)")
    void testInvalidEmailNoDot() {
        boolean result = service.isValidEmail("test@examplecom");
        logResult("isValidEmail", "test@examplecom", "N/A", "N/A", false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("isValidEmail returns false for null email")
    void testInvalidEmailNull() {
        boolean result = service.isValidEmail(null);
        logResult("isValidEmail", "null", "N/A", "N/A", false, result);
        assertFalse(result);
    }

    // ---------------------------------------------------------------
    // 2️ Parameterized Tests cho registerAccount() với CSV
    // ---------------------------------------------------------------

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("registerAccount() validates input based on business rules")
    void testRegisterAccountFromCsv(String username, String password, String email, boolean expected) {
        boolean actual = service.registerAccount(username, password, email);

        // In thông báo ra terminal (PASS/FAIL)
        String status = (actual == expected) ? "✅ PASS" : "❌ FAIL";
        String message = "";

        if (!status.equals("✅ PASS")) {
            if (username == null || username.trim().isEmpty()) {
                message = "→ Username is empty or null but was accepted (should be rejected).";
            } else if (password == null || password.length() <= 6) {
                message = "→ Password ≤ 6 characters but was accepted (should be rejected).";
            } else if (email == null || !email.contains("@") || !email.contains(".")) {
                message = "→ Invalid email format but was accepted (should be rejected).";
            } else if (expected && (!service.isValidEmail(email)
                    || username.trim().isEmpty()
                    || password.length() <= 6)) {
                message = "→ Valid input was incorrectly rejected (should be accepted).";
            } else {
                message = "→ Output does not match business logic.";
            }
        } else {
            message = "→ Testcase passed successfully.";
        }

        System.out.printf("%s | Input: [username='%s', password='%s', email='%s'] %s%n",
                status, username, password, email, message);

        // Ghi kết quả vào file UnitTestResult.txt
        logResult("registerAccount", username, password, email, expected, actual);

        // Kiểm tra kết quả
        assertEquals(expected, actual, () ->
                String.format("Failed for input: username='%s', password='%s', email='%s'",
                        username, password, email));
    }

    // ---------------------------------------------------------------
    // 3️ Test đơn lẻ cho các trường hợp đặc biệt
    // ---------------------------------------------------------------

    @Test
    @DisplayName("registerAccount rejects empty username")
    void testEmptyUsername() {
        boolean result = service.registerAccount("", "validPass123", "mail@test.com");
        logResult("registerAccount", "", "validPass123", "mail@test.com", false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("registerAccount rejects short password")
    void testShortPassword() {
        boolean result = service.registerAccount("john", "short", "john@mail.com");
        logResult("registerAccount", "john", "short", "john@mail.com", false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("registerAccount rejects invalid email")
    void testInvalidEmailFormat() {
        boolean result = service.registerAccount("john", "password123", "johnmail.com");
        logResult("registerAccount", "john", "password123", "johnmail.com", false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("registerAccount accepts valid credentials")
    void testValidAccountRegistration() {
        boolean result = service.registerAccount("john123", "password123", "john@mail.com");
        logResult("registerAccount", "john123", "password123", "john@mail.com", true, result);
        assertTrue(result);
    }

    // ---------------------------------------------------------------
    // 4️ Hàm ghi kết quả vào file UnitTestResult.txt
    // ---------------------------------------------------------------

    private void logResult(String function, String username, String password, String email,
                           boolean expected, boolean actual) {
        String status = (expected == actual) ? "PASS" : "FAIL";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(fmt);

        try (FileWriter fw = new FileWriter(RESULT_FILE, true)) {
            fw.write(String.format("[%s] Function=%s | username=%s | password=%s | email=%s | expected=%s | result=%s | %s%n",
                    time, function, username, password, email, expected, actual, status));
        } catch (IOException e) {
            System.err.println("⚠ Error writing UnitTestResult.txt: " + e.getMessage());
        }
    }
}

package HuynhQuangHuy;

public class AccountService {

    // Kiểm tra email hợp lệ
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }

    // Đăng ký tài khoản
    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.isEmpty()) return false;
        if (password == null || password.length() <= 6) return false;
        if (!isValidEmail(email)) return false;
        return true;
    }
}

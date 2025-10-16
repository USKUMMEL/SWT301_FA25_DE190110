package huynhquanghuy;

import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public void display() {
        LOGGER.log(Level.INFO, "Name: {0}, Age: {1}", new Object[]{name, age});
    }

    public static void main(String[] args) {
        User user = new User("Huy", 21);
        user.display();
    }
}

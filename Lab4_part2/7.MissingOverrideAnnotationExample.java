package huynhquanghuy;

import java.util.logging.Logger;

class Animal {
    private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

    void speak() {
        LOGGER.info("Animal speaks");
    }
}

class Dog extends Animal {
    private static final Logger LOGGER = Logger.getLogger(Dog.class.getName());

    @Override
    void speak() {
        LOGGER.info("Dog barks");
    }
}

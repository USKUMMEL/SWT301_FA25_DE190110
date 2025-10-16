package huynhquanghuy;

import java.util.logging.Logger;

interface Drawable {
    void draw();
}

class Circle implements Drawable {

    private static final Logger LOGGER = Logger.getLogger(Circle.class.getName());

    @Override
    public void draw() {
        LOGGER.info("Drawing a circle");
    }
}

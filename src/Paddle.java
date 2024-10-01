import java.awt.*;

public class Paddle {
    private int x, width, height, speed;
    private boolean left, right;
    private int originalWidth;

    public Paddle() {
        x = 200;
        width = 60;
        originalWidth = width;
        height = 10;
        speed = 5;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void update() {
        if (left && x > 0) {
            x -= speed;
        }
        if (right && x + width < 400) {
            x += speed;
        }
    }

    public void increaseSize() {
        width = 100; // Increase paddle size
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                width = originalWidth; // Reset to original size after 5 seconds
            }
        }, 5000);
    }

    public Rectangle getRect() {
        return new Rectangle(x, 370, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, 370, width, height);
    }
}

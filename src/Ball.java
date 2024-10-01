import java.awt.*;

public class Ball {
    private int x, y, diameter, xSpeed, ySpeed;

    public Ball() {
        diameter = 20;
        reset();
    }

    public void reset() {
        x = 200;
        y = 200;
        xSpeed = 2;
        ySpeed = 3;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;

        if (x <= 0 || x + diameter >= 400) {
            xSpeed = -xSpeed;
        }
        if (y <= 0) {
            ySpeed = -ySpeed;
        }
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }

    public void increaseSpeed() {
        xSpeed *= 1.2; // Increase speed by 20%
        ySpeed *= 1.2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
}

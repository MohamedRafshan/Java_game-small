import java.awt.*;

public class PowerUp {
    private int x, y, size;

    public PowerUp(int startX, int startY) {
        x = startX;
        y = startY;
        size = 15;
    }

    public void update() {
        y += 2; // Power-up falls down the screen
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, size, size); // Draw the power-up as a green circle
    }
}

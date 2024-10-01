import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Ball ball;
    private Paddle paddle;
    private PowerUp powerUp;
    private Timer timer;
    private int score;
    private int level;
    private int hits;

    public GamePanel() {
        ball = new Ball();
        paddle = new Paddle();
        timer = new Timer(5, this);  // Timer triggers every 5ms
        this.setPreferredSize(new Dimension(400, 400));
        this.setFocusable(true);
        this.addKeyListener(this);
        score = 0;
        level = 1;
        hits = 0;
        powerUp = null; // No power-up initially
        timer.start();  // Start the timer for game updates
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.update();
        paddle.update();
        checkCollisions();
        checkPowerUp();
        repaint();
    }

    private void checkCollisions() {
        if (ball.getRect().intersects(paddle.getRect())) {
            ball.reverseYDirection();
            hits++;
            score += 10;  // Increase score for every paddle hit

            // Randomly drop a power-up
            if (new Random().nextInt(10) < 2 && powerUp == null) {
                powerUp = new PowerUp(ball.getX(), ball.getY());
            }

            // Progress to next level after 5 hits
            if (hits % 5 == 0) {
                level++;
                ball.increaseSpeed();  // Increase ball speed for higher levels
            }
        }

        // If the ball falls off the screen, reset its position
        if (ball.getY() > getHeight()) {
            ball.reset();
        }
    }

    private void checkPowerUp() {
        if (powerUp != null && powerUp.getRect().intersects(paddle.getRect())) {
            paddle.increaseSize();  // Increase paddle size when collecting power-up
            powerUp = null;  // Remove power-up after collection
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);

        // Draw score and level
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + level, 300, 20);

        // Draw power-up if it exists
        if (powerUp != null) {
            powerUp.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setLeft(true);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setLeft(false);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.setRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}

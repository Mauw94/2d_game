package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveSpeed;

    public Background(String s, double ms) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveSpeed = ms;
        } catch (Exception e) {}
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveSpeed) % GamePanel.WIDTH;
        this.y = (y * moveSpeed) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        this.x += dx;
        this.y += dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);

        if (x < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
        }
        if (x > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
        }
    }

}

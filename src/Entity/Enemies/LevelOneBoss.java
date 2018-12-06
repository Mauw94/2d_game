package Entity.Enemies;

import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelOneBoss extends Enemy {

    private BufferedImage bossImage;

    public LevelOneBoss(TileMap tm) {

        super(tm);

        moveSpeed = 0.2;
        maxSpeed =  0.2;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 25;
        damage = 0;

        try {
            bossImage = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/level1_boss.gif"));
        } catch (Exception e) {}

        right = true;
        facingRight = true;
        // post x 3050 y 200
    }

    private void getNextPosition() {

        // movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }

        if (falling) {
            dy += fallSpeed;
        }
    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        // if hit walls, go other direction
        if (right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;
        }
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        g.drawImage(bossImage,
                (int)(x + xmap - width / 2),
                (int)(y + ymap - height / 2),
                null);
    }

}

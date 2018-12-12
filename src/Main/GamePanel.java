package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int WIDTH = 320;
    public static int HEIGHT = 240;
    public static int SCALE = 2;

    private Thread gameThread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(this);
            addKeyListener(this);
            gameThread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
    }

    public void run() {
        init();

        long start, elapsed, wait;

        while (running) {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;

            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {}

        }
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT* SCALE, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }

}

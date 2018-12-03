package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DeadState extends GameState {

    private Background bg;
    private int currentChoice = 0;
    private String[] options = {
            "Retry",
            "Main menu"
    };
    private String title;
    private Font titleFont;

    public DeadState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-1.9, 0);
            title = "You have died.";
            titleFont = new Font("Century Gother", Font.PLAIN, 28);

        } catch (Exception e) {}
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(titleFont);
        g.setColor(new Color(128, 0, 0));
        g.drawString(title, 80, 70);
        DrawTextToScreen.drawOptions(currentChoice, options, g);
    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice > options.length -1) {
                currentChoice = 0;
            }
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length -1;
            }
        }

    }

    @Override
    public void keyReleased(int k) {

    }
}

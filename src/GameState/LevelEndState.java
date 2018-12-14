package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelEndState extends GameState {

    private Background bg;
    private String text;
    private String[] options = {
            "Next level",
            "Main menu"
    };
    private Font textFont;
    private int currentChoice = 0;

    public LevelEndState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", -0.1);
            bg.setVector(-0.5, 0);
            text = "Level finished!";
            textFont = new Font("Century Gothic", Font.PLAIN, 14);
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
        g.setFont(textFont);
        g.setColor(Color.BLACK);
        g.drawString(text, 80, 70);
        DrawTextToScreen.drawOptions(currentChoice, options, g);
    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL2STATE);
        } else if (currentChoice == options.length - 1) {
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
            if (currentChoice > options.length - 1) {
                currentChoice = 0;
            }
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}

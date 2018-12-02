package GameState;

import Main.Game;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DeadState extends GameState {

    private Background bg;
    private int currentChoice = 0;
    private String[] choices = {
            "Retry",
            "Back to main menu"
    };

    public DeadState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.9, 0);

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
        for (int i = 0; i < choices.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(choices[i], 145, 140 + i * 15);
        }
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
            if (currentChoice > choices.length -1) {
                currentChoice = 0;
            }
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = choices.length -1;
            }
        }

    }

    @Override
    public void keyReleased(int k) {

    }
}

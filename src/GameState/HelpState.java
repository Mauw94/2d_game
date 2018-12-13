package GameState;

import TileMap.Background;

import java.awt.*;

public class HelpState extends GameState {

    private String[] info = {
            "Use",
            "UP/DOWN/LEFT/RIGHT",
            "arrows to move",
            "W to jump",
            "R to scratch attack",
            "F to fireball attack",
            "E to glide"
    };
    private Background bg;

    public HelpState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.5, 0);

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
        DrawTextToScreen.drawHelp(info, g);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}

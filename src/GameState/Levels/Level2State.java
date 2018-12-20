package GameState.Levels;

import Entity.HUD;
import Entity.Inventory;
import Entity.Player;
import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level2State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
    private Inventory inventory;

    private HUD hud;

    public Level2State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level2.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        bg.setPosition(100, 100);

        player = new Player(tileMap);
        player.setPosition(100, 200);

        inventory = player.getInventory();

        hud = new HUD(player);
    }

    @Override
    public void update() {
        player.update();
        setWorldPositions();
    }

    private void setWorldPositions() {
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety());

        bg.setPosition(tileMap.getx(), tileMap.gety());
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        tileMap.draw(g);
        player.draw(g);
        inventory.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_W) player.setJumping(true);
        if(k == KeyEvent.VK_E) player.setGliding(true);
        if(k == KeyEvent.VK_R) player.setScratching();
        if(k == KeyEvent.VK_F) player.setFiring();
        if(k == KeyEvent.VK_A) inventory.cycleLeft();
        if(k == KeyEvent.VK_D) inventory.cycleRight();
        if(k == KeyEvent.VK_SPACE) inventory.useSelectedInventoryItem();
    }

    @Override
    public void keyReleased(int k) {

    }
}

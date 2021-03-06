package GameState.Levels;

import Entity.*;
import Entity.Enemies.LevelOneBoss;
import Entity.Enemies.Slugger;
import Entity.Items.BoostPotion;
import Entity.Items.HealthPotion;
import Entity.Items.Item;
import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    private ArrayList<Enemy> enemies;
    // add explosions

    private HUD hud;
    private Inventory inventory;

    private ArrayList<Item> itemsInWorld;

    private boolean levelOneEnd;
    private Score levelScore;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        bg.setPosition(100, 100);

        levelOneEnd = false;
        levelScore = new Score();

        player = new Player(tileMap);
        player.setPosition(100, 200);

        inventory = player.getInventory();

        // explosions

        hud = new HUD(player);

        populateEnemies();
        createItemsInWorld();
    }

    private void populateEnemies() {
        enemies = new ArrayList<>();
        Slugger s;
        Point[] points = new Point[] {
                new Point(200, 100),
                new Point(300, 100),
                new Point(860, 200),
                new Point(1525, 200),
                new Point(1680, 200),
                new Point(1800, 200)
        };
        for (int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }

        LevelOneBoss b = new LevelOneBoss(tileMap);
        b.setPosition(3050, 200);
        enemies.add(b);
    }

    private void createItemsInWorld() {
        itemsInWorld = new ArrayList<>();
        Item healthPotion = new HealthPotion(tileMap, Item.HEALTH_POTION, player);
        healthPotion.setPosition(180, 100);
        itemsInWorld.add(healthPotion);

        Item boostPotion = new BoostPotion(tileMap, Item.BOOST_POTION, player);
        boostPotion.setPosition(840, 200);
        itemsInWorld.add(boostPotion);
    }

    @Override
    public void update() {

        /*
        if the world positions are updated before the player
        the player animations lag..
         */
        player.update();

        setWorldPositions();

        player.checkAttack(enemies);
        player.checkItemPickup(itemsInWorld);

        updateWorldEnemies();

        updateWorldItems();

        if (player.isDead()) { gsm.setState(GameStateManager.DEADSTATE); }
        if (levelOneEnd) {
            System.out.println(this.levelScore.getLevelScore());
            gsm.setState(GameStateManager.LEVELENDSTATE);
        }
    }

    private void setWorldPositions() {
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety());

        bg.setPosition(tileMap.getx(), tileMap.gety());
    }

    private void updateWorldEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) {
                // enemy can drop an item
                if (e.dropSmallLoot()) {
                    int itemType = e.getDroppedItemType();
                    Item item = Item.dropItemFromEnemy(tileMap, itemType, player);
                    if (item != null) {
                        item.setPosition(e.getx(), e.gety());
                        itemsInWorld.add(item);
                    }
                }
                if (e.isDeadByScratching()) {
                    this.levelScore.addPoints(3);
                } else {
                    this.levelScore.addPoints(2);
                }
                if (e instanceof LevelOneBoss) {
                    this.levelScore.addPoints(5);
                    levelOneEnd = true;
                }
                enemies.remove(i);
                i--;
            }
        }
    }

    private void updateWorldItems() {
        for (int i = 0; i < itemsInWorld.size(); i++) {
            Item item = itemsInWorld.get(i);
            item.update();
            if (item.shouldRemove()) {
                inventory.addItemToInventory(item);
                itemsInWorld.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        tileMap.draw(g);
        player.draw(g);
        hud.draw(g);
        inventory.draw(g);

        // draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        // draw items in the world
        for (int i = 0; i < itemsInWorld.size(); i++) {
            itemsInWorld.get(i).draw(g);
        }
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
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_W) player.setJumping(false);
        if(k == KeyEvent.VK_E) player.setGliding(false);
    }
}

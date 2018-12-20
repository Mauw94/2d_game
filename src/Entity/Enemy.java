package Entity;

import TileMap.TileMap;

import java.util.Random;

public class Enemy extends MapObject {
    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected boolean deadByScratching;
    protected int damage;

    protected boolean flinching;
    protected long flinchTimer;

    protected Random rndLoot;

    public Enemy(TileMap tm) {
        super(tm);
    }

    public boolean isDead() { return dead; }
    public boolean isDeadByScratching() { return deadByScratching; }
    public int getDamage() { return damage; }

    public void hit(int damage, boolean playerAttack) {
        if (dead || flinching) return;
        health -= damage;
        deadByScratching = playerAttack;
        if (health < 0) health = 0;
        if (health == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();
    }

    public boolean dropSmallLoot() {
        rndLoot = new Random();
        int loot = rndLoot.nextInt(3) + 1;
        if (loot == 1) {
            // determine item to drop

            return true;
        } else {
            return false;
        }
    }

    public void changeDirectionIfWallHit() {
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

    public void update() {}
}


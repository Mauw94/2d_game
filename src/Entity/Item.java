package Entity;

import TileMap.TileMap;

public class Item extends MapObject {

    private String name;
    private int type;
    public static final int HEALTH_POTION = 0;
    public static final int SPEED_POTION = 1;
    public static final int DAMAGE_POTION = 2;

    public Item(TileMap tm, int type) {
        super(tm);
        this.type = type;
        setItemName(this.type);
    }

    private void setItemName(int type) {
        switch (type) {
            case 0:
                this.name = "Health Potion";
                break;
            case 1:
                this.name = "Boost Potion";
                break;
            case 2:
                this.name = "Damage Potion";
                break;
        }
    }

    public String getItemName() { return this.name; }
}

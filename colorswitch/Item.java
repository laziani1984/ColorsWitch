package colorswitch;

/**
 * Classe abstraite représentant un item (powerup/champignon/...)
 */
public abstract class Item extends LevelElement {

    /**
     * Constructeur
     * @param x --
     * @param y sont le centre de l'Item.
     */
    public Item(double x, double y) { super(x, y); }
}

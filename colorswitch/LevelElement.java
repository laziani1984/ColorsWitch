package colorswitch;

/**
 * Éléments qui composent un niveau.
 *
 * Le joueur peut interagir avec toutes les objets de ce type en entrant en
 * collision avec.
 */
public abstract class LevelElement extends Entity {


    /**
     * Constructeur
     * @param x --
     * @param y représentent le centre d'un élément de niveau(potion, obstacles, etc...).
     */
    public LevelElement(double x, double y) { super(x, y); }


    /**
     * Méthode pour vérifier si l'entité et le player s'intersectent ou pas.
     * @param player
     * @return
     */
    public abstract boolean intersects(Player player);


    /**
     * La réaction du jeu lors de l'intersection entre player et levelElement.
     * @param player
     * @param game
     */
    public abstract void handleCollision(Player player, Game game);
}

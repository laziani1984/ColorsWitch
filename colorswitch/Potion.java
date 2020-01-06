package colorswitch;

/**
 * Item : Potion magique.
 * 
 * Fait changer la sorcière de couleur
 */
public class Potion extends Item {

    /**
     * L'attribut used indique que la sorcière a utilisé le potion ou pas.
     */
    private boolean used = false;

    /**
     * Constructeur
     * @param x --
     * @param y sont le centre de l'image qui représente le potion à réclamer.
     */
    public Potion(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("potion", this);
    }

    /**
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        // Rien à faire
    }

    /**
     *
     * @return Indique la largeur du cercle dont le rayon est 16 qui représente le potion à utiliser.
     */
    @Override
    public double getWidth() { return 32; }

    /**
     *
     * @return Indique la largeur du cercle dont le rayon est 16 qui représente le potion à utiliser aussi.
     */
    @Override
    public double getHeight() { return 32; }

    /**
     * Si la sorcière réclame le potion ,elle va changer sa couleur.
     * @param player La sorcière représente par un cercle.
     * @param game Indique le modèle du jeu.
     */
    @Override
    public void handleCollision(Player player, Game game) {
        used = true;
        this.renderer = new ImageRenderer("black", this);
        player.randomizeColor();
    }

    /**
     * Si le portion n'est pas utilisé et que la sorcière l'a touché, la méthode retourne vrai.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le potion de changement de couleur s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) {
        return !used
                && player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}

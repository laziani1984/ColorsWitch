package colorswitch;

/**
 * Item : champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {


    /**
     * Constructeur
     * @param x --
     * @param y sont le centre du Mushroom.
     */
    public Mushroom(double x, double y) {

        super(x, y);
        this.renderer = new AnimationRenderer(this);
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
     * @return La largeur actuelle de l'image de Mushroom.
     */
    @Override
    public double getWidth() { return 64; }


    /**
     *
     * @return La hauteur actuelle de l'image de Mushroom.
     */
    @Override
    public double getHeight() { return 64; }


    /**
     * Si la sorcière s'intersecte avec le champignon, alors la sorcière a passé au niveau suivant et la méthode game.win
     * sera appelée.
     * @param player C'est la sorcière.
     * @param game C'est le modèle du jeu responsable de l'algo de démarrage du jeu.
     */
    @Override
    public void handleCollision(Player player, Game game) { game.win(); }


    /**
     * La méthode intersects vérifie si player(la sorcière) a touché une partie de l'image du champignon pour indiquer
     * qu'il a gagné pour ce niveau.
     * @param player La sorcière représentée par un cercle.
     * @return Vrai si le champignon s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) {

        return player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}

package colorswitch;

/**
 * Fait le rendu d'un shield qui protège la sorcière pendant 3 secondes.
 */
public class Shield extends Item {


    /**
     * Les attributs sont used qui indique si la sorcière a reclamé le potion ou pas, timeSinceShield pour calculer le
     * temps d'utilisation de la potion, l'instanciation de joueur qui sera une référence pour player.
     */
    private boolean used = false;
    private double timeSinceShield;
    private Player joueur = new Player(0, 0, 0);


    /**
     * Constructeur
     * @param x --
     * @param y pour le centre de l'image qui réfère au shield.
     */
    public Shield(double x, double y) {

        super(x, y);
        used = false;
        this.renderer = new ImageRenderer("shield", this);
    }


    /**
     * Si la sorcière réclame le shield et l'utilisateur n'est pas au test mode. Donc la sorcière aura un shield de
     * protection pendant 3 secondes.
     * @param player La sorcière représente par un cercle.
     * @param game Indique le modèle du jeu.
     */
    @Override
    public void handleCollision(Player player, Game game) {

        joueur = player;
        used = true;
        timeSinceShield = 0;
        this.renderer = new ImageRenderer("black", this);
        player.setInvincible(true);

    }


    /**
     * @return la largeur totale de l'entité
     */
    @Override
    public double getWidth() { return 32; }


    /**
     * @return la hauteur totale de l'entité
     */
    @Override
    public double getHeight() { return 32; }


    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {

        timeSinceShield += dt;

        if(used && timeSinceShield < 3) {
            joueur.setInvincible(true);
            joueur.setColor(4);
        } else {
            if(!joueur.isTabPressed()) { joueur.setInvincible(false); }
            joueur.setColor((int) Math.random() * 3);
        }
    }


    /**
     * Si le shield n'est pas utilisé et que la sorcière l'a touché, la méthode retourne vrai.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le shield s'intersecte avec player, faux sinon.
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

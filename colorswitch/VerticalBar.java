package colorswitch;

public class VerticalBar extends Obstacle {

    /**
     * Les attributs sont longueur et largeur du rectangle, la vitesse de déplacement vx, le niveau actuel et la nouvelle
     * position sur l'axe des x newX, et la vitesse par rapport au diificulté speed.
     */
    private double longueur, largeur, newX, vx, level;
    protected static int speed = 1;


    /**
     * Constructeur
     * @param x --
     * @param y sont le centre de l'entité.
     * @param longueur C'est la longueur de l'entité au total
     * @param largeur C'est sa largeur.
     * @param level C'est le niveau actuel.
     */
    public VerticalBar(double x, double y, double longueur, double largeur, int level) {

        super(x, y);
        this.longueur = longueur;
        this.largeur = largeur;
        this.level = level;
        this.vx = 0.5 * largeur * level * speed;
        this.color = (int) (Math.random()*4);
        this.renderer = new VerticalBarRenderer(this);
        speed++;
    }


    /**
     * @return la largeur totale de l'entité
     */
    @Override
    public double getWidth() {
        return this.largeur;
    }


    /**
     * @return la hauteur totale de l'entité
     */
    @Override
    public double getHeight() {
        return this.longueur;
    }


    /**
     *
     * @return La couleur actuelle.
     */
    public int getColor() { return this.color; }


    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {

        newX = x + dt * vx;

        if (newX + largeur / 2 > ColorsWitch.WIDTH || newX - largeur / 2 < 0) {
            vx *= -1;
        } else {
            x = newX;
        }

    }


    /**
     * La méthode checkIntersection se trouve dans la classe abstraite Entity.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le rectangle s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) { return this.checkIntersection(player, this.color); }
}

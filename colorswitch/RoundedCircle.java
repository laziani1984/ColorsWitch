package colorswitch;

/**
 * Fait le rendu de dessiner un cercle(des deux cercles) de l'obstacle TwoRoundedCircles.
 */
public class RoundedCircle extends Obstacle {


    /**
     * Les attributs à utiliser.
     */
    private double width;
    private int level, color;


    /**
     * Constructeur
     * @param x --
     * @param y représentent le centre du cercle RoundedCircle à dessiner.
     * @param width Largeur du cercle.
     * @param level Le niveau actuel.
     */
    public RoundedCircle(double x, double y, double width, int level) {

        super(x,y);
        this.width = width;
        this.level = level;
        this.color = (int) (Math.random() * 4);
    }


    /**
     *
     * @return La largeur actuelle.
     */
    @Override
    public double getWidth() { return width; }


    /**
     *
     * @param width remplace la largeur actuelle lors de l'appel de la méthode.
     */
    public void setWidth(double width) { this.width = width; }


    /**
     *
     * @return Retourne la largeur du cercle aussi.
     */
    @Override
    public double getHeight() { return width; }


    @Override
    public void tick(double dt) {
        // rien à faire.
    }


    /**
     *
     * @return La couleur actuelle du cercle.
     */
    public int getColor() { return color; }


    /**
     *
     * @param color Le numéro de la couleur à remplacer par la couleur actuelle.
     */
    public void setColor(int color) { this.color = color; }


    /**
     * La méthode checkIntersection se trouve dans la classe abstraite Entity.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le cercle s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) { return this.checkIntersection(player, this.color); }
}

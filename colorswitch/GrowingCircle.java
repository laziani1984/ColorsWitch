package colorswitch;

/**
 * Fait le rendu de dessiner un circle qui +/- la largeur en changeant ses couleurs à chaque 2 secondes.
 */
public class GrowingCircle extends Obstacle {


    /**
     * Les attributs : width pour la largeur du cercle à dessiner, deltaWidth pour le changement de largeur, original
     * width c'est la largeur originale du cercle lors de l'instanciation, maxWidth c'est la largeur maximale dont
     * le cercle pourrait augmenter.
     * timeSinceColorChange pour les changements de couleur, et circleGrowRate pour controller le taux de variation +/-
     * de la largeur du cercle.
     */
    private double width, deltaWidth, originalWidth, maxWidth, timeSinceColorChange = 0;
    protected static double circleGrowRate = 1;


    /**
     * Constructeur
     * @param x --
     * @param y sont le centre du cercle GrowingCircle.
     * @param width La largeur du cercle.
     * @param level Le niveau actuel.
     */
    public GrowingCircle(double x, double y, double width, int level) {

        super(x, y);
        this.isIncreasing = true;
        this.width = width;
        originalWidth = width;
        maxWidth = (2 + (double)level/4) * width;
        this.deltaWidth = 0.04 * circleGrowRate;
        this.renderer = new GrowingCircleRenderer(this);
        this.color = (int) (Math.random() * 4);
        circleGrowRate++;
    }


    /**
     *
     * @return La largeur actuelle.
     */
    @Override
    public double getWidth() { return this.width; }


    /**
     *
     * @return Retourne la largeur du cercle aussi.
     */
    @Override
    public double getHeight() { return this.width; }


    /**
     * À chaque changement de frame(canvas), le tick va faire un changement de largeur en appelant la méthode
     * growingObstacle(classe abstraite Entity) pour calculer la variation en largeur qui sera utilisé en dessinant
     * le cercle avec la nouvelle largeur et donc au nouveau frame la largeur sera +/- le changement en width.
     * timeSinceColorChange, c'est pour les changements des couleurs dans un intervalle de deux secondes pour chaque
     * couleur.
     * @param dt C'est la différence de temps (now - lastTime) en nanosecondes * 1e-9 pour l'avoir en secondes.
     */
    @Override
    public void tick(double dt) {

        timeSinceColorChange += dt;

        width = growingOstacle(this.width, this.maxWidth, this.originalWidth, this.deltaWidth);

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }


    /**
     *
     * @return La couleur actuelle du cercle.
     */
    public int getColor() { return color; }


    /**
     * La méthode checkIntersection se trouve dans la classe abstraite Entity.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le cercle s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) { return this.checkIntersection(player, this.color); }
}

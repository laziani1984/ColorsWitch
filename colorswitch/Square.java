package colorswitch;

/**
 * Obstacle simple : un carré qui change de rayon et de couleur à toutes les 2 secondes.
 */
public class Square extends Obstacle {


    /**
     * Les attributs : width pour la largeur du carré, deltaWidth, originalWidth, maxWidth sont responsable de la largeur
     * du carré, circleWidth et theta sont responsable de la rotation du carré dans un cercle ayant la largeur
     * circleWidth, originalX et Y pour le centre du cercle de rotation. squareGrowRate pour le +/- de la largeur
     * du carré, rotationSpeed pour la vitesse de rotation sur l'axe du cercle de rotation. timeSinceColorChange pour
     * faire le changement des couleurs. Level pour le niveau actuel.
     */
    private double width, deltaWidth, originalWidth, maxWidth, theta = 0, circleWidth, originalX, originalY;
    protected static double squareGrowRate = 1, rotationSpeed = 1;
    private double timeSinceColorChange = 0;
    private int level;


    /**
     * constructeur
     * @param x --
     * @param y Sont le centre du cercle de rotation. qui seront changés à chaque tick.
     * @param largeur Largeur du carré à dessiner.
     * @param level Le niveau actuel.
     * @param circleWidth Largeur du cercle de rotation(2 * rayon).
     */
    public Square(double x, double y, double largeur, int level, double circleWidth) {

        super(x, y);
        this.originalX = x;
        this.originalY = y;
        this.circleWidth = circleWidth;
        this.level = level;
        this.isIncreasing = true;
        this.width = largeur;
        this.originalWidth = largeur;
        this.maxWidth =  (1 + (double) level/4)  * largeur;
        this.deltaWidth = 0.015 * squareGrowRate;
        this.renderer = new SquareRenderer(this);
        this.color = (int) (Math.random() * 4);
        squareGrowRate++;
    }


    /**
     *
     * @return Retourne la largeur du carré.
     */
    @Override
    public double getWidth() {
        return width;
    }


    /**
     *
     * @return Retourne la hauteur du carré.
     */
    @Override
    public double getHeight() {
        return width;
    }


    /**
     * À chaque frame le carré va changer de largeur(growingObstacle), d'angle(rotateObstacle) et de couleur en utilisant
     * timeSinceColorChange > 2 pour changer la couleur du carré.
     * growingObstacle/rotateObstacle se trouvent dans la classe abstraite Entity.
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        width = growingOstacle(this.width, this.maxWidth, this.originalWidth, this.deltaWidth);

        x = this.originalX;
        y = this.originalY;

        double[] coordonnees = rotateObstacle(x, y, this.theta, rotationSpeed, this.level, this.circleWidth);

        x = coordonnees[0];
        y = coordonnees[1];
        theta = coordonnees[2];

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }


    /**
     *
     * @return La couleur du carré.
     */
    public int getColor() {
        return color;
    }


    /**
     *
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le carré s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) {
        return this.checkIntersection(player, this.color);
    }
}

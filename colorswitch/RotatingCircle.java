package colorswitch;

public class RotatingCircle extends Obstacle {


    /**
     * Les attributs sont width pour le diamètre du cercle à dessiner, widthGrandCercle pour le diamètre du cercle qui
     * représente l'axe sur lequel le petit cercle tourne, theta c'est l'angle entre l'axe des x et le rayon du grand
     * cercle, timeSinceColorChange pour calculer (2 secondes) delta changement de couleur. Level représente le niveau
     * actuel, originalX et Y sont les références pour la position initiale de l'entité lors de l'instanciation d'une
     * entité. RotationSpeed pour augmenter la vitesse de rotation par rapport au niveau(pour augmenter la difficulté).
     */
    private double originalX, originalY, width, widthGrandCercle, theta = 0, timeSinceColorChange = 0;
    private int level;
    protected static double rotationSpeed = 1;

    /**
     * Constructeur
     * @param x
     * @param y
     * @param widthPetitCercle Largeur de la petite cercle qu'on va dessiner.
     * @param widthGrandCercle Largeur de la grande cercle qui fonctionne comme un axe de rotation.
     * @param level
     */
    public RotatingCircle(double x, double y, double widthGrandCercle, double widthPetitCercle, int level) {

        super(x,y);
        this.originalX = x;
        this.originalY = y;
        this.width = widthPetitCercle;
        this.widthGrandCercle = widthGrandCercle;
        this.renderer = new RotatingCircleRenderer(this);
        this.level = level;
        this.color = (int) (Math.random() * 4);
        rotationSpeed++;
    }


    /**
     *
     * @return La largeur de l'entité.
     */
    @Override
    public double getWidth() { return width; }


    /**
     *
     * @return La largeur de l'entité aussi.
     */
    @Override
    public double getHeight() { return width; }


    /**
     *
     * @return La couleur actuelle de l'entité.
     */
    public int getColor() { return color; }


    /**
     * Cette méthode a deux fonctions à réaliser, la première c'est la rotation de l'obstacle en appelant la méthode
     * convenable à partir de la classe abstraite Entity, la deuxième fait le changement de couleur à chaque 2 secondes.
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {

        timeSinceColorChange += dt;
        x = this.originalX;
        y = this.originalY;

        double[] coordonnees = rotateObstacle(x, y, this.theta, rotationSpeed, this.level, this.widthGrandCercle);
        x = coordonnees[0];
        y = coordonnees[1];
        theta = coordonnees[2];

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }


    /**
     * La méthode checkIntersection se trouve dans la classe abstraite Entity.
     * @param player La sorcière représente par un cercle.
     * @return Vrai si le cercle s'intersecte avec player, faux sinon.
     */
    @Override
    public boolean intersects(Player player) { return this.checkIntersection(player, this.color); }
}

package colorswitch;

public class TwoRoundedCircles extends RoundedCircle {


    /**
     * Les attributs de RoundedCircle : circle1,2 pour les deux cercles qui s'intersectent en formant l'Entité
     * TwoRoundedCircles, x1,2 .... qui représente les coordonnées de chacune, le rayon, la largeur originale, max,
     * difference de largeur puisque les deux cercles vont changer de largeur. La couleur de la première et la deuxième.
     * Finalement, le ratio auquel le cercle augmente ou diminue en largeur(circleGrowRate).
     * L'attribut circleGrowRate est static puisqu'on va assigner un growRate(+1) pour chaque TwoRoundedCircles
     * instancié au jeu, pour augmenter le taux et la vitesse d'augmentation/diminution de grandeur(difficulté) en
     * passant par les niveaux.
     */
    private RoundedCircle circle1, circle2;
    private double x1, x2, y1, y2, width, timeSinceColorChange = 0, maxWidth, originalWidth, deltaWidth;
    private int level, color1, color2;
    protected static double circleGrowRate = 1;


    /**
     * Constructeur : il appele le constructeur de RoundedCircle pour assigner la position initiale du grand cercle qu'on
     * va utiliser pour dessiner les deux autres cercles de l'Entity. On va dessiner circle1(à un nouveau point x situé
     * un demi rayon avant le point x original de l'Entity en paramètre), circle2(un demi rayon après) pour faire une
     * intersection entre les deux cercles qui va contenir le Mushroom à réclamer.
     * La largeur Maximale sera une fraction de niveau dans lequel on est + 1 et multipliée par la largeur originale.
     * Le changement de largeur sera 0.027 multiplié par le circleGrowRate. (Pour augmenter la difficulté).
     * @param x position centrale de l'Entity à dessiner.
     * @param y position y sur le canvas selon le screenHeight.
     * @param width la largeur de cercle TwoRoundedCircles qu'on va utiliser pour dessiner les deux cercles.
     * @param level Le niveau actuel(j'ai décidé d'avoir cette obstacle à partir de niveau 4) donc le niveau de cette
     *              obstacle sera 1 au niveau 4 et ainsi de suite.
     */
    public TwoRoundedCircles (double x, double y, double width, int level) {

        super(x, y, width, level);
        circle1 = new RoundedCircle(x - width/4, y, width, level);
        circle2 = new RoundedCircle(x + width/4, y, width, level);
        this.renderer = new TwoRoundedCirclesRenderer(circle1, circle2);
        this.width = width;
        this.originalWidth = width;
        this.maxWidth = (1 + (double)level/4) * width;
        this.deltaWidth = 0.027 * circleGrowRate;
        this.level = level;
        circleGrowRate++;
    }


    /**
     * À chaque changement de frame(canvas), le tick va faire un changement de largeur en appelant la méthode
     * growingObstacle(classe abstraite Entity) pour calculer la variation en largeur qui sera utilisé en dessinant
     * l'ostacle. x1,..,y2 sont les coordonnées des deux cercles. Width sera (la nouvelle largeur) des deux cercles.
     * Et donc au nouveau frame la largeur sera +/- le changement en width.
     * timeSinceColorChange, c'est pour les changements des couleurs dans un intervalle de deux secondes pour chaque
     * couleur.
     * @param dt C'est la différence de temps (now - lastTime) en nanosecondes * 1e-9 pour l'avoir en secondes.
     */
    @Override
    public void tick(double dt) {

        timeSinceColorChange += dt;

        x1 = this.circle1.getX();
        x2 = this.circle2.getX();
        y1 = this.circle1.getY();
        y2 = this.circle2.getY();

        width = growingOstacle(this.width, this.maxWidth, this.originalWidth, this.deltaWidth);

        circle1.setWidth(width);
        circle2.setWidth(width);

        if (timeSinceColorChange > 2) {
            circle1.setColor((int) (Math.random() * 4));
            circle2.setColor((int) (Math.random() * 4));
            color1 = circle1.getColor();
            color2 = circle2.getColor();
            timeSinceColorChange = 0;
        }

    }


    /**
     * La méthode checkIntersection se trouve dans la classe abstraite Entity.
     * @param player La sorcière représente par un cercle.
     * @return Retourne vrai s'il intersecte une des deux, faux s'il n'intersecte aucune des deux.
     */
    @Override
    public boolean intersects(Player player) { return (circle1.intersects(player) && circle2.intersects(player)); }
}

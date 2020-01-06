package colorswitch;

/**
 * Classe abstraite pour représenter une entité sur le jeu.
 *
 * Une entité possède minimalement un Renderer et une position (x, y) définie
 * par rapport au niveau (où y=0 est tout en bas).
 */
public abstract class Entity {

    /**
     * Les attributs sont x, y pour les coordonnées de l'entité, renderer pour l'affichage graphique et isIncreasing pour
     * indiquer si l'entité augmente en diamètre ou pas ce qui sera utile dans les obstacles dont la largeur change en
     * fonction de temps.
     */
    protected double x, y;
    protected Renderer renderer;
    protected boolean isIncreasing;


    /**
     * Constructeur
     * @param x --
     * @param y sont le centre de l'entité.
     */
    public Entity(double x, double y) {

        this.x = x;
        this.y = y;
    }


    /**
     *
     * @return la position par rapport à x.
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return la position par rapport à y.
     */
    public double getY() { return y; }


    /**
     * @return la largeur totale de l'entité.
     */
    public abstract double getWidth();


    /**
     * @return la hauteur totale de l'entité
     */
    public abstract double getHeight();


    /**
     *
     * @return la représentation graphique de l'entité.
     */
    public Renderer getRepresentation() { return renderer; }


    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    public abstract void tick(double dt);


    /**
     * Cette méthode est utilisée pour augmenter et diminuer la largeur(diamètre) de l'entité à dessiner en fonction de
     * temps, largeur maximale à augmenter ou minimale à diminuer.
     * @param width C'est le diamètre de l'entité(distance d'une extrémité par rapport au centre * 2).
     * @param maxWidth C'est la largeur maximale que l'entité peut atteindre.
     * @param originalWidth C'est la largeur originale lors de l'instanciation de l'entité.
     * @param deltaWidth C'est la différence de largeur en fonction de temps.
     * @return La nouvelle largeur à dessiner au prochain tick.
     */
    public double growingOstacle(double width, double maxWidth, double originalWidth, double deltaWidth) {

        if(this.isIncreasing == true) {
            if(width < maxWidth) { width += deltaWidth; }
            else { if((int) (width - maxWidth) == 0) { this.isIncreasing = false; } }
        }
        else {
            if(width > originalWidth) { width -= deltaWidth; }
            else { if (((int) width == originalWidth)) { this.isIncreasing = true; } }
        }
        return width;
    }


    /**
     * Cette méthode est responsable de faire retourner l'obstacle à partir d'un axe.
     * @param x Position de l'entité par rapport à l'axe des x.
     * @param y Position de l'entité par rapport à l'axe des y.
     * @param theta L'angle formé entre le rayon du grand cercle et l'axe des x.
     * @param rotationSpeed La vitesse de rotation à considérer.
     * @param level Le niveau actuel.
     * @param rayonGrandCercle Le rayon du cercle dont l'entité tourne autour de lui.
     * @return un tableau des valeurs dont la première est la nouvelle position par rapport à l'axe de x, la deuxième
     * pour les axes des y, et la dernière pour le nouveau angle à considérer.
     */
    public double[] rotateObstacle(double x, double y, double theta, double rotationSpeed, int level, double rayonGrandCercle) {
        double[] valeurs = new double[3];
        if(theta == 0) { theta = 360; }
        valeurs[0] = x - (int) rayonGrandCercle * Math.cos(theta);
        valeurs[1] = y - (int) rayonGrandCercle * Math.sin(theta);
        valeurs[2] = theta - 0.002 * rotationSpeed * level;
        return valeurs;
    }


    /**
     * Pour vérifier l'intersection entre player et l'entité.
     * @param player La sorcière représente par un cercle.
     * @param color C'est la couleur à faire la comparaison avec pour vérifier l'intersection.
     * @return Vrai si l'entité s'intersecte avec player, faux sinon.
     */
    public boolean checkIntersection(Player player, int color) {
        if (!player.isInvincible()) {
            return color != player.getColor()
                    && player.getX() - player.getWidth() / 2 < this.getX() + this.getWidth() / 2
                    && player.getX() + player.getWidth() / 2 > this.getX() - this.getWidth() / 2
                    && player.getY() - player.getHeight() / 2 < this.getY() + this.getHeight() / 2
                    && player.getY() + player.getHeight() / 2 > this.getY() - this.getHeight() / 2;
        }
        return false;
    }
}

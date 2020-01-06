package colorswitch;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 *
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {


    /**
     * Les attributs sont radius pour indiquer le rayon du cercle de la sorcière, vy pour la vitesse à laquelle la
     * sorcière saute, vyToJump qui sera utilisé par les potions pour +/- la vitesse à laquelle la sorcière elle saute,
     * ay pour la gravité, color pour sa couleur, invincible pour passer par les obstacles sans interaction, tabPressed
     * pour le test mode.
     */
    private double radius;
    private double vy;
    private double vyToJump;
    private double ay;
    private int color = 1;
    private static boolean invincible = false;
    private static boolean tabPressed = false;


    /**
     * Constructeur
     * @param x --
     * @param y sont le centre de la balle qui représente la sorcière.
     * @param r pour le rayon de la balle.
     */
    public Player(double x, double y, double r) {

        super(x, y);
        this.radius = r;
        this.vyToJump = 200;
        this.vy = 0;
        this.ay = -400;
        this.renderer = new PlayerRenderer(this);
    }


    /**
     *
     * @return Retourne le rayon du cercle de la sorcière.
     */
    public double getRadius() { return radius; }


    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        y += dt * vy;

        // Clip la vitesse pour rester entre -300 et 300
        vy = Math.min(vy, 300);
        vy = Math.max(vy, -300);
    }


    /**
     *
     * @return La couleur de la sorcière.
     */
    public int getColor() { return color; }


    /**
     * Change la couleur actuelle de la sorcière à la couleur en paramètre.
     * @param color C'est la nouvelle couleur de la sorcière.
     */
    public void setColor(int color) { this.color = color; }


    /**
     * Remplace la couleur actuelle par une nouvelle couleur aléatoire
     */
    public void randomizeColor() {

        int newColor;

        do {
            newColor = (int) (Math.random() * 4);
        } while (newColor == this.color);

        this.color = newColor;
    }


    /**
     * Fait sauter la sorcière
     */
    public void jump() {

        vy = Math.max(vy, 0);
        vy += this.vyToJump;
    }


    /**
     * Change la position de la sorcière sur l'axe des y à la nouvelle y passée en paramètre.
     * @param y Sera la nouvelle position y de la sorcière.
     */
    public void setY(double y) { this.y = y; }


    /**
     *
     * @return La largeur de la balle de la sorcière.
     */
    @Override
    public double getWidth() { return this.getRadius() * 2; }


    /**
     *
     * @return La largeur de la balle aussi.
     */
    @Override
    public double getHeight() { return this.getRadius() * 2; }


    /**
     *
     * @return Si la sorcière peut passer les obstacles sans interaction ou pas.
     */
    public boolean isInvincible() { return this.invincible; }


    /**
     * Indique si la sorcière a déjà un shield ou pas.
     * @param gotShield une boolèene pour indiquer que la sorcière a utilisé un shield.
     */
    public void setInvincible(boolean gotShield) { this.invincible = gotShield; }


    /**
     * C'est pour le test mode, si l'utilisateur a tapé un tab le mode sera activé, un autre tab pour le désactiver.
     */
    public void tabPressed() {
        invincible = !invincible;
        tabPressed = !tabPressed;
    }


    /**
     *
     * @return si l'utilisateur a tapé un tab ou pas.
     */
    public boolean isTabPressed() { return this.tabPressed; }


    /**
     * Pour changer la vitesse de sauter de la sorcière.
     * @param newVyToJump La nouvelle vitesse à appliquer.
     */
    public void setVyToJump(double newVyToJump) { this.vyToJump = newVyToJump; }
}

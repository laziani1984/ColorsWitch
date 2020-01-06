package colorswitch;

public class SpeedUp extends Item {

    private boolean used;
    private double timeSinceSpeedUp;
    private Player joueur = new Player(0, 0, 0);

    public SpeedUp(double x, double y) {
        super(x, y);
        this.used = false;
        this.renderer = new ImageRenderer("fire", this);
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        timeSinceSpeedUp += dt;
        if (used && timeSinceSpeedUp < 2) {
            joueur.setVyToJump(600);
        } else {
            joueur.setVyToJump(200);
        }
    }

    @Override
    public double getWidth() {
        return 32;
    }

    @Override
    public double getHeight() {
        return 32;
    }

    @Override
    public void handleCollision(Player player, Game game) {
        joueur = player;
        this.used = true;
        this.renderer = new ImageRenderer("black", this);
    }

    @Override
    public boolean intersects(Player player) {
        return !this.used
                && player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}

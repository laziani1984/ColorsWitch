package colorswitch;

public class SlowDown extends Item {

    private boolean used;
    private double timeSinceSlowDown;
    private Player joueur = new Player(0,0,0);

    public SlowDown(double x, double y) {
        super(x, y);
        this.used = false;
        timeSinceSlowDown = 0;
        this.renderer = new ImageRenderer("freeze", this);
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        timeSinceSlowDown+= dt;
        if (used && timeSinceSlowDown < 5) {
            joueur.setVyToJump(0.02 );
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
        used = true;
        this.renderer = new ImageRenderer("black", this);
    }

    @Override
    public boolean intersects(Player player) {
        return !used
                && player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}

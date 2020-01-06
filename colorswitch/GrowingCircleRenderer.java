package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu de dessiner un circle qui +/- la largeur en changeant ses couleurs à chaque 2 secondes.
 */
public class GrowingCircleRenderer extends Renderer {


    /**
     * Attribut GrowingCircle pour contenir les nouvelles instances(this.cercle).
     */
    private GrowingCircle cercle;


    /**
     * Constructeur
     * @param cerc C'est le cercle dont on voulait dessiner en utilisant la méthode draw.
     */
    public GrowingCircleRenderer(GrowingCircle cerc) {
        this.cercle = cerc;
    }


    /**
     * La méthode pour dessiner le cercle en utilisant context.setFill pour avoir la couleur de l'obstacle à dessiner,
     * context.fillOval pour le dessiner dans les bonnes coordonnées.
     * @param level C'est le niveau actuel.
     * @param context C'est le canvas dont on va dessiner l'obstacle sur lequel.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        double canvasY = Renderer.computeScreenY(level, cercle.getY());

        context.setFill(Renderer.convertColor(cercle.getColor()));
        context.fillOval(
                cercle.getX() - cercle.getWidth() / 2,
                canvasY - cercle.getWidth() / 2,
                cercle.getWidth(),
                cercle.getWidth());
    }
}

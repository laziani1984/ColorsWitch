package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un cercle qui tourne autour d'un axe en dessinant un cercle qui change sa couleur à partir
 * d'une période (2 secondes) chacune sur l'écran.
 */
public class RotatingCircleRenderer extends Renderer {


    /**
     * Un attribut de type RotatingCircle qui contient les entités de la forme RotatingCircle à dessiner.
     */
    private RotatingCircle rotedCircle;


    /**
     * Constructeur pour l'affichage du RotatingCircle.
     * @param rotedCerc C'est le cercle dont on cherche à dessiner sur le canvas.
     */
    public RotatingCircleRenderer(RotatingCircle rotedCerc) { this.rotedCircle = rotedCerc; }


    /**
     * À chaque tick, le programme va dessiner un cercle(fillOval) à la position modifié selon le getX() et le
     * canvasY qui représente avec un déplacement circulaire autour de l'axe du grand cercle.
     * @param level Représente le niveau dans lequel on est au jeu.
     * @param context Répresente le canvas du jeu.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        double canvasY = Renderer.computeScreenY(level, rotedCircle.getY());

        context.setFill(Renderer.convertColor(rotedCircle.getColor()));
        context.fillOval(
                rotedCircle.getX() - rotedCircle.getWidth() / 2,
                canvasY - rotedCircle.getWidth() / 2,
                rotedCircle.getWidth(),
                rotedCircle.getWidth());
    }
}

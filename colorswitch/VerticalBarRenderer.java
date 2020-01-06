package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un rectangle en dessinant un rectangle qui se déplace verticalement sur l'écran.
 */
public class VerticalBarRenderer extends Renderer{


    /**
     * Attribut VerticalBar pour contenir le v appelé au constructeur.
     */
    private VerticalBar verticalBar;


    /**
     * Constructeur
     * @param v l'objet VerticalBar dont on va assigner l'affichage graphique.
     */
    public VerticalBarRenderer(VerticalBar v) { this.verticalBar = v; }


    /**
     * À chaque tick, le programme va dessiner le VerticalBar(fillRect) à la position modifié selon le getX() et le
     * canvasY qui représente un déplacement vertical de VerticalBar.
     * @param level Représente le niveau dans lequel on est au jeu.
     * @param context Répresente le canvas du jeu.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, verticalBar.getY());

        context.setFill(Renderer.convertColor(verticalBar.getColor()));
        context.fillRect(
                verticalBar.getX() - verticalBar.getWidth() / 2,
                canvasY - verticalBar.getHeight() / 2,
                verticalBar.getWidth(),
                verticalBar.getHeight());
    }
}

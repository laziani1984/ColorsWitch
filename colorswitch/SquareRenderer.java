package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Square en dessinant un carré coloré sur l'écran.
 */
public class SquareRenderer extends Renderer {


    /**
     * Attribut carré pour contenir le carré à dessiner(this.carré pour la nouvelle instance).
     */
    private Square carre;


    /**
     * Constructeur pour l'affichage du carré.
     * @param c
     */
    public SquareRenderer(Square c) {
        this.carre = c;
    }


    /**
     * À chaque tick, le programme va dessiner le Square(fillRect) à la position modifié selon le getX() et le
     * canvasY qui représente un déplacement circulaire.
     * @param level Représente le niveau dans lequel on est au jeu.
     * @param context Répresente le canvas du jeu.
     */
    @Override
    public void draw (Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, carre.getY());

        context.setFill(Renderer.convertColor(carre.getColor()));
        context.fillRect(
                carre.getX() - carre.getWidth() / 2,
                canvasY - carre.getWidth() / 2,
                carre.getWidth(),
                carre.getWidth());
    }
}

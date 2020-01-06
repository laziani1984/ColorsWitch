package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu de deux cercles en dessinant deux cercles intersectant entre eux sur l'écran.(changent leurs couleurs).
 */
public class TwoRoundedCirclesRenderer extends Renderer {


    /**
     * Attributs de la classe qui vont contenir le cercle1, cercle2 de la classe TwoRoundedCircles.
     */
    private RoundedCircle roundCircle1;
    private RoundedCircle roundCircle2;


    /**
     * Constructeur pour les deux RoundedCircle à dessiner.
     * @param roundCerc1 représente le premier cercle passé au constructeur(dans TwoRoundedCircles).
     * @param roundCerc2 représente le deuxième cercle.
     */
    public TwoRoundedCirclesRenderer(RoundedCircle roundCerc1, RoundedCircle roundCerc2) {

        this.roundCircle1 = roundCerc1;
        this.roundCircle2 = roundCerc2;
    }


    /**
     * À chaque tick, le programme va dessiner deux cercles RoundedCircle(fillOval) à la position modifié selon le
     * getX() et le canvasY. Ces deux cercles vont augmenter/diminuer en grandeur de rayon à un ratio déterminé.
     * On va dessiner deux cercles vides ayant juste la bordure colorée avec une gradeur de 3 pour chacune.
     * @param level Représente le niveau dans lequel on est au jeu.
     * @param context Répresente le canvas du jeu.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, roundCircle1.getY());

        context.setStroke(Renderer.convertColor(roundCircle1.getColor()));
        context.setLineWidth(3);
        context.strokeOval(
                roundCircle1.getX() - roundCircle1.getWidth() / 2,
                canvasY - roundCircle1.getWidth() / 2,
                roundCircle1.getWidth(),
                roundCircle1.getWidth());


        context.setStroke(Renderer.convertColor(roundCircle2.getColor()));
        context.setLineWidth(3);
        context.strokeOval(roundCircle2.getX() - roundCircle2.getWidth() / 2,
                canvasY - roundCircle2.getWidth() / 2,
                roundCircle2.getWidth(),
                roundCircle2.getWidth());
    }
}

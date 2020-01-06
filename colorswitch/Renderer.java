package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Classe abstraite
 *
 * Définit quelques fonctions statiques utiles lors du rendu
 */
public abstract class Renderer {

    public abstract void draw(Level level, GraphicsContext context);

    private static Color color0 = Color.YELLOW;
    private static Color color1 = Color.DODGERBLUE;
    private static Color color2 = Color.DARKGREEN;
    private static Color color3 = Color.ORANGERED;
    private static Color color4 = Color.LAVENDER;


    /**
     * Converti un numéro de couleur 0 à 3 en une couleur de JavaFX
     *
     * @param color le numéro de couleur
     * @return la couleur associée
     */
    public static Color convertColor(int color) {

        switch (color) {

            case 0:
                return color0;
            case 1:
                return color1;
            case 2:
                return color2;
            case 3:
                return color3;
            case 4:
                return color4;
        }

        throw new IllegalArgumentException("Couleur inconnue");
    }


    /**
     * Calcule la position sur l'écran d'une entité à pratir de sa position Y
     * dans le niveau.
     *
     * @param level Niveau actuel
     * @param levelY Coordonnée Y dans le niveau
     * @return La coordonnée Y dans le système de coordonnées de la fenêtre
     * JavaFX
     */
    public static double computeScreenY(Level level, double levelY) {

        double y = levelY - level.getScroll();
        return level.getScreenHeight() - y;
    }
}

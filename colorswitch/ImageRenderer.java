package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'une Entity sur l'écran en affichant une image
 */
public class ImageRenderer extends Renderer {


    /**
     * Les attributs sont Image pour contenir l'image passée en paramètre, Entity pour l'objet(this.render) passé en
     * paramètre.
     */
    private Image img;
    private Entity entity;


    /**
     * Constructeur
     * @param name Représente le nom de l'image à dessiner.
     * @param e Représente l'Entity à utiliser avec cette image.
     */
    public ImageRenderer(String name, Entity e) {
        if(!name.contentEquals("black")) { img = new Image("/" + name + ".png"); }
        this.entity = e;
    }


    /**
     * La méthode pour dessiner l'image en utilisant context.drawImage.
     * @param level C'est le niveau actuel.
     * @param context C'est le canvas dont on va dessiner l'image sur lequel.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());
    }
}

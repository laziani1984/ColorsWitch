package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimationRenderer extends Renderer {

    /**
     * number représente le nombre des images à afficher, prefix c'est l'extension fixe des images, index pour la position au tableau
     * frames qui contient toutes les extensions des images à représenter, entity pour représenter l'objet.
     */
    String prefix = "mushroom_animation";
    int number = 26, index = 0;
    String[] frames = new String[number];
    private Entity entity;

    /**
     * C'est le constructeur qui forme un tableau des images(1-26) qui sera utilisé pour animer le champignon en les affichant rapidement.
     * @param e le paramètre représente un Mushroom(this) dont on va lier une Animation à son affichage.
     */
    public AnimationRenderer(Entity e) {

        for(int i = 0; i < number; i++) {
            this.frames[i] = prefix + (i+1) + ".png";
        }
        this.entity = e;
    }

    /**
     * Cette méthode est responsable de l'affichage graphique du Mushroom.
     * x sera la position x du Mushroom sur l'écran, y sera sa position y à partir de son niveau.
     * À chaque tick, cette méthode sera responsable d'afficher une image.
     * @param level représente le niveau.
     * @param context représente la GUI(le canvas) dont le programme va l'utiliser pour dessiner les images.
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        Image img = new Image(frames[index]);
        if(index == number-1) { index = 0; }
        context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());
        index++;
    }
}

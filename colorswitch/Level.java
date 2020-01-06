package colorswitch;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite pour représenter un niveau.
 *
 */
public abstract class Level {


    protected int compteurObstacles, compteurItems;

    /**
     * À quel point on est avancés dans le level
     */
    protected double scroll;


    /**
     * Dimensions du niveau visible à l'écran
     */
    protected double screenWidth, screenHeight;


    /**
     * Obstacles du niveau
     */
    protected List<Obstacle> obstacles;


    /**
     * Items et champignon final
     */
    protected List<Item> items;
    protected Mushroom victoryMushroom;
    protected String[] listObstacles = {"GrowingCircle", "RotatingCircle", "VerticalBar", "Square", "TwoRoundedCircles"};
    protected String[] listItems = {"Potion", "Shield", "SlowDown", "SpeedUp"};


    /**
     * Constructeur
     * @param screenWidth
     * @param screenHeight
     */
    public Level(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        obstacles = new ArrayList<>();
        items = new ArrayList<>();
    }


    /**
     *
     * @return Le scroll de l'écran.
     */
    public double getScroll() { return scroll; }


    /**
     *
     * @return La largeur de l'écran.
     */
    public double getScreenWidth() {
        return screenWidth;
    }


    /**
     *
     * @return La hauteur de l'écran.
     */
    public double getScreenHeight() {
        return screenHeight;
    }


    /**
     *
     * @return Une liste qui contient tous les obstacles.
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }


    /**
     *
     * @return Une liste qui contient tous les powerUps.
     */
    public List<Item> getPowerUps() {
        return items;
    }


    /**
     * Pour vérifier les obstacles et les items à chaque frame.
     * @param dt
     */
    public void tick(double dt) {

        for (Obstacle o : obstacles) { o.tick(dt); }

        for (Item p : items) { p.tick(dt); }
    }


    /**
     *
     * @param scroll
     */
    public void incrementScroll(double scroll) {
        this.scroll += scroll;
    }


    /**
     * Retourne les entités dans le niveau (obstacles, items et champignon)
     *
     * @return List des entités
     */
    public List<LevelElement> getEntities() {

        List<LevelElement> entities = new ArrayList<>();

        for (LevelElement e : this.obstacles) { entities.add(e); }

        for (LevelElement e : this.items) { entities.add(e); }

        entities.add(victoryMushroom);

        return entities;
    }

    /**
     * Un switch pour créer des instances aléatoires à chaque niveau.
     * @param entity
     * @param x
     * @param y
     * @param widthGrandCercle
     * @param widthPetitCercle
     * @param longueur
     * @param largeur
     * @param circleWidth
     * @param width
     * @param level
     * @return
     */
    public Entity createEntity(String entity, double x, double y,double widthGrandCercle, double widthPetitCercle,
                               double longueur, double largeur, double circleWidth, double width ,int level) {

        switch (entity) {
            case "GrowingCircle" :
                return new GrowingCircle(x, y, width, level);
            case "RotatingCircle" :
                return new RotatingCircle(x, y, widthGrandCercle, widthPetitCercle, level);
            case "VerticalBar" :
                return new VerticalBar(x, y, longueur, largeur, level);
            case "Square" :
                return new Square(x, y, largeur, level, circleWidth);
            case "TwoRoundedCircles" :
                return new TwoRoundedCircles(x, y, width, level);
            case "Potion" :
                return new Potion(x, y);
            case "Shield" :
                return new Shield(x, y);
            case  "SlowDown" :
                return new SlowDown(x, y);
            case "SpeedUp" :
                return new SpeedUp(x, y);
            default:
                break;

        }
        return null;
    }
}

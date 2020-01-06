package colorswitch;

import java.util.ArrayList;
import java.util.List;

public class Game {


    /**
     * Les attributs sont level pour indiquer le niveau actuel et player pour avoir accès au méthodes présentes chez
     * la sorcière.
     */
    private Level level;
    private Player player;


    /**
     * Dimensions de l'écran
     */
    private double screenWidth, screenHeight;


    /**
     * Indique si la partie est terminée/gagnée
     */
    private boolean gameOver = false;
    private boolean hasWon = false;


    /**
     * Crée une partie dans le niveau levelNumber.
     *
     * @param screenWidth largeur de l'écran
     * @param screenHeight hauteur de l'écran
     * @param levelNumber numéro du niveau
     */
    public Game(double screenWidth, double screenHeight, int levelNumber) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        player = new Player(screenWidth / 2, 200, 15);
        createLevel(levelNumber);
    }


    /**
     * Fonction appelée à chaque frame
     *
     * @param dt Delta-Temps (en secondes)
     */
    public void tick(double dt) {

        level.tick(dt);
        player.tick(dt);

        if (player.getY() - player.getRadius() < level.getScroll()) {
            // Empêche la balle de sortir de l'écran
            player.setY(level.getScroll() + player.getRadius());
        } else if (player.getY() - level.getScroll() > screenHeight / 2) {
            // Scroll le level verticalement si nécessaire
            level.incrementScroll(player.getY() - level.getScroll() - screenHeight / 2);
        }

        // Gestion des collisions avec les éléments (items/obstacles/...) du niveau
        for (LevelElement element : level.getEntities()) {
            if (element.intersects(player)) {
                element.handleCollision(player, this);
            }
        }
    }


    /**
     * @return les entités à afficher à l'écran
     */
    public List<Entity> getEntities() {

        List<Entity> entities = new ArrayList<>();
        entities.addAll(level.getEntities());
        entities.add(player);
        return entities;
    }


    /**
     *
     * @return Le niveau actuel dans lequel la sorcière se retrouve.
     */
    public Level getLevel() { return level; }


    /**
     * C'est un switch avec un levelNumber pour créer le niveau convenable.
     * @param levelNumber C'est le paramètre qui indique à quel niveau la sorcière est rendue.
     */
    public void createLevel(int levelNumber) {

        switch (levelNumber) {

            case 1:
                level = new Level1(screenWidth, screenHeight);
                break;
            case 2:
                level = new Level2(screenWidth, screenHeight);
                break;
            case 3:
                level = new Level3(screenWidth, screenHeight);
                break;
            case 4:
                level = new Level4(screenWidth, screenHeight);
                break;
            case 5:
                level = new Level5(screenWidth, screenHeight);
                break;
            case 6:
                level = new Level6(screenWidth, screenHeight);
                break;
            case 7:
                level = new Level7(screenWidth, screenHeight);
                break;
            case 8:
                level = new Level8(screenWidth, screenHeight);
                break;
            case 9:
                level = new Level9(screenWidth, screenHeight);
                break;
            case 10:
                level = new Level10(screenWidth, screenHeight);
                break;
            default:
                System.out.println("You have Won!!!");
                break;
        }
    }


    /**
     * Cette méthode appelle la méthode jump en player pour animer que la sorcière saute dans l'écran.
     */
    public void jump() { player.jump(); }


    /**
     * Si la sorcière est morte pendant le niveau.
     */
    public void loose() {

        this.gameOver = true;
        resetSpeed();
    }


    /**
     * Si la sorcière a passé le niveau.
     */
    public void win() {

        this.hasWon = true;
        this.gameOver = true;
        resetSpeed();
    }


    /**
     * Indique si la partie est gagnée
     *
     * @return
     */
    public boolean hasWon() {
        return hasWon;
    }


    /**
     * Indique si la partie est terminée
     *
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }


    /**
     * C'est le testMode du jeu.
     */
    public void testMode() { this.player.tabPressed(); }


    /**
     * À chaque instanciation d'un nouveau niveau ou lors de la mort de la sorcière pendant un niveau, le reset speed
     * va initialiser la vitesse des différentes obstacles à un pour ne pas avoir des obstacles très rapides dans le
     * niveau 1 ou 2 par exemple.
     */
    public void resetSpeed() {

        VerticalBar.speed = 1;
        Square.squareGrowRate = 1;
        GrowingCircle.circleGrowRate = 1;
        RotatingCircle.rotationSpeed = 1;
        TwoRoundedCircles.circleGrowRate = 1;
    }
}

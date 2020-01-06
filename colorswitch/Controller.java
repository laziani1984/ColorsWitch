package colorswitch;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {


    /**
     *  Les attributs sont game pour la liaison avec le modèle, colorsWitch pour la vue et level pour le niveau actuel.
     */
    private Game game;
    private int level;
    private ColorsWitch colorsWitch = new ColorsWitch();


    /**
     * Constructeur
     * @param level pour indiquer le niveau actuel, createGame() va créer le niveau à partir de ce niveau actuel.
     */
    public Controller(int level) {

        this.level = level;
        createGame();
    }


    /**
     *
     * @return Une liste de toutes les entités qui se trouvent dans le niveau (potions, obstacles, etc).
     */
    public List<Entity> getEntities() { return this.game.getEntities(); }


    /**
     * Fonction appelée à chaque frame du jeu.
     * Si la sorcière a passé le niveau et si le niveau est plus petit que 10 on incremente le niveau de 1 et on instancie
     * un nouveau niveau. Sinon et que la sorcière est morte dans les niveaux de 1 à 10 on indique que la sorcière perd
     * le tour et on affiche le niveau de nouveau. Si la sorcière a gagné et le niveau est le dixième donc le jeu est
     * terminé et alors on ferme le jeu. Sinon alors on refait le tick.
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {

        if (this.game.isGameOver()) {
            if(this.game.hasWon() && level <10) {
                level++;
                Image img = new Image("/win.png");
//                colorsWitch.drawWon(colorsWitch.getPrimaryStage(), img);
                newGame();
            } else {
                if(!this.game.hasWon() && level <= 10) {
                    Image img1 = new Image("/loose.png");
//                    colorsWitch.drawWon(colorsWitch.getPrimaryStage(), img1);
                    this.game.loose();
                    newGame();
                } else {
                    if(this.game.hasWon() && level == 10) {
                        Image img2 = new Image("/youWin.gif");
//                        colorsWitch.drawWon(colorsWitch.getPrimaryStage(), img2);
                        Platform.exit();
                    }
                }
            }
        } else { this.game.tick(dt); }
    }


    /**
     *
     * @return retourne Le niveau actuel du jeu par rapport au modèle.
     */
    public Level getCurrentLevel() { return this.game.getLevel(); }


    /**
     *
     * @return Le niveau actuel.
     */
    public int getLevel() { return this.level; }


    /**
     * Assigne la valeur de niveau passé en paramètre au niveau actuel.
     * @param adjustedLevel C'est le niveau ajusté à partir des flèches qui indique le niveau dans le menu principal.
     */
    public void setLevel(int adjustedLevel) { this.level = adjustedLevel; }


    /**
     * L'instanciation d'un nouveau jeu si la sorcière a passé un niveau ou si elle est morte dans un niveau.
     */
    public void createGame() { this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, this.level); }


    /**
     * Fonction appelée lorsque la barre espace est enfoncée.
     */
    public void spaceTyped() {
        this.game.jump();
    }


    /**
     * La réaction du jeu au moment que l'utilisateur a tapé tab.
     */
    public void tabTyped() { this.game.testMode(); }


    /**
     * La réaction du jeu au moment que l'utilisateur a tapé escape.
     */
    public void escapeTyped() {

        Scene scene1 = colorsWitch.intro();
        game.resetSpeed();
        colorsWitch.showTime(scene1);
    }


    /**
     * Méthode qui fait le reset des vitesses des obstacles et instancie un nouveau jeu(game).
     */
    public void newGame() {

        this.game.resetSpeed();
        createGame();
    }
}

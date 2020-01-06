package colorswitch;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

/**
 *
 * Classe principale. Définit la vue.
 * @author Ce tp est présenté par Wael ABOU ALI 20034365(p1104434), Samuel Hélie 20113010.
 * @version 1.0.0
 * @description C'est un jeu d'une sorcière(ballon) qui change les couleurs à chaque période de temps(2 secondes), elle
 * doit passer des obstacles :
 * 1. Un rectangle qui rebondit sur les deux extremités de l'écran à une vitesse x sans changer ses couleurs.
 * 2. Un cercle qui +/- de largeur(2 * rayon) et change ses couleurs(à chaque 2 secondes).
 * 3. Un cercle qui tourne autour d'un axe et change ses couleurs aussi.
 * 4. Un carré qui +/- en largeur, tourne sur son axe et change ses couleurs.
 * 5. Deux cercles intersectés ensembles qui changent leurs couleurs et +/- en largeur.
 * La sorcière reçoit des potions :
 * 1. changeur des couleurs. 2. shield qui dure pour 3 secondes pour la rendre invincible.
 * 3. augmente de la vitesse du jeu. 4. diminue la vitesse du jeu.
 * La sorcière doit passer par 10 niveaux de difficultés, dont les espaces seront plus étroites, la largeur des obstacles
 * plus grandes et la vitesse des obstacles plus grandes à partir de niveau où elle est.
 *
 */
public class ColorsWitch extends Application {

    /**
     * Les attributs sont WIDTH et HEIGHT du canvas en final double pour ne pas les changer, Controlleur controller pour
     * lier la vue avec le controlleur, GraphicsContext context pour représenter le canvas qui sera utiliser pour le jeu.
     * instancié un Stage pour ne pas avoir un objet null et pour l'utiliser partout dans la vue sans avoir besoin de le
     * passer en paramètre à chaque fois, il va referer au primaryStage. Un int level pour le niveau actuel.
     */
    public static final double WIDTH = 320, HEIGHT = 480;
    private Controller controller;
    private static Stage gameStage;
    private Scene scene;
    private Canvas canvas;
    private Pane pane;
    private GraphicsContext context;
    private int level = 0;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Cette méthode va afficher l'intro du jeu sur le primaryStage.
     * @param primaryStage C'est la fenêtre qui affichera le jeu.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        scene = intro();
        showTime(scene);
    }


    /**
     * Cette méthode sera un VBox, pour former le menu cette méthode va :
     * 1. appeler drawLogo --> pour afficher le logo du jeu.
     * 2. drawMenuButton --> pour afficher le menu et elle va aussi appeler les méthodes suivantes :
     *    2.1 drawPlayButton --> pour afficher la bouton à cliquer pour Play!
     *    2.2 drawSelectLevelButton --> qui est un HBox ayant les composantes suivantes :
     *          3.1 decreaseLevelArrow --> pour diminuer le niveau si l'image est 1 ou plus.
     *          3.2 imageBar --> pour afficher l'image correspondante au niveau (select Level et 1) sont les mêmes
     *                           puisque le premier niveau est 1.
     *          3.3 inreaseLevelArrow --> pour augmenter le niveau de 1 si c'est plus petit que 10.
     *    2.3 drawExitButton --> pour afficher le bouton de la fermeture du jeu.
     *
     * @return Retourne un scene qui représente l'intro du jeu.
     */
    public Scene intro() {

        VBox root = new VBox();
        drawLogo(root);
        drawMenuButtons(root);
        return new Scene(root, WIDTH, HEIGHT);
    }

    /**
     *
     * @param root
     */
    public void drawLogo(VBox root) {

        FlowPane intro = new FlowPane();
        Image introImage = new Image("intro/animatedC.gif");
        ImageView iv = new ImageView();
        iv.setImage(introImage);
        iv.setFitHeight(480);
        iv.setFitWidth(320);
        iv.setPreserveRatio(true);
        intro.setColumnHalignment(HPos.CENTER);
        intro.setPadding(new Insets(50, 0, 0, 0));
        intro.getChildren().add(iv);
        root.setStyle("-fx-background-color: black");
        root.getChildren().add(intro);
    }

    /**
     *
     * @param root
     */
    public void drawMenuButtons(VBox root) {

        drawPlayButton(root);
        root.setSpacing(20);
        drawSelectLevelButton(root);
        root.setSpacing(20);
        drawExitButton(root);
        root.setAlignment(Pos.CENTER);

    }

    public void drawPlayButton(VBox root) {

        Image play = new Image("intro/play.png");
        ImageView playImage = new ImageView(play);
        playImage.setFitWidth(120);
        playImage.setFitHeight(120);
        playImage.setPreserveRatio(true);

        playImage.setOnMouseClicked(event -> {

            if (this.level>=1 && this.level<=10) { controller = new Controller(this.level); }
            Pane pane = gamePlay();
            scene = new Scene(pane, WIDTH, HEIGHT);
            action();
            showTime(scene);

        });

        root.getChildren().add(playImage);
    }

    public void drawSelectLevelButton(VBox root) {

        HBox selectChoices = new HBox();
        decreaseLevelArrow(selectChoices);
        imageBar(selectChoices);
        increaseLevelArrow(selectChoices);
        root.getChildren().add(selectChoices);

    }

    public void decreaseLevelArrow(HBox selectChoices) {

        Image leftArrow = new Image("intro/left-arrow.png");
        ImageView leftArrowView = new ImageView(leftArrow);
        leftArrowView.setFitWidth(30);
        leftArrowView.setFitHeight(120);
        leftArrowView.setPreserveRatio(true);
        leftArrowView.setId("left-arrow");

        leftArrowView.setOnMouseClicked(event -> {
            if (level >= 1) { this.level--; }
            try { start(gameStage);
            } catch (Exception e) { e.printStackTrace(); }
        });

        selectChoices.getChildren().add(leftArrowView);
        selectChoices.setSpacing(10);
    }

    public void imageBar(HBox selectChoices) {

        Image select;

        if (level == 0) {
            select = new Image("intro/selectLevel.png");
        } else {
            select = new Image("intro/" + level + ".png");
        }

        ImageView selectedImage = new ImageView(select);
        selectedImage.setFitWidth(120);
        selectedImage.setFitHeight(120);
        selectedImage.setPreserveRatio(true);
        selectedImage.setId("select");
        selectChoices.getChildren().add(selectedImage);
        selectChoices.setSpacing(10);
    }

    /**
     * Pour l'affichage de la flèche à droite et faire le changement de niveau à chaque mouseClicked.
     * @param selectChoices
     */
    public void increaseLevelArrow(HBox selectChoices) {

        Image rightArrow = new Image("intro/right-arrow.png");
        ImageView rightArrowView = new ImageView(rightArrow);
        rightArrowView.setFitWidth(30);
        rightArrowView.setFitHeight(120);
        rightArrowView.setPreserveRatio(true);
        rightArrowView.setId("right-arrow");

        rightArrowView.setOnMouseClicked(event -> {
            if (level <= 9) { this.level++;}
            try { start(gameStage);
            } catch (Exception e) { e.printStackTrace(); }
        });

        selectChoices.getChildren().add(rightArrowView);
        selectChoices.setPadding(new Insets(0,0,0,63));
    }

    public void drawExitButton(VBox root) {

        Image exited = new Image("intro/exit.png");
        ImageView exitImage = new ImageView(exited);
        exitImage.setFitWidth(120);
        exitImage.setFitHeight(120);
        exitImage.setPreserveRatio(true);
        exitImage.setId("exit");

        exitImage.setOnMouseClicked(event -> gameStage.close());

        root.getChildren().add(exitImage);
    }

    /**
     * C'est la méthode responsable du jeu, elle va créer un canvas pour dessiner les animations graphiques du jeu et le
     * commence l'interaction avec l'utilisateur. Si l'utilisateur clique sur play! sans changer le niveau, gamePlay()
     * va instancier un nouveau controlleur avec le niveau actuel 1, sinon le controlleur aura le niveau = level.
     * @return Retourne la grille pane qui contient le canvas.
     */
    public Pane gamePlay() {

        if(controller == null) {
            controller = new Controller(1);
        }

        canvas = new Canvas(WIDTH, HEIGHT);
        pane = new Pane(canvas);
        context = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                controller.tick((now - lastTime) * 1e-9);
                controller.setLevel(controller.getLevel());
                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);

                List<Entity> entities = controller.getEntities();

                for (Entity e : entities) {
                    e.getRepresentation().draw(controller.getCurrentLevel(), context);
                }

                lastTime = now;
            }
        };

        timer.start();
        return pane;
    }

    /**
     * C'est la méthode responsable d'enregister l'interaction de l'utilisateur, à chaque fois que l'utilisateur tape
     * quelquechose sur son ordinateur, cette méthode va envoyer le code de la clé tapé au controlleur pour commencer à
     * le traiter.
     */
    public void action() {

        scene.setOnKeyPressed((event) -> {

            KeyCode evenement = event.getCode();

            switch (evenement) {
                case SPACE:
                    controller.spaceTyped();
                    break;
                case TAB:
                    controller.tabTyped();
                    break;
                case ESCAPE:
                    controller.escapeTyped();
                    break;
            }
        });
    }

    /**
     * Comme son nom représente, c'est la méthode responsable de l'afichage de la scène reçu en paramètre sur le stage.
     * @param scene (scene ayant VBox si c'est l'intro, Pane si c'est le jeu et WIDTH, HEIGHT fixes,)
     */
    public void showTime(Scene scene) {
        gameStage.setTitle("Colors Witch");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();

    }

    // Cette fonction est bonne mais elle fonctionne juste pour un niveau

//    public void drawWon(Stage primaryStage, Image img) {
//
//        canvas = new Canvas(WIDTH,HEIGHT);
//        Pane root = new Pane(canvas);
//        context = canvas.getGraphicsContext2D();
//        Scene scene = new Scene(root,WIDTH,HEIGHT);
//
//        AnimationTimer timer = new AnimationTimer() {
//
//            private long lastTime = System.nanoTime();
//            private long now = 0;
//            private double width = 5, height = 5;
//
//            @Override
//            public void handle(long now) {
//                if (now == 0) lastTime = now;
//                else {
//                    if (((now - lastTime) * 1e-9) < 4) {
//                        context.setFill(Color.BLACK);
//                        context.fillRect(0, 0, WIDTH, HEIGHT);
//                        context.drawImage(img, 0, 80,width,height);
//                        width += 2;
//                        height += 2;
//                    }
//                }
//            }
//        };
//        timer.start();
//        showTime(scene);
//    }

    public static Stage getPrimaryStage() {
        return gameStage;
    }

    private void setPrimaryStage(Stage primaryStage) {
        ColorsWitch.gameStage = primaryStage;
    }
}

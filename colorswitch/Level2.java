package colorswitch;

public class Level2 extends Level {

    public Level2(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        this.compteurObstacles = 0;
        this.compteurItems = 0;

        // Création des obstacles
        for(int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {

                items.add((Item) createEntity(listItems[(int) (Math.random() * listItems.length)], x, 0.85 * (double) (i + 1)
                        * screenHeight, 60, 20, 60, 20, 60, 40, 2));
                compteurItems++;

            } else {

                obstacles.add((Obstacle) createEntity(listObstacles[(int) (Math.random() * listObstacles.length-1)], x,
                        0.85 * (double) (i + 1) * screenHeight, 60, 20, 60, 20,
                        60, 40, 2));
                compteurObstacles++;

            }
        }
        victoryMushroom = new Mushroom(screenWidth / 2, 8.5 * screenHeight);
    }
}

package colorswitch;

public class Level3 extends Level {

    public Level3(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        this.compteurObstacles = 0;
        this.compteurItems = 0;

        // Création des obstacles
        for(int i = 0; i < 10; i++) {
            if(i == 0) {
                obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length-1)], x,
                        0.75 * screenHeight, 60, 20, 60, 20,
                        60, 40, 3));
                compteurObstacles++;
            } else {
                if (i % 3 == 0) {

                    items.add((Item) createEntity(listItems[(int) (Math.random() * listItems.length)], x,
                            0.75 + (0.8 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 40, 3));
                    compteurItems++;
                } else {
                    obstacles.add((Obstacle) createEntity(listObstacles[(int) (Math.random() * listObstacles.length-1)],
                            x, 0.75 + (0.8 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 40, 3));
                    compteurObstacles++;
                }
            }
        }
        victoryMushroom = new Mushroom(screenWidth / 2, 9.25 * screenHeight);
    }
}

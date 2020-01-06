package colorswitch;

public class Level8 extends Level {

    public Level8(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        this.compteurObstacles = 0;
        this.compteurItems = 0;

        // Création des obstacles
        for(int i = 0; i < 15; i++) {

            if(i == 0) {

                obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length)], x,
                        0.75 * screenHeight, 60, 20, 60, 20,
                        60, 40, 8));
                compteurObstacles++;

            } else {

                if (i % 3 == 0) {

                    items.add((Item) createEntity(listItems[(int) (Math.random() * listItems.length)], x,
                            0.75 + (0.55 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 8));
                    compteurItems++;

                } else {

                    obstacles.add((Obstacle) createEntity(listObstacles[(int) (Math.random() * listObstacles.length)], x,
                            0.75 + (0.55 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 8));
                    compteurObstacles++;

                }
            }
        }
        TwoRoundedCircles obstacle = new TwoRoundedCircles(x, 9.25 * screenHeight, 80, 5);
        obstacles.add(obstacle);
        victoryMushroom = new Mushroom(screenWidth / 2, 9.25 * screenHeight);
    }
}

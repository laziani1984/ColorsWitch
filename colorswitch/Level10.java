package colorswitch;

public class Level10 extends Level {

    public Level10(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        this.compteurItems = 0;
        this.compteurObstacles = 0;
        double x = screenWidth / 2;

        // Création des obstacles
        for(int i = 0; i < 17; i++) {

            if(i == 0) {

                obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length)], x,
                        0.75 * screenHeight, 60, 20, 60, 20,
                        60, 40, 10));
                compteurObstacles++;

            } else {

                if(i % 3 == 0 ) {

                    items.add((Item) createEntity(listItems[(int)(Math.random() * listItems.length)], x,
                            0.75 + (0.45 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 10));
                    compteurItems++;

                } else {

                    obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length)], x,
                            0.75 + (0.45 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 10));
                    compteurObstacles++;

                }
            }
        }
        TwoRoundedCircles obstacle = new TwoRoundedCircles(x, 8.5 * screenHeight, 80, 7);
        obstacles.add(obstacle);
        victoryMushroom = new Mushroom(screenWidth / 2, 8.5 * screenHeight);
    }
}

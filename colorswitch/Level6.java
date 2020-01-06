package colorswitch;

public class Level6 extends Level {

    public Level6(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        this.compteurObstacles = 0;
        this.compteurItems = 0;

        // Création des obstacles
        for(int i = 0; i < 13; i++) {

            if(i == 0) {

                obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length)], x,
                        0.75 * screenHeight, 60, 20, 60, 20,
                        60, 40, 6));
                compteurObstacles++;

            } else {

                if (i % 3 == 0) {

                    items.add((Item) createEntity(listItems[(int) (Math.random() * listItems.length)], x,
                            0.75 + (0.65 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 6));
                    compteurItems++;

                } else {

                    obstacles.add((Obstacle) createEntity(listObstacles[(int) (Math.random() * listObstacles.length)], x,
                            0.75 + (0.65 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 6));
                    compteurObstacles++;

                }
            }
        }
        TwoRoundedCircles obstacle = new TwoRoundedCircles(x, 9.5 * screenHeight, 80, 3);
        obstacles.add(obstacle);
        victoryMushroom = new Mushroom(screenWidth / 2, 9.5 * screenHeight);
    }
}

package colorswitch;

public class Level5 extends Level {

    public Level5(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        this.compteurObstacles = 0;
        this.compteurItems = 0;

        // Création des obstacles
        for(int i = 0; i < 12; i++) {

            if(i == 0) {

                obstacles.add((Obstacle) createEntity(listObstacles[(int)(Math.random() * listObstacles.length-1)], x,
                        0.75 * screenHeight, 60, 20, 60, 20,
                        60, 40, 1));
                compteurObstacles++;

            } else {

                if (i % 3 == 0) {

                    items.add((Item) createEntity(listItems[(int) (Math.random() * listItems.length)], x,
                            0.75 + (0.7 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 5));
                    compteurItems++;

                } else {

                    obstacles.add((Obstacle) createEntity(listObstacles[(int) (Math.random() * listObstacles.length)], x,
                            0.75 + (0.7 * (double) (i + 1) * screenHeight), 60, 20,
                            60, 20, 60, 60, 5));
                    compteurObstacles++;

                }
            }
        }
        TwoRoundedCircles obstacle = new TwoRoundedCircles(x, 9 * screenHeight, 80, 2);
        obstacles.add(obstacle);
        victoryMushroom = new Mushroom(screenWidth / 2, 9 * screenHeight);
    }
}

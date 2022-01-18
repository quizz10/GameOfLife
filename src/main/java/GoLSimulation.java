public class GoLSimulation {
   private final int width;
   private final int height;
   private int[][] gameOfLifeBoard;

    public GoLSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.gameOfLifeBoard = new int[width][height];
    }

    public void setAlive(int x, int y) {
        gameOfLifeBoard[x][y] = 1;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width)
            return 0;
        if (y < 0 || y >= height)
            return 0;

        return gameOfLifeBoard[x][y];
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;
        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public void simulateNextGeneration() {
        int[][] tempBoard = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);
                if (gameOfLifeBoard[x][y] == 1) {
                    if (aliveNeighbours < 2) {
                        tempBoard[x][y] = 0;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        tempBoard[x][y] = 1;
                    } else {
                        tempBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        tempBoard[x][y] = 1;
                    }
                }
            }
        }
        gameOfLifeBoard = tempBoard;
    }

    public void showBoard() {
        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x < width; x++) {
                if (this.gameOfLifeBoard[x][y] == 1) {
                    line.append("+");
                } else {
                    line.append(" ");
                }
            }
            line.append("|");
            System.out.println(line);
        }

        System.out.println("\n");

    }
}

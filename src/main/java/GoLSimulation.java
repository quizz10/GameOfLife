public class GoLSimulation {
   private int width;
   private int height;
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

        count += getState(x -1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

}

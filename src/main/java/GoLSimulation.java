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

    public int getBoard(int x, int y) {
        return gameOfLifeBoard[x][y];
    }
}

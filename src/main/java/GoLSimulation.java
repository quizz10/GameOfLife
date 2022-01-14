public class GoLSimulation {
    int width;
    int height;
    int[][] gameOfLifeBoard;

    public GoLSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.gameOfLifeBoard = new int[width][height];
    }
}

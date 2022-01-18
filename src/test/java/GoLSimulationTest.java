import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GoLSimulationTest {
    GoLSimulation simulation;
    private final int ALIVE = 1;
    private final int DEAD = 0;
    @BeforeEach
    void setUp() {
        simulation = new GoLSimulation(10, 10);
    }

    @Test
    void tenByTenBoardExists() {
        assertEquals(DEAD, simulation.getState(0,0));
        assertEquals(DEAD, simulation.getState(9,9));
    }

    @Test
    void givenCellIsAlive() {
        simulation.setAlive(3,3);
        assertEquals(ALIVE, simulation.getState(3,3));
    }

    @Test
    void getStateOutOfBoundsReturnsDead() {
        assertEquals(DEAD, simulation.getState(-1,-1));
        assertEquals(DEAD, simulation.getState(100,100));
    }

    @Test
    void countAliveNeighboursWithNoLivingCellAlwaysReturnsDead() {
        assertEquals(DEAD, simulation.countAliveNeighbours(1, 9));
        assertEquals(DEAD, simulation.countAliveNeighbours(3, 6));
        assertEquals(DEAD, simulation.countAliveNeighbours(5, 5));
    }

    @Test
    void findingOneAliveNeighbourReturnsOneFindingTwoReturnsTwo() {
        simulation.setAlive(3,0);
        simulation.setAlive(3,1);
        simulation.setAlive(3,2);

        assertEquals(1, simulation.countAliveNeighbours(3,0));
        assertEquals(2, simulation.countAliveNeighbours(3,1));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0, 0,2, 0,4",
            "2,0, 2,2, 2,4",
            "4,0, 4,2, 8,4",
    })
    void nextGenerationWithNoAliveNeighboursDies(int x1,int y1, int x2,int y2, int x3,int y3) {
        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(DEAD, simulation.getState(x1,y1));

    }

    @ParameterizedTest
    @CsvSource({
            "3,3, 3,4, 3,5",
            "1,1, 1,2, 1,3",
            "6,6, 5,6, 4,6"
    })
    void nextGenerationWithTwoNeighboursBecomesAlive(int x1,int y1, int x2,int y2, int x3,int y3) {

        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);

        assertEquals(ALIVE, simulation.getState(x2,y2));
    }

    @ParameterizedTest
    @CsvSource({
            // Top left
            "0,0, 1,0, 1,1",
            // Top right
            "9,0, 8,0, 7,0",
            // Bottom left
            "0,9, 1,9, 2,9",
            // Bottom right
            "9,9, 8,9, 7,9"
    })
    void nextGenerationCornerTestWithTwoNeighboursBecomesAlive(int x1,int y1, int x2,int y2, int x3,int y3) {

        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(ALIVE, simulation.getState(x2,y2));
    }


    @ParameterizedTest
    @CsvSource({
            // Top
            "4,0, 5,0, 6,0",
            // Right
            "9,4, 9,5, 9,6",
            // Bottom
            "4,9, 5,9, 6,9",
            // Left
            "0,4, 0,5, 0,6"
    })
    void nextGenerationEdgeTestsWithTwoNeighboursBecomesAlive(int x1,int y1, int x2,int y2, int x3,int y3) {
        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(ALIVE, simulation.getState(x2,y2));
    }

    @Test
    void deadCellWithThreeAliveNeighboursBecomesAlive() {
        simulation.setAlive(3,0);
        simulation.setAlive(3,2);
        simulation.setAlive(2,2);
        simulation.simulateNextGeneration();

        assertEquals(ALIVE, simulation.getState(3,1));
    }

    @ParameterizedTest
    @CsvSource ({
            // Top left
            "1,0, 1,1, 0,1, 0,0",
            // Top right
            "8,0, 8,1, 9,1, 9,0",
            // Bottom right
            "8,9, 8,8, 9,8, 9,9",
            // Bottom left
            "0,8, 1,8, 1,9, 0,9"
    })
    void deadCellWithThreeAliveNeighboursBecomesAliveInAllCorners(int x1, int y1, int x2, int y2, int x3, int y3, int aliveCellX, int aliveCellY) {
        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(ALIVE, simulation.getState(aliveCellX, aliveCellY));
    }

    @Test
    void aliveCellWithThreeNeighboursDies() {

        simulation.setAlive(0,1);
        simulation.setAlive(0,2);
        simulation.setAlive(0,3);
        simulation.setAlive(1,2);
        simulation.setAlive(2,2);

        simulation.simulateNextGeneration();

        assertEquals(DEAD, simulation.getState(1,2));
        assertEquals(DEAD, simulation.getState(2,2));
    }
}
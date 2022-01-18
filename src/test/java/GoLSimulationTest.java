import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GoLSimulationTest {
    GoLSimulation simulation;
    @BeforeEach
    void setUp() {
        simulation = new GoLSimulation(10, 10);
    }

    @Test
    void tenByTenBoardExists() {
        assertEquals(0, simulation.getState(9,9));
    }

    @Test
    void givenCellGetsValueOne() {
        simulation.setAlive(3,3);
        assertEquals(1, simulation.getState(3,3));
    }

    @Test
    void getStateOutOfBoundsReturnsZero() {
        assertEquals(0, simulation.getState(-1,-1));
        assertEquals(0, simulation.getState(100,100));
    }

    @Test
    void countAliveNeighboursWithNoLivingCellAlwaysReturnsZero() {
        assertEquals(0, simulation.countAliveNeighbours(1, 9));
        assertEquals(0, simulation.countAliveNeighbours(3, 6));
        assertEquals(0, simulation.countAliveNeighbours(5, 5));
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
            "0,0, 0,2, 0,4, 0",
            "2,0, 2,2, 2,4, 0",
            "4,0, 4,2, 8,4, 0",
    })
    void simulateNextGenerationWithNoAliveNeighboursReturnsZero(int x1,int y1, int x2,int y2, int x3,int y3, int expected) {
        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(expected, simulation.getState(x1,y1));

    }

    @ParameterizedTest
    @CsvSource({
            "3,3, 3,4, 3,5, 1",
            "1,1, 1,2, 1,3, 1",
            "6,6, 5,6, 4,6, 1"
    })
    void simulateNextGenerationWithTwoNeighboursReturnsOne(int x1,int y1, int x2,int y2, int x3,int y3, int expected) {

        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);

        assertEquals(expected, simulation.getState(x2,y2));
    }

    @ParameterizedTest
    @CsvSource({
            // Top left
            "0,0, 1,0, 1,1, 1",
            // Top right
            "9,0, 8,0, 7,0, 1",
            // Bottom left
            "0,9, 1,9, 2,9, 1",
            // Bottom right
            "9,9, 8,9, 7,9, 1"
    })
    void nextGenerationCornerTestWithTwoNeighboursReturnsOne(int x1,int y1, int x2,int y2, int x3,int y3, int expected) {

        simulation.setAlive(x1,y1);
        simulation.setAlive(x2,y2);
        simulation.setAlive(x3,y3);
        simulation.simulateNextGeneration();

        assertEquals(expected, simulation.getState(x2,y2));
    }
}
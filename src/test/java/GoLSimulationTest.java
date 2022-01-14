import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
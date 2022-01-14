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
}
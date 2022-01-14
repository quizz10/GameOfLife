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
        int checkZeroOnGivenPosition = 0;
        int givenPosition = simulation.getBoard(9,9);
        assertEquals(checkZeroOnGivenPosition, givenPosition);
    }

    @Test
    void givenCellGetsValueOne() {
        simulation.setAlive(3,3);
        int findOneOnGivenPosition = 1;
        int givenPosition = simulation.getBoard(3,3);
        assertEquals(findOneOnGivenPosition, givenPosition);
    }
}
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
        int givenPosition = simulation.gameOfLifeBoard[9][9];
        assertEquals(checkZeroOnGivenPosition, givenPosition);
    }
}
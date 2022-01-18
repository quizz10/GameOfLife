public class Main {

    public static void main(String[] args) {
        GoLSimulation simulation = new GoLSimulation(55, 55);

        //Glider
        simulation.setAlive(26, 23);
        simulation.setAlive(27, 24);
        simulation.setAlive(25, 25);
        simulation.setAlive(26, 25);
        simulation.setAlive(27, 25);

        // Block
        simulation.setAlive(26, 28);
        simulation.setAlive(26, 29);
        simulation.setAlive(27, 28);
        simulation.setAlive(27, 29);

        int generation = 1;
        for (int i = 0; i < 181; i++) {
            System.out.println("GENERATION: " + generation);
            simulation.showBoard();
            simulation.simulateNextGeneration();
            generation++;
        }
    }
}

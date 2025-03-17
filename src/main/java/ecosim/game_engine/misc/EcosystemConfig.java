package ecosim.game_engine.misc;

/**
 * Configuration parameters for the ecosystem simulation.
 * Stores initial settings and simulation constraints.
 * @author darragh0
 */
public record EcosystemConfig(
    int initialAnimals,  // Number of animals to start with
    int initialPlants,   // Number of plants to start with
    int maxCapacity,     // Maximum allowed organisms
    int maxDays,         // Maximum days to simulate
    int hoursPerDay)     // Hours per simulation day
{
}

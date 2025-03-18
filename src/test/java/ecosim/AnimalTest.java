package ecosim;

/**
 * Test class for Animal functionality in the ecosystem simulator.
 * @author jjola00
 */

import static org.junit.jupiter.api.Assertions.*;

import ecosim.game_engine.enm.*;
import ecosim.game_engine.man.SeasonMan;
import ecosim.game_engine.man.TimeOfDayMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecosim.game_engine.man.ChangeMan;
import ecosim.game_engine.man.SimpleChangeMan;
import ecosim.game_engine.map.Map;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.builder.DesertAnimalBuilder;
import ecosim.game_engine.organism.builder.GrasslandAnimalBuilder;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.game_engine.organism.builder.DesertPlantBuilder;

public class AnimalTest {
    private Animal desertAnimal;
    private Animal grasslandAnimal;
    private Plant desertPlant; 
    private ChangeMan changeMan;

    /**
     * Sets up test environment with animal and plant instances
     */
    @BeforeEach
    public void setUp() {
        Map.init(10, 10);
        changeMan = SimpleChangeMan.getInstance();

        AnimalDescriptor desertDesc = new AnimalDescriptor(
            "Lizard", Size.SMALL, Diet.OMNIVORE, ActivityType.DIURNAL, false, "Hiss", "🦎");
        DesertAnimalBuilder desertBuilder = new DesertAnimalBuilder(desertDesc);
        desertAnimal = desertBuilder.buildBasicProperties().applyDecorators().build();
        desertAnimal.setHealth(desertAnimal.getMaxHealth()); // Initialize health

        AnimalDescriptor grassDesc = new AnimalDescriptor(
            "Rabbit", Size.MEDIUM, Diet.HERBIVORE, ActivityType.NOCTURNAL, true, "Squeak", "🐰");
        GrasslandAnimalBuilder grassBuilder = new GrasslandAnimalBuilder(grassDesc);
        grasslandAnimal = grassBuilder.buildBasicProperties().applyDecorators().build();
        grasslandAnimal.setHealth(grasslandAnimal.getMaxHealth()); 

        PlantDescriptor plantDesc = new PlantDescriptor("Cactus", Size.SMALL, "🌵");
        DesertPlantBuilder plantBuilder = new DesertPlantBuilder(plantDesc);
        desertPlant = plantBuilder.buildBasicProperties().build();
    }

    /**
     * Tests proper initialization of animal properties
     */
    @Test
    public void testAnimalInitialization() {
        assertTrue(desertAnimal.getName().startsWith("Lizard ("));
        assertEquals(Size.SMALL, desertAnimal.getSize());
        assertEquals(Diet.OMNIVORE, desertAnimal.getDiet());
        assertEquals(ActivityType.DIURNAL, desertAnimal.getActivityType());
        assertEquals("🦎", desertAnimal.getSymbol());
        assertEquals(10, desertAnimal.getMaxHealth());
        assertEquals(10, desertAnimal.getHealth(), 0.001); 

        assertTrue(grasslandAnimal.getName().startsWith("Rabbit ("));
        assertEquals(Size.MEDIUM, grasslandAnimal.getSize());
        assertEquals(Diet.HERBIVORE, grasslandAnimal.getDiet());
        assertEquals(ActivityType.NOCTURNAL, grasslandAnimal.getActivityType());
        assertEquals("🐰", grasslandAnimal.getSymbol());
        assertEquals(20, grasslandAnimal.getMaxHealth());
        assertEquals(20, grasslandAnimal.getHealth(), 0.001); 
    }

    /**
     * Tests animal activity state changes based on time of day and season
     */
    @Test
    public void testActivityStateChange() {
        desertAnimal.update(new TimeOfDayMan(changeMan) {
            @Override public TimeOfDay getCurrentState() { return TimeOfDay.DAY; }
        });
        assertEquals(ActivityState.AWAKE, desertAnimal.getActivityState());

        grasslandAnimal.update(new TimeOfDayMan(changeMan) {
            @Override public TimeOfDay getCurrentState() { return TimeOfDay.NIGHT; }
        });
        assertEquals(ActivityState.AWAKE, grasslandAnimal.getActivityState());

        grasslandAnimal.update(new SeasonMan(changeMan) {
            @Override public Season getCurrentState() { return Season.WINTER; }
        });
        assertEquals(ActivityState.HIBERNATING, grasslandAnimal.getActivityState());
    }

    /**
     * Tests animal health reduction mechanism
     */
    @Test
    public void testHealthAdjustment() {
        float initialHealth = desertAnimal.getHealth(); 
        desertAnimal.reduceHealth(0.5f);
        assertEquals(initialHealth - 0.5f, desertAnimal.getHealth(), 0.5);
    }

    /**
     * Tests animal eating behavior and health restoration
     */
    @Test
    public void testEating() {
        grasslandAnimal.setHealth(grasslandAnimal.getHealth() - 10);
        float testHealth = grasslandAnimal.getHealth(); 
        boolean ate = grasslandAnimal.eat(desertPlant); 
        assertTrue(ate); 
        assertTrue(grasslandAnimal.getHealth() > testHealth);
        assertTrue(grasslandAnimal.getHealth() <= grasslandAnimal.getMaxHealth());
    }
}
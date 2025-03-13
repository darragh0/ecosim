package ecosim;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Season;
import ecosim.enm.Size;
import ecosim.enm.TimeOfDay;
import ecosim.map.Map;  // Assuming this is the correct import for Map
import ecosim.organism.animal.CactusMouse;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.DesertAnimal;
import ecosim.organism.animal.GrasslandAnimal;
import ecosim.organism.animal.factory.ConcreteDesertAnimalFactory;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;

public class MainTest {
    
    private CactusMouse cactusMouse;
    private Deer deer;
    private Map map;
    
    @BeforeEach
    public void setUp() {
        // Initialize the Map singleton
        Map.init(10, 10);  // 10x10 grid
        map = Map.getInstance();
        
        cactusMouse = new CactusMouse();
        deer = new Deer();
        
        // Add animals to the map at a valid starting position to avoid out-of-bounds
        map.add(cactusMouse, 5, 5);  // Center of 10x10 grid
        map.add(deer, 5, 5);
    }

    @Test
    public void testCactusMouseInitialization() {
        assertEquals(Size.SMALL, cactusMouse.getSize());
        assertEquals(Diet.HERBIVORE, cactusMouse.getDiet());
        assertEquals(ActivityType.NOCTURNAL, cactusMouse.getActivityType());
        assertTrue(cactusMouse.getName().startsWith("Cactus Mouse"));
        assertEquals(ActivityState.SLEEPING, cactusMouse.getActivityState());
    }

    @Test
    public void testDeerInitialization() {
        assertEquals(Size.MEDIUM, deer.getSize());
        assertEquals(Diet.HERBIVORE, deer.getDiet());
        assertEquals(ActivityType.DIURNAL, deer.getActivityType());
        assertTrue(deer.getName().startsWith("Deer"));
        assertEquals(ActivityState.SLEEPING, deer.getActivityState());
    }

    @Test
    public void testCactusMouseClone() {
        CactusMouse clone = cactusMouse.clone();
        assertNotSame(cactusMouse, clone);
        assertEquals(cactusMouse.getSize(), clone.getSize());
        assertEquals(cactusMouse.getDiet(), clone.getDiet());
        assertEquals(cactusMouse.getActivityType(), clone.getActivityType());
        assertTrue(clone.getName().startsWith("Cactus Mouse"));
    }

    @Test
    public void testDeerClone() {
        Deer clone = deer.clone();
        assertNotSame(deer, clone);
        assertEquals(deer.getSize(), clone.getSize());
        assertEquals(deer.getDiet(), clone.getDiet());
        assertEquals(deer.getActivityType(), clone.getActivityType());
        assertTrue(clone.getName().startsWith("Deer"));
    }

    @Test
    public void testCactusMouseTimeOfDayUpdate() {
        cactusMouse.handleTimeOfDayUpdate(TimeOfDay.NIGHT);
        assertEquals(ActivityState.AWAKE, cactusMouse.getActivityState());
        
        cactusMouse.handleTimeOfDayUpdate(TimeOfDay.DAY);
        assertEquals(ActivityState.SLEEPING, cactusMouse.getActivityState());
    }

    @Test
    public void testDeerTimeOfDayUpdate() {
        deer.handleTimeOfDayUpdate(TimeOfDay.DAY);
        assertEquals(ActivityState.AWAKE, deer.getActivityState());
        
        deer.handleTimeOfDayUpdate(TimeOfDay.NIGHT);
        assertEquals(ActivityState.SLEEPING, deer.getActivityState());
    }

    @Test
    public void testSeasonUpdateWithHibernation() {
        // Reset to a neutral state
        cactusMouse.setSleepState(ActivityState.SLEEPING);
        cactusMouse.handleSeasonUpdate(Season.WINTER);
        assertEquals(ActivityState.HIBERNATING, cactusMouse.getActivityState());
        
        cactusMouse.handleSeasonUpdate(Season.SUMMER);
        assertEquals(ActivityState.AWAKE, cactusMouse.getActivityState());
    }

    @Test
    public void testDesertAnimalMoveHealthLoss() {
        float initialHealth = cactusMouse.getMaxHealth();
        cactusMouse.setSleepState(ActivityState.AWAKE);
        cactusMouse.move();
        float expectedHealthLoss = initialHealth * 0.05f; // DesertAnimal health loss
        assertTrue(cactusMouse.getHealth() <= initialHealth - expectedHealthLoss);
    }

    @Test
    public void testGrasslandAnimalMoveHealthLoss() {
        float initialHealth = deer.getMaxHealth();
        deer.setSleepState(ActivityState.AWAKE);
        deer.move();
        float expectedHealthLoss = initialHealth * 0.025f; // GrasslandAnimal health loss
        assertTrue(deer.getHealth() <= initialHealth - expectedHealthLoss);
    }

    @Test
    public void testCanEatAnimal() {
        assertFalse(cactusMouse.canEatAnimal(deer));
        assertFalse(deer.canEatAnimal(cactusMouse));
    }

    @Test
    public void testCanEatPlant() {
        assertTrue(cactusMouse.canEatPlant());
        assertTrue(deer.canEatPlant());
    }

    @Test
    public void testFactoryCreatesDesertAnimal() {
        ConcreteDesertAnimalFactory factory = new ConcreteDesertAnimalFactory();
        DesertAnimal createdMouse = factory.createAnimal(CactusMouse.class);
        assertTrue(createdMouse instanceof CactusMouse);
        assertEquals(Size.SMALL, createdMouse.getSize());
    }

    @Test
    public void testConservationBoostDecorator() {
        float initialHealth = cactusMouse.getMaxHealth();
        ConservationBoostDecorator decoratedMouse = new ConservationBoostDecorator(cactusMouse.clone());
        map.add(decoratedMouse, 5, 5); // Add decorated mouse to map
        
        float normalLoss = initialHealth * 0.1f; // 10% health loss from Conscious.move()
        float decoratedLoss = normalLoss * 0.5f; // ConservationBoost reduces by half
        
        cactusMouse.reduceHealth(normalLoss);
        decoratedMouse.reduceHealth(normalLoss);
        
        float expectedNormalHealth = initialHealth - normalLoss;
        float expectedDecoratedHealth = initialHealth - decoratedLoss;
        
        assertEquals(expectedNormalHealth, cactusMouse.getHealth(), 0.001);
        assertEquals(expectedDecoratedHealth, decoratedMouse.getHealth(), 0.001);
    }
}
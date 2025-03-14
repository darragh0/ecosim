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
import ecosim.map.Map; 
import ecosim.organism.animal.CactusMouse;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.Snake;
import ecosim.organism.animal.DesertAnimal;
import ecosim.organism.animal.factory.ConcreteDesertAnimalFactory;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;

public class MainTest {
    
    private CactusMouse cactusMouse;
    private Deer deer;
    private Snake normalSnake;
    private Snake boostedSnake;
    private Map map;
    
    @BeforeEach
    public void setUp() {
        Map.init(10, 10);
        map = Map.getInstance();
        
        cactusMouse = new CactusMouse();
        deer = new Deer();
        
        map.add(cactusMouse);
        cactusMouse.setCoords(5, 05);
        deer.setCoords(5, 4);
        map.add(deer); 
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
    public void testSeasonUpdateWithHibernation() {
        cactusMouse.setActivityState(ActivityState.SLEEPING);
        cactusMouse.handleSeasonUpdate(Season.WINTER);
        assertEquals(ActivityState.HIBERNATING, cactusMouse.getActivityState());
        
        cactusMouse.handleSeasonUpdate(Season.SUMMER);
        assertEquals(ActivityState.AWAKE, cactusMouse.getActivityState());
    }
    @Test
    public void testGrasslandAnimalMoveHealthLoss() {
        float initialHealth = deer.getMaxHealth();
        deer.setActivityState(ActivityState.AWAKE);
        deer.move();
        float expectedHealthLoss = initialHealth * 0.025f; 
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
        normalSnake = new Snake();
        float initialHealth = normalSnake.getHealth();
        assertEquals(normalSnake.getHealth(), boostedSnake.getHealth());
        ConservationBoostDecorator decoratedSnake = new ConservationBoostDecorator(boostedSnake);
        map.add(normalSnake); 
        normalSnake.setCoords(3, 1);
        map.add(decoratedSnake); 
        decoratedSnake.setCoords(4, 1);
        
        float normalLoss = initialHealth * 0.1f;
        float decoratedLoss = normalLoss * 0.5f;
        
        normalSnake.reduceHealth(normalLoss);
        decoratedSnake.reduceHealth(normalLoss);
        
        float expectedNormalHealth = initialHealth - normalLoss;
        float expectedDecoratedHealth = initialHealth - decoratedLoss;
        
        assertEquals(expectedNormalHealth, cactusMouse.getHealth(), 0.001);
        assertEquals(expectedDecoratedHealth, decoratedSnake.getHealth(), 0.001);
    }
}
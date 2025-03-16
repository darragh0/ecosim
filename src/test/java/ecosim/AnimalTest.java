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
import ecosim.map.ActionResult;
import ecosim.map.Map;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.animal.abs.DesertAnimal;
import ecosim.organism.animal.abs.GrasslandAnimal;
import ecosim.organism.animal.concrete.desert.CactusMouse;
import ecosim.organism.animal.concrete.desert.Snake;
import ecosim.organism.animal.concrete.grassland.Deer;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.organism.animal.factory.AnimalFactory;
import ecosim.organism.animal.factory.AnimalFactoryProducer;
import ecosim.misc.AnimalDescriptor;

public class AnimalTest {

    private CactusMouse cactusMouse;
    private Snake snake;
    private Deer deer;
    private Map map;

    @BeforeEach
    public void setUp() {
        Map.init(10, 10);
        map = Map.getInstance();

        cactusMouse = new CactusMouse();
        snake = new Snake();
        deer = new Deer();

        map.add(cactusMouse);
        cactusMouse.setCoords(5, 5);
        map.add(snake);
        snake.setCoords(3, 3);
        map.add(deer);
        deer.setCoords(4, 4);
    }

    @Test
    public void testAnimalInitialization() {
        assertEquals("CactusMouse (1)", cactusMouse.getName());
        assertEquals(ActivityState.AWAKE, cactusMouse.getActivityState());
        assertEquals(0.5f, cactusMouse.getSurvivalChance(), 0.001);
        assertEquals(0.5f, cactusMouse.getReproductiveChance(), 0.001);
        assertFalse(cactusMouse.canHibernate()); // Assuming default is false unless set
    }

    @Test
    public void testClone() {
        DesertAnimal clonedMouse = cactusMouse.clone();
        assertNotSame(cactusMouse, clonedMouse);
        assertEquals(cactusMouse.getName(), clonedMouse.getName());
        assertEquals(cactusMouse.getActivityType(), clonedMouse.getActivityType());
    }

    @Test
    public void testMoveHealthLoss() {
        float initialHealth = deer.getMaxHealth();
        ActionResult result = deer.move();
        assertTrue(deer.getHealth() <= initialHealth - (initialHealth * 0.025f));
        assertNotNull(result);
    }

    @Test
    public void testCanEatAnimal() {
        snake.setDiet(Diet.CARNIVORE).setSize(Size.MEDIUM);
        cactusMouse.setSize(Size.SMALL);
        assertTrue(snake.canEatAnimal(cactusMouse));
        assertFalse(cactusMouse.canEatAnimal(snake));
    }

    @Test
    public void testEatAnimal() {
        snake.setDiet(Diet.CARNIVORE).setSize(Size.MEDIUM);
        cactusMouse.setSize(Size.SMALL);
        float initialHealth = snake.getHealth();
        boolean ate = snake.eat(cactusMouse);
        if (ate) {
            assertTrue(snake.getHealth() > initialHealth);
        } else {
            assertEquals(initialHealth, snake.getHealth(), 0.001);
        }
    }

    @Test
    public void testBreed() {
        CactusMouse mate = new CactusMouse();
        Animal child = cactusMouse.breed(mate);
        if (child != null) {
            assertTrue(child instanceof CactusMouse);
            assertNotSame(cactusMouse, child);
        }
    }

    @Test
    public void testTimeOfDayUpdate() {
        cactusMouse.setActivityType(ActivityType.NOCTURNAL);
        cactusMouse.handleTimeOfDayUpdate(TimeOfDay.NIGHT);
        assertEquals(ActivityState.AWAKE, cactusMouse.getActivityState());
        cactusMouse.handleTimeOfDayUpdate(TimeOfDay.DAY);
        assertEquals(ActivityState.SLEEPING, cactusMouse.getActivityState());
    }

    @Test
    public void testSeasonUpdateHibernation() {
        snake.setCanHibernate(true);
        snake.handleSeasonUpdate(Season.WINTER);
        assertEquals(ActivityState.HIBERNATING, snake.getActivityState());
        snake.handleSeasonUpdate(Season.SUMMER);
        assertEquals(ActivityState.AWAKE, snake.getActivityState());
    }

    @Test
    public void testConservationBoostDecorator() {
        float initialHealth = snake.getHealth();
        ConservationBoostDecorator boostedSnake = new ConservationBoostDecorator(snake);
        float normalLoss = initialHealth * 0.1f;
        snake.reduceHealth(normalLoss);
        boostedSnake.reduceHealth(normalLoss);
        assertEquals(initialHealth - normalLoss, snake.getHealth(), 0.001);
        assertEquals(initialHealth - (normalLoss * 0.5f), boostedSnake.getHealth(), 0.001);
    }

    @Test
    public void testFertilityBoostDecorator() {
        FertilityBoostDecorator fertileDeer = new FertilityBoostDecorator(deer);
        assertEquals(0.7f, fertileDeer.getReproductiveChance(), 0.001); // 0.5 + 0.2
    }

    @Test
    public void testFactoryCreatesDesertAnimal() {
        AnimalFactory<DesertAnimal> factory = AnimalFactoryProducer.getFactory("DESERT");
        AnimalDescriptor descriptor = new AnimalDescriptor(CactusMouse.class, "🐭", Size.SMALL, Diet.HERBIVORE, ActivityType.NOCTURNAL, false, "Squeak");
        DesertAnimal createdMouse = factory.createAnimal(descriptor);
        assertTrue(createdMouse instanceof CactusMouse);
        assertEquals(Size.SMALL, createdMouse.getSize());
        assertEquals(Diet.HERBIVORE, createdMouse.getDiet());
    }
}
package ecosim;

/**
 * Test class for Environment functionality in the ecosystem simulator.
 * @author jjola00
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.enm.Weather;
import ecosim.game_engine.man.EnvironmentMan;

public class EnvironmentTest {
    
    private EnvironmentMan environmentMan;
    
    /**
     * Sets up environment manager with desert biome
     */
    @BeforeEach
    void setUp() {
        environmentMan = new EnvironmentMan();
        environmentMan.setBiome(Biome.DESERT);
    }
    
    /**
     * Tests season transition functionality
     */
    @Test
    void testSeasonTransition() {
        Season initialSeason = environmentMan.getSeason();
        
        environmentMan.updateSeason();
        Season newSeason = environmentMan.getSeason();
        
        assertNotEquals(initialSeason, newSeason, "Season should change after update");
        
        assertEquals(Season.SUMMER, initialSeason, "Initial season should be Summer");
        assertEquals(Season.AUTUMN, newSeason, "New season should be Autumn after one update");
    }
    
    /**
     * Tests time of day cycle changes
     */
    @Test
    void testTimeOfDaySwitch() {
        TimeOfDay initialTime = environmentMan.getTimeOfDay();
        
        environmentMan.updateTimeOfDay();
        TimeOfDay newTime = environmentMan.getTimeOfDay();
        
        assertNotEquals(initialTime, newTime, "Time of day should change after update");
        
        assertEquals(TimeOfDay.NIGHT, initialTime, "Initial time should be Night");
        assertEquals(TimeOfDay.DAY, newTime, "New time should be Day after one update");
    }
    
    /**
     * Tests weather update mechanism
     */
    @Test
    void testWeatherUpdate() {
        environmentMan.updateSeason(); 
        Weather initialWeather = environmentMan.getWeather();
        environmentMan.updateWeather();
        Weather newWeather = environmentMan.getWeather();
        
        assertNotNull(newWeather, "Weather should not be null after update");
        assertTrue(isValidWeather(newWeather), "Weather should be a valid Weather enum value");
        
        boolean weatherChanged = false;
        for (int i = 0; i < 5 && !weatherChanged; i++) {
            environmentMan.updateWeather();
            if (environmentMan.getWeather() != initialWeather) {
                weatherChanged = true;
            }
        }
        assertTrue(weatherChanged, "Weather should change at least once in multiple updates");
    }
    
    /**
     * Helper method to validate Weather enum values
     */
    private boolean isValidWeather(Weather weather) {
        for (Weather w : Weather.values()) {
            if (w == weather) {
                return true;
            }
        }
        return false;
    }
}
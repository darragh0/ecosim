package ecosim;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ecosim.enm.Biome;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.EnvironmentMan;

public class EnvironmentTest {
    
    private EnvironmentMan environmentMan;
    
    @BeforeEach
    void setUp() {
        environmentMan = new EnvironmentMan();
        environmentMan.setBiome(Biome.DESERT);
    }
    
    @Test
    void testSeasonTransition() {
        Season initialSeason = environmentMan.getSeason();
        
        environmentMan.updateSeason();
        Season newSeason = environmentMan.getSeason();
        
        assertNotEquals(initialSeason, newSeason, "Season should change after update");
        
        assertEquals(Season.SUMMER, initialSeason, "Initial season should be Summer");
        assertEquals(Season.AUTUMN, newSeason, "New season should be Autumn after one update");
    }
    
    @Test
    void testTimeOfDaySwitch() {
        TimeOfDay initialTime = environmentMan.getTimeOfDay();
        
        environmentMan.updateTimeOfDay();
        TimeOfDay newTime = environmentMan.getTimeOfDay();
        
        assertNotEquals(initialTime, newTime, "Time of day should change after update");
        
        assertEquals(TimeOfDay.NIGHT, initialTime, "Initial time should be Night");
        assertEquals(TimeOfDay.DAY, newTime, "New time should be Day after one update");
    }
    
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
        private boolean isValidWeather(Weather weather) {
        for (Weather w : Weather.values()) {
            if (w == weather) {
                return true;
            }
        }
        return false;
    }
}
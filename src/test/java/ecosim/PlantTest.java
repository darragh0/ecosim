package ecosim;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ecosim.game_engine.enm.*;
import ecosim.game_engine.man.EnvironmentMan;
import ecosim.game_engine.map.Map;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.DesertPlantBuilder;
import ecosim.game_engine.organism.builder.GrasslandPlantBuilder;
import ecosim.game_engine.organism.plant.abs.Plant;

public class PlantTest {

    private Plant desertPlant;
    private Plant grasslandPlant;
    private EnvironmentMan environmentMan;

    @BeforeEach
    public void setUp() {
        Map.init(10, 10);
        environmentMan = new EnvironmentMan();
        environmentMan.setBiome(Biome.DESERT);

        PlantDescriptor desertDesc = new PlantDescriptor("Cactus", Size.SMALL, "C");

        PlantDescriptor grasslandDesc = new PlantDescriptor("Grass", Size.MEDIUM, "G");

        DesertPlantBuilder desertBuilder = new DesertPlantBuilder(desertDesc);
        desertPlant = desertBuilder.buildBasicProperties().build();

        GrasslandPlantBuilder grasslandBuilder = new GrasslandPlantBuilder(grasslandDesc);
        grasslandPlant = grasslandBuilder.buildBasicProperties().build();

        environmentMan.registerTimeOfDayObservers(desertPlant);
        environmentMan.registerWeatherObservers(desertPlant);
        environmentMan.registerTimeOfDayObservers(grasslandPlant);
        environmentMan.registerWeatherObservers(grasslandPlant);
    }

    @Test
    public void testEnergyCycleTransition() {
        assertEquals(EnergyCycle.PHOTOSYNTHESIS, desertPlant.getEnergyCycleState().getEnergyCycle(),
                "Initial state should be Photosynthesis");

        environmentMan.updateTimeOfDay();
        environmentMan.updateTimeOfDay();
        assertEquals(TimeOfDay.NIGHT, environmentMan.getTimeOfDay(),
                "Time of day should be NIGHT after two updates");

        assertEquals(EnergyCycle.RESPIRATION, desertPlant.getEnergyCycleState().getEnergyCycle(),
                "State should transition to Respiration at night");

        environmentMan.updateTimeOfDay();
        assertEquals(TimeOfDay.DAY, environmentMan.getTimeOfDay(),
                "Time of day should be DAY after another update");

        assertEquals(EnergyCycle.PHOTOSYNTHESIS, desertPlant.getEnergyCycleState().getEnergyCycle(),
                "State should transition back to Photosynthesis during day");
    }

    @Test
    public void testGrowthRateAdjustment() {
        assertEquals(0.0f, desertPlant.getGrowthRate(), 0.001f,
                "Initial growth rate should be 0");

        environmentMan.setBiome(Biome.DESERT);
        environmentMan.updateSeason();

        int attempts = 0;
        while (environmentMan.getWeather() != Weather.SUNNY && attempts < 20) {
            environmentMan.updateWeather();
            attempts++;
        }
        if (environmentMan.getWeather() != Weather.SUNNY) {
            fail("Failed to set weather to SUNNY after 20 attempts");
        }

        desertPlant.setGrowthRate(1.0f);
        desertPlant.handleWeatherUpdate(environmentMan.getWeather());

        assertEquals(1.1f, desertPlant.getGrowthRate(), 0.001f,
                "Growth rate should increase to 1.1 in SUNNY weather");

        attempts = 0;
        while (environmentMan.getWeather() != Weather.DRY && attempts < 20) {
            environmentMan.updateWeather();
            attempts++;
        }
        if (environmentMan.getWeather() != Weather.DRY) {
            fail("Failed to set weather to DRY after 20 attempts");
        }

        desertPlant.setGrowthRate(1.1f);
        desertPlant.handleWeatherUpdate(environmentMan.getWeather());

        assertEquals(0.88f, desertPlant.getGrowthRate(), 0.001f,
                "Growth rate should decrease to 0.88 in DRY weather");
    }

    @Test
    public void testHealthAdjustmentDuringEnergyCycle() {
        float maxHealth = desertPlant.getMaxHealth();
        float initialHealth = maxHealth * 0.75f;
        assertEquals(initialHealth, desertPlant.getHealth(), 0.001f,
                "Initial health should be 75% of max health");

        environmentMan.updateTimeOfDay();
        assertEquals(EnergyCycle.PHOTOSYNTHESIS, desertPlant.getEnergyCycleState().getEnergyCycle(),
                "State should be Photosynthesis during DAY");

        desertPlant.performEnergyCycle();
        float expectedHealth = initialHealth + 1.2f;
        assertEquals(expectedHealth, desertPlant.getHealth(), 0.001f,
                "Health should increase by 1.2 in PhotosynthesisState");

        float currentHealth = desertPlant.getHealth();
        float targetHealth = maxHealth * 0.4f;
        float adjustment = targetHealth - currentHealth;
        desertPlant.adjustHealth(adjustment);
        assertTrue(desertPlant.getHealth() < maxHealth * 0.5f,
                "Health should be below 50% of max health");

        currentHealth = desertPlant.getHealth();
        desertPlant.performEnergyCycle();
        expectedHealth = currentHealth + 0.84f;
        assertEquals(expectedHealth, desertPlant.getHealth(), 0.001f,
                "Health increase should be reduced to 0.84 due to low health");
    }
}
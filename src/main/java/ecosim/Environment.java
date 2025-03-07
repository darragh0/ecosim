package ecosim;


import java.util.List;

import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.BiomeMan;
import ecosim.man.SeasonMan;
import ecosim.man.TimeOfDayMan;
import ecosim.man.WeatherMan;
import ecosim.organism.plant.Plant;


public class Environment {
    private WeatherMan weatherManager;
    private SeasonMan seasonManager;
    private TimeOfDayMan timeOfDayManager;
    private BiomeMan biome;
    private List<Plant> plants;

    public void setBiome(String biomeName) {
        this.biome = new BiomeMan(biomeName);
        this.biome.setupBiome();
    }

    public void updateSeason() {
        this.seasonManager.getNextSeason();
        // load probabilities for new season
        weatherManager.loadWeatherProbabilities(this.biome.getBiomeName(),
            this.seasonManager.getCurrentState().toString());
    }

    public void updateDay() {
        this.timeOfDayManager.switchTimeOfDay();
    }

    public void updateWeather() {
        this.weatherManager.updateRandomWeather();

    }

    public Weather getWeather() {
        return this.weatherManager.getCurrentState();
    }

    public Season getSeason() {
        return this.seasonManager.getCurrentState();
    }

    public TimeOfDay getTimeOfDay() {
        return this.timeOfDayManager.getCurrentState();
    }

    public List<String> getBiomeNativeAnimals() {
        return this.biome.getNativeAnimals();
    }

    public List<String> getBiomeNativePlants() {
        return this.biome.getNativePlants();
    }

    /**
     * Update all plants with the current time of day and weather
     */
    public void updatePlantsWithTimeAndWeather() {
        TimeOfDay currentTime = timeOfDayManager.getCurrentState();
        Weather currentWeather = weatherManager.getCurrentState();
        
        for (Plant plant : plants) {
            plant.updateTimeOfDay(currentTime);
            plant.updateWeather(currentWeather);
        }
    }

}
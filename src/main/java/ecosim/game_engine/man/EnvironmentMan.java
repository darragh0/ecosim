package ecosim.game_engine.man;


import java.util.List;

import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.enm.Weather;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.Observer;
import ecosim.game_engine.misc.PlantDescriptor;


/**
 * Manager for all environmental aspects of the ecosystem.
 * Coordinates weather, seasons, time of day, and biome settings.
 * @author Kabidoye-17
 */
public class EnvironmentMan {
    private final WeatherMan weatherMan;
    private final SeasonMan seasonMan;
    private final TimeOfDayMan timeOfDayMan;
    private final ChangeMan changeMan;
    private BiomeMan biomeMan;

    /**
     * Creates a new EnvironmentMan with default managers.
     * Initializes weather, season, and time of day systems.
     */
    public EnvironmentMan() {
        this.changeMan = SimpleChangeMan.getInstance();
        this.timeOfDayMan = new TimeOfDayMan(this.changeMan);
        this.seasonMan = new SeasonMan(this.changeMan);
        this.weatherMan = new WeatherMan(this.changeMan);
    }

    /**
     * Sets the ecosystem biome and initializes biome-specific settings.
     * 
     * @param biome The biome to set for the ecosystem
     */
    public void setBiome(final Biome biome) {
        this.biomeMan = new BiomeMan(biome);
        this.biomeMan.setupBiome();
    }

    /**
     * Updates to the next season and recalculates weather probabilities.
     * Notifies all season observers of the change.
     */
    public void updateSeason() {
        this.seasonMan.getNextSeason();
        // load probabilities for new season
        weatherMan.loadWeatherProbabilities(
            this.biomeMan.getBiome(),
            this.seasonMan.getCurrentState());
    }

    /**
     * Toggles between day and night.
     * Notifies all time of day observers of the change.
     */
    public void updateTimeOfDay() {
        this.timeOfDayMan.switchTimeOfDay();
    }

    /**
     * Generates new weather based on current biome and season.
     * Notifies all weather observers of the change.
     */
    public void updateWeather() {
        this.weatherMan.updateRandomWeather();
    }

    /**
     * Gets the current weather condition.
     * 
     * @return The current Weather enum value
     */
    public Weather getWeather() {
        return this.weatherMan.getCurrentState();
    }

    /**
     * Gets the current season.
     * 
     * @return The current Season enum value
     */
    public Season getSeason() {
        return this.seasonMan.getCurrentState();
    }

    /**
     * Gets the current time of day.
     * 
     * @return The current TimeOfDay enum value
     */
    public TimeOfDay getTimeOfDay() {
        return this.timeOfDayMan.getCurrentState();
    }

    /**
     * Gets available animal descriptors for the current biome.
     * 
     * @return List of animal descriptors
     */
    public List<AnimalDescriptor> getBiomeAnimals() {
        return this.biomeMan.getAnimals();
    }

    /**
     * Gets available plant descriptors for the current biome.
     * 
     * @return List of plant descriptors
     */
    public List<PlantDescriptor> getBiomePlants() {
        return this.biomeMan.getPlants();
    }

    /**
     * Registers an observer to be notified of time of day changes.
     * 
     * @param observer The observer to register
     */
    public void registerTimeOfDayObservers(Observer observer) {
        this.timeOfDayMan.attach(observer);
    }

    /**
     * Registers an observer to be notified of season changes.
     * 
     * @param observer The observer to register
     */
    public void registerSeasonObservers(Observer observer) {
        this.seasonMan.attach(observer);
    }

    /**
     * Registers an observer to be notified of weather changes.
     * 
     * @param observer The observer to register
     */
    public void registerWeatherObservers(Observer observer) {
        this.weatherMan.attach(observer);
    }

}

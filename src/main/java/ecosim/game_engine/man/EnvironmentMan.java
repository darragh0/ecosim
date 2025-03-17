package ecosim.game_engine.man;


import java.util.List;

import ecosim.attrs.Observer;
import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.enm.Weather;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;



public class EnvironmentMan {
    private final WeatherMan weatherMan;
    private final SeasonMan seasonMan;
    private final TimeOfDayMan timeOfDayMan;
    private final ChangeMan changeMan;
    private BiomeMan biomeMan;

    public EnvironmentMan() {
        this.changeMan = SimpleChangeMan.getInstance();
        this.timeOfDayMan = new TimeOfDayMan(this.changeMan);
        this.seasonMan = new SeasonMan(this.changeMan);
        this.weatherMan = new WeatherMan(this.changeMan);
    }

    public void setBiome(final Biome biome) {
        this.biomeMan = new BiomeMan(biome);
        this.biomeMan.setupBiome();
    }

    public void updateSeason() {
        this.seasonMan.getNextSeason();
        // load probabilities for new season
        weatherMan.loadWeatherProbabilities(
            this.biomeMan.getBiome(),
            this.seasonMan.getCurrentState());
    }

    public void updateTimeOfDay() {
        this.timeOfDayMan.switchTimeOfDay();
    }

    public void updateWeather() {
        this.weatherMan.updateRandomWeather();
    }

    public Weather getWeather() {
        return this.weatherMan.getCurrentState();
    }

    public Season getSeason() {
        return this.seasonMan.getCurrentState();
    }

    public TimeOfDay getTimeOfDay() {
        return this.timeOfDayMan.getCurrentState();
    }

    public List<AnimalDescriptor> getBiomeAnimals() {
        return this.biomeMan.getAnimals();
    }

    public List<PlantDescriptor> getBiomePlants() {
        return this.biomeMan.getPlants();
    }

    public void registerTimeOfDayObservers(Observer observer) {
        this.timeOfDayMan.attach(observer);
    }

    public void registerSeasonObservers(Observer observer) {
        this.seasonMan.attach(observer);
    }

    public void registerWeatherObservers(Observer observer) {
        this.weatherMan.attach(observer);
    }

}

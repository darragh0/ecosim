package ecosim;


import java.util.List;

import ecosim.attrs.Observer;
import ecosim.enm.Biome;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.BiomeMan;
import ecosim.man.ChangeMan;
import ecosim.man.SeasonMan;
import ecosim.man.SimpleChangeMan;
import ecosim.man.TimeOfDayMan;
import ecosim.man.WeatherMan;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;


public class Environment {
    private WeatherMan weatherMan;
    private SeasonMan seasonMan;
    private TimeOfDayMan timeOfDayMan;
    private ChangeMan  changeMan;
    private BiomeMan biomeMan;

    public void setBiome(final Biome biome) {
        this.changeMan = SimpleChangeMan.getInstance();
        this.timeOfDayMan = new TimeOfDayMan(this.changeMan);
        this.seasonMan = new SeasonMan(this.changeMan);
        this.weatherMan = new WeatherMan(this.changeMan);
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

    public void updateDay() {
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

    public List<Class<? extends Animal>> getBiomeAnimals() {
        return this.biomeMan.getAnimals();
    }

    public List<Class<? extends Plant>> getBiomePlants() {
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

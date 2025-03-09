package ecosim;


import java.util.List;

import ecosim.attrs.Observer;
import ecosim.enm.AnimalType;
import ecosim.enm.Biome;
import ecosim.enm.PlantType;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.BiomeMan;
import ecosim.man.SeasonMan;
import ecosim.man.TimeOfDayMan;
import ecosim.man.WeatherMan;


public class Environment {
    private WeatherMan weatherMan;
    private SeasonMan seasonMan;
    private TimeOfDayMan timeOfDayMan;
    private BiomeMan biomeMan;

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

    public List<AnimalType> getBiomeNativeAnimals() {
        return this.biomeMan.getNativeAnimals();
    }

    public List<PlantType> getBiomeNativePlants() {
        return this.biomeMan.getNativePlants();
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

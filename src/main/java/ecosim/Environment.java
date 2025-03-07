package ecosim;


import java.util.List;

import ecosim.attrs.Observer;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.BiomeMan;
import ecosim.man.SeasonMan;
import ecosim.man.TimeOfDayMan;
import ecosim.man.WeatherMan;


public class Environment {
    private WeatherMan weatherManager;
    private SeasonMan seasonManager;
    private TimeOfDayMan timeOfDayManager;
    private BiomeMan biome;

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

    public void registerTimeOfDayObservers(Observer observer){
        this.timeOfDayManager.attach(observer);
    }

    public void registerSeasonObservers(Observer observer){
        this.seasonManager.attach(observer);
    }

    public void registerWeatherObservers(Observer observer){
        this.weatherManager.attach(observer);
    }

}
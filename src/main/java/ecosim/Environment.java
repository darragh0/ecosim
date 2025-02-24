package ecosim;


import ecosim.attrs.Observable;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import java.util.List;


public class Environment implements Observable {
    private Weather weather;
    private WeatherManager weatherManager;
    private Season season;
    private TimeOfDay timeOfDay;
    private BiomeManager biome;

    @Override
    public void registerObservers() {}

    @Override
    public void unregisterObservers() {}

    @Override
    public void notifyObservers() {}

    public void updateSeason() {
        this.season = this.season.getNextSeason();
        // load probablilities for new season
        weatherManager.loadWeatherProbabilities(this.biome.getBiomeName(), this.season.toString());
        System.out.println("Season update! It is now " + this.season + ".");
    }

    public void updateDay() {
        this.timeOfDay = this.timeOfDay.switchTimeOfDay();
        System.out.println("Time of day update! It is now " + this.timeOfDay.toString() + ".");
    }

    public void updateWeather() {
        this.weather = this.weatherManager.getRandomWeather();
    }

    public void setBiome(String biomeName) {
        this.biome = new BiomeManager(biomeName);
        this.biome.setupBiome();
    }

    public List<String> getBiomeNativeAnimals(){
        return this.biome.getNativeAnimals();
    }

    public List<String> getBiomeNativePlants(){
        return this.biome.getNativePlants();
    }


}

package ecosim;


import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import java.util.List;


public class Environment  {
    private WeatherManager weatherManager;
    private SeasonManager seasonManager;
    private TimeOfDayManager timeOfDayManager;
    private BiomeManager biome;

    public void setBiome(String biomeName) {
        this.biome = new BiomeManager(biomeName);
        this.biome.setupBiome();
    }

    public void updateSeason() {
        this.seasonManager.getNextSeason();
        // load probabilities for new season
        weatherManager.loadWeatherProbabilities(this.biome.getBiomeName(), this.seasonManager.getCurrentState().toString());
    }

    public void updateDay() {
        this.timeOfDayManager.switchTimeOfDay();
    }

    public void updateWeather() {
        this.weatherManager.getRandomWeather();

    }

    public Weather getWeather(){
        return this.weatherManager.getCurrentState();
    }

    public Season getSeason(){
        return this.seasonManager.getCurrentState();
    }

    public TimeOfDay getTimeOfDay(){
        return this.timeOfDayManager.getCurrentState();
    }
    public List<String> getBiomeNativeAnimals(){
        return this.biome.getNativeAnimals();
    }

    public List<String> getBiomeNativePlants(){
        return this.biome.getNativePlants();
    }


}

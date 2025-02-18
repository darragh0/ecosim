package ecosim;


import ecosim.attrs.Observable;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;


public class Environment implements Observable {
    private Weather weather;
    private WeatherManager weatherManager;
    private Season season;
    private TimeOfDay timeOfDay;
    private Biome biome;

    @Override
    public void registerObservers() {}

    @Override
    public void unregisterObservers() {}

    @Override
    public void notifyObservers() {}

    public void updateSeason() {
        this.season = this.season.getNextSeason();
        // load probablilities for new season
        weatherManager.loadWeatherProbabilities(this.biome.getName(), this.season.toString());
        System.out.println("Season update! It is now " + this.season + ".");
    }

    public void updateDay() {
        this.timeOfDay = this.timeOfDay.switchTimeOfDay();
        System.out.println("Time of day update! It is now " + this.timeOfDay.toString() + ".");
    }

    public void updateWeather() {
        this.weather = this.weatherManager.getRandomWeather();
    }

}

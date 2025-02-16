package ecosim.ecosystem;

public class Environment implements Subject {
    private WeatherManager.Weather weather;
    private WeatherManager weatherManager;
    private Season season;
    private TimeOfDay timeOfDay;
    private Biome biome;

    @Override
    public void registerObservers() {
    }

    @Override
    public void unregisterObservers() {
    }

    @Override
    public void notifyObservers() {
    }

    public void updateSeason() {
        this.season = this.season.getNextSeason();
        weatherManager.loadWeatherProbabilities(this.biome.getName(), this.season.toString()); // load probabilities for new season
        System.out.println("Season update! It is now " + this.season + ".");
    }

    public void updateDay() {
        this.timeOfDay = this.timeOfDay.switchTimeOfDay();
        System.out.println("Time of day update! It is now " + this.timeOfDay.toString() + ".");
    }

    public void updateWeather(){
        this.weather = this.weatherManager.getRandomWeather();
    }
}

package ecosim;
import java.util.HashMap;
import java.util.Map;

public class WeatherManager {
    public enum Weather {
        RAINY,
        SUNNY,
        DRY,
        CLOUDY,
        SNOWY
    }


    private Map<Weather, Double> weatherProbabilities;

    public WeatherManager() {
        weatherProbabilities = new HashMap<>();
    }

    public void loadWeatherProbabilities(String biome, String season) {
     // TODO: implement loading and storing of weather probabilities based on biome and season
    }


    public Weather getRandomWeather() {
        // TODO: implement randomly calculating weather probability for the day based on weather probabilites
        return null;
    }

}

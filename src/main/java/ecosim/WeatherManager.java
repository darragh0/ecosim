package ecosim;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.enm.Weather;


public class WeatherManager {

    private Map<Weather, Double> weatherProbabilities;
    private static final Logger LOGGER = LoggerManager.getLogger();


    public WeatherManager() {
        weatherProbabilities = new HashMap<>();
    }

    public void loadWeatherProbabilities(String biome, String season) {
        String upperBiome = biome.toUpperCase();
        String upperSeason = season.toUpperCase();
        Map<Weather, Double> tempProbabilities = new HashMap<>();

        try {
            String content =
                new String(Files.readAllBytes(Paths.get("src/main/resources/json/weather_probabilities.json")));
            JSONObject json = new JSONObject(content);

            if (json.has(upperBiome)) {
                JSONObject biomeData = json.getJSONObject(upperBiome);
                if (biomeData.has(upperSeason)) {
                    JSONObject seasonData = biomeData.getJSONObject(upperSeason);
                    for (String key : seasonData.keySet()) {
                        Weather weather = Weather.valueOf(key.toUpperCase());
                        double probability = seasonData.getDouble(key);
                        tempProbabilities.put(weather, probability);
                    }
                } else {
                    LOGGER.warning("Season not found for biome: " + upperSeason);
                }
            } else {
                LOGGER.warning("Biome not found: " + upperBiome);
            }
        } catch (IOException | JSONException | IllegalArgumentException e) {
            LOGGER.severe("Error loading weather probabilities: " + e.getMessage());
        }

        this.weatherProbabilities = tempProbabilities;
    }


    public Weather getRandomWeather() {
        double random = Math.random();
        double cumulative = 0.0;
        for (Map.Entry<Weather, Double> entry : this.weatherProbabilities.entrySet()) {
            cumulative += entry.getValue();
            if (random <= cumulative) {
                return entry.getKey();
            }
        }
        return Weather.CLOUDY; // fallback
    }

    public static void main(String[] args) {
        WeatherManager wm = new WeatherManager();
        wm.loadWeatherProbabilities("GRASSLAND", "WINTER");
        System.out.println(wm.getRandomWeather());
    }

}

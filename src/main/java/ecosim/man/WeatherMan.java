package ecosim.man;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.attrs.Observable;
import ecosim.enm.Weather;


public class WeatherMan extends Observable {

    private Weather currentWeather;
    private Map<Weather, Double> weatherProbabilities;
    private static final Logger LOGGER = LoggerMan.getLogger();

    public WeatherMan(ChangeMan changeManager) {
        super(changeManager);
        weatherProbabilities = new HashMap<>();
    }

    @Override
    public Weather getCurrentState() {
        return this.currentWeather;
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


    public void updateRandomWeather() {
        double random = Math.random();
        double cumulative = 0.0;
        for (Map.Entry<Weather, Double> entry : this.weatherProbabilities.entrySet()) {
            cumulative += entry.getValue();
            if (random <= cumulative) {
                this.currentWeather = entry.getKey();
            }
        }
        this.currentWeather = Weather.CLOUDY; // fallback
    }


}

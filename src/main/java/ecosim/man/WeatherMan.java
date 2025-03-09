package ecosim.man;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.attrs.Observable;
import ecosim.enm.Biome;
import ecosim.enm.Weather;


public class WeatherMan extends Observable {

    private Weather currentWeather;
    private Map<Weather, Double> weatherProbabilities;

    public WeatherMan(ChangeMan changeManager) {
        super(changeManager);
        weatherProbabilities = new HashMap<>();
    }

    @Override
    public Weather getCurrentState() {
        return this.currentWeather;
    }

    public void loadWeatherProbabilities(final Biome biome, String season) {
        String biomeName = biome.name();
        String seasonName = season.toUpperCase();
        Map<Weather, Double> tempProbabilities = new HashMap<>();

        try {
            String content =
                new String(Files.readAllBytes(Paths.get("src/main/resources/json/weather_probabilities.json")));
            JSONObject json = new JSONObject(content);

            if (json.has(biomeName)) {
                JSONObject biomeData = json.getJSONObject(biomeName);
                if (biomeData.has(seasonName)) {
                    JSONObject seasonData = biomeData.getJSONObject(seasonName);
                    for (String key : seasonData.keySet()) {
                        Weather weather = Weather.valueOf(key.toUpperCase());
                        double probability = seasonData.getDouble(key);
                        tempProbabilities.put(weather, probability);
                    }
                } else {
                    LoggerMan.log(Level.WARNING, "Season not found for biome: {0}", seasonName);
                }
            } else {
                LoggerMan.log(Level.WARNING, "Biome not found: {0}", biomeName);
            }
        } catch (IOException | JSONException | IllegalArgumentException e) {
            LoggerMan.log(Level.SEVERE, "Error loading weather probabilities: {0}", e.getMessage());
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
                return;
            }
        }
        this.currentWeather = Weather.CLOUDY; // fallback
        changeManager.notifyObservers(this);
    }

}

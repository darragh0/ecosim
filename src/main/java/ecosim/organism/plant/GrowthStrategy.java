package ecosim.organism.plant;

import ecosim.enm.Weather;

public interface GrowthStrategy {
    void adjustGrowthRate(Plant plant, Weather currentWeather);
} 
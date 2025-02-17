// package ecosim.weather;
// import java.util.Random;
// /**
//  * Weather data class. Used to manage weather conditions and notify observers.
//  * @author MiaBorkoo
//  */
// public class WeatherData {
//     private double temp;
//     private double humidity;
//     private double pressure;
//     private WeatherSystem weatherSystem;

//     public WeatherData() {
//         this.temp = 0;
//         this.humidity = 0;
//         this.pressure = 0;
//         setRandomWeatherCondition();
//     }
//     //Set random weather condition for initial weather
//     private void setRandomWeatherCondition() {
//         WeatherType[] conditions = WeatherType.values(); // Get all enum values
//         Random random = new Random();
//         WeatherType randomCondition = conditions[random.nextInt(conditions.length)];
//         weatherSystem.setWeatherCondition(randomCondition.name()); // Notify observers with random condition
//     }

// }

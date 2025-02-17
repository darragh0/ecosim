// package ecosim.weather;
// /**
//  * Weather system class. Used to manage weather conditions and notify observers.
//  * @author MiaBorkoo
//  */
// import java.util.ArrayList;
// import java.util.List;

// public class WeatherSystem {
//     private String currentCondition;
//     private List<WeatherObserver> observers = new ArrayList<>();


//     public void addObserver(WeatherObserver observer) {
//         observers.add(observer);
//     }

//     // Remove an organism from observing weather updates
//     public void removeObserver(WeatherObserver observer) { //Idk if we'll use this since probably all organisms will be observing weather
//         observers.remove(observer);
//     }

//     // Notify all organisms when weather changes
//     public void notifyObservers() {
//         for (WeatherObserver observer : observers) {
//             observer.update(currentCondition);
//         }
//     }
//     // Set the weather condition
//     public void setWeatherCondition(String condition) {
//         this.currentCondition = condition;
//         notifyObservers();
//     }
//     // Change the weather condition
//     public void changeWeatherCondition(String newCondition) {
//         setWeatherCondition(newCondition);
//         System.out.println("\n🌦️ Weather changed to: " + newCondition);
//     }
     
// }

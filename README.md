# Ecosystem Simulator 🌳

A Java-based ecosystem simulation that models the interactions between animals, plants, and environmental conditions across different biomes. Watch as your ecosystem evolves through seasons, weather changes, and the daily activities of its inhabitants.

## Features

### 🌍 Biomes
- **Desert Biome**: Home to camels, eagles, lizards, snakes, and cactus mice
- **Grassland Biome**: Home to deer, foxes, lions, owls, and rabbits

### 🐾 Animals
- **Diet Types**: Herbivores, carnivores, and omnivores with realistic feeding patterns
- **Activity Patterns**: Diurnal and nocturnal animals with different daily cycles
- **Hibernation**: Some species can hibernate during harsh conditions
- **Size Categories**: Small, medium, and large animals with different energy requirements
- **Unique Sounds**: Each animal has its own distinctive call

### 🌱 Plants
- **Size Varieties**: Small, medium, and large plants affecting ecosystem capacity
- **Biome-Specific**: Plants native to each biome type
- **Energy Production**: Plants generate energy through photosynthesis cycles

### 🌤️ Environments
- **Weather Simulation**: Dynamic weather conditions affecting organism behavior
- **Seasonal Changes**: Different seasons impact ecosystem dynamics
- **Time Progression**: Day/night cycles with hourly simulation updates
- **Energy Management**: Realistic energy consumption and production cycles

### 📊 Simulations
- **Interactive Setup**: Choose your biome and initial organisms
- **Real-time Monitoring**: Watch daily ecosystem changes and organism activities
- **Comprehensive Reports**: Daily and final simulation reports
- **Configurable Parameters**: Adjustable simulation duration, capacity limits, and time settings

## Prerequisites

- **Java Development Kit (JDK) 21** or higher
- **Apache Maven 3.6+**

## Quick Start

### Option 1: Using Run Scripts (Recommended)

**Linux/macOS:**
```bash
chmod +x run.sh
./run.sh
```

**Windows:**
```cmd
run.bat
```

## Project Structure

```
ecosim/
├── src/main/java/ecosim/
│   ├── controller/          # Main simulation controller
│   ├── game_engine/         # Core simulation logic
│   │   ├── enm/            # Enumerations (Biome, Diet, etc.)
│   │   ├── man/            # Managers (Ecosystem, Environment, etc.)
│   │   ├── map/            # Grid-based map system
│   │   ├── organism/       # Animal and plant implementations
│   │   └── misc/           # Configuration and descriptors
│   ├── ui/                 # User interface components
│   │   ├── menu/           # Menu systems
│   │   └── view/           # Display views
│   └── common/             # Shared utilities
├── src/main/resources/json/ # Configuration files
│   ├── biome_natives.json   # Animal and plant definitions
│   ├── ecosystem_config.json # Simulation parameters
│   └── weather_probabilities.json # Weather patterns
└── src/test/java/          # Unit tests
```

## Usage

1. **Launch the simulation** using one of the run methods above
2. **Choose your biome** (Desert or Grassland)
3. **Select initial animals** from the available species
4. **Select initial plants** for your ecosystem
5. **Watch the simulation** as organisms interact, reproduce, and adapt
6. **Monitor daily reports** showing population changes and activities
7. **View the final report** when the simulation completes

## Development

### Running Tests
```bash
mvn test
```

### Building JAR
```bash
mvn clean package
```

## Architecture

The simulator uses several design patterns:
- **Builder Pattern**: For creating organisms with complex configurations
- **Factory Pattern**: For biome-specific organism creation
- **Decorator Pattern**: For adding special abilities to organisms
- **Observer Pattern**: For environmental change notifications
- **State Pattern**: For organism behavior states (sleeping, hibernating, etc.)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
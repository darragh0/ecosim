package ecosim.controller;

import ecosim.BiomeManager;
import ecosim.EcosystemManager;
import ecosim.Environment;
import ecosim.enm.Biome;
import ecosim.organism.animal.Animal;
import ecosim.view.EcosystemView;

import java.util.List;

public class EcosystemController {
    private EcosystemManager ecosystemManager;
    private EcosystemView ecosystemView;

    public EcosystemController() {
        this.ecosystemManager = new EcosystemManager();
        this.ecosystemView = new EcosystemView();
    }

    public void run(){
        // sample code of how the controller interacts with the model and view
        this.setup();
        this.ecosystemView.displayDailyReport(this.ecosystemManager);
    }

    public void setup(){
        // beginning of setup
        Biome biome = this.ecosystemView.promptBiomeSelection();
        Environment ecosystemEnvironment = this.ecosystemManager.getEnvironment();

        // set biome and set load associated plants and animals
        ecosystemEnvironment.setBiome(biome.getName());

        // get users initial plant and animal choices
        List<String> chosenPlants = this.ecosystemView.promptOrganismSelection("Plant", ecosystemEnvironment.getBiomeNativePlants(), 3);
        List<String> chosenAnimals = this.ecosystemView.promptOrganismSelection("Animal", ecosystemEnvironment.getBiomeNativeAnimals(), 3);

        // load ecosystem with animals and plants once factory is implemented
        for (String animal: chosenAnimals){
            this.ecosystemManager.createAnimal(animal);
        }
        for (String plant: chosenPlants){
            this.ecosystemManager.createPlant(plant);
        }
    }

}

package ecosim.controller;


import java.util.List;

import ecosim.Environment;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.view.EcosystemView;


public class EcosystemController {

    private final EcosystemMan man;
    private final EcosystemView view;

    public EcosystemController() {
        this.man = new EcosystemMan();
        this.view = new EcosystemView();
    }

    public void run() {
        // sample code of how the controller interacts with the model and view
        this.man.run();
        this.setup();
        this.view.displayDailyReport(this.man);
    }

    public void setup() {
        Environment env = this.man.getEnvironment();
        Biome biome = this.view.promptBiomeSelection();

        env.setBiome(biome.getName());

        final List<String> animals = this.view.promptAnimalSelection(env.getBiomeNativeAnimals(), 3);
        final List<String> plants = this.view.promptPlantSelection(env.getBiomeNativePlants(), 3);

        // load ecosystem with animals and plants once factory is implemented
        for (String animal : animals) {
            this.man.createAnimal(animal, biome.getName());
        }

        for (String plant : plants) {
            this.man.createPlant(plant, biome.getName());
        }
    }

}

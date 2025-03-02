package ecosim.organism.plant.factories;

import ecosim.enm.Size;
import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Bush;
import ecosim.organism.plant.Wildflower;
import ecosim.organism.plant.Plant;

public class ConcreteGrasslandPlantFactory implements PlantFactory {
    @Override
    public Plant createPlant(String plantType, int x, int y) {
        switch (plantType.toLowerCase()) {
            case "tree":
                return new Tree(Size.LARGE, x, y);
            case "bush":
                return new Bush(Size.MEDIUM, x, y);
            case "wildflower":
                return new Wildflower(Size.SMALL, x, y);
            default:
                throw new IllegalArgumentException("Unknown grassland plant type: " + plantType);
        }
    }
} 
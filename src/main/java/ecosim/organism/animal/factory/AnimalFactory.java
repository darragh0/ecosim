package ecosim.organism.animal.factory;


import ecosim.organism.animal.Animal;


public interface AnimalFactory {
    Animal createAnimal(String type, int x, int y);
}

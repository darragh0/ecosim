package ecosim.organism.animal;


import ecosim.attrs.Movable;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.map.Map;
import ecosim.organism.Organism;
import ecosim.organism.animal.conscious_state.Conscious;
import ecosim.organism.animal.conscious_state.ConsciousState;


public abstract class Animal extends Organism implements Movable {

    protected Size size;
    protected Diet diet;
    protected ActivityType activityType;
    protected ConsciousState consciousState;
    protected boolean canHibernate;
    protected float survivalChance;
    protected float reproductiveChance;

    public Animal(int x, int y, Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(size.getMaxHealth(), x, y, size.getNutritionalValue());
        this.size = size;
        this.diet = diet;
        this.activityType = activityType;
        this.canHibernate = canHibernate;
        this.consciousState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
    }

    public Animal(Animal animal) {
        this(animal.getX(), animal.getY(), animal.size, animal.diet, animal.activityType, animal.canHibernate);
    }

    public Diet getDiet() {
        return this.diet;
    }

    public Size getSize() {
        return this.size;
    }

    public ActivityType getActivityType() {
        return this.activityType;
    }

    public void makeSound() {}

    public void eat() {}

    public void breed() {}

    @Override
    public void update() {}

    public void move() {
        this.consciousState.move();
    }

    public float getSurvivalChance() {
        return this.survivalChance;
    }

    public float getReproductiveChance() {
        return this.reproductiveChance;
    }

    public boolean isEdible(Organism organism) {
        return false;
    }

    public void iWasGonnaCallThisFunctionMoveButICANTBecauseSomeoneElseAlreadyTookTheNameMoveSoIAmLeftToComeUpWithAnotherNameForThisMethodWhichHasUnfortunatelyBecomeVeryLongAsAResultOfTheFactThatSomebodyHasReservedTheMoveNameSoNowImSadAndHeresALongMethodName() {
        Map.getInstance().move(this);
    }

    @Override
    public void setCoords(int x, int y) {
        this.coords.setX(x);
        this.coords.setY(y);
    }

}

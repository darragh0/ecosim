package ecosim.organism.animal.conscious_state;


public class Unconscious implements ConsciousState {
    @Override
    public void move() {
        System.out.println("The animal is unconcious, therefore cannot move.");
    }
}

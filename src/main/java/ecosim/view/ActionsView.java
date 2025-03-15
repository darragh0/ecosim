package ecosim.view;

import static ecosim.common.io.ConsoleIO.add;
import ecosim.map.ActionResult;
import ecosim.map.ActionResult.ActionType;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;

public class ActionsView {

    public void displayAnimalActionsHeader() {
        StringBuilder str = new StringBuilder();
        add.accept(str, "** [fly:Today's Animal Highlights] **");
        System.out.println(str.toString());
    }

    public void displayAnimalActions(ActionResult result) {
        // Skip displaying movement or idle actions
        if (result.getActionType() == ActionType.MOVED || result.getActionType() == ActionType.NONE) {
            return;
        }

        StringBuilder str = new StringBuilder();


        if (result.getActor() == null) {
            add.accept(str, "  Mystery action occurred!");
            System.out.println(str.toString());
            return;
        }

        Animal actor = result.getActor();
        Organism target = result.getTarget();

        // Format and add the action message
        String message = formatActionMessage(
            actor,
            target,
            result.getActionType(),
            result.getNewX(),
            result.getNewY());

        add.accept(str, "  " + message);
        System.out.println(str.toString());
    }

    private String formatActionMessage(Animal actor, Organism target, ActionType actionType, int newX, int newY) {
        String actorName = actor.getName();
        String actorSymbol = actor.getSymbol();
        String sound = actor.getSound();

        return switch (actionType) {
            case DIED -> formatAttemptedDeathMessage(actorName, actorSymbol, sound);
            case ATTEMPTED_BREEDING -> formatAttemptedBreedingMessage(actorName, actorSymbol, target, sound);
            case SUCCESSFUL_BREEDING -> formatSuccessfulBreedingMessage(actorName, actorSymbol, target, sound);
            case ATTEMPTED_EATING -> formatAttemptedEatingMessage(actorName, actorSymbol, target, sound);
            case SUCCESSFUL_EATING -> formatSuccessfulEatingMessage(actorName, actorSymbol, target, sound);
            default -> null; 
        };
    }

    private String formatAttemptedDeathMessage(String actorName, String actorSymbol, String sound) {
        return String.format("(💀) %s %s has died from starvation. %s", actorSymbol, actorName, sound);
    }

    private String formatAttemptedBreedingMessage(String actorName, String actorSymbol, Organism target, String sound) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol(); // Assuming getSymbol() exists
            return String.format("(💔) %s %s got rejected by %s %s. %s", actorSymbol, actorName, targetSymbol,
                targetName, sound);
        }
        return String.format("(💔) %s %s tries dating app. No matches. %s", actorSymbol, actorName, sound);
    }

    private String formatSuccessfulBreedingMessage(String actorName, String actorSymbol, Organism target,
        String sound) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();
            return String.format("(❤️) %s %s breeds with %s %s! Baby time! %s",
                actorSymbol, actorName, targetSymbol, targetName, sound);
        }
        return String.format("(❤️) %s %s somehow has a baby! %s", actorSymbol, actorName, sound);
    }

    private String formatAttemptedEatingMessage(String actorName, String actorSymbol, Organism target, String sound) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();
            return String.format("(🥺🍽️) %s %s tried eating %s %s but failed. %s",
                actorSymbol, actorName, targetSymbol, targetName, sound);
        }
        return String.format("(🥺🍽️)%s %s missed lunch. %s", actorSymbol, actorName, sound);
    }

    private String formatSuccessfulEatingMessage(String actorName, String actorSymbol, Organism target, String sound) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();
            return String.format("(😌🍽️) %s %s devoured %s %s! %s",
                actorSymbol, actorName, targetSymbol, targetName, sound);
        }
        return String.format("(😌🍽️) %s %s had a tasty meal! %s", actorName, actorSymbol, sound);
    }
}
package ecosim.ui.view;

import static ecosim.common.io.ConsoleIO.add;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.map.ActionResult.ActionType;
import ecosim.game_engine.organism.Organism;

public class ActionsView {

    public void displayAnimalActionsHeader() {
        StringBuilder str = new StringBuilder();
        add.accept(str, "** [fly:Today's Animal Highlights] **");
        System.out.println(str.toString());
    }

    public void displayAnimalActions(ActionResult result) {
        // Skip displaying movement or idle actions
        if (result.getActionType() == ActionType.BASIC_ACTION) {
            return;
        }

        StringBuilder str = new StringBuilder();


        if (result.getActor() == null) {
            add.accept(str, "  Mystery action occurred!");
            System.out.println(str.toString());
            return;
        }

        Organism actor = result.getActor();
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

    private String formatActionMessage(Organism actor, Organism target, ActionType actionType, int newX, int newY) {
        String actorName = actor.getName();
        String actorSymbol = actor.getSymbol();

        return switch (actionType) {
            case DIED -> formatAttemptedDeathMessage(actorName, actorSymbol);
            case ATTEMPTED_BREEDING -> formatAttemptedBreedingMessage(actorName, actorSymbol, target);
            case SUCCESSFUL_BREEDING -> formatSuccessfulBreedingMessage(actorName, actorSymbol, target);
            case ATTEMPTED_EATING -> formatAttemptedEatingMessage(actorName, actorSymbol, target);
            case SUCCESSFUL_EATING -> formatSuccessfulEatingMessage(actorName, actorSymbol, target);
            default -> null; 
        };
    }

    private String formatAttemptedDeathMessage(String actorName, String actorSymbol) {
        return String.format("(💀) %s %s has died from starvation.", actorSymbol, actorName);
    }

    private String formatAttemptedBreedingMessage(String actorName, String actorSymbol, Organism target) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol(); // Assuming getSymbol() exists
            return String.format("(💔) %s %s got rejected by %s %s.", actorSymbol, actorName, targetSymbol,
                targetName);
        }
        return String.format("(💔) %s %s tries dating app. No matches", actorSymbol, actorName);
    }

    private String formatSuccessfulBreedingMessage(String actorName, String actorSymbol, Organism target
        ) {
        // Handle the case where target exists but there's no actor (plant asexual reproduction)
        if (target == null) {
            return String.format("(❤️) %s %s reproduced asexually! New plant created!", 
                actorSymbol, 
                actorName);
        }
        // Normal animal breeding case
        String targetName = target.getName();
        String targetSymbol = target.getSymbol();
        
        // Check if it's asexual reproduction (same name)
        if (actorName.equals(targetName)) {
            return String.format("(❤️) %s %s reproduced asexually! Baby time!",
                actorSymbol, actorName );
        }
        
        return String.format("(❤️) %s %s breeds with %s %s! Baby time!",
            actorSymbol, actorName, targetSymbol, targetName );
    }

    private String formatAttemptedEatingMessage(String actorName, String actorSymbol, Organism target) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();
            return String.format("(🥺🍽️) %s %s tried eating %s %s but failed.",
                actorSymbol, actorName, targetSymbol, targetName );
        }
        return String.format("(🥺🍽️)%s %s missed lunch.", actorSymbol, actorName );
    }

    private String formatSuccessfulEatingMessage(String actorName, String actorSymbol, Organism target) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();
            return String.format("(😌🍽️) %s %s devoured %s %s!",
                actorSymbol, actorName, targetSymbol, targetName );
        }
        return String.format("(😌🍽️) %s %s had a tasty meal!", actorName, actorSymbol );
    }
}
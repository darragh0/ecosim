package ecosim.view;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.prettify;
import static ecosim.common.io.ConsoleIO.toggleCursor;
import ecosim.common.io.enm.BoxDrawingChar;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.map.ActionResult;
import ecosim.map.ActionResult.ActionType;
import ecosim.map.Grid;
import ecosim.menu.AnimalMenu;
import ecosim.menu.BiomeMenu;
import ecosim.menu.OrganismMenu;
import ecosim.menu.PlantMenu;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;

public class EcosystemView {

    private static final BiConsumer<StringBuilder, String> add =
        (builder, str) -> builder.append(prettify(str)).append("\n");

    public void welcome() {
        SplashScreen.show();
        pprintln("Welcome to the *Ecosystem Simulator* 🌳");
        pprintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    public void end(int exitCode) {
        toggleCursor(true);
        pprintln("\n[flr:(Simulator finished w/ exit code %d)]", exitCode);
    }

    public void displayDailyReport(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();
        add.accept(str, "**✨ [fly:Day %d Report] ✨**\n".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **[flc:Headcount:]**");
        add.accept(str, "    Animals: **[flc:%d]**".formatted(ecosystem.getAnimalCount()));
        add.accept(str, "    Plants:  **[flc:%d]**\n".formatted(ecosystem.getPlantCount()));
        add.accept(str, "  **[flc:Statistics:]**");

        int maxWidth = calculateMaxWidth(ecosystem);
        addOrganismReport("Animals", ecosystem.getAnimals(), str, maxWidth);
        addOrganismReport("Plants", ecosystem.getPlants(), str, maxWidth);
        
        addLifeCycleReport("Animals", ecosystem.getNewbornAnimals(), ecosystem.getDeadAnimals(), str, maxWidth);
        
        List<Plant> emptyPlantsList = new ArrayList<>(); // placeholder
        addLifeCycleReport("Plants", emptyPlantsList, ecosystem.getDeadPlants(), str, maxWidth);

        System.out.println(str.toString());
    }

    private int calculateMaxWidth(EcosystemMan ecosystem) {
        int animalMaxLength = ecosystem.getAnimals().stream()
            .mapToInt(o -> o.getName().length())
            .max()
            .orElse(0);

        int plantMaxLength = ecosystem.getPlants().stream()
            .mapToInt(o -> o.getName().length())
            .max()
            .orElse(0);

        return Math.max(Math.max(animalMaxLength, plantMaxLength), "Thriving Animals:".length());
    }

    private <T extends Organism> void addOrganismReport(final String type, List<T> organisms, StringBuilder str,
        int maxWidth) {
        final int gap = 10;
        final int goodHealth = 70;
        final int poorHealth = 30;

        final List<T> thriving = organisms.stream()
            .filter(o -> o.getHealth() >= goodHealth)
            .collect(Collectors.toList());

        final List<T> declining = organisms.stream()
            .filter(o -> o.getHealth() <= poorHealth)
            .collect(Collectors.toList());

        final String thrivingHeader = "Thriving %s:".formatted(type);
        final String decliningHeader = "Declining %s:".formatted(type);

        int headerPadding = maxWidth - thrivingHeader.length() + gap;
        final String fStr =
            "    [flg:%s]%s[flr:%s]".formatted(thrivingHeader, " ".repeat(headerPadding), decliningHeader);
        add.accept(str, fStr);

        final int maxRows = Math.max(thriving.size(), declining.size());

        for (int i = 0; i < maxRows; i++) {
            StringBuilder row = new StringBuilder();

            if (i < thriving.size()) {
                final String name = thriving.get(i).getName();
                final String rowStr = String.format("      > %s", name);
                row.append(rowStr);

                int padding = maxWidth - rowStr.length() + gap;
                if (padding > 0)
                    row.append(" ".repeat(padding));
            } else {
                row.append(" ".repeat(maxWidth + gap));
            }

            if (i < declining.size())
                row.append("      > %s".formatted(declining.get(i).getName()));

            add.accept(str, row.toString());
        }
        add.accept(str, "");
    }

    private <T extends Organism> void addLifeCycleReport(String type, List<T> newborns, List<T> deceased, StringBuilder str, int maxWidth) {
        final int gap = 10;
        
        // Column headers
        final String newbornHeader = "New %s:".formatted(type);
        final String deceasedHeader = "Deceased %s:".formatted(type);

        int headerPadding = maxWidth - newbornHeader.length() + gap;
        final String headerStr = 
            "    [flg:%s]%s[flr:%s]".formatted(newbornHeader, " ".repeat(headerPadding), deceasedHeader);
        add.accept(str, headerStr);

        final int maxRows = Math.max(newborns.size(), deceased.size());
        if (maxRows == 0) {
            add.accept(str, " ".repeat(maxWidth - 1));
        } else {
          
            for (int i = 0; i < maxRows; i++) {
                StringBuilder row = new StringBuilder();

                // Left column (newborns)
                if (i < newborns.size()) {
                    T organism = newborns.get(i);
                    String rowStr = String.format("      > %s ", organism.getName());
                    row.append(rowStr);

                    int padding = maxWidth - rowStr.length() + gap;
                    if (padding > 0)
                        row.append(" ".repeat(padding));
                } else {
                    row.append(" ".repeat(maxWidth + gap));
                }

                // Right column (deceased)
                if (i < deceased.size()) {
                    T organism = deceased.get(i);
                    row.append(String.format("      > %s", organism.getName()));
                }

                add.accept(str, row.toString());
            }
        }
        
        add.accept(str, "");
    }

    public Biome promptBiomeSelection() {
        final BiomeMenu menu = new BiomeMenu(Biome.values());
        menu.print();

        final Biome choice = menu.getUserChoice();
        System.out.println();

        return choice;
    }

    public <T extends Organism> List<Class<? extends T>> promptOrganismSelection(OrganismMenu<T> menu, int num) {
        final List<Class<? extends T>> chosen = new ArrayList<>();
        menu.print();

        for (int i = 0; i < num; i++) {
            Class<? extends T> choice = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));
            chosen.add(choice);
        }
        System.out.println();

        return chosen;
    }

    public List<Class<? extends Animal>> promptAnimalSelection(List<Class<? extends Animal>> animals, int num) {
        return promptOrganismSelection(new AnimalMenu(animals), num);
    }

    public List<Class<? extends Plant>> promptPlantSelection(List<Class<? extends Plant>> plants, int num) {
        return promptOrganismSelection(new PlantMenu(plants), num);
    }

    public void displayTimeStatus(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();
        
        add.accept(str, "**⏱️ [fly:Simulation Time] ⏱️**\n");
        add.accept(str, "  **Day:** [flc:%d]".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **Time of Day:** [flc:%s] %s".formatted(
            ecosystem.getCurrentTimeOfDay().name(),
            ecosystem.getCurrentTimeOfDay().getIcon()
        ));
        System.out.println(str.toString());
    }
    
    public void displayEnvironmentConditions(EcosystemMan ecosystem) {
        this.displayTimeStatus(ecosystem);
        StringBuilder str = new StringBuilder();
        
        add.accept(str, "**🌍 [fly:Environment Status] 🌍**\n");
        
        // Get environment conditions
        
        add.accept(str, "  **Weather:** [flc:%s] %s".formatted(
            ecosystem.getCurrentWeather().name(), 
            ecosystem.getCurrentWeather().getIcon()
        ));
        
        add.accept(str, "  **Season:** [flc:%s] %s".formatted(
            ecosystem.getCurrentSeason().name(),
            ecosystem.getCurrentSeason().getIcon()
        ));
        
        System.out.println(str.toString());
    }


    public void displayEcosytemMap(EcosystemMan ecosystem) {
        final String EMPTY_CELL = ".";
        final int height = ecosystem.getMapSize().height();
        final int width = ecosystem.getMapSize().width();
        Grid grid = ecosystem.getMapGrid();
        final StringBuilder sb = new StringBuilder();
        final String border = BoxDrawingChar.HORIZONTAL.repeat(width * 4); // Adjust border width for consistent cells
        
        add.accept(sb, "**🗺️ [fly:Where is Everyone?] 🗺️**\n");

        sb.append("**")
            .append(BoxDrawingChar.TOP_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.TOP_RIGHT.getValue())
            .append("**\n");

        for (int y = height - 1; y >= 0; y--) {
            sb.append("**")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("**");
            
            for (int x = 0; x < width; x++) {
                final String ch = grid.get(x, y)
                    .map(Organism::getSymbol)
                    .orElse(EMPTY_CELL);
                
                // Adjust spacing based on character type
                if (isWideCharacter(ch)) {
                    sb.append(" ").append(ch).append(" ");
                } else {
                    sb.append(" ").append(ch).append("  ");
                }
            }

            sb.append("**")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("**\n");
        }
        
        sb.append("**")
            .append(BoxDrawingChar.BOTTOM_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.BOTTOM_RIGHT.getValue())
            .append("**\n");
            
        pprintln(sb.toString());
    }

    private boolean isWideCharacter(String ch) {
        if (ch == null || ch.isEmpty()) return false;

        // Simple heuristic: if it's not ASCII and not a common symbol, assume it's wide
        char c = ch.charAt(0);
        return c > 127 || ch.codePointAt(0) > 127;
    }

    public void displayAnimalActionsHeader() {
        StringBuilder str = new StringBuilder();
        add.accept(str, "** [fly:Animal Actions] **");
        System.out.println(str.toString());
    }

    public void displayAnimalActions(ActionResult result) {
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
            result.getNewY()
        );
        
        add.accept(str, "  " + message);
        System.out.println(str.toString());
    }

    private String formatActionMessage(Animal actor, Organism target, ActionType actionType, int newX, int newY) {
        String actorName = actor.getName();
        String actorSymbol = actor.getSymbol();  
        String sound = actor.getSound();
        
        return switch (actionType) {
            case NONE -> formatIdleMessage(actorName, actorSymbol, actor.getActivityState().toString(), newX, newY, sound);
            case MOVED -> formatMovementMessage(actorName, actorSymbol, newX, newY, sound);
            case ATTEMPTED_BREEDING -> formatAttemptedBreedingMessage(actorName, actorSymbol, target, sound);
            case SUCCESSFUL_BREEDING -> formatSuccessfulBreedingMessage(actorName, actorSymbol, target, sound);
            case ATTEMPTED_EATING -> formatAttemptedEatingMessage(actorName, actorSymbol, target, sound);
            case SUCCESSFUL_EATING -> formatSuccessfulEatingMessage(actorName, actorSymbol, target, sound);
        };
    }

    private String formatIdleMessage(String actorName, String actorSymbol, String activityState, int x, int y, String sound) {
        return String.format("%s %s lounges at %d,%d because they are %s. %s", 
            actorSymbol, actorName, x, y, activityState, sound);
    }

    private String formatMovementMessage(String actorName, String actorSymbol, int x, int y, String sound) {
        return String.format("%s %s moves to %d,%d. %s", actorSymbol, actorName, x, y, sound);
    }

    private String formatAttemptedBreedingMessage(String actorName, String actorSymbol, Organism target, String sound) {
        if (target != null) {
            String targetName = target.getName();
            String targetSymbol = target.getSymbol();  // Assuming getSymbol() exists
            return String.format("(💔) %s %s got rejected by %s %s. %s", actorSymbol, actorName, targetSymbol, targetName, sound);
        }
        return String.format("(💔) %s %s tries dating app. No matches. %s", actorSymbol, actorName, sound);
    }

    private String formatSuccessfulBreedingMessage(String actorName, String actorSymbol, Organism target, String sound) {
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
        return String.format("(😌🍽️) %s %s had a tasty meal! %s", actorSymbol, actorName, sound);
    }
}
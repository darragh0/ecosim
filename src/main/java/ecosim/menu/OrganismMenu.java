package ecosim.menu;

import java.util.List;
import java.util.Scanner;

/**
 * Base menu for selecting organism species by name.
 */
public abstract class OrganismMenu<T> {
    protected final List<T> options;
    protected final Scanner scanner;
    
    public OrganismMenu(List<T> options) {
        this.options = options;
        this.scanner = new Scanner(System.in);
    }
    
    public abstract void print();
    
    protected void printOptions() {
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, options.get(i));
        }
    }

    public T getUserChoice() {
        return getUserChoice("Enter your choice >> ");
    }
    
    public T getUserChoice(String prompt) {
        while (true) {
            System.out.print(prompt);
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice >= 1 && choice <= options.size()) {
                    return options.get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + options.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}
// ✅ Control Flow: if/else, switch-case
// ✅ Exception Handling: try-catch for input validation
// ✅ Looping: while, for loop
// ✅ Scanner for user input
// ✅ Object-Oriented Design: interacts with AnimalInfo and Animal
// ✅ Dynamic Object Creation: create new animals via user input
// ✅ Basic Input Validation

import java.util.Scanner;
import java.util.List;
import java.io.InputStream;
import java.net.URL;

public class AnimalScannerUtil
{
    private AnimalInfo animalInfo;
    private Scanner scanner;
    
    public AnimalScannerUtil(AnimalInfo info){
        this.animalInfo = info;
        this.scanner = new Scanner(System.in);
    }
    
    public void start(){
        while (true){
            System.out.println("\n===== Animal Management: Jempol Zoo Management System =====\n");
            List<Animal> animals = animalInfo.getAnimals();
            
            //listing animals with numbers
            if (animals.isEmpty() ){
                System.out.println("No animals in record");
            } else {
                
                for (int i = 0; i < animals.size(); i++){
                    System.out.println("[" + (i + 1) + "] " + animals.get(i).getName());
                }
                
            }
            
            //show options
            System.out.println("\nOptions:");
            System.out.println("\nEnter an animal number to display detail, or");
            System.out.println("a. Add Animal");
            System.out.println("b. Remove Animal");
            System.out.println("c. Save Current Animal Database");
            System.out.println("d. Restore Animal Database via GitHub (Requires Internet)");
            System.out.println("x. Back to Main Menu");
            
            System.out.println("\nSelect an option: ");
            String input = scanner.nextLine();
            
            //handle menu input
            switch (input) {
                case "a":
                    
                    addAnimal();
                    break;
                    
                case "b":
                    
                    removeAnimal();
                    break;
                    
                case "c":
                    
                    animalInfo.saveAnimalFile("Animal_Data.txt");
                    break;
                
                case "d":
                    
                    animalInfo.restoreAnimalData();
                    break;
                
                case "x":
                    
                    System.out.println("exiting...");
                    return;
                    
                default:
                    
                    try {
                        
                        int index = Integer.parseInt(input);
                        
                        if ( index < 1 || index > animals.size() ){
                            
                            System.out.println("Invalid animal number");
                            
                        } else {
                            
                            displayAnimalInfo(animals.get(index - 1));
                            
                        }
                        
                    } catch (NumberFormatException e){
                        
                        System.out.println("Invalid input. Enter either number or 'a', 'b', 'c', 'd', or 'x' to exit");
                        
                    }
                    break;
                    
            }
            
        }
    }
    
    private void addAnimal(){
        /* System.out.print("enter animal name: ");
        String name = scanner.nextLine(); // read animal name from user input
        if (name.trim().isEmpty() ) {
            System.out.println("enter a name");
            return;
        } */
        
        String name = "";
        while (true) {
            System.out.print("enter animal name: ");
            name = scanner.nextLine();
            if (!name.trim().isEmpty() ) {
                break; //exit the loop
            }
            System.out.println("enter a name");
        }
        
        //prompt user to choose animal diet type using single letter
        System.out.print("Enter the " + name + " diet type (c = Carnivore, h = Herbivore, o = Omnivore): ");
        String dietInputRaw = scanner.nextLine().toLowerCase().trim(); //prevent direct letter
        
        if (dietInputRaw.isEmpty() ){
            System.out.println("Please enter animal diet type");
            return;
        }
        
        char dietChar = dietInputRaw.charAt(0);
        DietType dietType;
        switch (dietChar) {
            case 'c':
                dietType = DietType.CARNIVORE;
                break;
            case 'h':
                dietType = DietType.HERBIVORE;
                break;
            case 'o':
                dietType = DietType.OMNIVORE;
                break;
            default:
                //invalid input; notify user and exit method
                System.out.println("Invalid diet type. Eelect letter c, h or o");
                return;
        }
        
        double foodWeight = getValidDouble("Enter " + name + "'s food weight (kg) needed per day");
        
        System.out.println("What does this " + name + " makes?");
        String sound = scanner.nextLine().trim();
        
        //create new Animal object with the data given
        Animal newAnimal = new Animal(name, dietType, foodWeight, sound);
        
        //add animal into animalInfo
        animalInfo.getAnimals().add(newAnimal);
        
        //confirm animal added
        System.out.println("Animal " + name + " added");
        
    }
    
    private void removeAnimal(){
        System.out.println("Enter animal number to remove");
        
        try {
            
            //try to detect animal input as integer index
            int index = Integer.parseInt(scanner.nextLine());
            
            //check if user interger input is valid within list size)
            if ( index >= 0 && index < animalInfo.getAnimals().size() ) {
                
                //remove animal at the index and store removed animal to show name
                Animal removed = animalInfo.getAnimals().remove(index);
                
                //inform user about removed animal
                System.out.println(removed.getName() + " animal removed");
                
            } else {
                //invalid input, show message
                System.out.println("invalid animal number index: animal number not exist");
            }
        } catch (NumberFormatException e) {
            //input not an integer number
            System.out.println("invalid input, enter number only");
        }
    }
    
    private void displayAnimalInfo(Animal animal){
                
        System.out.println("\n---Animal Details---");
        animal.describe();
        System.out.println("-----------------------");
    
    }
    
    private double getValidDouble(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt + ": ");
                    return Double.parseDouble(scanner.nextLine() );
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: Enter a valid number");
                }
            }
    }
    
}
        


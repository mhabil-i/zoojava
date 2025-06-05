// ✅ Control Flow: if/else, switch-case
// ✅ Exception Handling: try-catch for input validation
// ✅ Looping: while, for loop
// ✅ Scanner for user input
// ✅ Object-Oriented Design: interacts with AnimalInfo and Animal
// ✅ Dynamic Object Creation: create new animals via user input
// ✅ Basic Input Validation

import java.util.Scanner;
import java.util.List;

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
            System.out.println("===animal menu===");
            List<Animal> animals = animalInfo.getAnimals();
            
            //listing animals with numbers
            for (int i = 0; i < animals.size(); i++){
                System.out.println("[" + i + "] " + animals.get(i).getName());
            }
            
            //show options
            System.out.println("a. add new animal");
            System.out.println("b. remove an animal from list");
            System.out.println("x. exit");
            
            System.out.println("enter a number to display animal or a letter to modify");
            String input = scanner.nextLine();
            
            //handle menu input
            if (input.equals("a")){
                
                addAnimal();
                
            } else if (input.equals("b")){
                
                removeAnimal();
                
            } else if (input.equals("x")){
                
                System.out.println("exiting...");
                break;
                
            } else {
                
                try {
                    
                    int index = Integer.parseInt(input);
                    
                    if (index >= 0 && index < animals.size()){
                        
                        displayAnimalInfo(animals.get(index));
                        
                    } else {
                        
                        System.out.println("invalid animal number");
                        
                    }
                    
                } catch (NumberFormatException e){
                    
                    System.out.println("invalid input. enter either number or 'a' or 'b', x to exit");
                    
                }
                
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
        System.out.print("enter the " + name + " diet type (c = carnivore, h = herbivore, o = omnivore): ");
        String dietLetter = scanner.nextLine().toLowerCase(); //prevent direct letter
        
        DietType dietType;
        switch (dietLetter) {
            case "c":
                dietType = DietType.CARNIVORE;
                break;
            case "h":
                dietType = DietType.HERBIVORE;
                break;
            case "o":
                dietType = DietType.OMNIVORE;
                break;
            default:
                //invalid input; notify user and exit method
                System.out.println("invalid diet type. select letter c, h or o");
                return;
        }
        
        System.out.println("is the " + name + " a meat? (y/n)");
        String meatLetter = scanner.nextLine().toLowerCase();
        
        boolean isMeat;
        switch (meatLetter){
            case "y":
                isMeat = true;
                break;
            case "n":
                isMeat = false;
                break;
            default:
                System.out.println("wrong input, y/n only");
                return;
        }
        
        System.out.println("what does this " + name + " produced?");
        String sound = scanner.nextLine();
        
        //create new Animal object with the data given
        Animal newAnimal = new Animal(name, dietType, isMeat, sound);
        
        //add animal into animalInfo
        animalInfo.getAnimals().add(newAnimal);
        
        //confirm animal added
        System.out.println(name + " animal added");
        
    }
    
    private void removeAnimal(){
        System.out.println("enter animal number to remove");
        
        
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
        System.out.println("the animal is " + animal.getName());
        System.out.println(animal.getName() + " sound is " + animal.sound());
        
        if ( animal.isMeat() ) {
            System.out.println(animal.getName() + " is meat");
        } else{
            System.out.println(animal.getName() + " is not meat");
        }
        
        System.out.println(animal.getName() + " is " + animal.getDietType());
        
        DietType type = animal.getDietType();
        //get selected animal DietType from enum of carnivore, omnivore, herbivore
        
        if (type == DietType.CARNIVORE || type == DietType.OMNIVORE){
            
                System.out.println(animal.getName() + " eat these animals: ");
                
                for ( Animal prey : animalInfo.getAnimals() ){
                    
                    if (prey.isMeat() ) {
                        
                        if (prey.getName().equalsIgnoreCase(animal.getName() ) ){
                            System.out.println("-other " + prey.getName() );
                        } else {
                        
                        System.out.println("- " + prey.getName() );
                        
                    }
                }
                
            }
            
            if (type == DietType.HERBIVORE || type == DietType.OMNIVORE){
                
                System.out.println(animal.getName() + " eats these plants:");
                
                for (Plant plant : animalInfo.getPlants() ) {
                    
                    System.out.println( "- " +plant.getName() );
                    
                }
                
            }
        
        }
    
    }

}
        


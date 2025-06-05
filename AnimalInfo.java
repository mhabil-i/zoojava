// ✅ Collections (ArrayList)
// ✅ Encapsulation: private fields with public getters
// ✅ Constructor usage
// ✅ Composition: Stores Animals and Plants

import java.util.ArrayList;
import java.util.List;

public class AnimalInfo
{

    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    
    public AnimalInfo(){
        //create animal and organism objects
        Animal tiger = new Animal("tiger", DietType.CARNIVORE, true, "roar");
        Animal zookeeper = new Animal("zookeeper", DietType.OMNIVORE, true, "yap");
        Animal deer = new Animal("deer", DietType.HERBIVORE, true, "grunt");
        
        Plant grass = new Plant("grass");
        Plant flower = new Plant("flower");
        
        //Add objects to the lists — MUST be inside constructor
        animals.add(tiger);
        animals.add(zookeeper);
        animals.add(deer);
        plants.add(grass);
        plants.add(flower);
    }
    
    
    //getter for animal list into array
    public List<Animal> getAnimals(){
        return animals;
    }
    public List<Plant> getPlants(){
        return plants;
    }
}

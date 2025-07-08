// ✅ Encapsulation
// ✅ Constructor with parameters
// ✅ Interface Implementation (AnimalBehavior)
// ✅ Enum as a property
//polymorphism

class Animal extends Organism implements AnimalBehavior
//make sure declare all
{
    //using encapsulation must be followed by getter or ide became crazy asking for the class to be abstract
    private String name;
    private DietType dietType;
    private boolean meat;
    private String sound;
    
    //this is a constructor. below is method
    //modifier return-type method-namme (parameter list)
    /*
    public class Animal {
        String name1; // [1] This is the field (belongs to the object)
    
        public Animal(String name2) { // [2] This is the constructor parameter
            this.name1 = name2;       // [3] Assign value from [2] → into field [1]
        }
    }
       */
    public Animal(String name, DietType dietType, boolean meat, String sound){
        super(name);
        this.name = name;
        this.dietType = dietType;
        this.meat = meat;
        this.sound = sound;
    }
    
    //getter method
    public String getName(){
        return name;
    }
    
    public DietType getDietType(){
        return dietType;
    }
    
    public boolean isMeat(){
        return meat;
    }
    
    public String sound(){
        return sound;
    }
    
    //polymorphism
    @Override
    public String getOrganismType(){
        return "Animal";
    }
    
}

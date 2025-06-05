// ✅ Encapsulation
// ✅ Simple constructor and getter

public class Plant extends Organism
{
    private String name;
    
    public Plant(String name){
        super(name);
    }
    
    
    public String getName(){
        return name;
    }
    
    @Override
    public String getOrganismType(){
        return "Plant";
    }
}

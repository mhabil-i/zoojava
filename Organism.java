// ✅ Abstract class for inheriting to animal and plant class
// polymorphism using @Override

public abstract class Organism
{
    private String name;
    
    public  Organism(String name)
    {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public abstract String getOrganismType();
    
    //polymorphism method to override
    public void describe(){
        System.out.println(getOrganismType() + ": " + getName() );
    }
}

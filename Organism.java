// ✅ Abstract class for inheriting to animal and plant class
// polymorphism using @Override

public abstract class Organism
{
    protected String name;
    public  Organism(String name)
    {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public abstract String getOrganismType();
}

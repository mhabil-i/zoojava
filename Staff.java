// ✅ Encapsulation
// ✅ Constructor with parameters
// ✅ Interface Implementation (AnimalBehavior)
// ✅ Enum as a property
//polymorphism

class Staff extends Organism implements StaffSalary
//make sure declare all
{
    //using encapsulation must be followed by getter or ide became crazy asking for the class to be abstract
    private String name;
    private StaffPosition staffPosition;
    private double minSalary;
    private double otSalary;
    private int otHours;
    
    //this is a constructor. below is method
    //modifier return-type method-namme (parameter list)
    /*
    public class Staff {
        String name1; // [1] This is the field (belongs to the object)
    
        public Staff(String name2) { // [2] This is the constructor parameter
            this.name1 = name2;       // [3] Assign value from [2] → into field [1]
        }
    }
       */
    public Staff(String name, StaffPosition staffPosition, double minSalary, double otSalary, int otHours){
        super(name);
        this.name = name;
        this.staffPosition = staffPosition;
        this.minSalary = minSalary;
        this.otSalary = otSalary;
        this.otHours = otHours;
    }
    
    //getter method
    public String getName(){
        return name;
    }
    
    public StaffPosition getStaffPosition(){
        return staffPosition;
    }
    
    public double getMinSalary(){
        return minSalary;
    }
    
    public double getOtSalary(){
        return otSalary;
    }
    
    public int getOtHours(){
        return otHours;
    }
    
    //polymorphism
    @Override
    public String getOrganismType(){
        return "Staff";
    }
    
    @Override
    public void describe(){
        System.out.println("Name: " + name);
        System.out.println("- Position: " + staffPosition);
        System.out.println("- Base Salary: " + minSalary);
        System.out.println("- Overtime Rate per Hour: " + otSalary);
        System.out.println("- Overtime Hour Recorded: " + otHours);
    }
}

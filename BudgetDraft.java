
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class BudgetDraft
{
    private final AnimalInfo animalInfo;
    private final StaffInfo staffInfo;
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    
    public BudgetDraft(AnimalInfo animalInfo, StaffInfo staffInfo)
    {
        this.animalInfo = animalInfo;
        this.staffInfo = staffInfo;
    }

    public void displayBudget()
    {
        double animalCost = totalAnimalFoodCost();
        double staffCost = totalStaffCost();
        double total = animalCost + staffCost;
        
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("ms", "MY") ) ;
        
        System.out.println("\n------- Jempol Zoo Budget Draft -------");
        System.out.println("Animal Food Cost: " );
        
        for (Animal animal : animalInfo.getAnimals() ) {
            System.out.println( " - " + animal.getName() + " : " + AnimalFoodCost.calculateCost(animal) );
        }
        
        System.out.println("\nTotal Animal Food Cost: " + currency.format(animalCost) +"\n");
        
        System.out.println("Staff Salaries: ");
        
        for (Staff staff : staffInfo.getStaffList() ) {
            System.out.println( " - " + staff.getName() + " : " + staff.totalSalary() );
        }
        
        System.out.println("\nTotal Staff Salaries: " + currency.format(staffCost) );
        System.out.println("-----------------------------------------");
        System.out.println("Total Monthly Budget: " + currency.format(total) );
        System.out.println("=========================================");
    }
    
    public void exportBudgetFile(String filename) {
        
        try (FileWriter writer = new FileWriter(filename) ) {
            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("ms", "MY") ) ;
            
            double animalCost = totalAnimalFoodCost();
            double staffCost = totalStaffCost();
            double total = animalCost + staffCost;
            
            writer.write("===== Jempol Zoo Budget Report =====\n");
            writer.write("Animal Food Costs: " + "\n");
            
            for (Animal animal : animalInfo.getAnimals() ) {
            writer.write( " - " + animal.getName() + " : RM" + AnimalFoodCost.calculateCost(animal) + "\n");
            }
            
            writer.write("Total Animal Food Costs: " + currency.format(animalCost) + "\n\n");
            
            writer.write("Staff Salaries:    " + "\n");
            
            for (Staff staff : staffInfo.getStaffList() ) {
            writer.write( " - " + staff.getName() + " : RM" + staff.totalSalary() + "\n");
            }
            
            writer.write("Total Staff Salaries:    " + currency.format(staffCost)  + "\n\n");
            
            writer.write("TOTAL BUDGET:      " + currency.format(total) + "\n");
            writer.write("====================================\n");
            
            System.out.println("Budget report exported as " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting budget report: " + e.getMessage() );
        }
    }
    
    private double totalAnimalFoodCost(){
        double total = 0;
        
        for (Animal animal : animalInfo.getAnimals() ) {
            total += AnimalFoodCost.calculateCost(animal);
        }
        return total;
    }
    
    private double totalStaffCost(){
        double total = 0;
        
        for (Staff staff : staffInfo.getStaffList() ) {
            total += staff.totalSalary();
        }
        return total;
    }
}

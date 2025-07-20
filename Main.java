// ✅ Java Main Method and Class Instantiation
// ✅ Composition: Uses AnimalInfo and AnimalScannerUtil objects
import java.util.Scanner;

public class Main
{
    
    public static void main(String[] args) {
    
        AnimalInfo animalInfo = new AnimalInfo();
        StaffInfo staffInfo = new StaffInfo();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n===== Jempol Zoo Management System =====");
            System.out.println("1. System Introduction");
            System.out.println("2. Animal Management");
            System.out.println("3. Staff Management");
            System.out.println("4. Budget Draft");
            System.out.println("5. Export Budget");
            System.out.println("===========================================\n0. Exit System\n===========================================");
            
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.println(SystemInfo.getWelcomeMessage(animalInfo, staffInfo));
                    break;
                case "2":
                    new AnimalScannerUtil(animalInfo).start();
                    break;
                case "3":
                    new StaffScannerUtil(staffInfo).start();
                    break;
                case "4":
                    new BudgetDraft(animalInfo, staffInfo).displayBudget();
                    break;
                case "5":
                    new BudgetDraft(animalInfo, staffInfo).exportBudgetFile("Zoo_Budget_Report.txt");
                    break;
                case "0":
                    System.out.println("Exiting... Success!");
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
                    
            }
            
        }
        
    }

}

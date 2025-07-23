// ✅ Control Flow: if/else, switch-case
// ✅ Exception Handling: try-catch for input validation
// ✅ Looping: while, for loop
// ✅ Scanner for user input
// ✅ Object-Oriented Design: interacts with AnimalInfo and Animal
// ✅ Dynamic Object Creation: create new animals via user input
// ✅ Basic Input Validation

import java.util.Scanner;
import java.util.List;

public class StaffScannerUtil
{
    private StaffInfo staffInfo;
    private Scanner scanner;
    
    public StaffScannerUtil(StaffInfo info){
        this.staffInfo = info;
        this.scanner = new Scanner(System.in);
    }
    
    public void start(){
        while (true){
            System.out.println("\n===== Staff Management: Jempol Zoo Management System =====\n");
            List<Staff> staffList = staffInfo.getStaffList();
            
            //listing staffs with numbers
            if (staffList.isEmpty() ) { System.out.println("No staff in record"); } else {
                for (int i = 0; i < staffList.size(); i++){
                    System.out.println("[" + (i+1) + "] " + staffList.get(i).getName());
                }
            }
            
            //show options
            System.out.println("\nOptions:");
            System.out.println("\nEnter a staff number to display detail, or");
            System.out.println("a. Add Staff");
            System.out.println("b. Remove Staff");
            System.out.println("c. Save Current Staff Database");
            System.out.println("d. Restore Staff Database via GitHub (Requires Internet)");
            System.out.println("x. Back to Main Menu");
            
            System.out.println("\nSelect an option: ");
            String input = scanner.nextLine();
            
            //handle menu input
            switch (input) {
                case "a":
                    
                    addStaff();
                    break;
                    
                case "b":
                    
                    removeStaff();
                    break;
                    
                case "c":
                    
                    staffInfo.saveStaffFile("Staff_Data.txt");
                    break;
                    
                case "d":
                    
                    staffInfo.restoreStaffData();
                    break;
                    
                case "x":
                    
                    System.out.println("exiting...");
                    return;
                    
                default:
                    
                    try {
                        
                        int index = Integer.parseInt(input);
                        
                        if ( index < 1 || index > staffList.size() ){
                            
                            System.out.println("Invalid staff number");
                            
                        } else {
                            
                            displayStaffInfo(staffList.get(index - 1));
                            
                        }
                        
                    } catch (NumberFormatException e){
                        
                        System.out.println("Invalid input. enter either number or 'a', 'b', 'c'. 'd' or x to exit");
                        
                    }
                    break;
                    
            }
                
        }
    }

    
    private void addStaff(){
        /* System.out.print("enter animal name: ");
        String name = scanner.nextLine(); // read animal name from user input
        if (name.trim().isEmpty() ) {
            System.out.println("enter a name");
            return;
        } */
        
        String name = "";
        while ( name.trim().isEmpty() ) {
            System.out.print("Enter staff name: ");
            name = scanner.nextLine();
            if ( name.trim().isEmpty() ) {
                System.out.println("Name cannot be empty");
            }

        }
        
        //prompt user to choose animal diet type using single letter
        System.out.print("Enter " + name + " position (v = Veterinarian, z = Zookeeper, j = Janitor): ");
        String positionInputRaw = scanner.nextLine().toLowerCase().trim(); //prevent direct letter, trim to remove spces
        
        if (positionInputRaw.isEmpty() ){
            System.out.println("Position cannot be empty");
            return;
        }
        
        char positionChar = positionInputRaw.charAt(0);
        StaffPosition position;
        switch (positionChar) {
            case 'v':
                position = StaffPosition.VET;
                break;
            case 'z':
                position = StaffPosition.ZOOKEEPER;
                break;
            case 'j':
                position = StaffPosition.JANITOR;
                break;
            default:
                //invalid input; notify user and exit method
                System.out.println("Invalid input. Enter letter v, z or j");
                return;
        }
        
        double minSalary;
        while (true) {
            minSalary = getValidDouble("Enter " + name + "'s base salary");
            
            //dummy staff for validating user input in minSalary
            Staff tempStaff = new Staff ("dummy staff", StaffPosition.VET, minSalary, 0, 0);
            try {
                tempStaff.validateSalary();
                break;
            } catch (IllegalArgumentException e) {System.out.println(e.getMessage() );}
        }
        double otSalary = getValidDouble("Enter " + name + "'s overtime rate per hour");
        
        int otHours = -1;
        while ( otHours < 0 ) {
            try {
                System.out.print("Enter how many hours has " + name + " worked overtime on this month: ");
                otHours = Integer.parseInt( scanner.nextLine() );
                if ( otHours < 0 ) {
                    System.out.println("Hour cannot be negative");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Enter a valid number");
            } catch (IllegalArgumentException e) {System.out.println(e.getMessage() );}
        }

        
        //create new Staff object with the data given
        Staff newStaff = new Staff(name, position, minSalary, otSalary, otHours);
        
        //add staff into staffInfo
        staffInfo.getStaffList().add(newStaff);
        
        //confirm staff added
        System.out.println("Staff " + name + " added");
    }

    private void removeStaff(){
        System.out.println("Enter staff number to be removed");
        
        try {
            
            //try to detect animal input as integer index
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            
            //check if user interger input is valid within list size)
            if ( index >= 0 && index < staffInfo.getStaffList().size() ) {
                
                //remove animal at the index and store removed animal to show name
                Staff removed = staffInfo.getStaffList().remove(index);
                
                //inform user about removed animal
                System.out.println(removed.getName() + " removed");
                
            } else {
                //invalid input, show message
                System.out.println("Invalid staff number: Staff number entered does not exist");
            }
        } catch (NumberFormatException e) {
            //input not an integer number
            System.out.println("Invalid input: Enter number only");
        } 
    }
        
    private void displayStaffInfo(Staff staff){
        
        System.out.println("\n---Staff Details---");
        staff.describe();
        System.out.println("-----------------------");
    
    }

    private double getValidDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return Double.parseDouble(scanner.nextLine() );
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Enter a valid number");
            } catch (IllegalArgumentException e) {System.out.println(e.getMessage() );}
        } 
    }
    
}
        


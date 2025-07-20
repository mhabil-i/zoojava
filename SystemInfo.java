public class SystemInfo
{
    
    public static String getWelcomeMessage(AnimalInfo animalInfo, StaffInfo staffInfo)
    {
        String msg = "Welcome to the Jempol Zoo Basic Accounting System." + 
        "\nWe are introducing Java for upgrading our zoo management system. "
        +"\nAs a junior accountant, you need to add the database of new animal and staff salary. "
        + "\nThen, print the report for accounting budget. \n" 
        + "\nCurrent animals and staff in the zoo:\n";
        
        msg += "\nAnimals:\n";
        if (animalInfo.getAnimals().isEmpty() ) msg += " is empty\n";
        else for (Animal animal : animalInfo.getAnimals() ) msg += " -" + animal.getName() + "\n";
        
        msg += "\nStaff:\n";
        if (staffInfo.getStaffList().isEmpty() ) msg += " is empty\n";
        else for (Staff staff : staffInfo.getStaffList() ) msg += " -" + staff.getName() + "\n";
        
        return msg;
        
    }
    
}

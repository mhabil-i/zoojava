// ✅ Collections (ArrayList)
// ✅ Encapsulation: private fields with public getters
// ✅ Constructor usage
// ✅ Composition: Stores Animals and Plants
// class, name, position, baseSalary, otPayRate, otHours
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.URL;

public class StaffInfo
{

    private List<Staff> staffList = new ArrayList<>();
    
    public void loadStaffFile(String filename){
        
        staffList.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename) ) ) {
            
            String line;
            
            while ((line = br.readLine() ) != null) {
                
                if ( line.trim().isEmpty() || line.trim().startsWith("//") ) {continue;}
                
                String[] parts = line.split(",");
                
                if(parts[0].equalsIgnoreCase("staff") ) {
                    try {
                        
                        Staff staff = new Staff(
                    
                        parts[1],
                        StaffPosition.valueOf( parts[2].trim().toUpperCase() ),
                        Double.parseDouble( parts[3] ),
                        Double.parseDouble( parts[4] ),
                        Integer.parseInt ( parts[5] )
                        
                        );
                    
                        staffList.add(staff);
                    
                    } catch (IllegalArgumentException e){
                        System.err.println("Invalid staff position in file: " + parts[2] );
                    }
                    
                }
                
            }
            
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage() );
        }
        
    }
    
    public void saveStaffFile(String filename){
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename) ) ) {
                        
            for (Staff staff : staffList) {
                
                bw.write(
                
                "staff," +
                staff.getName() + "," +
                staff.getStaffPosition().name() + "," +
                staff.getMinSalary() + "," +
                staff.getOtSalary() + "," +
                staff.getOtHours()
                
                );
                
                bw.newLine();
                    
            }
            
            System.out.println("Staff_Data.txt updated");
            
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage() );
        }
        
    }    
    
    public void restoreStaffData() {
        String githubURL = "https://raw.githubusercontent.com/mhabil-i/zoojavadb/main/Staff_Data.txt";
        try (InputStream stream = new URL(githubURL).openStream() ) {
            
            //clear current data
            getStaffList().clear();
            
            //use parser logic
            loadStaffData(stream);
            
            System.out.println("Staff database restored from 'https://raw.githubusercontent.com/mhabil-i/zoojavadb/main/Staff_Data.txt' ");
        } catch (Exception e) {
            System.err.println("Staff database could not be restored: " + e.getMessage() );
        }
        
    }

    private void loadStaffData(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream) );
        
        String line;
        
        while ((line = reader.readLine() ) != null) {
                
            if ( line.trim().isEmpty() || line.trim().startsWith("//") ) {continue;}
            
            String[] parts = line.split(",");
            
            if(parts[0].equalsIgnoreCase("staff") ) {
                Staff staff = new Staff(
                    
                parts[1],
                StaffPosition.valueOf( parts[2].trim().toUpperCase() ),
                Double.parseDouble( parts[3] ),
                Double.parseDouble( parts[4] ),
                Integer.parseInt ( parts[5] )
                        
                );
                    
                staffList.add(staff);
                
            }
                
        }
    }
    
    public StaffInfo(){
        loadStaffFile("Staff_Data.txt");
    }
    
    //getter for animal list into array
    public List<Staff> getStaffList(){
        return staffList;
    }

}

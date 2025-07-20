// ✅ Collections (ArrayList)
// ✅ Encapsulation: private fields with public getters
// ✅ Constructor usage
// ✅ Composition: Stores Animals and Plants
// class , animalName , dietType , foodWeight , sound

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.URL;

public class AnimalInfo
{

    private List<Animal> animals = new ArrayList<>();
    
        public void loadAnimalFile(String filename){
        animals.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename) ) ) {
            
            String line;
            
            while ((line = br.readLine() ) != null) {
                
                if ( line.trim().isEmpty() || line.trim().startsWith("//") ) {continue;}
                
                String[] parts = line.split(",");
                
                if(parts[0].equalsIgnoreCase("animal") ) {
                    Animal animal = new Animal(
                    
                    parts[1],
                    DietType.valueOf( parts[2].toUpperCase() ),
                    Double.parseDouble( parts[3] ),
                    parts[4]
                    
                    );
                    
                    animals.add(animal);
                    
                }
                
            }
            
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage() );
        }
        
    }
    
    public void saveAnimalFile(String filename){
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename) ) ) {
                        
            for (Animal animal : animals) {
                
                bw.write(
                
                "animal," +
                animal.getName() + "," +
                animal.getDietType() + "," +
                animal.getFoodWeight() + "," +
                animal.getSound()
                
                );
                
                bw.newLine();
                    
            }
            
            System.out.println("Animal_Data.txt updated");
            
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage() );
        }
        
    }
    
    public void restoreAnimalData() {
        String githubURL = "https://raw.githubusercontent.com/mhabil-i/zoojavadb/main/Animal_Data.txt";
        try (InputStream stream = new URL(githubURL).openStream() ) {
            
            //clear current data
            getAnimals().clear();
            
            //use parser logic
            loadAnimalData(stream);
            
            System.out.println("Animal database restored from 'https://raw.githubusercontent.com/mhabil-i/zoojavadb/main/Animal_Data.txt' ");
        } catch (Exception e) {
            System.err.println("Animal database could not be restored: " + e.getMessage() );
        }
        
    }

    private void loadAnimalData(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream) );
        
        String line;
        
        while ((line = reader.readLine() ) != null) {
                
            if ( line.trim().isEmpty() || line.trim().startsWith("//") ) {continue;}
            
            String[] parts = line.split(",");
            
            if(parts[0].equalsIgnoreCase("animal") ) {
                Animal animal = new Animal(
                
                parts[1],
                DietType.valueOf( parts[2].toUpperCase() ),
                Double.parseDouble( parts[3] ),
                parts[4]
                
                );
                
                animals.add(animal);
                
            }
                
        }
    }
    
    public AnimalInfo(){
        loadAnimalFile("Animal_Data.txt");
    }
    
    //getter for animal list into array
    public List<Animal> getAnimals(){
        return animals;
    }

}

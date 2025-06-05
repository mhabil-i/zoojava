// ✅ Java Main Method and Class Instantiation
// ✅ Composition: Uses AnimalInfo and AnimalScannerUtil objects

public class AnimalDisplay
{
    
    public static void main(String[] args) {
    
        AnimalInfo animalInfo = new AnimalInfo();
        
        AnimalScannerUtil scannerUtil = new AnimalScannerUtil(animalInfo);
        
        scannerUtil.start();
        
    }

}

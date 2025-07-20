public class AnimalFoodCost
{
    
    private static double meatPriceKg = 15.00;
    private static double vegePriceKg = 5.00;
    private static double omniCostKg = 0.5 * meatPriceKg + 0.5 * vegePriceKg;
    
    public static double calculateCost(Animal animal)
    {
        double weight = animal.getFoodWeight();
        
        switch ( animal.getDietType() ) {
            case CARNIVORE:
                return weight * meatPriceKg;
            case HERBIVORE:
                return weight * vegePriceKg;
            case OMNIVORE:
                return weight * omniCostKg;
            default:
                throw new IllegalArgumentException("Unknown diet type");
        }
    }
    
}

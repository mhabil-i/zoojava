public class AnimalFoodCost
{
    
    private static double meatPriceKg = 15.00;
    private static double vegePriceKg = 5.00;
    private static double omniCostKg = 0.5 * meatPriceKg + 0.5 * vegePriceKg;
    
    public static double calculateCost(Animal animal)
    {
        double weight = animal.getFoodWeight();
        //calculated for monthly * 30
        switch ( animal.getDietType() ) {
            case CARNIVORE:
                return weight * meatPriceKg * 30;
            case HERBIVORE:
                return weight * vegePriceKg * 30;
            case OMNIVORE:
                return weight * omniCostKg * 30;
            default:
                throw new IllegalArgumentException("Unknown diet type");
        }
    }
    
}

// ✅ Enum: Fixed set of values, switch-case support, type-safe

/*
   what is enum for?
   -prevent typo
   -fixed data
   -one instance per value
   -can work with comparison (==)
   -can use switch(enum)
   
   without enum, in classes:
   -new method can override classes // new DietType("autotroph")
   -manually reuse objects
   -can make many duplicates
   -must be .equals()
   -must use if/else
*/

public enum DietType
{
   CARNIVORE, OMNIVORE, HERBIVORE;
}

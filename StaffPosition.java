// ✅ Enum: Fixed set of values, switch-case support, type-safe

/*
   what is enum for?
   -prevent typo
   -fixed data
   -one instance per value
   -can work with comparison (==)
   -can use switch(enum)
   
   without enum, in classes:
   -new method can override classes // new StaffPosition("autotroph")
   -manually reuse objects
   -can make many duplicates
   -must be .equals()
   -must use if/else
*/

public enum StaffPosition
{
   VET, ZOOKEEPER, JANITOR;
}

package HashMap;

import java.util.*;

public class JavaNotes {
    public static void main(String[] args) {

       // Creation of HashMap
        HashMap<String, String> Geeks = new HashMap<>();
 
        // Adding values to HashMap using put() method
        Geeks.put("Language", "Java");
        Geeks.put("Platform", "Geeks For Geeks");
        Geeks.put("Code", "HashMap");
        Geeks.put("Learn", "More");
        Geeks.put("Learn", "More");

        for (String i: Geeks.keySet()) {
            System.out.println(i + ": " + Geeks.get(i));
        }
 
        // Printing out the values of the HashMap
        System.out.println("HashMap Contents: " + Geeks);
        // ouput: HashMap Contents: {Language=Java, Platform=Geeks For Geeks, Learn=More, Code=HashMap}

        // Printing the size of the HashMap
        System.out.println("The size of the HashMap is: " + Geeks.size());
        // ouput: The size of the HashMap is: 4
 
        // Using the get() method to retrieve a value from the HashMap
        String language = Geeks.get("Language");
        System.out.println("Language: " + language);
        // ouput: Language: Java
 
        // Using the containsKey() method to check if a key exists in the HashMap
        boolean containsKey = Geeks.containsKey("Code");
        System.out.println("Contains Key 'Code'? " + containsKey);
        // output: Contains Key 'Code'? true
 
        // Using the containsValue() method to check if a value exists in the HashMap
        boolean containsValue = Geeks.containsValue("More");
        System.out.println("Contains Value 'More'? " + containsValue);
        // output: Contains Value 'More'? true
 
        // Using the keySet() method to retrieve a Set of all keys in the HashMap
        Set<String> keySet = Geeks.keySet();
        System.out.println("Key Set: " + keySet);
        // output: Key Set: [Language, Platform, Learn, Code]
 
        // Using the values() method to retrieve a Collection of all values in the HashMap
        Collection<String> values = Geeks.values();
        System.out.println("Values: " + values);
 
        // Using the entrySet() method to retrieve a Set of all key-value pairs in the HashMap
        Set<Map.Entry<String, String> > entrySet
            = Geeks.entrySet();
        System.out.println("Entry Set: " + entrySet);
 
        // Using the remove() method to remove a key-value pair from the HashMap
        String removedValue = Geeks.remove("Code");
        System.out.println("Removed Value: " + removedValue);
 
        // Using the clear() method to remove all key-value pairs from the HashMap
        Geeks.clear();
        System.out.println("HashMap Contents after clear(): " + Geeks);
 
        // Using the isEmpty() method to check if the HashMap is empty
        boolean isEmpty = Geeks.isEmpty();
        System.out.println("Is Empty? " + isEmpty);
    }
}

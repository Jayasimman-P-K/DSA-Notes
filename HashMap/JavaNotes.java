package HashMap;

import java.util.HashMap;
import java.util.HashSet;

public class JavaNotes {
    public static void main(String[] args) {

        //String input
        // String str = "thisisjavablog";

        // input arr
        int[] arr = {1,2,2,1,1,3};

        // init HashMap
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        // for(int i: arr) {
        //     map.computeIfPresent(i, (integer, count) -> count+1);
        //     map.computeIfAbsent(i, (integer) -> 1);
        // }


        for(int val: arr) {
            map.put(val, map.getOrDefault(val,0)+1);
        }

        HashSet<Integer> set = new HashSet<>(map.values());
        System.out.println(map.size() == set.size());

        // // assign values to the HashMap
        // map.put(1, "Jayasi");
        // map.put(2, "ash");
        // map.put(3, "Jayasimman");

        // String name = map.get(3);

        // // output 
        // System.out.println(map);
        // System.out.println("===================");
        // System.out.println(name);
        // System.out.println("===================");

    } 
}

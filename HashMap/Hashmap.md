# HashMap:

### Add key value pairs to HashMap:

- We can use `put()` method to add entries to the HashMap

```java
import java.util.HashMap;

public class JavaNotes {
    public static void main(String[] args) {

        // init HashMap
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        // assign values to the HashMap
        map.put(1, "Jayasi");
        map.put(2, "simman");
        map.put(3, "Jayasimman");
        map.put(3, "Jayasimman");

        // output
        System.out.println(map);
        // {1=Jayasi, 2=ash, 3=Jayasimman}
    }
}
```

##### HashMap Blog Post. [Click here](https://medium.com/swlh/a-comprehensive-guide-to-hashmap-in-java-363b6a2a2a3e).

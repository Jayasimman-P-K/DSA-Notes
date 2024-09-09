import java.util.* ;

public class QueuesNotes {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("apple");
        queue.add("banana");
        queue.add("jack");
        queue.add("yellow");
        queue.add("blue");

        System.out.println(queue);
        // ouput: [apple, banana, jack, yellow, blue]

        System.out.println(queue.poll());
        // ouput: apple
        System.out.println(queue);
        // output: [banana, jack, yellow, blue]

        System.out.println(queue.peek());
        // ouput: banana

        System.out.println(queue.contains("jack"));
        // output: true

        queue.remove("yellow");
        System.out.println(queue);
        // output: [banana, jack, blue]

    }
}

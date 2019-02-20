import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class Test {


    public static void main (String[] args) {
        Queue<String> queue = new PriorityQueue<>();

        queue.add("Hello!");
        queue.add("Yo.");
        queue.add("Hello!");

        System.out.println("Done!");
    }
}

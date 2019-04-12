public class Node {

    int x, y, used, avail;

    public String toString() {

        return String.format("%3s/%3s", used, used + avail);
    }
}

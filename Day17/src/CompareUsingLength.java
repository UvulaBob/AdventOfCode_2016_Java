import java.util.Comparator;

public class CompareUsingLength implements Comparator<String> {

    //puts the longer of the two strings higher on the list
    public int compare (String a, String b) {
        return Integer.compare(b.length(), a.length());
    }
}

public class Disc implements Comparable<Disc> {

    int id;
    int positions;
    int currentPosition;

    int futurePosition (int seconds) {
        int futurePosition = currentPosition + seconds;
        while (futurePosition > positions - 1) {
           futurePosition -= positions;
        }

        return futurePosition;
    }

    void rotate() {
        currentPosition = (currentPosition + 1 == positions) ? 0 : currentPosition + 1;
    }

    public int compareTo (Disc d) {
        return Integer.compare(d.positions, positions);
    }
}

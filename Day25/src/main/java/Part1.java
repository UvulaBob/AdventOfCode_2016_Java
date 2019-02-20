public class Part1 {

    public static void main (String[] args) {
        final int constant = 365 * 7;
        int counter = 10;

        while (!alternatingDivisors( counter + constant)) {
            counter++;
        }

        System.out.println(counter);
        System.out.println("Done!");

    }

    static boolean alternatingDivisors(int value) {
        while (value > 3) {
            if (value % 2 != 0) {
                return false;
            }

            value /= 2;

            if (value %2 == 0) {
                return false;
            }

            value /= 2;

        }
        return true;
    }
}

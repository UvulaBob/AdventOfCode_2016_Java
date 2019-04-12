public class Part1 {

    public static void main(String[] args) {

        int numberOfElves = 3018458;
        int closestPowerOf2 = findClosestPowerOf2(numberOfElves);
        int winningElf = ((numberOfElves - closestPowerOf2) * 2) + 1;


        System.out.println("Winner: " + winningElf);
        System.out.println("done!");
    }

    private static int findClosestPowerOf2(int value) {
        int powerOf2 = 2;
        while(true){
            if (powerOf2 < value){
                powerOf2 *= 2;
            }
            else {
                return powerOf2 / 2;
            }

        }
    }
}

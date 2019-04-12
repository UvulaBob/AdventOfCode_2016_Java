public class Part2 {

    public static void main(String[] args) {

        int numberOfElves = 3018458;
        int closestPowerOf3 = findClosestPowerOf3(numberOfElves);
        int winningElf;

        if (numberOfElves - closestPowerOf3 <= closestPowerOf3 * 2) {
            winningElf = (numberOfElves - closestPowerOf3);
        }

        else winningElf = (closestPowerOf3 + (2 * (numberOfElves - (2 * closestPowerOf3))));


        System.out.println("Winner: " + winningElf);
        System.out.println("done!");
    }

    private static int findClosestPowerOf3(int value) {
        int powerOf3 = 3;
        while(true){
            if (powerOf3 < value){
                powerOf3 *= 3;
            }
            else {
                return powerOf3 / 3;
            }

        }
    }
}

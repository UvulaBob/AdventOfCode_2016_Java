import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        for (int counter = 3; counter <= 82; counter++) {
            List<Integer> queue = new LinkedList<>();
            System.out.println("Playing with " + counter + " elves.");
            for(int i = 1; i <= counter; i++){
                queue.add(i);
            }



            while(true){
                int positionOfUnluckyElf = queue.size() / 2;
                Collections.rotate(queue, - positionOfUnluckyElf);
                queue.remove(0);
                Collections.rotate(queue, (positionOfUnluckyElf-1));
                if(queue.size()==1){
                    break;
                }
            }
            System.out.println("Winner: " + queue.get(0));

        }


        System.out.println("done!");
    }
}

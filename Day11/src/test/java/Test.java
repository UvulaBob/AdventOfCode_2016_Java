
public class Test {

    public static void main(String[] args) throws InterruptedException{
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        System.out.println("Took "+(endTime - startTime) / 1000 + " seconds ");
    }
}


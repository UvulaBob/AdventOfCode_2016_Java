import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Part1 {
    private static final int favoriteNumber = 1358;
    private static final int targetX = 31;
    private static final int targetY = 39;

    public static void main(String[] args) {
        HashMap<String, Integer> distances = new HashMap<>();
        HashSet<String> visitedPoints = new HashSet<>();
        distances.put(key(1, 1), 0);
        Queue<String> queue = new PriorityQueue<>();
        queue.add(key(1,1));
        int minimumDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            String currentPoint = queue.poll();
            visitedPoints.add(currentPoint);
            int currentX = Integer.parseInt(currentPoint.split(",")[0]);
            int currentY = Integer.parseInt(currentPoint.split(",")[1]);
            int currentDistance = distances.get(key(currentX, currentY));

            if (currentX == targetX && currentY == targetY) {
                minimumDistance = currentDistance;
            }

            for (Point adjacentOpenPoint : getAdjacentOpenPoints(currentX, currentY)) {
                String key = key(adjacentOpenPoint.x, adjacentOpenPoint.y);
                if (distances.keySet().contains(key)) {
                    int distanceOnRecord = distances.get(key);
                    if (currentDistance + 1 < distanceOnRecord) {
                        distances.put(key, currentDistance + 1);
                    }
                } else {
                    distances.put(key, currentDistance + 1);
                }

                if (!visitedPoints.contains(key)) {
                    queue.add(key(adjacentOpenPoint.x, adjacentOpenPoint.y));
                }
            }
        }

        System.out.println(minimumDistance);
        System.out.println("Done!");

    }

    private static String key(int x, int y) {
        return x + "," + y;
    }

    private static boolean isOpen(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        int sum = (x*x + 3*x + 2*x*y + y + y*y) + favoriteNumber;
        String binaryString = Integer.toBinaryString(sum);
        int characterCount = getOnesCount(binaryString);
        return characterCount % 2 == 0;
    }

    private static HashSet<Point> getAdjacentOpenPoints(int x, int y) {
        HashSet<Point> adjacentPoints = new HashSet<>();

        if (isOpen(x + 1, y)) {
            adjacentPoints.add(new Point(x + 1, y));
        }
        if (isOpen(x - 1, y)) {
            adjacentPoints.add(new Point(x - 1, y));
        }
        if (isOpen(x, y + 1)) {
            adjacentPoints.add(new Point(x, y + 1));
        }
        if (isOpen(x, y - 1)) {
            adjacentPoints.add(new Point(x, y - 1));
        }

        return adjacentPoints;
    }

    private static int getOnesCount(String input) {
        int count = 0;
        for (String digit : input.split("")) {
            count += (digit.equals("1")) ? 1 : 0;
        }
        return count;
    }
}

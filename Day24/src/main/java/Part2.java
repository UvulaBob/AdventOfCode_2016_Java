import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {
    private static ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day24\\src\\main\\resources\\input.txt"));
        HashMap<String, String> markerLocations = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> markerDistances = new HashMap<>();

        int y = 0;
        for (String line : lines) {
            int x = 0;
            ArrayList<String> row = new ArrayList<>();
            for (String character : line.split("")) {
                row.add(character);
                if (!character.equals("#") && !character.equals(".")) {
                    markerLocations.put(character, (x) + "," + (y));
                }
                x++;
            }
            grid.add(row);
            y++;
        }

        for (String marker : markerLocations.keySet()) {
            HashMap<String, Integer> distanceToOtherMarkers = getDistanceToOtherMarkers(markerLocations.get(marker));
            distanceToOtherMarkers.remove(marker);
            markerDistances.put(marker, distanceToOtherMarkers);
        }

        ArrayList<String> markers = new ArrayList<>(markerLocations.keySet());
        ArrayList<LinkedList<String>> markerPermutations = listPermutations(markers);

        int minDistance = Integer.MAX_VALUE;
        LinkedList<String> currentWinningPath = new LinkedList<>();

        for (LinkedList<String> permutation : markerPermutations) {
            if (!permutation.peekFirst().equals("0")) {
                continue;
            }

            permutation.addLast("0");

            LinkedList<String> permutationCopy = new LinkedList<>(permutation);
            int currentDistance = 0;
            while (!permutation.isEmpty()) {
                String currentMarker = permutation.poll();
                if (!permutation.isEmpty()) {
                    String nextMarker = permutation.peek();
                    currentDistance += markerDistances.get(currentMarker).get(nextMarker);
                }
            }
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                currentWinningPath = new LinkedList<>(permutationCopy);
            }
        }

        System.out.println(minDistance + " steps: " + currentWinningPath);
        System.out.println("Done!");
    }

    private static ArrayList<LinkedList<String>> listPermutations(ArrayList<String> list) {

        if (list.size() == 0) {
            ArrayList<LinkedList<String>> result = new ArrayList<>();
            result.add(new LinkedList<>());
            return result;
        }

        ArrayList<LinkedList<String>> returnMe = new ArrayList<>();

        String firstElement = list.remove(0);

        ArrayList<LinkedList<String>> recursiveReturn = listPermutations(list);
        for (List<String> li : recursiveReturn) {

            for (int index = 0; index <= li.size(); index++) {
                LinkedList<String> temp = new LinkedList<>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }

        }
        return returnMe;
    }

    private static HashMap<String, Integer> getDistanceToOtherMarkers(String markerLocation) {
        HashMap<String, Integer> allDistances = new HashMap<>();
        HashMap<String, Integer> markerDistances = new HashMap<>();

        allDistances.put(markerLocation, 0);
        Queue<String> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(markerLocation);

        while(!queue.isEmpty()) {
            String currentPoint = queue.poll();
            if (visited.contains(currentPoint)) {
                continue;
            } else {
                visited.add(currentPoint);
            }

            int currentDistance = allDistances.get(currentPoint);

            int x = Integer.parseInt(currentPoint.split(",")[0]);
            int y = Integer.parseInt(currentPoint.split(",")[1]);

            if (x > 1) {
                String character = grid.get(y).get(x - 1);
                if (!character.equals("#")) {
                    String key = key(x - 1, y);
                    allDistances.putIfAbsent(key, currentDistance + 1);
                    if (allDistances.get(key) > currentDistance + 1) {
                        allDistances.put(key, currentDistance + 1);
                    }
                    if (!character.equals(".")) {
                        markerDistances.putIfAbsent(character, currentDistance + 1);
                        if (markerDistances.get(character) > currentDistance + 1) {
                            markerDistances.put(character, currentDistance + 1);
                        }
                    }
                    queue.add(key);
                }

            }

            if (x < grid.get(0).size() - 1) {
                String character = grid.get(y).get(x + 1);
                if (!character.equals("#")) {
                    String key = key(x + 1, y);
                    allDistances.putIfAbsent(key, currentDistance + 1);
                    if (allDistances.get(key) > currentDistance + 1) {
                        allDistances.put(key, currentDistance + 1);
                    }
                    if (!character.equals(".")) {
                        markerDistances.putIfAbsent(character, currentDistance + 1);
                        if (markerDistances.get(character) > currentDistance + 1) {
                            markerDistances.put(character, currentDistance + 1);
                        }
                    }
                    queue.add(key);
                }
            }

            if (y > 1) {
                String character = grid.get(y - 1).get(x);
                if (!character.equals("#")) {
                    String key = key(x, y - 1);
                    allDistances.putIfAbsent(key, currentDistance + 1);
                    if (allDistances.get(key) > currentDistance + 1) {
                        allDistances.put(key, currentDistance + 1);
                    }
                    if (!character.equals(".")) {
                        markerDistances.putIfAbsent(character, currentDistance + 1);
                        if (markerDistances.get(character) > currentDistance + 1) {
                            markerDistances.put(character, currentDistance + 1);
                        }
                    }
                    queue.add(key);
                }
            }

            if (y < grid.size() - 1) {
                String character = grid.get(y + 1).get(x);
                if (!character.equals("#")) {
                    String key = key(x, y + 1);
                    allDistances.putIfAbsent(key, currentDistance + 1);
                    if (allDistances.get(key) > currentDistance + 1) {
                        allDistances.put(key, currentDistance + 1);
                    }
                    if (!character.equals(".")) {
                        markerDistances.putIfAbsent(character, currentDistance + 1);
                        if (markerDistances.get(character) > currentDistance + 1) {
                            markerDistances.put(character, currentDistance + 1);
                        }
                    }
                    queue.add(key);
                }
            }
        }

        return markerDistances;

    }

    private static String key(int x, int y) {
        return x + "," + y;
    }


}

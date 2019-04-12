import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static int numberOfPairs = 0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        HashMap<String, Integer> distances = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        HashSet<String> badStates = new HashSet<>();
        String currentGameState = initialize();

        distances.put(currentGameState, 0);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(currentGameState);

        int maxCurrentDistance = 0;
        while (!queue.isEmpty()) {
            currentGameState = queue.poll();

            if (visited.contains(currentGameState)) {
                continue;
            }

            int currentDistance = distances.get(currentGameState);
            if (currentDistance > maxCurrentDistance) {
                System.out.println(currentDistance);
                maxCurrentDistance = currentDistance;
            }

            StringBuilder victoryString = new StringBuilder("4|");
            for (int i = 0; i < numberOfPairs; i++) {
                victoryString.append("4&4|");
            }

            victoryString.reverse();
            victoryString.replace(0, 1, "");
            victoryString.reverse();

            if (currentGameState.equals(victoryString.toString())) {
                System.out.println("Victory in " + currentDistance + " steps!");
                break;
            }

            visited.add(currentGameState);

            int elevatorCurrentFloor = Integer.parseInt(currentGameState.substring(0, 1));
            String devicePairs = currentGameState.substring(2);
            String[] devicePairsSplit = currentGameState.substring(2).split("");
            ArrayList<Integer> indexesOfDevicesOnCurrentFloor = new ArrayList<>();

            for (int index = 0; index < devicePairsSplit.length; index++) {
                switch (devicePairsSplit[index]) {
                    case "&":
                    case "|":
                        continue;
                    default:
                        if (Integer.parseInt(devicePairsSplit[index]) == elevatorCurrentFloor) {
                            indexesOfDevicesOnCurrentFloor.add(index);
                        }
                }

            }

            if (elevatorCurrentFloor < 4) {
                boolean canMoveTwoDevicesUp = false;
                for (int firstDeviceIndex = 0; firstDeviceIndex < indexesOfDevicesOnCurrentFloor.size(); firstDeviceIndex++) {
                    for (int secondDeviceIndex = firstDeviceIndex + 1; secondDeviceIndex < indexesOfDevicesOnCurrentFloor.size(); secondDeviceIndex++) {
                        if (firstDeviceIndex == secondDeviceIndex) {
                            continue;
                        }
                        int firstIndexToReplace = indexesOfDevicesOnCurrentFloor.get(firstDeviceIndex);
                        int secondIndexToReplace = indexesOfDevicesOnCurrentFloor.get(secondDeviceIndex);
                        String futureGameState = devicePairs.substring(0, firstIndexToReplace) +
                                (elevatorCurrentFloor + 1) +
                                devicePairs.substring(firstIndexToReplace + 1, indexesOfDevicesOnCurrentFloor.get(secondDeviceIndex)) +
                                (elevatorCurrentFloor + 1) +
                                devicePairs.substring(secondIndexToReplace + 1);
                        if (!badStates.contains(futureGameState) && isValidState(futureGameState)) {
                            canMoveTwoDevicesUp = true;
                            futureGameState = (elevatorCurrentFloor + 1 ) + "|" + futureGameState;
                            distances.putIfAbsent(futureGameState, currentDistance + 1);
                            int distanceOfRecord = distances.get(futureGameState);
                            if (currentDistance + 1 < distanceOfRecord) {
                                distances.put(futureGameState, currentDistance + 1);
                            }
                            if (!visited.contains(futureGameState)) {
                                queue.add(futureGameState);
                            }
                        } else {
                            badStates.add(futureGameState);
                        }
                    }
                }

                if (!canMoveTwoDevicesUp) {
                    for (Integer indexToReplace : indexesOfDevicesOnCurrentFloor) {
                        String futureGameState = devicePairs.substring(0, indexToReplace) +
                                (elevatorCurrentFloor + 1) +
                                devicePairs.substring(indexToReplace + 1);
                        if (isValidState(futureGameState)) {
                            futureGameState = (elevatorCurrentFloor + 1 + "|") + futureGameState;
                            distances.putIfAbsent(futureGameState, currentDistance + 1);
                            int distanceOfRecord = distances.get(futureGameState);
                            if (currentDistance + 1 < distanceOfRecord) {
                                distances.put(futureGameState, currentDistance + 1);
                            }
                            if (!visited.contains(futureGameState)) {
                                queue.add(futureGameState);
                            }
                        }
                    }
                }
            }


            if (elevatorCurrentFloor > 1) {

                boolean allLowerFloorsClear = true;
                for (int lowerFloor = 1; lowerFloor < elevatorCurrentFloor; lowerFloor++) {
                    if (currentGameState.contains(String.valueOf(lowerFloor))){
                        allLowerFloorsClear = false;
                        break;
                    }
                }

                if (allLowerFloorsClear) {
                    continue;
                }

                boolean canMoveOneDeviceDown = false;
                for (Integer indexToReplace : indexesOfDevicesOnCurrentFloor) {
                    String futureGameState = devicePairs.substring(0, indexToReplace) +
                            (elevatorCurrentFloor - 1) +
                            devicePairs.substring(indexToReplace + 1);
                    if (!badStates.contains(futureGameState) && isValidState(futureGameState)) {
                        canMoveOneDeviceDown = true;
                        futureGameState = (elevatorCurrentFloor - 1) + "|" + futureGameState;
                        distances.putIfAbsent(futureGameState, currentDistance + 1);
                        int distanceOfRecord = distances.get(futureGameState);
                        if (currentDistance + 1 < distanceOfRecord) {
                            distances.put(futureGameState, currentDistance + 1);
                        }
                        if (!visited.contains(futureGameState)) {
                            queue.add(futureGameState);
                        } else {
                            badStates.add(futureGameState);
                        }
                    }
                }

                if (!canMoveOneDeviceDown) {
                    for (int firstDeviceIndex = 0; firstDeviceIndex < indexesOfDevicesOnCurrentFloor.size(); firstDeviceIndex++) {
                        for (int secondDeviceIndex = firstDeviceIndex + 1; secondDeviceIndex < indexesOfDevicesOnCurrentFloor.size(); secondDeviceIndex++) {
                            if (firstDeviceIndex == secondDeviceIndex) {
                                continue;
                            }
                            int firstIndexToReplace = indexesOfDevicesOnCurrentFloor.get(firstDeviceIndex);
                            int secondIndexToReplace = indexesOfDevicesOnCurrentFloor.get(secondDeviceIndex);
                            String futureGameState = devicePairs.substring(0, firstIndexToReplace) +
                                    (elevatorCurrentFloor - 1) +
                                    devicePairs.substring(firstIndexToReplace + 1, indexesOfDevicesOnCurrentFloor.get(secondDeviceIndex)) +
                                    (elevatorCurrentFloor - 1) +
                                    devicePairs.substring(secondIndexToReplace + 1);
                            if (isValidState(futureGameState)) {
                                futureGameState = (elevatorCurrentFloor - 1) + "|" + futureGameState;
                                distances.putIfAbsent(futureGameState, currentDistance + 1);
                                int distanceOfRecord = distances.get(futureGameState);
                                if (currentDistance + 1 < distanceOfRecord) {
                                    distances.put(futureGameState, currentDistance + 1);
                                }
                                if (!visited.contains(futureGameState)) {
                                    queue.add(futureGameState);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Done!");
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        System.out.println("Visited " + visited.size() + " nodes");

    }

    private static String initialize () throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        HashMap<String, HashMap<String, Integer>> pairs = new HashMap<>();

        int currentFloor = 1;
        for (String line : lines) {
            Pattern pattern = Pattern.compile("(\\w+ generator|\\w+-\\w+ microchip)");
            Matcher m = pattern.matcher(line);
            while (m.find()) {
                String device = m.group(0).replace("-compatible", "");
                String element = device.split(" ")[0];
                String deviceType = device.split(" ")[1];
                pairs.putIfAbsent(element, new HashMap<>());
                pairs.get(element).put(deviceType, currentFloor);
            }
            currentFloor++;
        }

        StringBuilder sb = new StringBuilder("1|");
        for (String element : pairs.keySet()) {
            sb.append(pairs.get(element).get("generator"));
            sb.append("&");
            sb.append(pairs.get(element).get("microchip"));
            sb.append("|");
            numberOfPairs++;
        }
        sb.append("1&1|1&1");
        numberOfPairs += 2;

        return sb.toString();

    }

    private static boolean isValidState(String futureGameState) {
        for (String devicePair : futureGameState.split("\\|")) {
            String generatorFloor = devicePair.split("&")[0];
            String microchipFloor = devicePair.split("&")[1];
            if (microchipFloor.equals(generatorFloor)) {
                continue;
            }

            if (futureGameState.contains(microchipFloor + "&")) {
                return false;
            }
        }

        return true;

    }
}
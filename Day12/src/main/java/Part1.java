import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day12\\src\\main\\resources\\input.txt"));
        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        int pointer = 0;
        while (pointer < lines.size()) {
            String line = lines.get(pointer);
            String instruction = line.split(" ")[0];
            switch (instruction) {
                case "cpy":
                    boolean sourceIsLiteralValue = true;
                    String destinationRegister = line.split(" ")[2];
                    int value = 0;
                    try {
                        value = Integer.parseInt(line.split(" ")[1]);
                    } catch (NumberFormatException e) {
                        sourceIsLiteralValue = false;
                    }

                    if (sourceIsLiteralValue) {
                        registers.put(destinationRegister, value);
                    } else {
                        value = registers.get(line.split(" ")[1]);
                        registers.put(destinationRegister, value);
                    }

                    pointer++;
                    break;
                case "inc":
                    String register = line.split(" ")[1];
                    registers.put(register, registers.get(register) + 1);
                    pointer++;
                    break;
                case "dec":
                    register = line.split(" ")[1];
                    registers.put(register, registers.get(register) - 1);
                    if (registers.get(register) < 0) {
                        registers.put(register, 0);
                    }
                    pointer++;
                    break;
                case "jnz":
                    sourceIsLiteralValue = true;
                    value = 0;
                    try {
                        value = Integer.parseInt(line.split(" ")[1]);
                    } catch (NumberFormatException e) {
                        sourceIsLiteralValue = false;
                    }

                    if (sourceIsLiteralValue) {
                        if (value != 0) {
                            pointer += Integer.parseInt(line.split(" ")[2]);
                        } else {
                            pointer++;
                        }
                    } else {
                        register = line.split(" ")[1];
                        if (registers.get(register) != 0) {
                            pointer += Integer.parseInt(line.split(" ")[2]);
                        } else {
                            pointer++;
                        }
                    }
                    break;

            }

            System.out.println(registers);

        }

        System.out.println(registers.get("a"));
        System.out.println("Done!");

    }
}

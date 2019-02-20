import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day23\\src\\main\\resources\\input.txt"));
        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", 7);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        System.out.println("5: inc a.*");
        System.out.println("6: dec c.*");
        System.out.println("\\d: jnz c -2.*");
        System.out.println();
        System.out.println(registers);

        int counter = 0;
        int pointer = 0;
        while (pointer < lines.size()) {
            counter++;
            if (counter > 55000) {
                return;
            }

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
                    boolean aIsLiteral = true;
                    int aValue = 0;
                    try {
                        aValue = Integer.parseInt(line.split(" ")[1]);
                    } catch (NumberFormatException e) {
                        aIsLiteral = false;
                    }

                    boolean bIsLiteral = true;
                    int bValue = 0;
                    try {
                        bValue = Integer.parseInt(line.split(" ")[2]);
                    } catch (NumberFormatException e) {
                        bIsLiteral = false;
                    }

                    int pointerAdjustment = 1;
                    if (aIsLiteral) {
                        if (aValue != 0) {
                            if (bIsLiteral) {
                                pointerAdjustment = bValue;
                            } else {
                                pointerAdjustment = registers.get(line.split(" ")[2]);
                            }
                        }
                    } else {
                        if (registers.get(line.split(" ")[1]) != 0) {
                            if (bIsLiteral) {
                                pointerAdjustment = bValue;
                            } else {
                                pointerAdjustment = registers.get(line.split(" ")[2]);
                            }
                        }
                    }
                    pointer += pointerAdjustment;
                    break;
                case "tgl":
                    String targetInstruction;
                    int targetInstructionLocation = pointer;

                    boolean targetInstructionLocationIsLiteralValue = true;
                    try {
                        targetInstructionLocation += Integer.parseInt(line.split(" ")[1]);
                    } catch (NumberFormatException e) {
                        targetInstructionLocationIsLiteralValue = false;
                    }

                    if (!targetInstructionLocationIsLiteralValue) {
                        register = line.split(" ")[1];
                        targetInstructionLocation += registers.get(register);
                    }

                    if (targetInstructionLocation < lines.size()) {
                        targetInstruction = lines.get(targetInstructionLocation);
                        boolean oneArgumentInstruction = targetInstruction.split(" ").length < 3;
                        if (oneArgumentInstruction) {
                            if (targetInstruction.contains("inc")) {
                                targetInstruction = "dec" + targetInstruction.substring(3);
                            } else {
                                targetInstruction = "inc" + targetInstruction.substring(3);
                            }
                        } else {
                            if (targetInstruction.contains("jnz")) {
                                targetInstruction = "cpy" + targetInstruction.substring(3);
                            } else {
                                targetInstruction = "jnz" + targetInstruction.substring(3);
                            }
                        }
                        lines.set(targetInstructionLocation, targetInstruction);
                    }
                    pointer++;
                    break;
                default:
                    throw new RuntimeException();
            }

            System.out.println(String.format("%s: %-10s %s",pointer, line, registers));

        }

        System.out.println(registers.get("a"));
        System.out.println("Done!");

    }
}

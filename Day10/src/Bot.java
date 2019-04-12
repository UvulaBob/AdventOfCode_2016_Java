public class Bot implements Comparable<Bot>{
    int id;
    int lowChip = 0;
    int highChip = Integer.MAX_VALUE;
    int chipCount = 0;
    String lowDestination, highDestination;
    String lowDestinationId, highDestinationId;

    void takeChip(int chip) {
        if (chipCount == 0) {
            lowChip = chip;
            chipCount++;
        } else if (chipCount == 1) {
            if (chip > lowChip) {
                highChip = chip;
                chipCount++;
            } else {
                highChip = lowChip;
                lowChip = chip;
                chipCount++;
            }
        } else if (chipCount == 2) {
            if (chip != lowChip && chip != highChip) {
                throw new RuntimeException("Trying to give me a third chip!");
            }
        }
    }

    public int compareTo(Bot b) {
        return Integer.compare(b.chipCount, this.chipCount);
    }

    public String toString() {
        return String.format("Bot %s: [ChipCount: %s, Low Chip: %s, Low Destination: %s %s, High Chip: %s, High Destination: %s %s", id, chipCount, lowChip, lowDestination, lowDestinationId, highChip, highDestination, highDestinationId);
    }
}

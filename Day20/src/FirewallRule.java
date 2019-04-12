public class FirewallRule implements Comparable<FirewallRule> {
    long start, end;

    boolean contains (long address) {
        return address >= start && address <= end;
    }

    public int compareTo(FirewallRule f) {
        return Long.compare(start, f.start);
    }

    public String toString() {
        return start + "-" + end;
    }
}

public class LetterCount implements Comparable<LetterCount>{

    String letter;
    int count;

    public LetterCount(String letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public int compareTo(LetterCount lc) {
        if (this.count == lc.count) {
            return this.letter.compareTo(lc.letter);
        } else {
            return Integer.compare(lc.count, this.count);
        }
    }

}

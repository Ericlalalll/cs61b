public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int dfft = x - y;
        return Math.abs(dfft) == 1;
    }
}

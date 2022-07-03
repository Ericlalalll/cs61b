public class OffByN implements CharacterComparator{
    private int dfft;
    public OffByN(int N) {
        dfft = N;

    }
    @Override
    public boolean equalChars(char x, char y) {
        int temp = x -y;
        return Math.abs(temp) == dfft;
    }
}

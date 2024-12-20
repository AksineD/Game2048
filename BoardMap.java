public class BoardMap {
    public int size;
    private int[][] _board;

    public BoardMap(int size) {
        this.size = size;
        _board = new int[size][size];
    }

    public int get(int x, int y) {
        if (isValidPosition(x, y))
            return _board[size][size];
        return -1;
    }

    public void set(int x, int y, int number) {
        if (isValidPosition(x, y))
            _board[size][size] = number;
    }

    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                _board[i][j] = 0;
            }
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(_board[i][j] == 0 ? ".    " : String.format("%-5d", _board[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
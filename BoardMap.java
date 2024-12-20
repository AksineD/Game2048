public class BoardMap {
    private int _size;
    private int[][] _board;

    public BoardMap(int size) {
        _size = size;
        _board = new int[size][size];
    }

    public int getSize() {
               return _size;
    }

    public int get(int x, int y) {
        if (isValidPosition(x, y))
            return _board[_size][_size];
        return -1;
    }

    public void set(int x, int y, int number) {
        if (isValidPosition(x, y))
            _board[_size][_size] = number;
    }

    public void reset() {
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                _board[i][j] = 0;
            }
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < _size && y >= 0 && y < _size;
    }

    public void printBoard() {
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                System.out.print(_board[i][j] == 0 ? ".    " : String.format("%-5d", _board[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
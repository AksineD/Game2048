import java.util.Random;

public class GameModel {
    private boolean _isGameOver;
    private int _score;
    private BoardMap _boardMap;

    private static final Random _random = new Random();

    int max = 4, min = 1;

    public GameModel(int bordSize) {
        _boardMap = new BoardMap(bordSize);
    }

    public void init() {
        _isGameOver = false;
        _score = 0;
        _boardMap.reset();
        /*
        for (var y = 0; y < _boardMap.size; y++) {
            for (var x = 0; x < _boardMap.size; x++) {
                _boardMap.set(x, y, 0);
            }
        }
        */
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        if (_isGameOver) return;
/*
        int x, y;
        do {
            x = _random.nextInt( _boardMap.getSize());
            y = _random.nextInt( _boardMap.getSize());
        }
        while (_boardMap.get(x, y) != 0);
        _boardMap.set(x, y, _random.nextInt(2) == 0 ? 2 : 4);
        */
        int x, y;
        do {
            x = _random.nextInt(_boardMap.getSize());
            y = _random.nextInt(_boardMap.getSize());
        } while (_boardMap.get(x, y) != 0);  // Error likely occurs here
        _boardMap.set(x, y, _random.nextInt(2) == 0 ? 2 : 4);
    }

    public int BoardCell(int x, int y) {
        return _boardMap.get(x, y);
    }

    public void move(Direction direction) {
        boolean moved = false;
        int size = _boardMap.getSize();

        switch (direction) {
            case LEFT:
                for (int y = 0; y < size; y++) {
                    for (int x = 1; x < size; x++) {
                        moved |= moveTile(x, y, -1, 0);
                    }
                }
                break;
            case RIGHT:
                for (int y = 0; y < size; y++) {
                    for (int x = size - 2; x >= 0; x--) {
                        moved |= moveTile(x, y, 1, 0);
                    }
                }
                break;
            case UP:
                for (int x = 0; x < size; x++) {
                    for (int y = 1; y < size; y++) {
                        moved |= moveTile(x, y, 0, -1);
                    }
                }
                break;
            case DOWN:
                for (int x = 0; x < size; x++) {
                    for (int y = size - 2; y >= 0; y--) {
                        moved |= moveTile(x, y, 0, 1);
                    }
                }
                break;
        }

        if (moved) {
            addRandomTile();
        }

    }
    private boolean moveTile(int x, int y, int dx, int dy) {
        int currentValue = _boardMap.get(x, y);
        if (currentValue == 0) {
            return false;
        }

        int newX = x;
        int newY = y;

        while (true) {
            int nextX = newX + dx;
            int nextY = newY + dy;

            if (!isValidPosition(nextX, nextY)) {
                break;
            }

            int nextValue = _boardMap.get(nextX, nextY);

            if (nextValue == 0) {
                _boardMap.set(nextX, nextY, currentValue);
                _boardMap.set(newX, newY, 0);
                newX = nextX;
                newY = nextY;
            } else if (nextValue == currentValue) {
                _boardMap.set(nextX, nextY, currentValue * 2);
                _boardMap.set(newX, newY, 0);
                _score += currentValue * 2;
                return true;
            } else {
                break;
            }
        }

        return newX != x || newY != y;
    }

    // Check if a position is within the board bounds
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < _boardMap.getSize() && y >= 0 && y < _boardMap.getSize();
    }

    public int getBoardCell(int x, int y) {
        return _boardMap.get(x, y);
    }

    public int getSize() {
        return _boardMap.getSize();
    }

    public int getScore() {
        return _score;
    }
}


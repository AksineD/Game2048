import java.util.Random;

public class GameModel {
    private boolean _isGameOver;
    private int _score;
    private BoardMap _boardMap;

    private static final Random _random = new Random();

    public GameModel(int bordSize) {
        _boardMap = new BoardMap(bordSize);
    }

    public void Init() {
        _isGameOver = false;
        _score = 0;

        for (var y = 0; y < _boardMap.size; y++) {
            for (var x = 0; x < _boardMap.size; x++) {
                _boardMap.set(x, y, 0);
            }
        }
        AddRandomTile();
    }

    Random random = new Random();
    int max = 4, min = 1;

    private void AddRandomTile() {
        if (_isGameOver) return;

        int x, y;
        do {
            x = random.nextInt(0, _boardMap.size);
            y = random.nextInt(0, _boardMap.size);
        }
        while (_boardMap.get(x, y) != 0);
        int number = random.nextInt(max - min + 1) + min;
        _boardMap.set(x, y, number * 2); // Adds a tile with value 2 or 4
    }

    public int BoardCell(int x, int y) {
        return _boardMap.get(x, y);
    }

    public void Move(Direction direction) {
        switch (direction) {
            case Direction.LEFT: {
                for (var y = 0; y < _boardMap.size; y++)
                    for (var x = 1; x < _boardMap.size; x++) {
                        Move(x, y, -1, 0);
                    }
                for (var y = 0; y < _boardMap.size; y++)
                    for (var x = 1; x < _boardMap.size; x++) {
                        Join(x, y, -1, 0);
                    }
                break;
            }
            case Direction.RIGHT: {
                for (var y = 0; y < _boardMap.size; y++)
                    for (var x = _boardMap.size - 2; x >= 0; x--) {
                        Move(x, y, 1, 0);
                    }
                for (var y = 0; y < _boardMap.size; y++)
                    for (var x = _boardMap.size - 2; x >= 0; x--) {
                        Join(x, y, 1, 0);
                    }
                break;
            }
            case Direction.UP: {

                for (var x = 0; x < _boardMap.size; x++)
                    for (var y = 1; y < _boardMap.size; y++) {
                        Move(x, y, 0, -1);
                    }
                for (var x = 0; x < _boardMap.size; x++)
                    for (var y = 1; y < _boardMap.size; y++) {
                        Join(x, y, 0, -1);
                    }
                break;
            }
            case Direction.DOWN: {
                for (var x = 0; x < _boardMap.size; x++)
                    for (var y = _boardMap.size - 2; y >= 0; y--) {
                        Move(x, y, 0, 1);
                    }
                for (var x = 0; x < _boardMap.size; x++)
                    for (var y = _boardMap.size - 2; y >= 0; y--) {
                        Join(x, y, 0, 1);
                    }
                break;
            }
        }

        AddRandomTile();
        AddRandomTile();
    }

    public void Move(int x, int y, int xn, int yn) {
        if (_boardMap.get(x, y) > 0) {
            while (_boardMap.get(x + xn, y + yn) == 0) {
                _boardMap.set(x + xn, y + yn, _boardMap.get(x, y));
                _boardMap.set(x, y, 0);
                x += xn;
                y += yn;
            }
        }
    }

    public void Join(int x, int y, int xn, int yn) {
        if (_boardMap.get(x, y) > 0) {
            if (_boardMap.get(x, y) == _boardMap.get(x + xn, y + yn)) {
                _boardMap.set(x + xn, y + yn, _boardMap.get(x, y) * 2);
                _score += 1;
                while (_boardMap.get(x - xn, y - yn) > 0) {
                    _boardMap.set(x, y, _boardMap.get(x - xn, y - yn));
                    x -= xn;
                    y -= yn;
                }

                _boardMap.set(x, y, 0);
            }
        }
    }

    public int Size() {
        return _boardMap.size;
    }

    public int Score() {
        return _score;
    }
}


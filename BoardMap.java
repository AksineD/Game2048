public class BoardMap {
    public int size;
    private int[][] _map;

    public BoardMap(int size)
    {
        this.size = size;
        _map = new int[size][size];
    }

    public int Get(int x, int y)
    {
        if (onMap(x, y))
            return _map[size][size];
        return -1;
    }
    public void Set(int x, int y, int number)
    {
        if (onMap(x, y))
            _map[size][size] = number;
    }


    public boolean onMap(int x, int y)
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}

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
        if (OnMap(x, y))
            return _map[x, y];
        return -1;
    }
    public void Set(int x, int y, int number)
    {
        if (OnMap(x, y))
            _map[x, y] = number;
    }


    public bool OnMap(int x, int y)
    {
        return x >= 0 && x < Size && y >= 0 && y < Size;
    }
}

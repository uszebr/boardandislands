package connectedislands

class Point {
    boolean r
    int x
    int y

    Point(int x, int y, boolean r) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x and y should be more then 0")
        }
        this.r = r
        this.x = x
        this.y = y
    }


    @Override
    public String toString() {
        return "Point{" +
                " x=" + x +
                " y=" + y +
                " r=" + r +
                ' }'
    }
}
package connectedislands

/**
 * Holder class for group of Points(Islands)
 * Island should contain at least one Point
 */
class Island {
    List<Point> points = []

    Board board
    Point initialPoint

    Island(Board board, Point initialPoint) {
        if (board == null || initialPoint == null) {
            throw new IllegalArgumentException("Board and initial point can not be null ")
        }
        this.board = board
        this.initialPoint = initialPoint
        points.add(initialPoint)
    }

    void addToIsland(Point point) {
        if (point == null) {
            throw new IllegalArgumentException('Point is null')
        }
        if (isPointInIsland(point)) {
            throw new IllegalArgumentException('Point already in the island')
        }
        if (point.r) {
            points.add(point)
        }
    }

    boolean isPointInIsland(Point point) {
        return points.contains(point)
    }

    static boolean isPointInAnyIsland(List<Island> islands,Point point ){
        return islands.any(){it.isPointInIsland(point)}
    }


    @Override
    public String toString() {
        return "Island{\n" +
                points.collect() {"   "+it.toString()+"\n"} +
                '}\n';
    }
}

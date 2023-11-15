package connectedislands

/**
 * Holder for all points on the board
 * Some Points may be not set at all
 */
class Board {
    // starting from 1
    int width
    int height

    private List<Point> points

    final static String TRUE_SYMBOL = "1"
    final static String FALSE_SYMBOL = "0"
    final static String EMPTY_VALUE_SYMBOL = '-'

    Board(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException('Wrong Width or height')
        }
        this.width = width
        this.height = height
        points = []
    }

    void addPoint(Point point) {
        isPointValid(point)
        if (isPointWithXYPresent(point)) {
            throw new IllegalArgumentException("Point x: ${point.x} y: ${point.y} already present in the board")
        }
        points.add(point)
    }

    void isPointValid(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point is null")
        }
        if (point.x >= width || point.y >= height) {
            throw new IllegalArgumentException("Point is out of current Board")
        }
    }

    boolean isPointWithXYPresent(Point point) {
        return points.find { it.x == point.x && it.y == point.y } as boolean
    }

    List<Point> getPoints() {
        return points
    }

    /**
     * Printing instance in the console
     * - if no point r for current location
     */
    void printBoard() {
        for (int j = 0; j < height; j++) {
            String line = ''
            for (int i = 0; i < width; i++) {
                Boolean pointValue = points.find() { it.x == i && it.y == j }?.r
                if (pointValue == true) {
                    line = line + TRUE_SYMBOL + ' '
                } else if (pointValue == false) {
                    line = line + FALSE_SYMBOL + ' '
                } else {
                    line = line + EMPTY_VALUE_SYMBOL + ' '
                }
            }
            println line
        }
    }

    List<Point> findNeighbors(Point point) {
        isPointValid(point)
        def result = []
        def resultHorizontal = points.findAll() { it.x == point.x && it.y != point.y && Math.abs(it.y - point.y) < 2 }
        def resultVertical = points.findAll() { it.y == point.y && it.x != point.x && Math.abs(it.x - point.x) < 2 }
        result.addAll(resultVertical)
        result.addAll(resultHorizontal)
        return result
    }

    /**
     * Check if Point instance located on the border(edge) of the board
     * @param point
     * @return
     */
    boolean isBorderPoint(Point point) {
        if (point.x == 0 || point.y == 0 || point.x == width - 1 || point.y == height - 1) {
            return true
        }
        return false
    }
}

package connectedislands

import org.junit.Test

class UtBoard {
    @Test
    void boardCreationTest() {
        def width = 10
        def height = 12
        Board board = BoardService.createRandomBoard(width, height)
        //   board.printBoard()
        assert board.getPoints().size() == width * height
        assert board.width == width
        assert board.height == height
    }

    @Test
    void boardWrongPoint() {
        def width = 10
        def height = 12
        Board board = new Board(width, height)
        boolean nullPointException = false
        try {
            board.addPoint(new Point(10, 2, true))
        } catch (Exception e) {
            nullPointException = true
        }
        assert nullPointException
    }

    @Test
    void boardExistingPoint() {
        def width = 10
        def height = 12
        Board board = new Board(width, height)

        assert board.getPoints().size() == 0
        board.addPoint(new Point(2, 2, true))
        assert board.getPoints().size() == 1

        boolean existingPointException = false
        try {
            board.addPoint(new Point(2, 2, true))
        } catch (Exception e) {
            existingPointException = true
        }
        assert existingPointException
        boolean existingPointException2 = false
        try {
            board.addPoint(new Point(2, 2, false))
        } catch (Exception e) {
            existingPointException2 = true
        }
        assert existingPointException2
    }

    @Test
    void findNeighbors() {
        def width = 10
        def height = 12
        Board board = BoardService.createRandomBoard(width, height)
        board.printBoard()

        List<Point> neighbors0 = board.findNeighbors(board.getPoints().find() { it.x == 0 && it.y == 0 })
        assert neighbors0.size() == 2
        List<Point> neighbors01 = board.findNeighbors(board.getPoints().find() { it.x == 0 && it.y == 1 })
        assert neighbors01.size() == 3
        List<Point> neighbors90 = board.findNeighbors(board.getPoints().find() { it.x == 9 && it.y == 0 })
        assert neighbors90.size() == 2

        List<Point> neighbors22 = board.findNeighbors(board.getPoints().find() { it.x == 2 && it.y == 2 })
        assert neighbors22.size() == 4

        List<Point> neighbors011 = board.findNeighbors(board.getPoints().find() { it.x == 0 && it.y == 11 })
        assert neighbors011.size() == 2
        List<Point> neighbors1_11 = board.findNeighbors(board.getPoints().find() { it.x == 1 && it.y == 11 })
        assert neighbors1_11.size() == 3

        List<Point> neighbors9_11 = board.findNeighbors(board.getPoints().find() { it.x == 9 && it.y == 11 })
        assert neighbors9_11.size() == 2
    }

    @Test
    void findNeighborsDetails() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
        Point one = board.getPoints().find() { it.x == 1 && it.y == 0 }
        one.r = true
        Point two = board.getPoints().find() { it.x == 2 && it.y == 0 }
        two.r = true
        board.printBoard()

        List<Point> neighbors = board.findNeighbors(board.getPoints().find() { it.x == 0 && it.y == 0 })

        assert neighbors.size() == 2
        assert neighbors.find() { it.x == 1 && it.y == 0 }.r
        assert !neighbors.find() { it.x == 0 && it.y == 1 }.r

        List<Point> neighborsCenter = board.findNeighbors(board.getPoints().find() { it.x == 1 && it.y == 1 })
        assert neighborsCenter.size() == 4
        assert neighborsCenter.findAll() { it.r }.size() == 1
    }

    @Test
    void isBorderPoint() {
        def width = 10
        def height = 12
        Board board = BoardService.createRandomBoard(width, height)

        board.printBoard()
        println "--"
        assert board.isBorderPoint(new Point(0, 0, true))
        assert board.isBorderPoint(new Point(0, 1, true))
        assert board.isBorderPoint(new Point(1, 0, true))

        assert board.isBorderPoint(new Point(9, 11, true))
        assert board.isBorderPoint(new Point(9, 10, true))
        assert board.isBorderPoint(new Point(8, 11, true))

        assert !board.isBorderPoint(new Point(2, 2, true))
        assert !board.isBorderPoint(new Point(2, 3, true))
        assert !board.isBorderPoint(new Point(4, 5, true))
        assert !board.isBorderPoint(new Point(7, 8, true))

    }
}

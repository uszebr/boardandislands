package connectedislands

/**
 * Methods for different types of boards creation
 */
class BoardService {
    static Board createRandomBoard(int width, int height) {
        def board = new Board(width, height)
        Random random = new Random()
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.addPoint(new Point(i, j, random.nextBoolean()))
            }
        }
        return board
    }

    static Board createRandomBoardWithPercentage(int width, int height, int probabilityPercentTrue) {
        if (probabilityPercentTrue < 0 || probabilityPercentTrue > 100) {
            throw new IllegalArgumentException("Wrong Probability percent")
        }
        def board = new Board(width, height)
        Random random = new Random()
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                def pointValue = random.nextInt(100)+1
                board.addPoint(new Point(i, j, pointValue < probabilityPercentTrue))
            }
        }
        return board
    }

    static Board createFalseBoard(int width, int height) {
        def board = new Board(width, height)
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.addPoint(new Point(i, j, false))
            }
        }
        return board
    }
}

package connectedislands

import org.junit.Test

class UtExplorationService {
    @Test
    void simpleExplorationService() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
        Point oneIslandPoint = board.getPoints().find() { it.x == 0 && it.y == 0 }
        oneIslandPoint.r = true
//        board.printBoard()
//        println "-------"
        ExplorationService explorationService = new ExplorationService(board)

        def islands = explorationService.explore()
        assert islands.size() == 1
    }

    @Test
    void noIslands() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
//        board.printBoard()
//        println "-------"
        ExplorationService explorationService = new ExplorationService(board)

        def islands = explorationService.explore()
        assert islands.size() == 0
    }

    @Test
    void oneIslandTwoPoints() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
        Point one = board.getPoints().find() { it.x == 1 && it.y == 0 }
        one.r = true
        Point two = board.getPoints().find() { it.x == 2 && it.y == 0 }
        two.r = true
//        board.printBoard()
//        println "-------"
        ExplorationService explorationService = new ExplorationService(board)

        def islands = explorationService.explore()
        assert islands.size() == 1
    }

    @Test
    void oneIslandTwoPointsDetails() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
        Point one = board.getPoints().find() { it.x == 1 && it.y == 0 }
        one.r = true
        Point two = board.getPoints().find() { it.x == 2 && it.y == 0 }
        two.r = true
        board.printBoard()
        println "-------"
        ExplorationService explorationService = new ExplorationService(board)

        def islands = explorationService.explore()
        assert islands.size() == 1
        for(island in islands){
            println island
        }
    }

    @Test
    void twoIslandsTwoPoints() {
        def width = 3
        def height = 3
        Board board = BoardService.createFalseBoard(width, height)
        Point one1 = board.getPoints().find() { it.x == 1 && it.y == 0 }
        one1.r = true
        Point two1 = board.getPoints().find() { it.x == 2 && it.y == 0 }
        two1.r = true
        Point one2 = board.getPoints().find() { it.x == 1 && it.y == 2 }
        one2.r = true
        Point two2 = board.getPoints().find() { it.x == 2 && it.y == 2 }
        two2.r = true
        board.printBoard()
        println "-------"
        ExplorationService explorationService = new ExplorationService(board)

        def islands = explorationService.explore()
        assert islands.size() == 2
    }

}

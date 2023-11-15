package connectedislands

/**
 * Service to find Island and all Islands
 */
class ExplorationService {

    private Board board
    private List<Point> exploredPoints

    ExplorationService(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board is null")
        }
        this.board = board
        setUp()
    }

    void setUp() {
        this.exploredPoints = []
    }

    private static List<Point> findTruePoints(List<Point> allPoints) {
        return allPoints.findAll() { it.r }
    }

    List<Island> explore() {
        List<Island> islands = []
        def truePoints = findTruePoints(board.points)
        for (truePoint in truePoints) {
            if (exploredPoints.contains(truePoint)) {
                continue
            }
            Island island = new Island(board, truePoint)
            exploredPoints.add(truePoint)
            explorePoint(truePoint, island)
            islands.add(island)
        }
        return islands
    }

   private void explorePoint(Point point, Island island) {

        List<Point> neighbors = board.findNeighbors(point)
        List<Point> trueNeighbors = findTruePoints(neighbors)
        for (trueNeighbor in trueNeighbors) {
            if (exploredPoints.contains(trueNeighbor)) {
                continue
            }
            //todo Debug cleanUp
//            println"TEMPLog: trueNeighbor "+trueNeighbor.toString()
//            println "TEMPLog: island "+island.toString()
//            println "TEMPLog: island.initialPoint"+island.initialPoint.toString()
//            println "TEMPLOG exploredPoints" + exploredPoints
//            println "--"
            island.addToIsland(trueNeighbor)
            exploredPoints.add(trueNeighbor)
            explorePoint(trueNeighbor, island)
        }
    }


}

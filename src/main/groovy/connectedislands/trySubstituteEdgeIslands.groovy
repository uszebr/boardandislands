package connectedislands

// creating board with random values
// getting all islands
// creating list of islands that has no points on the edge(border)
// creating new board with the same values excluding edge(border) islands
int weight = 10
int height = 12
def board = BoardService.createRandomBoardWithPercentage(weight, height, 25)
board.printBoard()
println "-------"
def islands = new ExplorationService(board).explore()
println "Islands found: " + islands.size()
//for (int i = 0; i < islands.size(); i++) {
//    println "Island: $i"
//    def island = islands[i]
//    for (int j = 0; j < island.points.size(); j++) {
//        def point = island.points[j]
//        println("   Point: ${point.x} ${point.y} ${point.r}" )
//    }
//}

//def islandNoEdgeTouch = islands.collect() { it.points.every() { point->!board.isBorderPoint(point) } }
def islandNoEdgeTouch = []

//todo refactor to Island class
for (island in islands) {
    if (!island.points.any() { board.isBorderPoint(it) }) {
        islandNoEdgeTouch.add(island)
    }
}
println "No Edge Islands found: " + islandNoEdgeTouch.size()

Board resultBoard = new Board(weight, height)

for (pointInBoard in board.points) {
    if (Island.isPointInAnyIsland(islandNoEdgeTouch, pointInBoard)) {
      //  println "Point: ${pointInBoard.x} ${pointInBoard.y} not added"
    } else {
        resultBoard.addPoint(pointInBoard)
    }
}

println "======="
resultBoard.printBoard()


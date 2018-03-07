import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class GenerationTest extends Canvas{

  // Initial parameters
  public static int canvasSize = 400;
  public static int pixelSize = 4;
  public static int mapCode = 2;
  public static int numOfCells = 100;

  // Main function
  public static void main(String[] args) {
    JFrame frame = new JFrame("Generation Testing");
    Canvas canvas = new GenerationTest();
    canvas.setSize(canvasSize, canvasSize);
    frame.add(canvas);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Overload paint() method
  public void paint(Graphics g) {
    int[][] coordinates = initialMap(mapCode);

    for(int i=0; i<numOfCells; i++) {
      for(int j=0; j<numOfCells; j++) {
        if(coordinates[i][j]==1) {
          g.fillRect(i*pixelSize, j*pixelSize, pixelSize, pixelSize);
        }
      }
    }
  }

  // Initialization function that creates initial seed map
  public static int[][] initialMap(int mapCode) {

    // Create initial map of zeroes
    int[][] coordinates = new int[numOfCells][numOfCells];

    // Set of intial maps
    if(mapCode==1) { // single blinker
      coordinates[25][25]=1;
      coordinates[26][25]=1;
      coordinates[27][25]=1;
    } else if(mapCode==2) { // single glider
      coordinates[25][25]=1;
      coordinates[26][26]=1;
      coordinates[25][27]=1;
      coordinates[24][27]=1;
      coordinates[26][27]=1;
    }

    return coordinates;


  }

  // Generate new population map based on current Generation
  public static int[][] nextGeneration(int[][] currentGeneration) {

    // Initialize next generation map
    int[][] nextGeneration = new int[numOfCells][numOfCells];

    // Initializing neighborCount map
    int[][] neighborCountMap = countNeighbors(currentGeneration);

    // Next generation logic (Base game logic)
    for(int i=0; i<numOfCells; i++) {
      for(int j=0; j<numOfCells; j++) {

        if(currentGeneration[i][j]==0 && neighborCountMap[i][j]==3) { // Dead cell
          nextGeneration[i][j] = 1
        } else if(currentGeneration[i][j]==1) { // Live cell
          if(neighborCountMap[i][j]<2 || neighborCountMap[i][j]>3){
            nextGeneration[i][j] = 0
          }
        }
      }
    }

    return nextGeneration;
  }

  // Create function that will return the total number of living neighbors
  public static int countNeighbors(int[][] currentGeneration){

    // Initialize neighborCountMap to hold number of neighbors for each cell
    int[][] neighborCountMap = new int[numOfCells][numOfCells];
    int neighborCount;

    // Perform count considering periodic boundary conditions
    for(int i=0; i<numOfCells; i++) {
      for(int j=0; j<numOfCells; j++) {

        // Set neighborCount to zero for each cell iteration
        neighborCount = 0;

        // Consider periodic boundary conditions
        int a = i - 1;
        int b = i + 1;
        int c = j - 1;
        int d = j + 1;

        if(a<0){
          a += numOfCells;
        }
        if(b>0) {
          b -= numOfCells;
        }
        if(c<0){
          c += numOfCells;
        }
        if(d>0){
          d -= numOfCells;
        }

        // Check neighbors and add to neighborCount if live
        if(currentGeneration[a][c]==1){ // Top left
          neighborCount += 1;
        }
        if(currentGeneration[a][j]==1){ // Top center
          neighborCount += 1;
        }
        if(currentGeneration[a][d]==1){ // Top right
          neighborCount += 1;
        }
        if(currentGeneration[i][c]==1){ // Middle left
          neighborCount += 1;
        }
        if(currentGeneration[i][d]==1){ // Middle right
          neighborCount += 1;
        }
        if(currentGeneration[b][c]==1){ // Bottom left
          neighborCount += 1;
        }
        if(currentGeneration[b][j]==1){ // Bottom center
          neighborCount += 1;
        }
        if(currentGeneration[b][d]==1){ // Bottom right
          neighborCount += 1;
        }

        // Add total live nieghbors to neighborCountMap
        neighborCountMap[i][j] = neighborCount;
      }
    }

    return neighborCountMap;
  }
















}

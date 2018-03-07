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

    // Initializing neighborCount variable to keep track of cell neighbors
    int neighborCount;

    // Next generation logic (Base game logic)
    for(int i=0; i<numOfCells; i++) {
      for(int j=0; j<numOfCells; j++) {
        neighborCount = countNeighbors(currentGeneration);

        if(currentGeneration[i][j]==0 && neighborCount==3) { // Dead cell
          nextGeneration[i][j] = 1
        } else if(currentGeneration[i][j]==1) { // Live cell
          if(neighborCount<2 || neighborCount>3){
            nextGeneration[i][j] = 0
          }
        }
      }
    }

    return nextGeneration;




  }
















}

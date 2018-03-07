import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class InitialMapTest extends Canvas{

    public static int canvasSize = 400;
    public static int pixelSize = 4;
    public static int mapCode = 2;
    public static int numOfCells = 100;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Initial Map Test");
        Canvas canvas = new InitialMapTest();
        canvas.setSize(canvasSize, canvasSize);
        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void paint(Graphics g){
        int[][] coordinates = initialMap(mapCode);

        for(int i=0; i<numOfCells; i++){
            for(int j=0; j<numOfCells; j++){
                if(coordinates[i][j]==1){
                    g.fillRect(i*pixelSize, j*pixelSize, pixelSize, pixelSize);
                }
            }
        }

    }

    public static int[][] initialMap(int mapCode){
        int[][] coordinates = new int[numOfCells][numOfCells]; // Create initial map of zeros

        if(mapCode==1){
            coordinates[25][25]=1;
            coordinates[26][25]=1;
            coordinates[27][25]=1;
        } else if(mapCode==2){
            coordinates[25][25]=1;
            coordinates[26][26]=1;
            coordinates[25][27]=1;
            coordinates[24][27]=1;
            coordinates[26][27]=1;
        }

        return coordinates;
    }

}

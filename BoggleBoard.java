import java.util.*;

/** Represents a Boggle board to play a game of Boggle. 
  * 
  * @author Owen Astrachan
  * @author Raghuram Ramanujan
  */
public class BoggleBoard {
    
    private String[] myFaces; // the cube faces
    private int mySize; // the size of the board
    
    /** Creates a square boggle board from an array of Strings. 
      * 
      * The array should contain size x size entries, e.g., 16 for a 4x4 Boggle 
      * game.
      * 
      * @param faces row-major order of cubes used in the board for Boggle
      */
    public BoggleBoard(String[] faces) {
        myFaces = faces;
        mySize = (int) Math.sqrt(faces.length);
    }
    
    
    /** Returns dimension of a square board, (i.e., 4 or 5).
      * 
      * @return size of board
      */
    public int size() {
        return mySize;
    }
    
    
    /** Returns the cube face at specified location. 
      * 
      * This will be a one-character string, or "Qu" for a Q-cube.
      * 
      * @param row row index of cube whose face is returned.
      * @param col column index of cube whose face is returned.
      * @return the letter(s) on the face of specified cube
      * @throws ArrayIndexOutOfBoundsException if cube location isn't valid
      */
    public String getFace(int row, int col) {
        return myFaces[row * mySize + col];
    }
    
    
    /** Returns the letter(s) on the face of the specified cell.
      * 
      * This will be a one-character string, or "Qu" for a Q-cell.
      * 
      * @param cell the cell whose face we wish to query.
      * @return the letter(s) on the face of specified cell.
      */
    public String getFace(BoardCell cell) {
        return getFace(cell.getRow(), cell.getCol());
    }
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int r=0; r < mySize; r++){
            for(int c=0; c < mySize; c++){
                sb.append(String.format("%3s",getFace(r,c)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

/** Encapsulates a (row,column) entry on a Boggle board.
  * 
  * This wrapper class exists for convenience -- so that a board entry can be 
  * hashed, compared, and stored as a single entry. This implementation provides 
  * simple methods for determining when one BoardCell is a neighbor of another. 
  * Here, neighbor means adjacent horizontally, vertically, or diagonally.
  * BoardCell objects are immutable.
  * 
  * <P>
  * @author Owen Astrachan
  * @author Raghuram Ramanujan
  * 
  */

public class BoardCell implements Comparable<BoardCell> {
    
    private int row; // row index of this cell
    private int col; // column index of this cell
    private int hash; // hash code
    
    /** Constructs a new board cell at specified row and column indices.
      * 
      * @param row the row index of this board cell.
      * @param col the column index of this board cell.
      */
    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
        hash = new Integer(row*1000+col).hashCode();
    }
    
    /** Returns the row index of this board cell.
      * 
      * @return the row index of this cell.
      */
    public int getRow() {
        return this.row;
    }
    
    
    /** Returns the column index of this board cell.
      * 
      * @return the column index of this cell.
      */
    public int getCol() {
        return this.col;
    }
    
    
    /** Returns whether the given BoardCell is a neighbor of this BoardCell.
      * 
      * By "neighbor", we mean that the given BoardCell is adjacent 
      * horizontally, vertically, or diagonally (and not this BoardCell itself).
      * 
      * @param other the BoardCell being compared to this BoardCell for 
      * adjacency
      * @throws IllegalArgumentException if other is <code>null</code>
      * @return true iff other is adjacent to this BoardCell
     */
    public boolean isNeighbor(BoardCell other){
        if (other == null) 
            throw new IllegalArgumentException("other cannot be null!");
        
        if (row == other.row) 
            return Math.abs(col - other.col) == 1;
        
        if (col == other.col) 
            return Math.abs(row - other.row) == 1;
        
        return Math.abs(col - other.col) + Math.abs(row - other.row) == 2;
    }
    
    
    @Override
    public boolean equals(Object o){
        BoardCell c = (BoardCell) o;
        return c.row == row && c.col == col;
    }
    
    
    @Override
    public int hashCode(){
        return this.hash;
    }
    
    
    /** Returns whether other is less than, equal to, or greater than this cell. 
      * 
      * The natural ordering is determined first by row value, then by column 
      * value for cells whose rows are equal.
      * 
      * @param other cell being compared to this BoardCell
      * @return row difference if rows aren't the same, and the column 
      * difference if rows are the same
      */
    public int compareTo(BoardCell other){
        int rowDiff = this.row - other.row;
        
        if (rowDiff != 0) 
            return rowDiff;
        
        return col - other.col;
    }
}

/** WordonBoardFinder class
 * 
 * Given the Boggle Board, checks to see if a word can be found on it.
 * 
 * @author - Owen Bezick
 * @author - Daniel Cowan
 * 
 * Time: 2 hours
 */
import java.util.*;


public class WordOnBoardFinder implements IWordOnBoardFinder {
	
	/** Looks in the Boggle board and figures out the board cells needed
	 *  to construct a given word, using a recursive procedure.
	 * 
	 * 	@param board - boggle board to search
	 *  @param word - word to search for
	 *  @return list - list of board cells if word can be found, empty if not
	 */
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {

		List<BoardCell> list = new ArrayList<BoardCell>();
		for(int r = 0; r < board.size(); r++) {
			for(int c = 0; c < board.size(); c++){
				if (searchForWord(board, r, c, list, word, 0)) {
					return list;
				}
			}
		}
		return list;
	}


	/** Checks to see if the r, c pair is a valid cell on the board.
	 * 
	 * @param board - Boggle Board to search
	 * @param r - row 
	 * @param c - column
	 * @return true if r, c pair is on the boggle board, false if not. 
	 */
	private boolean checkCellBounds(BoggleBoard board, int r, int c) {
		if(r >= board.size() || c >= board.size() || r < 0 || c < 0) {
			return false;
		} 
		return true;
	}

	/** Checks to make sure it is a valid index in the word.
	 * 
	 * @param word - word to check
	 * @param index - index to check
	 * @return true if valid index
	 */
	private boolean checkIndex(String word, int index) {
		if (index< 0 || index >= word.length()){
			return false;
		}
		return true;
	}
	
	
	/** Checks to see if the boggle face corresponds to the letter in the word.
	 * 
	 * Checks for qu.
	 * 
	 * @param board - boggle board
	 * @param r - row
	 * @param c - column
	 * @param word - word to reference
	 * @param index - index of word to check
	 * @return [true, true] if valid and qu, [true, false] if valid and not qu 
	 * 			[false, false] if not valid
	 */
	private boolean[] checkLetter(BoggleBoard board, int r, int c, String word, int index) {
		// special case, q.
		// return 
		if (board.getFace(r,c).length() > 1 && checkIndex(word, index +1)) {
				if (board.getFace(r,c).equals(String.valueOf(word.charAt(index) 
						+ String.valueOf(word.charAt(index + 1))))) {
					return new boolean[] {true, true};
				}
			}
		if (board.getFace(r,c).equals(String.valueOf(word.charAt(index)))) {
			return new boolean[] {true, false};
		}
		return new boolean[] {false, false};	
	}
	
	
	/** Recursively searches for a given word on a Boggle Board.
	 * 
	 * @param board - boggle board
	 * @param r - row
	 * @param c - column
	 * @param list - list of board cells
	 * @param word - word to reference
	 * @param index - index of word to check
	 * @return true if the queried word is on the face of the boggle board. 
	 */
	private boolean searchForWord(BoggleBoard board, int r, int c, List<BoardCell> list, String word, int index) {
		// word is found 
		if (index == word.length()) {
			return true;
		}
		// out of bounds
		if (!(checkCellBounds(board, r, c))){
			return false;
		}
		if (!(checkIndex(word, index))) {
			return false;
		}
		// checks letter
		// single character
		if (!(checkLetter(board, r, c, word, index)[0])) {
			return false;
		}
		// qu
		if (checkLetter(board, r,c, word, index)[1] && checkLetter(board, r,c, word, index)[0]) {
			index++;
		}

		// therefore, letter matches
		
		// check to see if face has been used
		if (list.contains(new BoardCell(r,c))){
			return false;
		}
		
		BoardCell newCell = new BoardCell(r,c);
		list.add(newCell);
		
		// check surrounding cells for the next letter
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)){ 
					if(searchForWord(board, r+i, c+j,list, word, index + 1)) {
						return true;
					} 
				}
			}
		}
		// didn't return true, so backtrack
		list.remove(newCell);
		return false;
	}
}
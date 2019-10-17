/** Autoplayer class
 * 
 * Serves as computer "opponent" in the game of Boggle. 
 * 
 * @author - Owen Bezick
 * @author - Daniel Cowan
 * 
 * Time: 1.5 hours
 */
import java.util.*;


public class AutoPlayer extends AbstractAutoPlayer {

	/** Looks in the Boggle board and finds all words that can be formed
	 * (given the lexicon), using a recursive procedure.
	 * 
	 * @param board - Boggle board to search
	 * @param lex - Lexicon to reference
	 * @return wordList - list of valid words on the Boggle board
	 */
	public List<String> findAllValidWords(BoggleBoard board, ILexicon lex) {

		List<String> wordList = new ArrayList<String>();

		for(int r = 0; r < board.size(); r++) {
			for(int c = 0; c < board.size(); c++){
				findWords(board, lex, r, c, wordList, new ArrayList<BoardCell>(), "");
			}
		}
		return wordList;
	}


	/** Checks to see if the r, c pair is a valid cell on the board.
	 * 
	 * @param board - Boggle board to search 
	 * @param r - row to check
	 * @param c - column to check
	 * @return true if r, c pair is on the boggle board, false if not. 
	 */
	private boolean checkCellBounds(BoggleBoard board, int r, int c) {
		if(r >= board.size() || c >= board.size() || r < 0 || c < 0) {
			return false;
		} 
		return true;
	}

	
	/** Recursively finds all words in a boggle board. 
	 * 
	 * @param board - Boggle board
	 * @param lex - Lexicon to reference 
	 * @param r - row to check
	 * @param c - column to check
	 * @param wordList - list of valid words
	 * @param word - list of board cells for a potential word
	 * @return none
	 */
	private void findWords(BoggleBoard board, ILexicon lex, int r, int c, List<String> wordList, ArrayList<BoardCell> word, String runningWord) {
		// out of bounds
		if (!(checkCellBounds(board, r, c))){
			return;
		}
		// checking a face twice
		if (word.contains(new BoardCell(r,c))) {
			return;
		}
		
		// add letter to running word 
		runningWord += board.getFace(r,c);

		// check the lexicon 
		if (lex.wordStatus(runningWord) == LexStatus.WORD) {
			if(!wordList.contains(runningWord)){
				//System.out.println(runningWord);
				wordList.add(runningWord);
			}
		}
		if (lex.wordStatus(runningWord) == LexStatus.NOT_WORD) {
			return;
		}

		// add board cell
		word.add(new BoardCell(r,c));

		// recursive call
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)){ 
					findWords(board, lex, r+i, c+j,wordList, word, runningWord);
				} 
			}
		}
		// backtrack
		word.remove(word.size() -1);
	}
}
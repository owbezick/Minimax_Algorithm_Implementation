
/**
 * Add method for auto/computer player to the
 * standard player interface. An autoplayer
 * will find words on a board using a lexicon
 * via the single method exported by this interface.
 * <P>
 * @author Owen Astrachan
 * @author Raghuram Ramanujan
 *
 */

import java.util.SortedSet;
import java.util.List;

public interface IAutoPlayer extends IPlayer{
   
    /** Finds and returns all the valid words automatically.
     * 
     * @param board is the BoggleBoard on which wrods are found
     * @param lex is the lexicon in which words are searched/validated
     * @param minLength of words found by an autoplayer
     */    
    public List<String> findAllValidWords(BoggleBoard board, ILexicon lex);
    
}

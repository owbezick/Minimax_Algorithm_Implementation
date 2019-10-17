
/** Specification for a Lexicon as used in the Boggle program. 
  * 
  * Lexicons can be loaded from a scanner or a List, searched for a string to
  * see if the string is a WORD, the PREFIX of a word, or is NOT_WORD 
  * (see LexStatus).
  * <P>
  * Words can be queried by using either a StringBuilder or a String.
  * Implementations may convert one to another so that there's no
  * guarantee that the <code>wordStatus</code> method doesn't create
  * a new object in performing a lexicon query.
  * <P>
  * 
  * @author Owen Astrachan
  * @author Raghuram Ramanujan
  *
  */

import java.util.*;

public interface ILexicon extends Iterable<String>{
    
    /** Clears the current lexicon and stores all values read from the Scanner.
      * 
      * @param s Scanner that is the source of words for the lexicon
      */
    public void load(Scanner s);
    
    /** Clears the current lexicon and stores all values read from the list.
      * 
      * @param list is the source of words for the lexicon
      */
    public void load(List<String> list);

    /** Returns the outcome of querying the lexicon for the given string.
      * 
      * The value returned is one of the following: 
      *     WORD - if a word in the lexicon
      *     PREFIX - if the prefix of a word in the lexicon,
      *     NOT_WORD - if not a word and not a prefix of a word.
      * See LexStatus
      * 
      * @param s the word/sequence to look up in the lexicon
      * @return status of <code>s</code>, as to how it appears in lexicon
      */
    public LexStatus wordStatus(String s);

    /** Returns an iterator over all words stored in the lexicon.
      * 
      * @return an iterator over the words in the lexicon
      */
    public Iterator<String> iterator();
    
    /** Returns the number of words stored in the lexicon.
      * 
     * @return number of words in the lexicon
     */
    public int size();
}

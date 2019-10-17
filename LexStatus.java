
/** Enum denoting the outcome of querying for a word in a lexicon.
  * 
  * Variables of this type can only take on one of three values and is the
  * return value of the wordStatus() method in the ILexicon interface.
  * <P>
  * If PREFIX is returned, then the string is not a word, but is the prefix of 
  * at least one word in the lexicon.
  * <P>
  * If WORD is returned, then the string is a word in the dictionary (it could 
  * be a prefix too, e.g., MOM is a word, but is a prefix of MOMENT).
  * <P>
  * If NOT_WORD is returned, the string is not a word, and it's not the prefix 
  * of any word in the lexicon.
  * <P>
  * 
  * @author Owen Astrachan
  * @author Raghuram Ramanujan
  *
  */
public enum LexStatus {
    WORD,
    PREFIX,
    NOT_WORD
}

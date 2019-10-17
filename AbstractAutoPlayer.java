
import java.util.SortedSet;
import java.util.List;

/**
 * Augments <code>AutoPlayer</code> by adding
 * abstract method for autoplayer.
 * <P>
 * @author Owen Astrachan
 *
 */
public abstract class AbstractAutoPlayer extends AbstractPlayer implements IAutoPlayer{
    
    public abstract List<String> findAllValidWords(BoggleBoard board, ILexicon lex);

    /**
     * Return string "AutoPlayer".
     * @return this player's name
     */
    public String getName() {
       return "AutoPlayer";
    }
}

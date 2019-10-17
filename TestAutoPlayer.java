
import java.util.*;
import java.io.*;

/** Program for testing an implementation of an AutoPlayer for Boggle.
  * 
  * @author Raghuram Ramanujan
  */
public class TestAutoPlayer {
    
    /** Loads the dictionary file into the supplied lexicon.
      * 
      * @param lexicon the lexicon into which to store the words.
      */
    private static void loadLexicon(ILexicon lexicon) {
        
        final String DICT_FILE = "ospd3.txt";
        
        try {
            Scanner s = new Scanner(new File(DICT_FILE));
            lexicon.load(s);            
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }        
    }
    
       
    /** Builds a specific board for testing purposes.
      * 
      * @return a BoggleBoard with pre-determined tiles.
      */
    private static BoggleBoard makeBoard() {
        String[] tiles = {"atru",
        		"sean",
        		"niot",
        		"bden"};
        
        String[] faces = new String[tiles.length * tiles.length];
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length(); j++) {
                if (tiles[i].charAt(j) != 'q') {
                    faces[count++] = "" + tiles[i].charAt(j);
                }
                else {
                    faces[count++] = "qu";
                }
            }
        }
  
        return new BoggleBoard(faces);
    }

    
    /** Determines legality of words found by AutoPlayer, and which words were
      * missed.
      * 
      * @param words words found by the AutoPlayer.
      * @param answers the actual answers.
      * @return false iff all the tests passed.
      */
    private static boolean checkWords(List<String> words, String[] answers) {
        
        boolean broken = false;
        
        // Did the computer find all the words that are in answers?
        for (String s : answers) {
            if (!words.contains(s)) {
                System.out.println("Your program failed to find the word " + s);
                broken = true;
            }
        }
        
        // Did the computer find any words that are not in answers?
        for (String s : words) {
            if (Arrays.binarySearch(answers, s) < 0) {
                System.out.println("Your program found " + s 
                                       + " which does not exist on the board");
                broken = true;
            }
        } 
        
        return broken;
    }
    
    
    /** Main tester function.*/
    public static void main(String[] args) {
        
        final String[] answers =  {"queen"};
//           {"ae", "ai", "ain", "ains", "ais", "ar", "are", 
//            "area", "areas", "ares", "as", "asea", "ax", "axe", 
//            "axes", "ear", "en", "ens", "er", "era", "es", 
//            "ex", "in", "ins", "is", "ne", "near", "oar", "oe", 
//            "oes", "rain", "rains", "raise", "rax", "raxes", 
//            "re", "rei", "rein", "reins", "reis", "res", "resin", 
//            "rex", "sae", "sax", "sea", "sear", "sei", "sen", 
//            "ser", "sera", "serai", "sex", "si", "sin", "sine", 
//            "xenia"};  
       
        
        IAutoPlayer computer = new AutoPlayer();
        BoggleBoard board = makeBoard();
        ILexicon lexicon = new TrieLexicon();
        
        loadLexicon(lexicon);
        
        System.out.println("\nTesting with following board:");
        System.out.println(board);
        
        computer.clear();
        List<String> words = computer.findAllValidWords(board, lexicon);
                       
        if (!checkWords(words, answers))
            System.out.println("\nPassed all test cases!\n");
    }
    
    
    
}

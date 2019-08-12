package src;
import java.util.*;
public class LipogramAnalyzer {
	public static void main(String[] args) {

	}
	private String text;
    private String[] textSplit;
    //textSplit is an array of each separated word in text
    public LipogramAnalyzer(String text) {
        this.text = text; //get input text
        textSplit = cleanse(text).split(" ");
        //remove non letters or spaces from text and split it into an array
    }
    public String mark(char letter) {
        return text.replaceAll("" + letter, "#");
    }
    public String allWordsWith(char letter) {
        //could use arraylist, but the contains() operation is O(n) whereas
        //hashset contains() is O(1) --> will be much faster given long input text
        HashSet<String> offending = new HashSet<String>();
        for (String word : textSplit) {
            if (word.indexOf(letter) != -1 && !offending.contains(word)) {
                offending.add(word);
            }
        }
        String rv = "";
        for (String badWord : offending) {
            rv += (badWord + "\n");
        }
        return rv;
    }
    private String cleanse(String s) {
        //removes all non letters or spaces from the string
        String rv = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || c == ' ') {
                rv += s.charAt(i);
            }
        }
        return rv;
    }
}
	/*public static String txt, marked;
	public LipogramAnalyzer(String text) {
		txt=text;
	}
	public static String mark(char letter) {String save="";
	return txt=txt.replace(letter, '#');
	}
	public String allWordsWith(char letter){
		String list = "";
		int last=0;
		for(int i=0;i<txt.length()-1;i++){
			 HashSet<String> offending = new HashSet<String>();
		        for (String word : textSplit) {
		            if (word.indexOf(letter) != -1 && !offending.contains(word)) {
		                offending.add(word);
		            }
		        }
		        String rv = "";
		        for (String badWord : offending) {
		            rv += (badWord + "\n");
		        }
		        return rv;
		}
		return list;}*/
		/*String result="";
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		for(int i=0; i<txt.length(); i++) {
		int pos0=txt.indexOf(letter);
		int pos1=pos0;
		while(alphabet.indexOf(pos0)!=-1) {
			pos0--;}
		while(alphabet.indexOf(pos1)!=-1) {
			pos1++;}
		result+=txt.substring(pos0, pos1);
		result+="\n";
		}
		return result;}*/


package src;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * @author thwilliams
 */
public class Lispy {
    public static Def dfs=new Def();
    public static void main(String args[]){
       String input;
        while(true){
            System.out.print("ST>");
            Scanner sc=new Scanner(System.in);
            input=sc.nextLine();
            if(input.equals("quit")||input.equals("exit")){
                break;
            }
            if(input.length()==1&&Lispy.dfs.contains(input)){
                System.out.println(Lispy.dfs.get(input));
            }else{
                System.out.println(toTree(parse(input)));
            }
        }
    }
    public static boolean checkParens(String str){
        int opencount=0; int closedcount=0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='('){
                opencount++;
            }
            if(str.charAt(i)==')'){
                closedcount++;
            }
        }
        return opencount==closedcount;
    }
    public static List<String> parse(String input){
        if(!checkParens(input)){
            System.out.println("Error. Unbalanced parentheses!");
        }
         List<String> words = new ArrayList<>();
         String str="";
         for(int i=0; i<input.length(); i++){
             if(input.charAt(i)=='('||input.charAt(i)==')'){
                 continue;
             }else{
                if(input.charAt(i)==' '){
                    words.add(str);
                    str="";
                }else{
                    str+=input.charAt(i);
                }
             }
         }
         words.add(str);
         return words;
    }
    public static boolean isOp(String s){
        String ops="+-/%*&|^";
        for(int i=0; i<ops.length(); i++){
            if(s.contains(Character.toString(ops.charAt(i)))){
                return true;
            }
        }
        return false;
    }
    public static boolean containsNumbers(String a){
        String ops="0123456789";
        int count=0;
        for(int i=0; i<ops.length(); i++){
            if(a.contains(Character.toString(ops.charAt(i)))){
                count++;
            }
        }
        if(a.length()==0){
            return false;
        }
        return a.length()==count;
    }
    //method to convert into nodes
    public static int toTree(List<String> s){
        String temp;
        Node parent=null; Node child=null; Node firstparent=null;
        for(int i=0; i<s.size(); i++){
            temp=s.get(i);
            if(isOp(temp)){
                if(parent!=null){
                    child=new Node(temp, null);
                    parent.setChild(child);
                    parent=child;
                }else{
                    parent=new Node(temp, null);
                    firstparent=parent;
                }
            }else{
                if(containsNumbers(temp)||Lispy.dfs.contains(temp)){
                    if(Lispy.dfs.contains(temp)){
                        temp=Integer.toString(Lispy.dfs.get(temp));
                        //System.out.println("added to tree!");
                    }
                    child=new Node(null, temp);
                    parent.setChild(child);
                }else{
                    if(temp.equals("def")){
                        i++;
                        Lispy.dfs.addDef(s.get(i++), Integer.parseInt(s.get(i++)));
                    }else{
                            System.out.println("Error: not a number!");
                    }
                } 
            }
        }
            if(firstparent==null){
                return 0;
            }
      return firstparent.eval();
    }
}

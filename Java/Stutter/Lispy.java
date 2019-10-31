package src;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * @author thwilliams
 */
public class Lispy {
    public static void main(String args[]){
       /* Node e=new Node(null, "3", null, null);
        Node d=new Node(null, "2", null, null);
        Node b=new Node("+", null, d, e);
        Node c=new Node(null, "5", null, null);
        Node a=new Node("*", null, b, c);
        System.out.println(a.eval());*/
       String input;
        while(true){
            System.out.print("ST>");
            Scanner sc=new Scanner(System.in);
            input=sc.nextLine();
            if(input.equals("quit")||input.equals("exit")){
                break;
            }
            System.out.println(toTree(parse(input)));
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
             if(input.charAt(i)==' '){
                 words.add(str);
                 str="";
             }else{
                 str+=input.charAt(i);
             }
         }
         words.add(str);
         return words;
    }
    public static boolean isOp(String s){
        String ops="+-/%*";
        for(int i=0; i<ops.length(); i++){
            if(s.contains(Character.toString(ops.charAt(i)))){
                return true;
            }
        }
        return false;
    }
    //method to convert into nodes
    public static int toTree(List<String> s){
        String temp;
        Node a=null; Node b=null; 
        for(int i=0; i<s.size(); i++){
            temp=s.get(i);
            if(temp.contains(")")){
                if(temp.length()>1){
                     if(b==null){
                        b=new Node(null, temp.substring(0, temp.length()-1), null, null);
                     }
                    if(a!=null){
                        a.setChild(b);
                        b=null;
                    }}
            }else{
                if(temp.contains("(")){
                    if(temp.length()>1){
                        //(+ case
                        a=new Node(Character.toString(temp.charAt(1)), null, null, null);
                    }else{
                        continue;
                    }
                     //( + case
                }else{
                    if(isOp(temp)){
                        a=new Node(Character.toString(temp.charAt(0)), null, null, null);
                    }else{
                        if(b==null){
                            b=new Node(null, temp, null, null);
                        }
                        if(a!=null){
                            a.setChild(b);
                            b=null;
                        }
                    }
                }
            }
        }
            if(a==null){
                return 0;
            }
        return a.eval();
}
}

package src;
/**
 * @author thwilliams
 */
import java.util.ArrayList;
public class Node {
    public ArrayList<Node> arr=new ArrayList<Node>();
    public String symbol;
    public String value;
    public Node(String symbol, String value){
        this.value=value;
        this.symbol=symbol;
    }
    public void setChild(Node next){
        this.arr.add(next);
    }
    public String toString(){
        if(this.value==null){
            return this.symbol;
        }else{
            if(this.symbol==null){
                return this.value;
            }else{
                if(this.value!=null && this.symbol!=null){
                    return this.value+" "+this.symbol;
                }
                return "null";
            }
        }
    }
    public int eval(){
        //System.out.println(this.arr);
        //System.out.println(this.arr.size());
        //System.out.println(arr.get(0));
        if(this.arr.size()==0){
            if(this.value==null){
                return 1;
            }
           return Integer.parseInt(this.value);
        }else{
            int sum=this.arr.get(0).eval();
            if(this.value!=null){
                return Integer.parseInt(this.value);
            }else if(this.symbol.equals("+")){
                for(int i=1; i<arr.size(); i++){
                    sum+=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("-")){
                for(int i=1; i<arr.size(); i++){
                    sum-=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("*")){
                for(int i=1; i<arr.size(); i++){
                    sum*=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("%")){
                for(int i=1; i<arr.size(); i++){
                    sum%=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("/")){
                for(int i=1; i<arr.size(); i++){
                    sum/=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("&")){
                for(int i=1; i<arr.size(); i++){
                    sum=sum&this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("|")){
                for(int i=1; i<arr.size(); i++){
                    sum|=this.arr.get(i).eval();
                }
            }else if(this.symbol.equals("^")){
                for(int i=1; i<arr.size(); i++){
                    sum=sum^this.arr.get(i).eval();
                }
            }
        return sum;
    }
   }
}

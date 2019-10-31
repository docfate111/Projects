package src;
/**
 * @author thwilliams
 */
public class Node {
    public Node right;
    public Node left;
    public String symbol;
    public String value;
    public Node(String symbol, String value, Node nextLeft, Node nextRight){
        this.left=nextLeft;
        this.right=nextRight;
        this.value=value;
        this.symbol=symbol;
    }
    public void setChild(Node next){
        if(this.right!=null){
            this.left=next;
        }else{
            this.right=next;
        }
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
        if(this.value!=null){
            return Integer.parseInt(this.value);
        }else if(this.symbol.equals("+")){
            return this.left.eval()+this.right.eval();
        }else if(this.symbol.equals("-")){
            return this.left.eval()-this.right.eval();
        }else if(this.symbol.equals("*")){
            return this.left.eval()*this.right.eval();
        }else if(this.symbol.equals("%")){
            return this.left.eval()%this.right.eval();
        }else if(this.symbol.equals("/")){
            int a=this.right.eval();
            if(a==0){
                System.out.println("Division by zero error");
                return this.left.eval();
            }else{
                return (this.left.eval()/a);
            }
        }
        return 0;
    }
}

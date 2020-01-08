package src;
import java.util.HashMap;
import java.util.Map;
/**
 * @author thelford williams
 */
public class Def {
    HashMap<String, Integer> vars= new HashMap<>(); 
    public void addDef(String a, int value){
        this.vars.put(a, value);
    }
    public int get(String a){
        return this.vars.get(a);
    }
    public boolean contains(String a){
        return this.vars.containsKey(a);
    }
    @Override
    public String toString(){
        String output="";
        for (Map.Entry mapElement : this.vars.entrySet()) { 
            String key = (String)mapElement.getKey(); 
            int value = ((int)mapElement.getValue()); 
            output+=key + " : " + value+"\n"; 
        } 
        return output;
    }
}

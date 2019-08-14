package pkg;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author thwilliams
 */
public class Inventory{
    int[][] worksheet_count= new int[43][20];
                          //0     1      2     3    4     5     6    7     8     9     10   11   12    13    14   15   16    17
    String[] math_levels={"7A", "6A", "5A", "4A", "3A", "2A", "A", "B",  "C",  "D",  "E", "F",  "G",  "H",  "I", "J",  "K",  "L"};
                            //18   19     20    21    22    23    24     25    26    27     28     29    30   31
    String[] reading_levels={"7A", "6A", "5A", "4A", "3A", "2A", "AI", "AII", "BI", "BII", "CI", "CII", "DI","DII", 
        //32   33      34    35    36    37     38     39    40    41     42
        "EI", "EII", "FI", "FII", "GI", "GII", "HI", "HII", "II", "III", "J"};
    //constructor
    public Inventory(){
    }
    public boolean manageInputEntry4args(char MorR, String lvl, int worksheet_num, int num_of_worksheets){
        if(MorR=='M'||MorR=='R'){
            if(lvl.length()<4){
                if(num_of_worksheets<20){
                   if((worksheet_num<200)&&(worksheet_num%10==1)){
                       return true;
                   }
                }
            }
        }
        return false;
    }
    public boolean manageInput3args(char MorR, String lvl, int worksheet_num){
        if(MorR=='M'||MorR=='R'){
            if(lvl.length()<4){
                   if((worksheet_num<200)&&(worksheet_num%10==1)){
                       return true;
                   }
            }
        }
        return false;
    }
    public String greaterThan(int b){
        String output="";
        for(int h=0; h<43; h++){
             for(int i=0; i<20; i++){
                 if(this.worksheet_count[h][i]>b){
                     output+=worksheet_count[h][i]+": ";
                     if(h<=17){
                         output+="Math level "+this.math_levels[h]+" "+(10*i+1)+"\n";
                     }else{
                         output+="Reading level "+this.reading_levels[h-18]+" "+(10*i+1)+"\n";
                   }
                }
             }}
        return output;
    }
    public String lessThan(int b){
        String output="";
        for(int h=0; h<43; h++){
             for(int i=0; i<20; i++){
                 if(this.worksheet_count[h][i]<b){
                     output+=this.worksheet_count[h][i]+" : ";
                     if(h<=17){
                         output+="Math level "+this.math_levels[h]+" "+(10*i+1)+"\n";
                     }else{
                         output+="Reading level "+this.reading_levels[h-18]+" "+(10*i+1)+"\n";
                     }
                 }
             }}
        return output;
    }
    public String savefile(String filename){
        File p=new File(filename);
        String output="";
        p.delete();
        try{
                File f=new File(filename);
                if(f.createNewFile()){
                        output+="File created: "+f.getName()+"\n";
                        try {
                            FileWriter wr= new FileWriter(filename);
                            for(int h=0; h<43; h++){
                                for(int i=0; i<20; i++){
                                    wr.write(this.worksheet_count[h][i]+",");
                                }
                                wr.write("\n");
                            }
                            wr.close();
                            output+="Successfully wrote to the file."+"\n";
                        }catch(IOException e){
                            output+="Can't write to file"+"\n";
                            e.printStackTrace();
                        } 
                }else{
                        output+="File already exists."+"\n";
                }
            }catch(IOException e){
                output+="File could not be made"+"\n";
                e.printStackTrace();
            } 
        return output;
    }
    public String newfile(String filename){
           String output="";
           try{
                File f=new File(filename);
                if(f.createNewFile()){
                        output+="File created: "+f.getName();
                        try {
                            FileWriter wr= new FileWriter(filename);
                            for(int h=0; h<43; h++){
                                for(int i=0; i<20; i++){
                                    wr.write("0, ");
                                }
                                wr.write("\n");
                            }
                            wr.close();
                            output+="Successfully wrote to the file.\n";
                        }catch(IOException e){
                            output+="Can't write to file\n";
                            e.printStackTrace();
                        } 
                }else{
                        output+="File already exists.\n";
                }
            }catch(IOException e){
                output+="File could not be made\n";
                e.printStackTrace();
            } 
           return output;
    }
    public String loadfile(String filename){
            int m=0;
            String output="";
            try {
                File obj=new File(filename);
                Scanner rdr=new Scanner(obj);
                while(rdr.hasNextLine()){  
                    String s=rdr.nextLine();
                    String[] strArray=s.split(",");
                    int[] intArray = new int[strArray.length];
                    for(int i = 0; i < strArray.length; i++) {
                        intArray[i] = Integer.parseInt(strArray[i]);
                     }
                    this.worksheet_count[m++]=intArray;
                }       
                rdr.close();
            }catch(FileNotFoundException e){
                output+="File not created yet.\nMake sure you enter file to write to\n";
                e.printStackTrace();
            } 
            return output;
            }
    public String setCount(String lvl, int num_of_sheets, int worksheet_num, char MorR){
        String[] levels;
        int o=0;
        if(MorR=='R'){
            levels=this.reading_levels;
            o+=18;
        }else{
            levels=this.math_levels;
        }
        String output="Task done\n";
        o+=Arrays.asList(levels).indexOf(lvl);
        this.worksheet_count[o][(worksheet_num/10)]=num_of_sheets;
        return output;
    }
    public String addCount(String lvl, int num_of_sheets, int worksheet_num, char MorR){
        String[] levels;
        int o=0;
        if(MorR=='R'){
            levels=this.reading_levels;
            o+=18;
        }else{
            levels=this.math_levels;
        }
        o+=Arrays.asList(levels).indexOf(lvl);
        this.worksheet_count[o][(worksheet_num/10)]+=num_of_sheets;
        return "Task done\n";
    }
    public String removeCount(String lvl, int num_of_sheets, int worksheet_num, char MorR){
        String[] levels;
        String output="";
        int o=0;
        if(MorR=='R'){
            levels=this.reading_levels;
            o+=18;
        }else{
            levels=this.math_levels;
        }
        o+=Arrays.asList(levels).indexOf(lvl);
        if(this.worksheet_count[o][(worksheet_num/10)]-num_of_sheets<0){
            output+="Not enough worksheets\n";
            output+="Only "+this.worksheet_count[o][(worksheet_num/10)]+" sheets"+"\n";
        }else{
            this.worksheet_count[o][(worksheet_num/10)]-=num_of_sheets;
            output+="Task done boss.\n";
        }
        return output;
    }
    public int getCount(String lvl, int worksheet_num, char MorR){
        int o=0;
        if(MorR=='R'){
            o+=18;
            o+=Arrays.asList(this.reading_levels).indexOf(lvl);
        }else{
            o+=Arrays.asList(this.math_levels).indexOf(lvl);
        }
        return this.worksheet_count[o][(worksheet_num/10)];
    }
    public String getFullLevel(String level, char MorR){
        String[] levels;
        String output="";
        int o=0;
        if(MorR=='R'){
            levels=this.reading_levels;
            o+=18;
        }else{
            levels=this.math_levels;
        }
        o+=Arrays.asList(levels).indexOf(level);
        output+="Retrieving level "+level+"\n";
        int p=0;
        for(int n=1; n<=191; n+=10){
                output+=n+" : "+this.worksheet_count[o][p++]+"\n";
        }
        return output;
    }
    public String getAllSubjectLevels(char MorR){
        String[] levels;
        int val=17;
        int o=0;
        String output="";
        if(MorR=='R'){
            levels=this.reading_levels;
            val=25;
        }else{
            levels=this.math_levels;
        }
        int p=0;
        while(o<val){
           output+="Retrieving level "+levels[o]+"\n";
            for(int n=1; n<=191; n+=10){
                output+=n+" : "+this.worksheet_count[o][p++]+"\n";
            }
            o++;
            p=0;
        }
        return output;
     }   
   
}
        

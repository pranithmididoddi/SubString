import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Created by Pranith on 10/21/16.
 */
public class checkPassage {



    public static List<String> tokenize(String words, String regex){

        List<String> list=new ArrayList<>();

        Pattern p= Pattern.compile(regex,Pattern.DOTALL);
        Matcher m=p.matcher(words);

        while(m.find()){
            list.add(m.group());
        }
        return list;
    }

    public static String converter(List<String> list){

        StringBuffer sb=new StringBuffer();
        for(Object o : list){
            sb.append(o).append(" ");
        }

        String s= sb.toString();
        return s;
    }




    public static void extractPassage(String[] string){
        String previousKey="";
        String previousValue="";

        for(String input : string){
            List<String> words= tokenize(input, "[a-z0-9]+");
            String key= converter(words);


            //System.out.println(key.indexOf(previousKey));

            if(key.indexOf(previousKey)<0 ||
                (previousKey.equals(key) && input.length() >previousValue.length() )){
                continue;
            }
            previousKey=key;
            previousValue=input;
        }

        System.out.println(previousValue);
    }

    public static void main(String[] args){
        String[] s= { "IBM cognitive computing" , "IBM \"cognitive\" computing is a revolution" ,
                "ibm cognitive computing" , "'IBM Cognitive Computing' is a revolution?" };

        extractPassage(s);
    }

}


/*
You will be given a sequence of passages, and must filter out any passage whose text (sequence of whitespace-delimited words) is wholly contained as a sub-passage of one or more of the other passages.

When comparing for containment, certain rules must be followed:

The case of alphabetic characters should be ignored

Leading and trailing whitespace should be ignored

Any other block of contiguous whitespace should be treated as a single space

non-alphanumeric character should be ignored, white space should be retained

Duplicates must also be filtered - if two passages are considered equal with respect to the comparison rules listed above, only the shortest should be retained. If they are also the same length, the earlier one in the input sequence should be kept. The retained passages should be output in their original form (identical to the input passage), and in the same order.

Input: For each test case a single line comprising the passages (strings) to be processed, delimited by | characters. The | characters are not considered part of any passage.

Output: A single line of filtered passages in the same |-delimited format.

Input1: IBM cognitive computing|IBM "cognitive" computing is a revolution| ibm cognitive computing|'IBM Cognitive Computing' is a revolution?

Output1: IBM "cognitive" computing is a revolution

Input2: IBM cognitive computing|IBM "cognitive" computing is a revolution|the cognitive computing is a revolution

Output2: IBM "cognitive" computing is a revolution|the cognitive computing is a revolution


 */
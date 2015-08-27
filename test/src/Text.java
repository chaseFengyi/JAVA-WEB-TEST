import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*public class Text {
	public static void main(String[] args) {
//		int[] str = {13,20,22,21,28,219,217,6,8};
//		int[] str = {3,83,8,13,1};
		int[] str = {13,24,22,6,8};
		Arrays.sort(str);
		for(int i = 0 ; i < str.length; i++){
			System.out.println(str[i]);
		}
		String[] str1 = new String[str.length];
		for(int i = 0; i < str.length;i++){
			str1[i] = str[i]+"";
		}
		Arrays.sort(str1);
		for(int i = 0 ; i < str.length; i++){
			System.out.print(str1[i]+",");
		}
		for(int i = 0 ; i < str1.length-1; i++){
			if(str1[i].charAt(0) == str1[i+1].charAt(0));
			if(new Compare().compare(str1[i], str1[i+1]) == 1){
				String c = str1[i];
				str1[i] = str1[i+1];
				str1[i+1] = c;
			}
		}
		System.out.print("\n");
		for(int i = 0; i < str1.length; i++){
			System.out.print(str1[i]+",");
		}
	}
}

class Compare implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		for(int i = 0 ; i < o1.length(); i++){
			if(o1.length() == o2.length()){
				if(o1.charAt(i) > o2.charAt(i)){
					return 1;
				}else if(o1.charAt(i) < o2.charAt(i)) {
					return -1;
				}else{
					continue;
				}
			}else if(o1.length() > o2.length()){
				compare(o1+o2, o2+o1);
			}else{
				compare(o2+o1, o1+o2);
			}
		}
		return 0;
	}
	
}*/
class StringComparator implements Comparator<String> {  
    public int compare(String a, String b) {  
        if (a.length() == b.length()) {  
            return a.compareTo(b);  
        } else {  
            String ab = a + b;  
           String ba = b + a;  
           return ab.compareTo(ba);   
        }  
    }  
}  
  
public class Text {  
    public String largestNumber(int[] num) {  
        StringBuffer sbuf = new StringBuffer();  
        ArrayList<String> numstrings = new ArrayList<String>(num.length);  
  
        for (int i : num) numstrings.add(String.valueOf(i));  
        Collections.sort(numstrings,  new StringComparator());  
        for (String s : numstrings) sbuf.append(s);  
  
        String res = sbuf.toString();  
        if (res.length() > 0 && res.charAt(0) == '0') return "0";  
  
        return res;  
        }
    
    public static void main(String[] args) {
    	int[] str = {3,83,8,13,1};
    	Text text = new Text();
    	String str1 = text.largestNumber(str);
    	System.out.println(str1);
	}
}
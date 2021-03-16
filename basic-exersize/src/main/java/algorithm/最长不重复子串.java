package algorithm;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * @author zhaixt
 * @date 2021/3/11 17:20
 *  滑动窗口法的思路很重要，先用一个窗口限定字符，每次判断字符是否重复
 */
public class 最长不重复子串 {
    public static void main(String[] args) {
       String str = "aabcdefgg";
       System.out.println("max lenght:"+getMaxsub(str));
    }

    public static int getMaxsub(String s) {
        if(null ==s || s.length() < 0){
            return 0;
        }
        int start = 0;
        int max = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i =0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                start = Math.max(start,map.get(s.charAt(i))+1);
            }

            map.put(s.charAt(i),i);
            max = Math.max(max,i-start+1);

        }
        return max;
    }

}

package algorithm;
/**
* 小红书
* */
public class 字符串反转并找出相邻重复个数 {
    public static void main(String[] args) {
        String str = "aabaacc";
        int result = reverseStr(str);
        System.out.println("result:"+result);
    }

    private static int reverseStr(String str){
        int duplicateCount = 0;
        if(null == str || str.length() <= 1){
            return 0;
        }
        int length = str.length();
        Character old = str.charAt(length-1);
        String resultStr = "" + old;
        /*或者下面一行也可以字符转字符串*/
        //String resultStr = String.valueOf(old);
        for(int index =length;index>1;index--){
            Character c = str.charAt(index-2);
            if(c == old){
                duplicateCount++;
            }else{
                resultStr += c;
            }
            old = c;

        }
        str = resultStr;
        return duplicateCount;
    }
}

package leetcode;

/**
 *
 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 */
public class Test06ZConvert {
    public String convert(String s, int numRows) {
        if(numRows<2){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(int i =1 ;i <= numRows;i++){
            if(i==1){
                int j = 0;
                while(j<s.length()){
                    sb.append(s.charAt(j));
                    j = j + (numRows-1)*2;
                }
            }

            if(i>1 && i < numRows){
                int j = i-1;
                boolean flag = true;
                while(j<s.length()){
                    sb.append(s.charAt(j));
                    if(flag){
                        flag = false;
                        j = j + (numRows-i) * 2;
                    }else{
                        flag = true;
                        j = j + (i-1)*2;
                    }
                }
            }

            if(i==numRows){
                int j = i - 1;
                while(j<s.length()){
                    sb.append(s.charAt(j));
                    j = j + (numRows-1)*2;
                }
            }
        }
        return sb.toString();
    }


}

package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Test03LengthOfLongestSubstring {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int sdfasd = solution.lengthOfLongestSubstring(" ");
        System.out.println(sdfasd);

    }
    static class Solution {
        public int lengthOfLongestSubstring1(String s) {
            int max = 0;
            StringBuilder smax = new StringBuilder();
            for (int i = 0; i <= s.length(); i++) {
                String theMax = "";
                for (int j = i+1; j < s.length(); j++) {
                    theMax = s.substring(i, j-1);
                    String s2 = s.substring(j-1 , j);
                    if (theMax.contains(s2)) {
                        break;
                    }else {
                        theMax = s.substring(i, j);
                    }
                }
                smax = smax.length()>theMax.length()?smax: new StringBuilder(theMax);
            }
            return smax.length();
        }

        /**
         * 方法二：滑动窗口
         * 时间复杂度：O(2n) = O(n)O(2n)=O(n)，在最糟糕的情况下，每个字符将被 ii 和 jj 访问两次。
         * 空间复杂度：O(min(m, n))O(min(m,n))，与之前的方法相同。滑动窗口法需要 O(k)O(k) 的空间，其中 kk 表示 Set 的大小。而 Set 的大小取决于字符串 nn 的大小以及字符集 / 字母 mm 的大小。
         */

        public int lengthOfLongestSubstring2(String s) {
           int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0 , i = 0 , j = 0;
            while (i < n && j < n) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                    j ++ ;
                    ans = Math.max(ans, set.size());
                }else {
                    set.remove(i);
                    i ++ ;
                }
            }
            return ans;
        }

        /**
         * 滑动窗口优化
         * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring3(String s) {
            Map<Character,Integer> map = new HashMap<>();
            int  ans = 0;
            for (int i = 0,j = 0; j < s.length(); j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)),i);
                }
                ans = Math.max(ans,j-i+1);
                map.put(s.charAt(j),j+1);
            }

            return ans;
        }

        /**
         * Java（假设字符集为 ASCII 128）
         * int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
         * int [128] 用于ASCII码
         * int [256] 用于扩展ASCII码
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            int[] index = new int[128];
            int  ans = 0;
            for (int i = 0,j = 0; j < s.length(); j++) {
                i = Math.max(index[s.charAt(j)],i);
                ans = Math.max(ans,j-i+1);
                index[s.charAt(j)] = j + 1;
            }

            return ans;
        }
    }

}

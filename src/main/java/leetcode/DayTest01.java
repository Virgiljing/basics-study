package leetcode;



import java.util.ArrayDeque;
import java.util.Queue;

public class DayTest01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int count = solution.bfs(1, 2, 1);
        System.out.println(count);

        System.out.println(solution.get(88));

    }

    static class Solution {
        int m;
        int n;
        int k;
        public int movingCount(int m, int n, int k) {
            this.m = m;
            this.n = n;
            this.k = k;
            boolean[][] record = new boolean[m][n];
            return dfs(0,0,0,record);
        }

        /**
         * 深搜
         * 时间复杂度：O(mn) 最坏情况，遍历矩阵。复杂度不敢确定，不知道需不需要乘上计算位数和的时间。
         * @param i
         * @param i1
         * @param i2
         * @param record
         * @return
         */
        private int dfs(int i, int i1, int i2, boolean[][] record) {
            if (i == m || i1 == n || i2>k || record[i][i1]) {
                return 0;
            }
            record[i][i1] = true;
            return 1 + dfs(i+1,i1,sum(i+1,i1),record) + dfs(i,i1+1,sum(i,i1+1),record);
        }

        private int sum(int i, int i1) {
            return get(i)+get(i1);
        }


        // 计算 x 的数位之和
        public int get(int x) {
            int res=0;
            for (; x>0; x /= 10) {
                res += x % 10;
            }
            return res;
        }

        public int bfs(int m, int n, int k) {
            boolean[][] record = new boolean[m][n];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{0,0});
            int count = 0;
            record[0][0] = true;
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                count ++ ;
                if (poll[0] + 1 < m && sum(poll[0] + 1,poll[1]) <= k && !record[poll[0]+1][poll[1]]){
                    queue.add(new int[]{poll[0]+1,poll[1]});
                    record[poll[0]+1][poll[1]] = true;
                }
                if (poll[1] + 1 < n && sum(poll[0] ,poll[1]+ 1) <= k && !record[poll[0]][poll[1]+1]){
                    queue.add(new int[]{poll[0],poll[1]+1});
                    record[poll[0]][poll[1]+1] = true;
                }
            }


            return count;
        }
    }
}

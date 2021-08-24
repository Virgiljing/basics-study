package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 */
public class Test01TwoSumOne {
    public static Solution solution = new Solution();
    public static void main(String[] args) {

        int[] a = {2,7,11,15};
        int[] ints = solution.twoSumOne(a, 9);
        System.out.println();
        Arrays.stream(ints).forEach(x -> {
            System.out.println(x);
        });
    }
    static class Solution {
        /**
         * 暴力解题
         *
         * 时间复杂度：O(n^2)
         * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n) 的时间。因此时间复杂度为 O(n^2)
         * 空间复杂度：O(1)。
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumERROR(int[] nums, int target) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i,j};
                    }
                }
            }
            throw new IllegalArgumentException("No tow sum solution");
        }

        /**
         * 时间复杂度：O(n)，
         * 我们把包含有 nn 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1) ，所以时间复杂度为 O(n)。
         *
         * 空间复杂度：O(n)，
         * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 nn 个元素。
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumMap(int[] nums , int target){
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i],i);
            }
            for (int i = 0; i < nums.length; i++) {
                int  complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(nums[i]),map.get(complement)};
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }

        /**
         *时间复杂度：O(n)，
         * 我们只遍历了包含有 nn 个元素的列表一次。在表中进行的每次查找只花费 O(1)O(1) 的时间。
         *
         * 空间复杂度：O(n)，
         * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 nn 个元素。
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumOne(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] { map.get(complement), i };
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }

    }
}

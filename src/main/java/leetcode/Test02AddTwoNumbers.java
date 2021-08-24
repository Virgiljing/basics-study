package leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 */
public class Test02AddTwoNumbers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode11 = new ListNode(8);
        listNode1.next = listNode11;

        ListNode listNode2 = new ListNode(0);
        //ListNode listNode22 = new ListNode(9);
        //listNode2.next = listNode22;

        ListNode listNode = solution.addTwoNumbers(listNode1, listNode2);
        System.out.println(listNode);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode listNode = new ListNode(0);
            ListNode p = l1 , q = l2 ,curr = listNode;
            int curry = 0;
            while (p != null || q != null) {
                int a1 = p!=null?p.val:0;
                int a2 = q!=null?q.val:0;
                int sum = a1 + a2 +curry;
                curry = sum / 10;
                curr.next = new ListNode(sum%10);
                curr = curr.next;
                if (p != null) {
                    p = p.next;
                }
                if (q != null) {
                    q = q.next;
                }
            }

            if (curry > 0) {
                curr.next = new ListNode(curry);
            }
            return listNode.next;
        }

        private String getNumber(ListNode l1) {
            if (l1 == null) {
                return "";
            }
            return  getNumber(l1.next) + "" +l1.val ;
        }

        public ListNode addTwoNumbersError(ListNode l1, ListNode l2) {
            String s1= getNumber(l1);
            String s2= getNumber(l2);
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            int i3 = i1+i2;
            ListNode listNode = null;
            ListNode nowNode = null;
            while (i3 > 0) {
                int a = i3 % 10;
                i3 = i3 / 10;
                if (listNode == null) {
                    listNode = new ListNode(a);
                    nowNode = listNode;
                }else {
                    nowNode.next = new ListNode(a);
                    nowNode = nowNode.next;
                }


            }
            if (listNode == null) {
                return new ListNode(0);
            }
            return listNode;
        }
    }

}
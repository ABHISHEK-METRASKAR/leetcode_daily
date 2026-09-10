/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //slow pointing to middle

        ListNode prev = null;
        ListNode curr = slow;

        while(curr != null){
            ListNode front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }

        //prev has pointing to reversed newHead

        int maxSum = 0;
        ListNode first = head;
        ListNode second = prev;

        while(second != null){
            maxSum = Math.max(maxSum, first.val+second.val);
            first = first.next;
            second = second.next;
        }
        
        return maxSum;

    }
}
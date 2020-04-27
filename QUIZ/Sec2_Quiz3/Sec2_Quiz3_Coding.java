/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  /*
     * Complete the 'middleNode' function below.
     *
     * The function takes the head ListNode of given LinkedList as parameter.
     * The function is expected to return the middle ListNode of given LinkedList.
     */
  public ListNode middleNode(ListNode head) {
    // Write your solution here
    // Corner case:
    if(head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head;

    while(fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;

  }
}

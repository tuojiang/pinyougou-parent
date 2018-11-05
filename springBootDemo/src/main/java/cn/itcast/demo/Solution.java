package cn.itcast.demo;

/**
 * @program: pinyougou-parent
 * @Date: 2018/11/4
 * @Author: chandler
 * @Description: 输入一个链表，反转链表后，输出新链表的表头
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;

        if (head == null){
            return null;
        }
        while (head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
